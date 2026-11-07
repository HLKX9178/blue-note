package com.han.oss.exception;

import com.han.framework.common.exception.BizException;
import com.han.framework.common.response.Response;
import com.han.oss.enums.ResponseCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 对象存储服务全局异常处理器.
 *
 * <p>统一将业务异常、未知异常转换为
 * {@link com.han.framework.common.response.Response} 结构返回，避免暴露堆栈信息。
 *
 * @author han
 * @date 2026/07/07
 * @since 0.0.1
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Response<Object> handleBizException(HttpServletRequest request, BizException e) {
        log.warn("{} request error, errorCode: {}, errorMessage: {}",
                request.getRequestURI(), e.getErrorCode(), e.getErrorMessage());
        return Response.fail(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response<Object> handleOtherException(HttpServletRequest request, Exception e) {
        log.error("{} request error: ", request.getRequestURI(), e);
        return Response.fail(ResponseCodeEnum.SYSTEM_ERROR);
    }
}
