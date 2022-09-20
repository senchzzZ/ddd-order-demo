package cn.zsq.ddd.demo.shared.err;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.TreeMap;


/**
 * 业务异常
 */
public class DemoBusinessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 4801959191828714311L;

    private ErrorCode errorCode;

    private String errorInfo;

    private Object[] formats;

    private final Map<String, Object> properties = new TreeMap<>();

    public static DemoBusinessException wrap(Throwable exception, ErrorCode states) {
        if (exception instanceof DemoBusinessException) {
            return (DemoBusinessException) exception;
        } else {
            return new DemoBusinessException(exception.getMessage(), exception, states);
        }
    }

    public static DemoBusinessException convertSystemException(Throwable exception) {
        if (exception instanceof DemoBusinessException) {
            return (DemoBusinessException) exception;
        } else {
            return new DemoBusinessException(exception, ErrorCode.FAIL);
        }
    }

    /**
     * 判断异常是否由某些底层的异常引起.
     */
    public static boolean isCausedBy(Throwable ex, Class<?>... causeExceptionClasses) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            for (Class<?> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }

    public static DemoBusinessException getInstance(ErrorCode states) {
        return getInstance(null, states);
    }

    public static DemoBusinessException getInstance(String message, ErrorCode states) {
        return new DemoBusinessException(message, states);
    }

    public DemoBusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorInfo = errorCode.getMsg();
    }

    public DemoBusinessException(ErrorCode errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public DemoBusinessException(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public DemoBusinessException(String message, ErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public DemoBusinessException(Throwable cause, ErrorCode errorCode) {
        this("", cause, errorCode);
    }

    public DemoBusinessException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorInfo = errorCode.getMsg();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorInfo() {
        return String.format(errorInfo, this.formats);
    }

    public DemoBusinessException setErrorCode(ErrorCode states) {
        this.errorCode = states;
        return this;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) properties.get(name);
    }

    public DemoBusinessException set(String name, Object value) {
        properties.put(name, value);
        return this;
    }

    public DemoBusinessException set(Map<?, ?> map) {
        for (Object key : map.keySet()) {
            properties.put(String.valueOf(key), map.get(key));
        }
        return this;
    }

    public DemoBusinessException formats(Object... formats) {
        this.formats = formats;
        return this;
    }

    /**
     * 将ErrorStack转化为String.
     */
    public String getStackTraceAsString() {
        StringWriter stringWriter = new StringWriter();
        this.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

}
