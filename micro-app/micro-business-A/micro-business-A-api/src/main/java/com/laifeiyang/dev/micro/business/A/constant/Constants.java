package com.laifeiyang.dev.micro.business.A.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *  nacos和zookeeper动态取值
 * </pre>
 *
 * @author laifeiyang
 * @since 2021-07-12
 */
@Component
@RefreshScope
@Data
public class Constants {

    //测试：发送短信的url
    @Value("${send.msg.url}")
    public String sendMsgUrl;

    //测试：发送短信的手机号
    @Value("${send.msg.tel}")
    public String sendMsgTel;

    //测试：不同业务需求的发送短信的手机号
    @Value("${send.msg.business.tel}")
    public String sendMsgBusinessTel;

    //测试：不同业务需求的发送短信的手机号test
    @Value("${send.msg.business.tel.test}")
    public String sendMsgBusinessTelTest;

}
