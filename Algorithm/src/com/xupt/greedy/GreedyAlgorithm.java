package com.xupt.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Wnlife
 * @create 2020-02-04 16:51
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        //创建一个map，保存每个广播电台和对应的覆盖地区
        Map<String, HashSet<String>> broadcast = new HashMap<>(16);
        //存储第一个电台对应的覆盖区域
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        //存储第二个电台对应的覆盖区域
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        //存储第三个电台对应的覆盖区域
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        //存储第四个电台对应的覆盖区域
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        //存储第五个电台对应的覆盖区域
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入到map
        broadcast.put("k1", hashSet1);
        broadcast.put("k2", hashSet2);
        broadcast.put("k3", hashSet3);
        broadcast.put("k4", hashSet4);
        broadcast.put("k5", hashSet5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //存放一个ArrayList存放选择的电台
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义给maxKey ， 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果maxKey 不为null , 则会加入到 selects
        String maxKey = null;
        // 如果allAreas 不为0, 则表示还没有覆盖到所有的地区
        while (allAreas.size() != 0) {
            //每次循环前，maxKey清空
            maxKey = null;
            //遍历 broadcasts, 取出对应key
            for (String key : broadcast.keySet()) {
                //清空tempSet
                tempSet.clear();
                //取出当前电台对应的覆盖区域
                tempSet.addAll(broadcast.get(key));
                //当前电台覆盖的区域 和 所有未被覆盖区域的交集
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多,就需要重置maxKey
                // tempSet.size() >broadcasts.get(maxKey).size()) 体现出贪心算法的特点,每次都选择最优的
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcast.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            //maxKey != null, 就应该将maxKey 加入selects
            if(maxKey!=null){
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区，从 allAreas 去掉
                allAreas.removeAll(broadcast.get(maxKey));
            }
        }
        //[K1,K2,K3,K5]
        System.out.println("得到的选择结果是" + selects);
    }
}
