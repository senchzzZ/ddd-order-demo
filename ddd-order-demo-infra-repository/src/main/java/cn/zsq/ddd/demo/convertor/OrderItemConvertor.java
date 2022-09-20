package cn.zsq.ddd.demo.convertor;

import cn.zsq.ddd.demo.domain.model.order.OrderItem;
import cn.zsq.ddd.demo.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhaoshengqi
 */
@Mapper
public interface OrderItemConvertor {
    OrderItemConvertor INSTANCT = Mappers.getMapper(OrderItemConvertor.class);


    @Mapping(source = "itemId", target = "id")
    OrderItemEntity convertToEntity(OrderItem orderItem);

    @Mapping(source = "id", target = "itemId")
    OrderItem convertToDO(OrderItemEntity entity);

    List<OrderItem> convertToDOList(List<OrderItemEntity> entityList);

    List<OrderItemEntity> convertToEntities(List<OrderItem> orderItemList);

}
