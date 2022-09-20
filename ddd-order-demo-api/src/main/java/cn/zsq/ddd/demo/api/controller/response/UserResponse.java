package cn.zsq.ddd.demo.api.controller.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id; // id
    private String name; // 名称
    private Integer age; // 年龄

}
