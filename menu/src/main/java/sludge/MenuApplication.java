package sludge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sludge.redis.common.CacheKeyGenerator;
import sludge.redis.common.LockKeyGenerator;

/**
 * @Author Giggles
 * @Date 2019-09-26 10:05 AM
 */
@SpringBootApplication
@MapperScan("sludge.repository")
public class MenuApplication {
    public static void main(String[] args) {
        SpringApplication.run(MenuApplication.class, args);
    }

    @Bean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGenerator();
    }
}
