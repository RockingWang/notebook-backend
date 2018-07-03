package cn.rockingwang.notebook.exception;

import cn.rockingwang.notebook.common.constant.Constants;
import cn.rockingwang.notebook.config.ErrorDefineConfig;

/**
 * @Author: wangpeng
 * @Description:
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -7399402745462528535L;

    private int code;

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BizException() {
        this(Constants.CODE_ERROR, "系统错误，请稍后重试！");
    }

    public BizException(String message) {
        this(Constants.CODE_ERROR, message);
    }

    public BizException(int code, Object... args) {
        this(code, String.format(ErrorDefineConfig.define.get(code)));
    }

    public BizException(Throwable cause) {
        super(cause);
        this.code = Constants.CODE_ERROR;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
        this.code = Constants.CODE_ERROR;
    }

    public int getCode() {
        return this.code;
    }

    public static BizException getException(int errorCode, Object... args) {
        if (ErrorDefineConfig.define.containsKey(errorCode)) {
            return new BizException(errorCode, args);
        }
        return new BizException(String.valueOf(errorCode));
    }

    public static BizException getException(String errorMessage) {
        return new BizException(errorMessage);
    }

    public static BizException getException(Throwable error) {
        if (error instanceof BizException) {
            return (BizException) error;
        }
        return new BizException(error);
    }

}