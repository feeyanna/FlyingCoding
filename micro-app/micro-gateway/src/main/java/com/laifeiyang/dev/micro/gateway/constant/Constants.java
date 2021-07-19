package com.laifeiyang.dev.micro.gateway.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *  zk取值
 * </pre>
 *
 * @author laifeiyang
 * @since 2021-07-18
 */
@Component
@RefreshScope
@Data
public class Constants {

    @Value("${spring-cloud-gateway.intfAddr}")
    public String intfAddr;

}
