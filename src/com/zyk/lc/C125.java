package com.zyk.lc;

public class C125 {

    public static boolean isPalindrome(String s) {
        char[] str = s.toCharArray();
        int l = 0;
        int r = str.length-1;
        while ( l < r ) {
            while( l < r && !isValid(str[l]) ) {
                l++;
            }
            while( l < r && !isValid(str[r]) ) {
                r--;
            }
            if(l == r) {
                break;
            }
            if(str[l] != str[r]) {
                if( Character.toLowerCase(str[l]) != Character.toLowerCase(str[r]) ) {
                    return false;
                }
            }
            l++;
            r--;
        }
        return true;
    }

    public static boolean isValid(char c) {
        return ( c >= '0' && c <= '9' ) ||
                ( c >= 'a' && c <= 'z' ) ||
                ( c >= 'A' && c <= 'Z' );
    }

    public static void main(String[] args) {
//        System.out.println((int) 'a');
//        System.out.println((int) 'z');
//        System.out.println((int) 'A');
//        System.out.println((int) 'Z');
//        System.out.println((int) '0');
//        System.out.println((int) '9');

        String s = "0P";
        System.out.println(isPalindrome(s));

    }

}
