package com.han.user.rpc;

import com.han.framework.common.response.Response;
import com.han.id.api.DistributedIdHttpApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 分布式 ID 生成服务调用封装.
 *
 * <p>通过 {@link DistributedIdHttpApi} 调用 ID 服务生成 bluenote-id 与 user-id。
 *
 * @author han
 * @date 2026/07/08
 * @since 0.0.1
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DistributedIdRpcService {

    private final DistributedIdHttpApi distributedIdHttpApi;

    /**
     * 生成小憨书 ID.
     *
     * @return 成功返回 ID；失败返回 {@code null}
     */
    public Long generateBluenoteId() {
        Response<Long> response = distributedIdHttpApi.generateBluenoteId();
        if (Objects.isNull(response) || !response.isSuccess()) {
            log.error("==> 调用分布式 ID 服务生成 bluenote-id 失败, response: {}", response);
            return null;
        }
        return response.getData();
    }

    /**
     * 生成用户 ID.
     *
     * @return 成功返回 ID；失败返回 {@code null}
     */
    public Long generateUserId() {
        Response<Long> response = distributedIdHttpApi.generateUserId();
        if (Objects.isNull(response) || !response.isSuccess()) {
            log.error("==> 调用分布式 ID 服务生成 user-id 失败, response: {}", response);
            return null;
        }
        return response.getData();
    }
}
