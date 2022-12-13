package cn.zsq.ddd.demo.application.service;

import cn.zsq.ddd.demo.application.pojo.cmd.co.OrderCreateProductItemCO;
import cn.zsq.ddd.demo.domain.infra.mq.OrderMessageProducer;
import cn.zsq.ddd.demo.domain.model.order.DeliveryAddress;
import cn.zsq.ddd.demo.domain.model.order.OrderEventTypeEnum;
import cn.zsq.ddd.demo.domain.model.order.OrderItem;
import cn.zsq.ddd.demo.domain.model.user.User;
import cn.zsq.ddd.demo.domain.infra.repository.IDeliveryAddressRepository;
import cn.zsq.ddd.demo.domain.infra.repository.IUserRepository;
import cn.zsq.ddd.demo.shared.err.DemoBusinessException;
import cn.zsq.ddd.demo.application.pojo.cmd.OrderChangeItemCmd;
import cn.zsq.ddd.demo.application.pojo.cmd.OrderCreateCmd;
import cn.zsq.ddd.demo.domain.model.order.Order;
import cn.zsq.ddd.demo.domain.infra.repository.IOrderRepository;
import cn.zsq.ddd.demo.domain.service.OrderDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author zhaoshengqi
 */
@Service
@Slf4j
public class OrderService {

    @Resource
    IOrderRepository orderRepository;

    @Resource(name = "orderRepositoryES")
    IOrderRepository orderRepositoryES;

    @Resource(name = "orderRepositoryRedis")
    IOrderRepository orderRepositoryRedis;

    @Resource
    OrderDomainService orderDomainService;

    @Resource
    IUserRepository userRepository;

    @Resource
    IDeliveryAddressRepository deliveryAddressRepository;

    @Resource
    OrderMessageProducer orderMessageProducer;

    /**
     * 示例:修改订单项;单聚合,可不使用domain service
     *
     * @param cmd
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Order changeOrderItemCount(OrderChangeItemCmd cmd) {
        //如果需要order所有属性,则需要在order领域服务里面依次获取用户/配送地址等信息
        Optional<Order> optionalOrder = orderRepository.findById(cmd.getOrderId());
        Order order = optionalOrder.orElseThrow(() -> new DemoBusinessException("订单不存在"));
        //逻辑内聚性体现
        order.changeItemCount(cmd.getItemId(), cmd.getCount());
        //保存
        orderRepository.save(order);
        orderRepository.saveOrderItem(order.retrieveItem(cmd.getItemId()));

        //返回领域模型,由用户界面层决定转为什么样的模型, 从架构层面限制模型滥用和模型滥转
        return order;
    }

    /**
     * 创建订单:
     * 校验订单信息
     * 商品域-库存校验
     * 营销域-优惠信息
     * 会员域-权益
     * 调度域-锁定库存
     * 物流域-运费计算
     * 生成订单
     * 发布订单创建事件
     */
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(OrderCreateCmd cmd) {
        String orderNo = UUID.randomUUID().toString();
        //用户域-用户校验,单聚合,未使用domain service,直接在应用服务层编排
        Optional<User> userOptional = userRepository.findById(cmd.getUserId());
        User user = userOptional.orElseThrow(() -> new DemoBusinessException("用户不存在"));

        //像这种把参数模型转为领域模型的情况,并不适合放到领域模型中,因为领域模型只关心自己本身的行为和状态,
        // 如果要放到领域模型中,其他模型就会侵入到当前领域模型中,也可以在当前层级定义convertor来进行转换
        List<OrderItem> orderItemList = makeOrderItems(cmd.getProductItems(), orderNo);
        //校验库存并组装订单项,因为product和OrderItem属于两个域,且使用了外部product服务,所以使用了领域服务
        orderDomainService.checkInventoryAndAssembleOrderItems(orderItemList);

        //配送地址
        Optional<DeliveryAddress> deliveryInfoOptional = deliveryAddressRepository.findById(cmd.getDeliveryAddressId());
        DeliveryAddress deliveryAddress = deliveryInfoOptional.orElseThrow(() -> new DemoBusinessException("配送信息不存在"));

        //创建订单,最好的方式是使用工厂,这里的做法是让聚合根承担了工厂的职责
        Order order = Order.create(orderNo, deliveryAddress, orderItemList, user.getUserId());
        //调度域-锁定库存,用到了远程服务,所以放到了领域服务
        orderDomainService.lockInventory(order);
        //创建订单
        orderRepository.createOrder(order);

        //发布订单创建事件
        orderMessageProducer.publish(order, OrderEventTypeEnum.INIT);
        //创建订单的后续操作,例如通知或其他异步操作
        //orderDomainService.postCreateOrder(order);

        //返回领域模型,由用户界面层决定转为什么样的模型, 从架构层面限制模型滥用和模型滥转
        return order;
    }

    private List<OrderItem> makeOrderItems(List<OrderCreateProductItemCO> productItems, String orderNo) {
        return productItems.stream().map(item ->
                new OrderItem(orderNo,item.getProductId(),item.getCount())).collect(Collectors.toList());
    }



    public Order findById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElseThrow(() -> new DemoBusinessException("订单不存在"));
    }

}
