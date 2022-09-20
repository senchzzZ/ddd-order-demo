package cn.zsq.ddd.demo.domain.model.user;


import lombok.Getter;

/**
 * @author zhaoshengqi
 */
public enum UserStateEnum {
    ENABLE(0),
    DISABLE(1);

    @Getter
    private final int value;

    UserStateEnum(int value) {
        this.value = value;
    }
}
