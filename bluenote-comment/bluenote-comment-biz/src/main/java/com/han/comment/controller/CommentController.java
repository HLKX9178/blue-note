package com.han.comment.controller;

import com.han.comment.model.vo.PublishCommentReqVO;
import com.han.comment.model.vo.FindCommentPageListReqVO;
import com.han.comment.model.vo.FindChildCommentPageListReqVO;
import com.han.comment.model.vo.FindCommentItemRspVO;
import com.han.comment.model.vo.FindChildCommentItemRspVO;
import com.han.comment.model.vo.LikeCommentReqVO;
import com.han.comment.model.vo.DeleteCommentReqVO;
import com.han.comment.model.response.CommentPageResponse;
import com.han.comment.service.CommentService;
import com.han.comment.service.CommentQueryService;
import com.han.comment.service.CommentInteractionService;
import com.han.comment.service.CommentDeleteService;
import com.han.framework.biz.operationlog.aspect.ApiOperationLog;
import com.han.framework.common.response.Response;
import com.han.framework.common.response.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论控制器.
 *
 * @author han
 * @date 2026/07/14
 * @since 0.0.1
 */
@RestController
@RequestMapping("/comment")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentQueryService commentQueryService;
    private final CommentInteractionService commentInteractionService;
    private final CommentDeleteService commentDeleteService;

    /**
     * 发布评论.
     */
    @PostMapping("/publish")
    @ApiOperationLog(description = "发布评论")
    public Response<?> publishComment(@Validated @RequestBody PublishCommentReqVO publishCommentReqVO) {
        return commentService.publishComment(publishCommentReqVO);
    }

    /**
     * 一级评论分页.
     */
    @PostMapping("/list")
    @ApiOperationLog(description = "一级评论分页")
    public CommentPageResponse<FindCommentItemRspVO> findRootComments(
            @Validated @RequestBody FindCommentPageListReqVO request) {
        return commentQueryService.findRootComments(request);
    }

    /**
     * 二级评论分页（不重复返回一级列表已经展示的首条回复）.
     */
    @PostMapping("/child/list")
    @ApiOperationLog(description = "二级评论分页")
    public PageResponse<FindChildCommentItemRspVO> findChildComments(
            @Validated @RequestBody FindChildCommentPageListReqVO request) {
        return commentQueryService.findChildComments(request);
    }

    /** 点赞评论 */
    @PostMapping("/like")
    @ApiOperationLog(description = "点赞评论")
    public Response<?> likeComment(@Validated @RequestBody LikeCommentReqVO request) {
        return commentInteractionService.like(request);
    }

    /** 取消点赞评论 */
    @PostMapping("/unlike")
    @ApiOperationLog(description = "取消点赞评论")
    public Response<?> unlikeComment(@Validated @RequestBody LikeCommentReqVO request) {
        return commentInteractionService.unlike(request);
    }

    /** 删除评论及其回复分支 */
    @PostMapping("/delete")
    @ApiOperationLog(description = "删除评论")
    public Response<?> deleteComment(@Validated @RequestBody DeleteCommentReqVO request) {
        return commentDeleteService.delete(request);
    }
}
