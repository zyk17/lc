package com.zyk.lc;

import java.util.*;

public class C127 {

    public static int ladderLength(String begin, String end, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        wordSet.add(begin);
        HashMap<String, ArrayList<String>> nexts = getNexts(wordSet);       // next表
        HashMap<String, Integer> distanceMap = new HashMap<>();             // 当前字符串的距离，因为是用的队列没法只用一个变量记录
        distanceMap.put(begin, 1);

        HashSet<String> visited = new HashSet<>();                          // 是否访问过， 不做重复访问
        Queue<String> queue = new LinkedList<>();                           // 队列
        queue.add(begin);
        while( !queue.isEmpty() ) {
            String cur = queue.poll();
            int distance = distanceMap.get(cur);
            for (String next : nexts.get(cur)) {
                if( next.equals(end) ) {
                    return distance + 1;
                }
                if( !visited.contains( next ) ) {
                    distanceMap.put(next, distance + 1);
                    queue.add(next);
                    visited.add(next);
                }
            }
        }
        return 0;
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

    public static int ladderLength2(String begin, String end, List<String> wordList) {
        HashSet<String> startSet = new HashSet<>(),         // next 分支较少的一端
                endSet = new HashSet<>(),                   // next 分支较多的一端
                visited = new HashSet<>(),                  // 是否访问过当前字符串
                wordSet = new HashSet<>(wordList);          // 允许访问的字符串
        startSet.add(begin);
        wordSet.add(begin);
        endSet.add(end);
        if(!wordSet.contains(end)) {
            return 0;
        }
        // len 当前走过的距离
        for (int len = 2; !startSet.isEmpty(); len++) {
            // 当前路走过之后， 产生的 next 路们
            HashSet<String> tempSet = new HashSet<>();
            for (String nextStr : startSet) {
                char[] next = nextStr.toCharArray();
                for (int i = 0; i < next.length; i++) {     // 尝试， 每个位置
                    char tempChar = next[i];
                    for (char c = 'a'; c <= 'z'; c++) {     // 尝试， 每种字符
                        if(c != tempChar ) {                // 不做自己
                            next[i] = c;
                            String temp = new String(next);
                            if(endSet.contains(temp)) {     // 双向碰上了
                                return len;
                            }
                            // 可以走， 同时没走过
                            if(wordSet.contains(temp) && !visited.contains(temp)) {
                                visited.add(temp);
                                tempSet.add(temp);
                            }
                        }
                    }
                    next[i] = tempChar;
                }
            }
            // startSet = 短的， endSet = 长的
            startSet = (endSet.size() < tempSet.size()) ? endSet : tempSet;
            endSet = (endSet == startSet) ? tempSet : endSet;
        }
        return 0;
    }


    // for test
    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(ladderLength( beginWord, endWord, wordList ));
        System.out.println(ladderLength2( beginWord, endWord, wordList ));

    }

}
