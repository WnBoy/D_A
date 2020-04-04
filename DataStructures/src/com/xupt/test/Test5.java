package com.xupt.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wnlife
 * @create 2019-12-17 12:30
 */
public class Test5 {

    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
//        IntSummaryStatistics statistics = list.stream().mapToInt((x) -> x).summaryStatistics();
//        Optional<Integer> reduce = list.stream().reduce(Integer::sum);
//        System.out.println(reduce.get());
//        IntSummaryStatistics collect = list.stream().collect(Collectors.summarizingInt((x) -> x));
//        System.out.println(collect.getSum());

        List<Integer> ageList = Arrays.asList(11, 22, 13, 14, 25, 26);
        ageList.forEach(System.out::print);
        Map<String, List<Integer>> ageGrouyByMap = ageList.stream()
                .collect(Collectors.groupingBy(age -> String.valueOf(age / 10)));

        ageGrouyByMap.forEach((K,V)->{
            System.out.println("年龄" + K + "0多岁的有：" + V);
        });





    }
}
