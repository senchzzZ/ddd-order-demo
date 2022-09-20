package cn.zsq.ddd.demo.convertor;

import cn.zsq.ddd.demo.domain.model.order.DeliveryAddress;
import cn.zsq.ddd.demo.entity.DeliveryAddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhaoshengqi
 */
@Mapper
public interface DeliveryAddressConvertor {
    DeliveryAddressConvertor INSTANCT = Mappers.getMapper(DeliveryAddressConvertor.class);

    @Mapping(source = "id", target = "deliveryAddressId")
    DeliveryAddress convertToDO(DeliveryAddressEntity entity);

    @Mapping(source = "deliveryAddressId", target = "id")
    DeliveryAddressEntity convertToEntity(DeliveryAddress deliveryAddress);

    List<DeliveryAddress> convertToDOList(List<DeliveryAddressEntity> entityList);

    List<DeliveryAddressEntity> convertToEntities(List<DeliveryAddress> deliveryAddressList);

}
