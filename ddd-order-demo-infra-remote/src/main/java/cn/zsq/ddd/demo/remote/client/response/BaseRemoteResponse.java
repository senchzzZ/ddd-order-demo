package cn.zsq.ddd.demo.remote.client.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author zhaoshengqi
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRemoteResponse<T> implements Serializable {
    private int ret;
    private String msg;
    private T data;

    public boolean succeed() {
        return this.getRet() == ResponseMessageEnum.SUCCESS.getCode();
    }

    public boolean failed() {
        return this.getRet() != ResponseMessageEnum.SUCCESS.getCode();
    }

}
