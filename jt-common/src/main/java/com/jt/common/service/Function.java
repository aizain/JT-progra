package com.jt.common.service;

/**
 * 封装了一下ShardedJedis
 * @author zain
 * 16/11/09
 * @param <ShardedJedis>
 * @param <T>
 */
public abstract class Function<ShardedJedis, T> {
    public abstract T execute(ShardedJedis obj);
}
