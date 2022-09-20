package cn.zsq.ddd.demo.remote.client.response;

/**
 * @author zhaoshengqi
 */
public enum ResponseMessageEnum {
    SUCCESS(10000, "操作成功"),
    NOT_FOUND(40001, "没有查询结果"),
    UNSUPPORT_PARAM(40005, "不支持的参数"),
    ERROR(50000, "操作失败");

    private int code;
    private String message;

    ResponseMessageEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
