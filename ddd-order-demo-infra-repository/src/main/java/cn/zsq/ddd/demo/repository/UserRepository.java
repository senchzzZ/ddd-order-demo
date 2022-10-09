package cn.zsq.ddd.demo.repository;

import cn.zsq.ddd.demo.convertor.UserConvertor;
import cn.zsq.ddd.demo.domain.model.user.User;
import cn.zsq.ddd.demo.domain.infra.repository.IUserRepository;
import cn.zsq.ddd.demo.entity.UserEntity;
import cn.zsq.ddd.demo.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

	@Resource
	private UserMapper userMapper;


	/**
	 * 根据id查找
	 * @param user
	 * @return
	 */
	@Override
	public int addUser(User user) {
		UserEntity entity = UserConvertor.INSTANCT.convertToEntity(user);
		return userMapper.insert(entity);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@Override
	public int updateUser(User user) {
		UserEntity entity = UserConvertor.INSTANCT.convertToEntity(user);
		return userMapper.update(entity);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@Override
	public Optional<User> findById(Long id) {
		return Optional.ofNullable(UserConvertor.INSTANCT.convertToDO(userMapper.findById(id)));
	}

}
