package cn.zsq.ddd.demo.application.pojo.query;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserQuery {

	@NotNull(message = "用户ID不能为空")
	private Long id;
}
