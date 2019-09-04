package com.example.demo.util;

import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Java8Stream {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("111");
        list1.add("222");
        list1.add("333");
        list1.add("555");
        list1.add("666");

        List<String> list2 = new ArrayList<String>();
        list2.add("222");
        list2.add("333");
        list2.add("777");
        list2.add("888");
        List<User> list3 = new ArrayList<User>();
        User user = new User();
        user.setId(11);
        user.setInfo("22");
        user.setName("33");
        list3.add(user);
        List<User> list4 = new ArrayList<User>();
        User user1 = new User();
        user1.setId(22);
        user1.setInfo("22");
        user1.setName("33");
        list4.add(user1);
        //两个集合比较 如果ID相等我就认为这俩相等 注：与contains不同  如果非要用contains方式实现 可以重写equals、hashcode的方式实现
        list4 = list4.stream().filter(
                item -> list3.stream().allMatch(
                        item2 -> !item.getId().equals(item2.getId())))
                .collect(Collectors.toList());
        list4.parallelStream().forEach(System.out::println);
        // 取两个集合交集
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(toList());
        System.out.println("---交集 intersection---");
        intersection.parallelStream().forEach(System.out::println);
        // 取两个集合差集 (list1 - list2)
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        System.out.println("---差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out::println);

        // 取两个集合差集 (list2 - list1)
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
        System.out.println("---差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out::println);

        // 取两个集合并集
        List<String> listAll = list1.parallelStream().collect(toList());
        List<String> listAll2 = list2.parallelStream().collect(toList());
        listAll.addAll(listAll2);
        System.out.println("---并集 listAll---");
        listAll.parallelStream().forEachOrdered(System.out::println);

        // 取两个集合去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
        System.out.println("---得到去重并集 listAllDistinct---");
        listAllDistinct.parallelStream().forEachOrdered(System.out::println);

        System.out.println("---原来的List1---");
        list1.parallelStream().forEachOrdered(System.out::println);
        System.out.println("---原来的List2---");
        list2.parallelStream().forEachOrdered(System.out::println);

    }
}

