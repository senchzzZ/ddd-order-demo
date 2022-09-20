package cn.zsq.ddd.demo.remote.convertor;

import cn.zsq.ddd.demo.remote.client.request.LockInventoryRequest;
import cn.zsq.ddd.demo.domain.model.order.Order;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoshengqi
 */
public class OrderConvertor {

    private OrderConvertor() {
    }

    public static LockInventoryRequest convertToLockInventoryRequest(Order order) {
        LockInventoryRequest request = new LockInventoryRequest();
        List<LockInventoryRequest.OrderItemData> orderItemDataList = new ArrayList<>();

        request.setOrderNo(order.getOrderNo());
        request.setOrderTime(order.getOrderCreateTime());

        order.getOrderItemList().forEach(orderItem -> {
            LockInventoryRequest.OrderItemData orderItemData = new LockInventoryRequest.OrderItemData();
            BeanUtils.copyProperties(orderItem, orderItemData);
            orderItemDataList.add(orderItemData);
        });

        request.setOrderItemDataList(orderItemDataList);
        return  request;
    }

}
