package com.laifeiyang.dev.micro.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
@Order(value = 0)
public class ValidateFilter implements GlobalFilter{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerRequest serverRequest = new DefaultServerRequest(exchange);
        // mediaType
        MediaType mediaType = exchange.getRequest().getHeaders().getContentType();
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());
        // the new content type will be computed by bodyInserter
        // and then set in the request decorator
        headers.remove(HttpHeaders.CONTENT_LENGTH);

        //文件上传
        if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(mediaType)) {
            ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(
                    exchange.getRequest()) {
                public HttpHeaders getHeaders() {
                    long contentLength = headers.getContentLength();
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.putAll(super.getHeaders());
                    if (contentLength > 0) {
                        httpHeaders.setContentLength(contentLength);
                    } else {
                        httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                    }
                    return httpHeaders;
                }
            };
            return chain.filter(exchange.mutate().request(decorator).build());
        }

        //文件下载
        if (MediaType.APPLICATION_PROBLEM_JSON.isCompatibleWith(mediaType)) {
            ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {};
            return chain.filter(exchange.mutate().request(decorator).build()).then(Mono.defer(() -> {
                exchange.getResponse().getHeaders().entrySet().stream()
                        .filter(kv -> (kv.getValue() != null && kv.getValue().size() > 1))
                        .filter(kv -> (kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                                || kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS)))
                        .forEach(kv -> {
                            kv.setValue(new ArrayList<String>() {{
                                add(kv.getValue().get(0));
                            }});
                        });

                return chain.filter(exchange);
            }));
        }
        // read & modify body 数据请求
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class)
                .flatMap(body -> {
                    if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {

                        // origin body map
                        //Map<String, Object> bodyMap = decodeBody(body);
                        String bodyXss = XssFilterUnit.xssEncodes(body);

                        // TODO decrypt & auth
                        // new body map
                        //Map<String, Object> newBodyMap = new HashMap<>();
                        //return Mono.just(encodeBody(newBodyMap));

                        return Mono.just(bodyXss);
                    } else {
                        return Mono.just(body);
                    }
                    //return Mono.empty();
                });

        BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        return bodyInserter.insert(outputMessage,  new BodyInserterContext())
                .then(Mono.defer(() -> {
                    ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(
                            exchange.getRequest()) {
                        public HttpHeaders getHeaders() {
                            long contentLength = headers.getContentLength();
                            HttpHeaders httpHeaders = new HttpHeaders();
                            httpHeaders.putAll(super.getHeaders());
                            if (contentLength > 0) {
                                httpHeaders.setContentLength(contentLength);
                            } else {
                                httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                            }
                            return httpHeaders;
                        }

                        public Flux<DataBuffer> getBody() {
                            return outputMessage.getBody();
                        }
                    };
                    return chain.filter(exchange.mutate().request(decorator).build());
                }));
    }

}
