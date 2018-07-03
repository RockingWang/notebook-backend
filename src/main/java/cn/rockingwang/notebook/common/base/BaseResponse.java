package cn.rockingwang.notebook.common.base;

import cn.rockingwang.notebook.common.constant.Constants;
import cn.rockingwang.notebook.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindException;

/**
 * @Author: wangpeng
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    private int errorCode;
    private String errorMessage;
    private String callback;

    public BaseResponse error(Throwable error) {
        if (error instanceof BizException) {
            BizException bizException = (BizException) error;
            this.errorCode = bizException.getCode();
            this.errorMessage = bizException.getMessage();
        } else if (error instanceof BindException) {
            BindException bindException = (BindException) error;
            this.errorCode = Constants.CODE_ERROR;
            this.errorMessage = bindException.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        } else {
            this.errorCode = Constants.CODE_ERROR;
            this.errorMessage = error.getMessage();
        }
        return this;
    }
}
