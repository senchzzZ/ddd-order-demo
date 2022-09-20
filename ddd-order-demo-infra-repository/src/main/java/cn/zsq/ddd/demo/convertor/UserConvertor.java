package cn.zsq.ddd.demo.convertor;

import cn.zsq.ddd.demo.domain.model.user.User;
import cn.zsq.ddd.demo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhaoshengqi
 */
@Mapper
public interface UserConvertor {
    UserConvertor INSTANCT = Mappers.getMapper(UserConvertor.class);

    @Mapping(source = "id", target = "userId")
    User convertToDO(UserEntity entity);

    @Mapping(source = "userId", target = "id")
    UserEntity convertToEntity(User user);

    List<User> convertToDOList(List<UserEntity> entityList);

    List<UserEntity> convertToEntities(List<User> userList);

}
