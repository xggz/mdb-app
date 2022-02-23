package app.mly.mdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 应用启动器（茉莉dodo机器人）
 *
 * @author feitao yyimba@qq.com
 * @since 2022/2/23 8:24 PM
 */
@EnableAsync
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}