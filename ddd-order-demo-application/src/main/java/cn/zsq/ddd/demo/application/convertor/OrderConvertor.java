package cn.zsq.ddd.demo.application.convertor;

import cn.zsq.ddd.demo.application.pojo.response.OrderResponse;
import cn.zsq.ddd.demo.domain.model.order.Order;
import org.springframework.beans.BeanUtils;

/**
 * @author zhaoshengqi
 */
public class OrderConvertor {

    private OrderConvertor() {
    }

    public static OrderResponse convertToResponse(Order source) {
        if (source == null)
            return null;
        OrderResponse response = new OrderResponse();
        BeanUtils.copyProperties(source, response);
        return response;
    }
}
