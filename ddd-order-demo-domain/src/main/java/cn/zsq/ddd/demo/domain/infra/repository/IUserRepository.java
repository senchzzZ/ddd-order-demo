package cn.zsq.ddd.demo.domain.infra.repository;

import cn.zsq.ddd.demo.domain.model.user.User;

import java.util.Optional;

/**
 * @author zhaoshengqi
 */
public interface IUserRepository {
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(User user);


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Optional<User> findById(Long id);

}
