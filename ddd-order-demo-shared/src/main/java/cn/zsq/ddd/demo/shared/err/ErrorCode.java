
package cn.zsq.ddd.demo.shared.err;

/**
 * 状态码
 */
public enum ErrorCode {
    OK(0, "OK"),
    FAIL(9000, "system error"),
    NULL(9004, "illegal parameter"),
    CONNECT_ERROR(9500, "not connect");

    private int code;
    private String msg;

    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

}
