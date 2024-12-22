package vip.dengwj.exception;

/**
 * @date 2024/12/22 21:35
 * @author 朴睦
 * @description 系统异常
 */
public class SystemException extends RuntimeException {
    private Integer code;

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
