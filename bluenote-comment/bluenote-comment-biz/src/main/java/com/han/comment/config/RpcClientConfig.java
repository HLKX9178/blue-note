package com.han.comment.config;

import com.han.id.api.DistributedIdHttpApi;
import com.han.id.api.constant.IdApiConstants;
import com.han.kv.api.KeyValueHttpApi;
import com.han.kv.api.constant.KvApiConstants;
import com.han.note.api.NoteHttpApi;
import com.han.note.api.constant.NoteApiConstants;
import com.han.count.api.CountHttpApi;
import com.han.count.api.constant.CountApiConstants;
import com.han.user.api.UserHttpApi;
import com.han.user.api.constant.UserApiConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

/**
 * RPC 客户端声明（HTTP Interface + LoadBalancer）.
 *
 * @author han
 * @date 2026/07/14
 * @since 0.0.1
 */
@Configuration
@ImportHttpServices(group = IdApiConstants.SERVICE_NAME, types = DistributedIdHttpApi.class)
@ImportHttpServices(group = KvApiConstants.SERVICE_NAME, types = KeyValueHttpApi.class)
@ImportHttpServices(group = NoteApiConstants.SERVICE_NAME, types = NoteHttpApi.class)
@ImportHttpServices(group = CountApiConstants.SERVICE_NAME, types = CountHttpApi.class)
@ImportHttpServices(group = UserApiConstants.SERVICE_NAME, types = UserHttpApi.class)
public class RpcClientConfig {
}
