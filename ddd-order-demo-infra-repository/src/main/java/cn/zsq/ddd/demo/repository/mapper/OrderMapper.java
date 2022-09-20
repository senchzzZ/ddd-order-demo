package cn.zsq.ddd.demo.repository.mapper;

import cn.zsq.ddd.demo.domain.model.order.OrderItem;
import cn.zsq.ddd.demo.entity.OrderEntity;
import cn.zsq.ddd.demo.entity.OrderItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface OrderMapper {

	int insert(OrderEntity entity);

	int update(OrderEntity entity);

	int updateSelective(OrderEntity entity);

	OrderEntity findById(@Param("id") Long id);

	void saveItem(OrderItem item);

	void deleteItemByOrderNo(@Param("orderNo")String orderNo);

    void updateItem(OrderItemEntity entity);
}
