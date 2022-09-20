package cn.zsq.ddd.demo.remote.service;

import cn.zsq.ddd.demo.domain.infra.remote.IProductRemoteService;
import cn.zsq.ddd.demo.remote.client.response.BaseRemoteResponse;
import cn.zsq.ddd.demo.domain.model.product.Product;
import cn.zsq.ddd.demo.remote.client.ProductApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * todo 当做防腐层,或适配层, 改名facade后缀
 *
 * @author zhaoshengqi
 */
@Component
@Slf4j
public class ProductRemoteService implements IProductRemoteService {

    @Resource
    ProductApiClient productApiClient;


    @Override
    public List<Product> getProductInfos(List<Long> productIds) {
        BaseRemoteResponse<List<Product>> response = productApiClient.getProductInfos(productIds);

        if (response == null || response.failed()) {
            log.error("getProductInfos error,request:{},response:{}", productIds, response);
            return Collections.emptyList();
        }
        //todo 这里是直接把第三方返回的模型当做领域模型来用,如果业务较为复杂,
        // 可以把第三方服务的返回的模型和业务领域模型区分开,但需要额外做一次模型转换;
        // 建议:如非必要,可以直接把第三方模型当做领域模型使用,架构层面上是允许这么做的
        return response.getData();
    }
}
