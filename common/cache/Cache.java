package com.ray.image.utils.cache;

/**
 * 缓存接口
 * 
 * @author wangfeiyang
 * @version $Id: Cache.java, v 0.1 2016年8月3日 下午6:55:29 wangfeiyang Exp $
 */
public interface Cache<K,V> {
    
    /**
     * 返回当前缓存大小
     * 
     * @return
     */
    int size();
    
    
    /**
     * 返回默认存活时间
     * 
     * @return
     */
    long getDefaultExpire();
    
    /**
     * 向缓存添加value对象,其在缓存中生存时间为默认值
     * 
     * @param key
     * @param value
     */
    void put(K key, V value);
    
    /**
     * 
     * 向缓存添加value对象,并指定存活时间
     * 
     * @param key
     * @param value
     * @param expire
     */
    void put(K key, V value, long expire );
    
    
    /**
     * 查找缓存对象
     * 
     * @param key
     * @return
     */
    V get(K key);
    
    
    /**
     * 淘汰对象
     * 
     * @return
     */
    int eliminate();
    
    /**
     * 缓存是否已满
     * 
     * @return
     */
    boolean isFull();
    
    /**
     * 删除缓存对象
     * 
     * @param key
     */
    void remove(K key);
    
    /**
     * 清楚缓存
     */
    void clear();
    
    
   /**
    * 缓存中是否为空
    * 
    * @return
    */
    boolean isEmpty();
    
    
    

}
