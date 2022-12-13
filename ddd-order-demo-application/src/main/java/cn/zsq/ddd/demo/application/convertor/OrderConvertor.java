package cn.zsq.ddd.demo.application.convertor;

import cn.zsq.ddd.demo.application.pojo.cmd.co.OrderCreateProductItemCO;
import cn.zsq.ddd.demo.application.pojo.response.OrderResponse;
import cn.zsq.ddd.demo.domain.model.order.Order;
import cn.zsq.ddd.demo.domain.model.order.OrderItem;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<OrderItem> makeOrderItems(List<OrderCreateProductItemCO> productItems, String orderNo) {
        return productItems.stream().map(item ->
                new OrderItem(orderNo,item.getProductId(),item.getCount())).collect(Collectors.toList());
    }
}
