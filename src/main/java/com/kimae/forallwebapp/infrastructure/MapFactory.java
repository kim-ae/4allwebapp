package com.kimae.forallwebapp.infrastructure;

import java.util.HashMap;
import java.util.Map;

public class MapFactory {
    static public <K, V> Map<K,V> createOf(K key, V value){
        Map<K, V> map = new HashMap<K, V>();
        map.put(key, value);
        return map;
    }
    
    static public <K, V> Map<K,V> createOf(K key, V value,K key1, V value1){
        Map<K, V> map = new HashMap<K, V>();
        map.put(key, value);
        map.put(key1, value1);
        return map;
    }
    
    static public <K, V> Map<K,V> createOf(K key, V value,K key1, V value1,K key2, V value2){
        Map<K, V> map = new HashMap<K, V>();
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        return map;
    }
    
    public static class InitMap<K,V>{
        private Map<K, V> map;
        
        private InitMap(final Map<K, V> map){
            this.map = (Map<K, V>) map;
        }
        
        public static <K, V> InitMap<K, V> with(final Map<K, V> map){
            if(map == null){
                throw new NullPointerException("map cannot be null");
            }
            return new InitMap<K, V>(map);
        }
        
        public InitMap<K, V> put(K key, V value){
            this.map.put(key, value);
            return this;
        }
        
        public Map<K,V> toMap(){ return map; }
    }
}
