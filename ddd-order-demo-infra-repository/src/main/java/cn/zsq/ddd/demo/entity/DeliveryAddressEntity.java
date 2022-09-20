package cn.zsq.ddd.demo.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author zhaoshengqi
 */
@Alias("delivery_address")
@Data
public class DeliveryAddressEntity {
    Long id;

    Long userId;

    String deliveryPhoneNum;

    String city;

    String province;

    String region;

    String addressDetail;

}
