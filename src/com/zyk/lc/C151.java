package com.zyk.lc;

import java.util.Arrays;

public class C151 {
    
    public static String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0 ; i--) {
            if(words[i].length() > 0) {
                sb.append(words[i]).append(' ');
            }
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    public static void main(String[] args) {
        String s = "hello     world";
        System.out.println(Arrays.toString(s.split(" ")));
    }
    
}
