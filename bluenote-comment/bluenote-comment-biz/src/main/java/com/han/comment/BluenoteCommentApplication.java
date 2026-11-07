package com.han.comment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 评论服务启动类.
 *
 * @author han
 * @date 2026/07/14
 * @since 0.0.1
 */
@SpringBootApplication
@MapperScan("com.han.comment.domain.mapper")
public class BluenoteCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BluenoteCommentApplication.class, args);
    }
}
