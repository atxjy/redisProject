package com.TBT.utils;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author xjy
 * @create 2023-05-05 10:07
 */
public class JedisUtil {

    private static Jedis jedis = null;

    static {
        jedis = new Jedis("localhost",6379);
    }

    public static Jedis getJedis(){
        return jedis;
    }

    /**
     * String放
     * @param key
     * @param value
     */
    public static void set(String key,String value){
        jedis.set(key, value);
    }

    /**
     * String取
     * @param key
     * @return
     */
    public static String get(String key){
        return jedis.get(key);
    }

    /**
     * 将指定的key的值设为newValue，返回旧址oldValue
     * @param key
     * @param newValue
     * @return
     */
    public static String  getSet(String key,String newValue){
        return jedis.getSet(key, newValue);
    }

    /**
     * 获取多个key的value
     * @param keys
     * @return
     */
    public static List<String> mget(String...keys){
        return jedis.mget(keys);
    }

    /**
     * String放，并设置有效时间
     * @param key
     * @param seconds 单位s
     * @param value
     */
    public static void setex(String key,long seconds,String value){
        jedis.setex(key,seconds,value);
    }

    /**
     * 返回String中key的value的长度
     * @param key
     * @return
     */
    public static long strLen(String key){
        Long length = jedis.strlen(key);
        return length;
    }

    /**
     * String同时设置多个key-value
     * @param keysAndValues
     */
    public static void mset(String...keysAndValues){
        if(keysAndValues.length % 2 == 0){
            jedis.mset(keysAndValues);
        }else {
            System.out.println("参数异常");
        }
    }

    /**
     * String同时设置多个key-value，当且仅当所有key不存在时
     * @param keysAndValues
     */
    public static void msetnx(String...keysAndValues){
        jedis.msetnx(keysAndValues);
    }

    /**
     * String中将key存储的数字值加1
     * @param key
     * @return key的新值
     */
    public static long incr(String key){
        return jedis.incr(key);
    }

    /**
     * 将key存储的值加上给定的增量increment
     * @param key
     * @param increment
     * @return key的新值
     */
    public static long incrby(String key,long increment){
        return jedis.incrBy(key, increment);
    }

    /**
     * String中将key存储的数字值减1
     * @param key
     * @return key的新值
     */
    public static long decr(String key){
        return jedis.decr(key);
    }

    /**
     * 在list左侧放入值
     * @param key
     * @param values
     * @return push操作后列表内元素的个数。
     */
    public static long lpush(String key,String...values){
        return jedis.lpush(key, values);
    }

    /**
     * 在list右侧放入值
     * @param key
     * @param values
     * @return push操作后列表内元素的个数。
     */
    public static long rpush(String key,String...values){
        return jedis.rpush(key, values);
    }

    /**
     *list 移除并获取列表中第一个元素
     * @param key
     * @return 左侧第一个元素
     */
    public static String lpop(String key){
        String value = jedis.lpop(key);
        return value;
    }

    /**
     *list 移除并获取列表中最后一个元素
     * @param key
     * @return 右侧第一个元素
     */
    public static String rpop(String key){
        String value = jedis.rpop(key);
        return value;
    }

    /**
     * list 通过索引获取list中的值
     * @param key
     * @param index
     * @return
     */
    public static String index(String key,long index){
        String value = jedis.lindex(key, index);
        return value;
    }

    /**
     * list 获取list中指定范围的元素
     * @param key
     * @param start
     * @param stop
     * @return
     */
    public static List<String> lrange(String key,long start,long stop){
        List<String> values = jedis.lrange(key, start, stop);
        return values;
    }

    /**
     * list 获取list的长度
     * @param key
     * @return
     */
    public static long llen(String key){
        return jedis.llen(key);
    }

    /**
     * 删除count个值等于value的元素
     * @param key
     * @param count
     * @param value
     */
    public static void lrem(String key,long count,String value){
        jedis.lrem(key,count,value);
    }

    /**
     * 截取指定范围的值后再赋值给key
     * @param key
     * @param startindex
     * @param endindex
     */
    public static void ltrim(String key,long startindex,long endindex){
        jedis.ltrim(key, startindex, endindex);
    }

}
