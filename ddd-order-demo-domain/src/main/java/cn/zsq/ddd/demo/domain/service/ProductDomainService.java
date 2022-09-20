package cn.zsq.ddd.demo.domain.service;

import cn.zsq.ddd.demo.domain.model.product.Product;
import cn.zsq.ddd.demo.domain.infra.remote.IProductRemoteService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhaoshengqi
 */
@Component
public class ProductDomainService {

    @Resource
    private IProductRemoteService productRemoteService;

    /**
     * 查询商品列表
     * @param productIds
     * @return
     */
    public List<Product> getProductInfos(List<Long> productIds) {
        return productRemoteService.getProductInfos(productIds);
    }

}
