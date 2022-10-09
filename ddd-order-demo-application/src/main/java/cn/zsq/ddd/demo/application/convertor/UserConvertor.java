package cn.zsq.ddd.demo.application.convertor;

import cn.zsq.ddd.demo.application.pojo.response.UserResponse;
import cn.zsq.ddd.demo.domain.model.user.User;
import org.springframework.beans.BeanUtils;

/**
 * @author zhaoshengqi
 */
public class UserConvertor {

    private UserConvertor() {
    }

    public static UserResponse convertToResponse(User source) {
        if (source == null)
            return null;
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(source, response);
        return response;
    }
}
