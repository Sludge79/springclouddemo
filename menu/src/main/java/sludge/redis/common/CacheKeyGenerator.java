package sludge.redis.common;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author Giggles
 * @Date 2019-10-25 9:42 AM
 */
public interface CacheKeyGenerator {
    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
