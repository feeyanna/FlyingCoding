
package com.laifeiyang.dev.micro.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.fusesource.jansi.Ansi;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * <p>
 *  打印项目信息
 * </p>
 * @author laifeiyang
 * @date 2019-11-19
 **/
@Slf4j
public class PrintApplicationInfo {


    /**
     * 执行之前，打印前置条件提示
     */
    public static void printTip(){
        StringBuffer tip = new StringBuffer();
        tip.append("======================================================================================\n");
        tip.append("                                                                                  \n");
        tip.append("                               !!!准备工作!!!                                      \n");
        tip.append(" 1.请先在MySQL中创建数据库，默认数据库名称为：maangement-app                        \n");
        tip.append(" 2.请先启动redis服务                                                              \n");
        tip.append("                                                                                  \n");
        tip.append("======================================================================================\n");
        log.info("\n{}",Ansi.ansi().eraseScreen().fg(Ansi.Color.YELLOW).a(tip.toString()).reset().toString());
    }

    /**
     * 启动成功之后，打印项目信息
     */
    public static void print(ConfigurableApplicationContext context){
        ConfigurableEnvironment environment = context.getEnvironment();

        // 项目名称
        String projectFinalName = environment.getProperty("info.project-finalName");
        // 项目版本
        String projectVersion = environment.getProperty("info.project-version");
        // 项目profile
        String profileActive = environment.getProperty("spring.profiles.active");
        // 项目路径
        String contextPath = environment.getProperty("server.servlet.context-path");
        // 项目端口
        String port = environment.getProperty("server.port");

        //log.info("projectFinalName : {}",projectFinalName);
        //log.info("projectVersion : {}",projectVersion);
        log.info("profileActive : {}",profileActive);
        //log.info("contextPath : {}",contextPath);
        log.info("port : {}",port);

        String startSuccess = " ____    __                    __        ____                                                   \n" +
                "/\\  _`\\ /\\ \\__                /\\ \\__    /\\  _`\\                                                 \n" +
                "\\ \\,\\L\\_\\ \\ ,_\\    __     _ __\\ \\ ,_\\   \\ \\,\\L\\_\\  __  __    ___    ___     __    ____    ____  \n" +
                " \\/_\\__ \\\\ \\ \\/  /'__`\\  /\\`'__\\ \\ \\/    \\/_\\__ \\ /\\ \\/\\ \\  /'___\\ /'___\\ /'__`\\ /',__\\  /',__\\ \n" +
                "   /\\ \\L\\ \\ \\ \\_/\\ \\L\\.\\_\\ \\ \\/ \\ \\ \\_     /\\ \\L\\ \\ \\ \\_\\ \\/\\ \\__//\\ \\__//\\  __//\\__, `\\/\\__, `\\\n" +
                "   \\ `\\____\\ \\__\\ \\__/.\\_\\\\ \\_\\  \\ \\__\\    \\ `\\____\\ \\____/\\ \\____\\ \\____\\ \\____\\/\\____/\\/\\____/\n" +
                "    \\/_____/\\/__/\\/__/\\/_/ \\/_/   \\/__/     \\/_____/\\/___/  \\/____/\\/____/\\/____/\\/___/  \\/___/ \n" +
                "                                                                                                \n" +
                "                                                                                                ";

        log.info(projectFinalName + " project start success...........");
        log.info("\n{}",AnsiUtil.getAnsi(Ansi.Color.BLUE,startSuccess));
    }

}
