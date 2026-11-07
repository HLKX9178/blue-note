package com.han.relation.controller;

import com.han.framework.biz.operationlog.aspect.ApiOperationLog;
import com.han.framework.common.response.PageResponse;
import com.han.framework.common.response.Response;
import com.han.relation.model.vo.FindFansListReqVO;
import com.han.relation.model.vo.FindFansUserRspVO;
import com.han.relation.model.vo.FindFollowingListReqVO;
import com.han.relation.model.vo.FindFollowingUserRspVO;
import com.han.relation.model.vo.FollowUserReqVO;
import com.han.relation.model.vo.UnfollowUserReqVO;
import com.han.relation.service.RelationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户关系控制器.
 *
 * <p>经网关转发，需 JWT；当前登录用户 ID 由网关透传的 userId 头解析得到
 * （{@code LoginUserContextHolder}）。
 *
 * @author han
 * @date 2026/07/10
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class RelationController {

    private final RelationService relationService;

    /**
     * 关注用户.
     */
    @PostMapping("/follow")
    @ApiOperationLog(description = "关注用户")
    public Response<?> follow(@Validated @RequestBody FollowUserReqVO followUserReqVO) {
        return relationService.follow(followUserReqVO);
    }

    /**
     * 取关用户.
     */
    @PostMapping("/unfollow")
    @ApiOperationLog(description = "取关用户")
    public Response<?> unfollow(@Validated @RequestBody UnfollowUserReqVO unfollowUserReqVO) {
        return relationService.unfollow(unfollowUserReqVO);
    }

    /**
     * 查询关注列表.
     */
    @PostMapping("/following/list")
    @ApiOperationLog(description = "查询用户关注列表")
    public PageResponse<FindFollowingUserRspVO> findFollowingList(@Validated @RequestBody FindFollowingListReqVO findFollowingListReqVO) {
        return relationService.findFollowingList(findFollowingListReqVO);
    }

    /**
     * 查询粉丝列表.
     */
    @PostMapping("/fans/list")
    @ApiOperationLog(description = "查询用户粉丝列表")
    public PageResponse<FindFansUserRspVO> findFansList(@Validated @RequestBody FindFansListReqVO findFansListReqVO) {
        return relationService.findFansList(findFansListReqVO);
    }
}
