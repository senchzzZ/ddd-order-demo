package cn.zsq.ddd.demo.repository;

import cn.zsq.ddd.demo.convertor.DeliveryAddressConvertor;
import cn.zsq.ddd.demo.domain.infra.repository.IDeliveryAddressRepository;
import cn.zsq.ddd.demo.domain.model.order.DeliveryAddress;
import cn.zsq.ddd.demo.repository.mapper.DeliveryAddressMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author zhaoshengqi
 */
@Repository
public class DeliveryAddressRepository implements IDeliveryAddressRepository {

    @Resource
    DeliveryAddressMapper deliveryAddressMapper;


    public Optional<DeliveryAddress> findById(Long id) {
        return Optional.ofNullable(DeliveryAddressConvertor.INSTANCT.convertToDO(deliveryAddressMapper.findById(id)));
    }
}
