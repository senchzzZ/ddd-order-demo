package cn.zsq.ddd.demo.domain.service;

import cn.zsq.ddd.demo.domain.infra.remote.IProductRemoteService;
import cn.zsq.ddd.demo.domain.infra.repository.IOrderRepository;
import cn.zsq.ddd.demo.domain.model.order.LockInventoryResponse;
import cn.zsq.ddd.demo.domain.model.order.Order;
import cn.zsq.ddd.demo.domain.infra.remote.IScheduleRemoteService;
import cn.zsq.ddd.demo.domain.model.order.OrderItem;
import cn.zsq.ddd.demo.domain.model.product.Product;
import cn.zsq.ddd.demo.shared.err.DemoBusinessException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 调度域-领域服务
 *
 * @author zhaoshengqi
 */
@Component
public class OrderDomainService {

    @Resource
    private IScheduleRemoteService scheduleRemoteService;

    @Resource
    private IProductRemoteService productRemoteService;

    @Resource
    private IOrderRepository orderRepository;
    /**
     * 检查库存,组装订单项
     * @param orderItems
     * @return
     */
    public void checkInventoryAndAssembleOrderItems(List<OrderItem> orderItems) {
        if (CollectionUtils.isEmpty(orderItems)) {
            throw new DemoBusinessException("未选择商品");
        }

        //从商品服务获取商品信息
        List<Long> productIds = orderItems.stream().map(OrderItem::getProductId).collect( Collectors.toList());
        List<Product> productList = productRemoteService.getProductInfos(productIds);
        if (CollectionUtils.isEmpty(productList)) {
            throw new DemoBusinessException("未查询到商品信息");
        }

        Map<Long, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, p -> p));
        //库存校验
        for (OrderItem item : orderItems) {
            Product product = productMap.get(item.getProductId());
            if (product == null)
                throw new DemoBusinessException("商品[" + item.getProductName() + "]不存在");
            if (product.getInventoryCount() < item.getCount())
                throw new DemoBusinessException("商品[" + product.getProductName() + "]库存不足");

            //组装订单项信息
            item.setPrice(product.getPrice());
            item.setProductName(product.getProductName());
        }
    }
    /**
     * 锁定库存
     *
     * @param order
     */
    public void lockInventory(Order order) {
        Optional<LockInventoryResponse> lockInventoryDTOOptional = scheduleRemoteService.lockInventory(order);
        LockInventoryResponse lockInventoryResponse = lockInventoryDTOOptional.orElseThrow(() -> new DemoBusinessException("库存锁定失败"));

        if (lockInventoryResponse.getLockEndTime().before(new Date())) {
            throw new DemoBusinessException("库存锁定失败");
        }
        order.setLockInventoryEndTime(lockInventoryResponse.getLockEndTime());
    }

}
