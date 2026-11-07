package com.han.id.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 分布式 ID 生成服务集成测试（依赖 Redis）.
 *
 * @author han
 * @date 2026/07/08
 * @since 0.0.1
 */
@SpringBootTest
class DistributedIdServiceImplTest {

    @Autowired
    private DistributedIdService distributedIdService;

    @Test
    void bluenoteId_should_be_monotonically_increasing() {
        long first = distributedIdService.generateBluenoteId();
        long second = distributedIdService.generateBluenoteId();
        assertTrue(second > first, "bluenote-id 应趋势递增");
        assertTrue(first >= 10000L, "bluenote-id 起始值应 >= offset(10000)");
    }

    @Test
    void userId_should_be_monotonically_increasing() {
        long first = distributedIdService.generateUserId();
        long second = distributedIdService.generateUserId();
        assertTrue(second > first, "user-id 应趋势递增");
        assertTrue(first >= 10000L, "user-id 起始值应 >= offset(10000)");
    }

    @Test
    void noteId_should_be_monotonically_increasing() {
        long first = distributedIdService.generateNoteId();
        long second = distributedIdService.generateNoteId();
        assertTrue(second > first, "note-id 应趋势递增");
        assertTrue(first >= 10000L, "note-id 起始值应 >= offset(10000)");
    }
}
