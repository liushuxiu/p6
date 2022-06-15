package com.uestc.collection;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FlatMapTest {

    public static void main(String[] args) {


        demo1();
        
        demo2();


        String[] arrayOfWords = {"Hello", "World"};

        List<String> words = new ArrayList<>();
        words.addAll(Arrays.asList(arrayOfWords));


        List<String> uniqueCharacters = words.stream()
                        .map(w -> w.split("")) //将每个单词转换为由其字母构成的数组
                        .flatMap(Arrays::stream) //将各个生成流扁平化为单个流
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println(uniqueCharacters);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<String> pairs = numbers1.stream()
                .flatMap(
                    i -> numbers2.stream().
                    map(j -> new String("("+i + "," + j + ")"))
                )
                .collect(toList());
        System.out.println(pairs);


        testDescardMap();
    }

    private static void demo2() {
        // 例二
        List<List<Map<String,Object>>> lists = ImmutableList.of(
                ImmutableList.of(
                        ImmutableMap.of("a", 1, "b", 2), ImmutableMap.of("a", 2, "b", 3)
                ),
                ImmutableList.of(
                        ImmutableMap.of("a", 3, "b", 4), ImmutableMap.of("a", 4, "b", 5)
                ),
                ImmutableList.of(
                        ImmutableMap.of("a", 5, "b", 6), ImmutableMap.of("a", 6, "b", 7)
                )
        );
// 将多个list合并为一个list
        List<Map<String,Object>> list = lists.stream().flatMap(Collection::stream).collect(Collectors.toList());// [{a=1, b=2}, {a=2, b=3}, {a=3, b=4}, {a=4, b=5}, {a=5, b=6}, {a=6, b=7}]

        System.out.println(list);

    }

    private static void demo1() {
        // 例一
        List<Integer> list = ImmutableList.of(1, 3, 5);
        list = list.stream().flatMap(l -> {
            List<Integer> list1 = new ArrayList<>();
            list1.add(l + 1);
            list1.add(l + 2);
            return list1.stream();
        }).collect(Collectors.toList());
        System.out.println(list);// [2, 3, 4, 5, 6, 7]
    }

    public static void testDescardMap(){
        Map<String, Object> colormap = new HashMap<>();
        colormap.put("id","1");
        colormap.put("name","红色");
        Map<String, Object> colormap1 = new HashMap<>();
        colormap1.put("id","2");
        colormap1.put("name","黑色");
        Map<String, Object> colormap2 = new HashMap<>();
        colormap2.put("id","3");
        colormap2.put("name","金色");
        List<Map> colorList = Arrays.asList(colormap, colormap1, colormap2);

        Map<String, Object> sizemap = new HashMap<>();
        sizemap.put("ml","750ml");
        sizemap.put("size","32G");
        Map<String, Object> sizemap1 = new HashMap<>();
        sizemap1.put("ml","400ml");
        sizemap1.put("size","64G");
        List<Map> sizeList = Arrays.asList(sizemap, sizemap1);

        Map<String, Object> placemap = new HashMap<>();
        placemap.put("place","国产");
        Map<String, Object> placemap1 = new HashMap<>();
        placemap1.put("place","进口");
        List<Map> placeList = Arrays.asList(placemap, placemap1);
        //对颜色、大小、产地三个集合进行笛卡尔积组合
        List<Map> descartesList = descartesMap(colorList, sizeList, placeList);
        descartesList.forEach(System.out::println);
    }


    public static List<Map> descartesMap(List<Map>... lists) {
        List<Map> tempList = new ArrayList<>();
        Integer i = 0 ;
        for (List<Map> list : lists) {
            if (tempList.isEmpty()) {
                tempList = list;
            } else {
                System.out.println("要组合的list，第"+i+"次组合"+ JSON.toJSONString(tempList));
                System.out.println("==============第"+i+"次组合结果===============");
                //java8新特性，stream流的flatMap ，可以对list集合做并集、交集、差集
                tempList = tempList.stream().flatMap(item -> list.stream().map(item2 -> {
                    Map<String, Object> map = new HashMap<>();
                    StringBuffer strKeysBuf = new StringBuffer();
                    StringBuffer strValsBuf = new StringBuffer();
                    item.forEach((k,v)->{
                        //key值以逗号拼接
                        strKeysBuf.append(k).append(",");
                        //value以空格拼接
                        strValsBuf.append(v).append(" ");
                    });
                    item2.forEach((k,v)->{
                        strKeysBuf.append(k).append(",");
                        strValsBuf.append(v).append(" ");
                    });
                    //去掉最后一个逗号和空格
                    String strKeys = strKeysBuf.deleteCharAt(strKeysBuf.length() - 1).toString();
                    String strVals = strValsBuf.deleteCharAt(strValsBuf.length() - 1).toString();
                    map.put(strKeys,strVals);
                    System.out.println(JSON.toJSONString(map));
                    return map;
                })).collect(toList());
            }
            i++;
        }
        return tempList;
    }
}

