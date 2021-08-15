package com.zyk.lc;

import java.util.*;

public class C126 {

    // 未完
    public static List<List<String>> findLadders(String begin, String end, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(end)) return new ArrayList<>();
        wordSet.add(begin);
        HashMap<String, ArrayList<String>> nexts = getNexts(wordSet);       // next表
        HashMap<String, Integer> distanceMap = new HashMap<>();             // 当前字符串的距离，因为是用的队列没法只用一个变量记录
        distanceMap.put(begin, 1);

        HashSet<String> visited = new HashSet<>();                          // 是否访问过， 不做重复访问
        Queue<String> queue = new LinkedList<>();                           // 队列
        queue.add(begin);

        List<List<String>> res = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        while( !queue.isEmpty() ) {
            String cur = queue.poll();
            path.add(cur);
            int distance = distanceMap.get(cur);
            for (String next : nexts.get(cur)) {
                if( next.equals(end) ) {
                    ArrayList<String> temp = new ArrayList<>(path);
                    temp.add(end);
                    res.add(temp);
                    continue;
                }
                if( !visited.contains( next ) ) {
                    distanceMap.put(next, distance + 1);
                    queue.add(next);
                    visited.add(next);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (List<String> re : res) {
            min = Math.min(re.size(), min);
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> re : res) {
            if(re.size() == min) {
                ans.add(re);
            }
        }
        return ans;
    }

    // 获得每个字符串只改变一个字符能走的路
    private static HashMap<String, ArrayList<String>> getNexts(HashSet<String> wordSet) {
        HashMap<String, ArrayList<String>> res = new HashMap<>();
        for (String s : wordSet) {
            ArrayList<String> nexts = new ArrayList<>();
            char[] str = s.toCharArray();
            for (int i = 0; i < str.length; i++) {
                char originChar = str[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (originChar != c) {     // 不做重复尝试
                        str[i] = c;
                        String tempStr = new String(str);
                        if (wordSet.contains(tempStr)) {
                            nexts.add(tempStr);
                        }
                    }
                }
                str[i] = originChar;
            }
            res.put(s, nexts);
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(findLadders( beginWord, endWord, wordList ));
    }

}
