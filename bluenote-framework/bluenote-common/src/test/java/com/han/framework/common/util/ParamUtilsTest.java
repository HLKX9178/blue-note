package com.han.framework.common.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParamUtilsTest {

    @Test
    void checkNickname_validAndInvalid() {
        assertThat(ParamUtils.checkNickname("小憨薯")).isTrue();
        assertThat(ParamUtils.checkNickname("a")).isFalse();                 // 太短
        assertThat(ParamUtils.checkNickname("a".repeat(25))).isFalse();      // 太长
        assertThat(ParamUtils.checkNickname("bad@name")).isFalse();          // 特殊字符
    }

    @Test
    void checkBluenoteId_validAndInvalid() {
        assertThat(ParamUtils.checkBluenoteId("hanser_01")).isTrue();
        assertThat(ParamUtils.checkBluenoteId("abc")).isFalse();              // 太短
        assertThat(ParamUtils.checkBluenoteId("a".repeat(16))).isFalse();     // 太长
        assertThat(ParamUtils.checkBluenoteId("有中文")).isFalse();            // 非法字符
    }

    @Test
    void checkLength_validAndInvalid() {
        assertThat(ParamUtils.checkLength("hi", 100)).isTrue();
        assertThat(ParamUtils.checkLength("", 100)).isFalse();               // 空串
        assertThat(ParamUtils.checkLength("a".repeat(101), 100)).isFalse();  // 超长
    }
}
