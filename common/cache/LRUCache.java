package com.ray.image.utils.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU(Least Recently Used ，最近最少使用)
 * 
 * @author wangfeiyang
 * @version $Id: LRUCache.java, v 0.1 2016年8月3日 下午7:24:21 wangfeiyang Exp $
 */
public class LRUCache<K, V> extends AbstractCacheMap<K, V> {

    @SuppressWarnings("serial")
    public LRUCache(int cacheSize, long defaultExpire) {
        super(cacheSize, defaultExpire);

        
        // linkedHash已经实现LRU算法 是通过双向链表来实现，当某个位置被命中，通过调整链表的指向将该位置调整到头位置，新加入的内容直接放在链表头，如此一来，最近被命中的内容就向链表头移动，需要替换时，链表最后的位置就是最近最少使用的位置
        // 排序模式accessOrder，该属性为boolean型变量，对于访问顺序，为true；对于插入顺序，则为false
        this.cacheMap = new LinkedHashMap<K, CacheObject<K, V>>(cacheSize + 1, 1f, true) {

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, CacheObject<K, V>> eldest) {

                return LRUCache.this.removeEldestEntry(eldest);
            }

        };
    }

    @Override
    protected int eliminateCache() {
        if (!isNeedClearExpiredObject()) {
            return 0;
        }

        Iterator<CacheObject<K, V>> iterator = cacheMap.values().iterator();
        int count = 0;
        while (iterator.hasNext()) {
            CacheObject<K, V> cacheObject = iterator.next();

            if (cacheObject.isExpired()) {
                iterator.remove();
                count++;
            }
        }

        return count;
    }

    private boolean removeEldestEntry(Map.Entry<K, CacheObject<K, V>> eldest) {

        if (cacheSize == 0)
            return false;

        return size() > cacheSize;
    }

}
