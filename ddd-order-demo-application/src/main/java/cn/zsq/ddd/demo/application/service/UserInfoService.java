package cn.zsq.ddd.demo.application.service;

import cn.zsq.ddd.demo.domain.model.user.User;
import cn.zsq.ddd.demo.shared.err.DemoBusinessException;
import cn.zsq.ddd.demo.application.pojo.cmd.UserCreateCmd;
import cn.zsq.ddd.demo.domain.infra.repository.IUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Optional;

/**
 *
 */
@Service
public class UserInfoService {

    @Resource
    private IUserRepository userInfoRepository;

    @Resource
    private TransactionTemplate transactionTemplate;

    public User createUser(UserCreateCmd userCreateCmd) {
        User user = new User();
        BeanUtils.copyProperties(userCreateCmd, user);

        //事务最小化示例
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                userInfoRepository.addUser(user);
                userInfoRepository.updateUser(user);
            }
        });

        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.initSynchronization();
        }

        return null;
    }

    public User findById(Long id) {
        Optional<User> userOptional = userInfoRepository.findById(id);
        return userOptional.orElseThrow(() -> new DemoBusinessException("user not exist"));
    }

}
