package cn.zsq.ddd.demo.domain.model.order;

import cn.zsq.ddd.demo.shared.err.DemoBusinessException;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhaoshengqi
 */
@Data
@Builder
public class DeliveryAddress {
    Long deliveryAddressId;

    Long userId;

    String deliveryPhoneNum;

    String city;

    String province;

    String region;

    String addressDetail;


    public void transformAddress(String addressDetail) {
        if (StringUtils.isEmpty(addressDetail)) {
            throw new DemoBusinessException("Illegal address!");
        }
        this.addressDetail = addressDetail;

    }
}
