package cn.zsq.ddd.demo.entity;


import java.util.Date;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("user")
@Data
public class UserEntity {

	private Long id; // id
	private String name; // 名称
	private Integer age; // 年龄
	private Date createTime;
}
