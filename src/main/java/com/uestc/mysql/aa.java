package com.uestc.mysql;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class aa {

    public static void main(String[] args) {
        var map = Map.of("hello","world");
        String var = map.get("hello");
        System.out.println(var);

        var x = 1;
        var y = 2;
        ConcurrentHashMap map1 = new ConcurrentHashMap();
        map1.put(1,1);
    }

}
