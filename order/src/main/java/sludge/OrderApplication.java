package sludge;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author Giggles
 * @Date 2019-09-26 9:43 AM
 */
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

    @Value("${spring.redis.host}")
    private String RHOST;

    @Value("${spring.redis.port}")
    private String RPORT;

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://"+RHOST+":"+RPORT).setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
