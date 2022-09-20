package cn.zsq.ddd.demo.repository.mapper;

import cn.zsq.ddd.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserInfoMapper {

	/**
	 * 插入用户
	 * @param userInfo
	 * @return
	 */
	int insert(UserEntity userInfo);

	/**
	 * 修改用户
	 * @param userInfo
	 * @return
	 */
	int update(UserEntity userInfo);

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	UserEntity findById(@Param("id") Long id);
	
}
