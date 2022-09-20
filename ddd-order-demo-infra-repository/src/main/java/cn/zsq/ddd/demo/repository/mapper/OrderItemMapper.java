package cn.zsq.ddd.demo.repository.mapper;

import cn.zsq.ddd.demo.entity.OrderItemEntity;
import cn.zsq.ddd.demo.repository.condition.UserItemCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface OrderItemMapper {

	int insert(OrderItemEntity entity);

	int update(OrderItemEntity entity);

	OrderItemEntity findById(@Param("id") Long id);

	void deleteItemByOrderNo(@Param("orderNo")String orderNo);

    List<OrderItemEntity> listByCondition(UserItemCondition condition);
}
