package cn.zsq.ddd.demo.shared.configuration;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

/**
 * @author zhaoshengqi
 */
@Configuration
public class EventBusConfig {
    @Bean
    public EventBus eventBus(){
        return new AsyncEventBus(Executors.newCachedThreadPool());
    }
}

