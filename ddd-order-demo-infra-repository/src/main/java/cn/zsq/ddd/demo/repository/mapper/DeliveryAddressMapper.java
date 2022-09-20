package cn.zsq.ddd.demo.repository.mapper;

import cn.zsq.ddd.demo.entity.DeliveryAddressEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DeliveryAddressMapper {

	int insert(DeliveryAddressEntity entity);

	int update(DeliveryAddressEntity entity);

	int updateSelective(DeliveryAddressEntity entity);

	DeliveryAddressEntity findById(@Param("id") Long id);

}