/*
要组合的list，第1次组合[{"name":"红色","id":"1"},{"name":"黑色","id":"2"},{"name":"金色","id":"3"}]
==============第1次组合结果===============
{"name,id,size,ml":"红色 1 32G 750ml"}
{"name,id,size,ml":"红色 1 64G 400ml"}
{"name,id,size,ml":"黑色 2 32G 750ml"}
{"name,id,size,ml":"黑色 2 64G 400ml"}
{"name,id,size,ml":"金色 3 32G 750ml"}
{"name,id,size,ml":"金色 3 64G 400ml"}
要组合的list，第2次组合[{"name,id,size,ml":"红色 1 32G 750ml"},{"name,id,size,ml":"红色 1 64G 400ml"},{"name,id,size,ml":"黑色 2 32G 750ml"},{"name,id,size,ml":"黑色 2 64G 400ml"},{"name,id,size,ml":"金色 3 32G 750ml"},{"name,id,size,ml":"金色 3 64G 400ml"}]
==============第2次组合结果===============
{"name,id,size,ml,place":"红色 1 32G 750ml 国产"}
{"name,id,size,ml,place":"红色 1 32G 750ml 进口"}
{"name,id,size,ml,place":"红色 1 64G 400ml 国产"}
{"name,id,size,ml,place":"红色 1 64G 400ml 进口"}
{"name,id,size,ml,place":"黑色 2 32G 750ml 国产"}
{"name,id,size,ml,place":"黑色 2 32G 750ml 进口"}
{"name,id,size,ml,place":"黑色 2 64G 400ml 国产"}
{"name,id,size,ml,place":"黑色 2 64G 400ml 进口"}
{"name,id,size,ml,place":"金色 3 32G 750ml 国产"}
{"name,id,size,ml,place":"金色 3 32G 750ml 进口"}
{"name,id,size,ml,place":"金色 3 64G 400ml 国产"}
{"name,id,size,ml,place":"金色 3 64G 400ml 进口"}
==============最终结果=============
{name,id,size,ml,place=红色 1 32G 750ml 国产}
{name,id,size,ml,place=红色 1 32G 750ml 进口}
{name,id,size,ml,place=红色 1 64G 400ml 国产}
{name,id,size,ml,place=红色 1 64G 400ml 进口}
{name,id,size,ml,place=黑色 2 32G 750ml 国产}
{name,id,size,ml,place=黑色 2 32G 750ml 进口}
{name,id,size,ml,place=黑色 2 64G 400ml 国产}
{name,id,size,ml,place=黑色 2 64G 400ml 进口}
{name,id,size,ml,place=金色 3 32G 750ml 国产}
{name,id,size,ml,place=金色 3 32G 750ml 进口}
{name,id,size,ml,place=金色 3 64G 400ml 国产}
{name,id,size,ml,place=金色 3 64G 400ml 进口}
 */
