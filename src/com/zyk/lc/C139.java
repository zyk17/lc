package com.zyk.lc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class C139 {

    // 暴力dfs，超时
    public static boolean wordBreak(String s, List<String> wordDict) {
        char[] str = s.toCharArray();
        return process(str, 0, new StringBuilder(), new HashSet<>(wordDict));
    }

    public static boolean process(char[] str, int i, StringBuilder path, Set<String> dict) {
        if (i == str.length) {
            return dict.contains(path.toString());
        }
        path.append(str[i]);
        // p1: 尝试在这里断开
        boolean p1 = false;
        if (dict.contains(path.toString())) {
            p1 = process(str, i + 1, new StringBuilder(), dict);
        }
        // p2: 继续往后尝试
        boolean p2 = process(str, i + 1, path, dict);
        return p1 || p2;
    }


    public static class Node {
        public Node[] nodes;
        public boolean end;

        public Node() {
            nodes = new Node[26];
        }
    }

    static Node root;

    static void insert(String word) {
        char[] chars = word.toCharArray();
        Node node = root;
        int path;
        for (char c : chars) {
            path = c - 'a';
            if (node.nodes[path] == null) {
                node.nodes[path] = new Node();
            }
            node = node.nodes[path];
        }
        node.end = true;
    }

    // str 从 start ~ end 是否在前缀树中存在
    static boolean search(char[] str, int start, int end) {
        int path;
        Node node = root;
        for (int i = start; i <= end; i++) {
            path = str[i] - 'a';
            if(node.nodes[path] == null) {
                return false;
            }else {
                node = node.nodes[path];
            }
        }
        return node.end;
    }

    // 前缀树优化
    public static boolean wordBreak2(String s, List<String> wordDict) {
        root = new Node();
        for (String word : wordDict) {
            insert(word);
        }
        int N = s.length();
        // dp 以dp[i~end] 为前缀（在词典中存在），后续能否组成。
        // dp[i] 的值依赖 i~end是有效的 && dp[end+1] 是有效的。
        boolean[] dp = new boolean[N+1];
        dp[N] = true;
        char[] str = s.toCharArray();
        for(int i = N-1; i >= 0; i--) {
            for (int end = i; end < N; end++) {
                if(search(str, i, end)) {
                    dp[i] |= dp[end+1];
                }
                if(dp[i]) break;    // 可以组成
            }
        }
        return dp[0];
    }


    // for test
    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = List.of("leet", "code");
        System.out.println(wordBreak(s, wordDict));
        System.out.println(wordBreak2(s, wordDict));
    }

}
