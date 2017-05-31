package com.feagle.exception;

/**
 * 重复秒杀异常，是一个运行期异常，不需要我们手动try catch
 * Spring声明式事务只收接运行期异常,如果抛出编译期异常,spring事务不会帮我们做事务回滚.
 * Created by feagle on 2017/5/27.
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
