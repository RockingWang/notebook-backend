package cn.rockingwang.notebook.common.base;

import cn.rockingwang.notebook.common.constant.Constants;
import cn.rockingwang.notebook.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: wangpeng
 * @Description:
 */
@Slf4j
public class BaseController {

    @Autowired
    protected HttpSession session;
    @Autowired
    protected HttpServletRequest httpServletRequest;
//    @Autowired
//    protected HttpServletResponse httpServletResponse;

    @ExceptionHandler
    public @ResponseBody
    ResponseEntity<BaseResponse> onError(HttpServletRequest request, Exception exception) {
        log.error(exception.getMessage(), exception);
        BaseResponse response = new BaseResponse().error(exception);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    protected boolean isLogin() {
        if (session.getAttribute(Constants.SESSION_ATTRIBUTE_UID) != null) {
            String userId = session.getAttribute(Constants.SESSION_ATTRIBUTE_UID).toString();
            log.debug("isLogin userId={} and sessionId={}", userId, session.getId());
            return true;
        }
        return false;
    }

    protected String getLoginUser() {
        if (session.getAttribute(Constants.SESSION_ATTRIBUTE_UID) == null) {
            throw new BizException(10101, "当前用户未登录或会话已超时");
        }
        String userId = session.getAttribute(Constants.SESSION_ATTRIBUTE_UID).toString();
        log.debug("get login user userId={} and sessionId={}", userId, session.getId());
        return userId;
    }

    protected void setLoginUser(String userId) {
        session.setAttribute(Constants.SESSION_ATTRIBUTE_UID, userId);
        log.info("userId {} login, and sessionId is {}", userId, session.getId());
    }

}
