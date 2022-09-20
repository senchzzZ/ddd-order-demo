package cn.zsq.ddd.demo.api.controller;

import cn.zsq.ddd.demo.api.controller.response.OrderResponse;
import cn.zsq.ddd.demo.api.controller.convertor.OrderConvertor;
import cn.zsq.ddd.demo.application.pojo.cmd.OrderChangeItemCmd;
import cn.zsq.ddd.demo.application.pojo.cmd.OrderCreateCmd;
import cn.zsq.ddd.demo.application.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhaoshengqi
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @PostMapping(name = "/changeOrderItemCount")
    public OrderResponse changeOrderItemCount(OrderChangeItemCmd cmd) {
        return OrderConvertor.convertToResponse(orderService.changeOrderItemCount(cmd));
    }

    @PostMapping(name = "/create")
    public OrderResponse create(OrderCreateCmd cmd) {
        return OrderConvertor.convertToResponse(orderService.createOrder(cmd));
    }

}
