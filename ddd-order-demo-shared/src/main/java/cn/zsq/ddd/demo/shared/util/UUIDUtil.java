package cn.zsq.ddd.demo.shared.util;

import java.util.UUID;

public class UUIDUtil {

    private UUIDUtil(){}
	
    public static final String getTraceId(){
        return  UUID.randomUUID().toString().replace("-", "");
    }
    public static final String getUUID(){
        return  UUID.randomUUID().toString();
    }
    
}
