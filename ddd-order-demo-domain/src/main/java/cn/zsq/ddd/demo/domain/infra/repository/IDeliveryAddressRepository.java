package cn.zsq.ddd.demo.domain.infra.repository;

import cn.zsq.ddd.demo.domain.model.order.DeliveryAddress;

import java.util.Optional;

/**
 * @author zhaoshengqi
 */
public interface IDeliveryAddressRepository {

    Optional<DeliveryAddress> findById(Long deliveryAddressId);
}
