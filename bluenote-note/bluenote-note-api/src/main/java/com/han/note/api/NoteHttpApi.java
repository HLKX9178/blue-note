package com.han.note.api;

import com.han.framework.common.response.Response;
import com.han.note.api.dto.req.FindPublishedNoteReqDTO;
import com.han.note.api.dto.resp.FindPublishedNoteRspDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * 笔记服务内网 HTTP Interface 契约.
 *
 * @author han
 * @date 2026/07/15
 * @since 0.0.1
 */
@HttpExchange
public interface NoteHttpApi {

    String PREFIX = "/note";

    /**
     * 查询正常发布中的笔记.
     *
     * @param request 查询入参
     * @return 笔记最小信息；不存在时 data 为 null
     */
    @PostExchange(PREFIX + "/findPublishedById")
    Response<FindPublishedNoteRspDTO> findPublishedById(@RequestBody FindPublishedNoteReqDTO request);
}
