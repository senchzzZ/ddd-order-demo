package cn.zsq.ddd.demo.repository;

import cn.zsq.ddd.demo.convertor.UserConvertor;
import cn.zsq.ddd.demo.domain.model.user.User;
import cn.zsq.ddd.demo.domain.infra.repository.IUserRepository;
import cn.zsq.ddd.demo.entity.UserEntity;
import cn.zsq.ddd.demo.repository.mapper.UserInfoMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

	@Resource
	private UserInfoMapper userInfoMapper;


	/**
	 * 根据id查找
	 * @param user
	 * @return
	 */
	@Override
	public int addUser(User user) {
		UserEntity entity = UserConvertor.INSTANCT.convertToEntity(user);
		return userInfoMapper.insert(entity);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@Override
	public int updateUser(User user) {
		UserEntity entity = UserConvertor.INSTANCT.convertToEntity(user);
		return userInfoMapper.update(entity);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@Override
	public Optional<User> findById(Long id) {
		return Optional.ofNullable(UserConvertor.INSTANCT.convertToDO(userInfoMapper.findById(id)));
	}

}
