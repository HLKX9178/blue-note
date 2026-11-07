package com.han.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户服务启动类.
 *
 * @author han
 * @date 2026/07/07
 * @since 0.0.1
 */
@SpringBootApplication
@MapperScan("com.han.user.domain.mapper")
public class BluenoteUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BluenoteUserApplication.class, args);
    }
}
