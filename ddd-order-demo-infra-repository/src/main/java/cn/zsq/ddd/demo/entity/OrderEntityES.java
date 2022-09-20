package cn.zsq.ddd.demo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;


/**
 * @author zhaoshengqi
 */
@Data
@Document(indexName = "book", createIndex = true)
public class OrderEntityES {

    /**
     * id
     */
    @Id
    @Field(type = FieldType.Text)
    Long id;

    /**
     * 订单状态
     */
    @Field(type = FieldType.Integer)
    int status;

    /**
     * 总价
     */
    @Field(type = FieldType.Long)
    Long totalPrice;

    /**
     * 订单号
     */
    @Field(analyzer = "ik_max_word")
    String orderNo;

    /**
     * 用户id
     */
    @Field(type = FieldType.Long)
    Long userId;

    /**
     * 配送地址
     */
    @Field(analyzer = "ik_max_word")
    String deliveryAddress;

    /**
     * 备注
     */
    @Field(analyzer = "ik_max_word")
    String remark;

    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    Date orderCreateTime;
}
