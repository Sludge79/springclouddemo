package sludge.redis.annotation;

import java.lang.annotation.*;

/**
 * @Author Giggles
 * @Date 2019-10-25 9:41 AM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {
    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}
