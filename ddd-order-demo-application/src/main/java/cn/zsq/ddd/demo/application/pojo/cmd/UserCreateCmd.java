package cn.zsq.ddd.demo.application.pojo.cmd;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserCreateCmd {

	@NotNull(message = "用户年龄不能为空")
	private Integer age;

	@NotNull(message = "用户姓名不能为空")
	private String name;
}
