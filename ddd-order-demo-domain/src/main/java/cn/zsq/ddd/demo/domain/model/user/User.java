package cn.zsq.ddd.demo.domain.model.user;

import cn.zsq.ddd.demo.shared.err.DemoBusinessException;
import lombok.Data;

import java.util.Date;

/**
 * @author zhaoshengqi
 */
@Data
public class User {
    Long userId;
    String name;
    Integer age;
    Date createTime;
    int state;

    /**
     * 用户合法性检查
     */
    public void legalCheck() {
        if (state == UserStateEnum.DISABLE.getValue()) {
            throw new DemoBusinessException("user disabled");
        }
        //other check...
    }
    public boolean isLegal() {
        return state == UserStateEnum.ENABLE.getValue();
    }
}
