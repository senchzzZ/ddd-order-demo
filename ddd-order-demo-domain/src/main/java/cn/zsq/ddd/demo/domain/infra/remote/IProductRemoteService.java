package cn.zsq.ddd.demo.domain.infra.remote;

import cn.zsq.ddd.demo.domain.model.product.Product;

import java.util.List;

/**
 * 基础设施-商品远程服务
 * @author zhaoshengqi
 */
public interface IProductRemoteService {
    List<Product> getProductInfos(List<Long> productIds);
}
