package com.zyk.lc;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class C150 {

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if ("+".equals(token)) {
                stack.push(stack.pop() + stack.pop());
            } else if ("-".equals(token)) {
                stack.push(stack.pop() - stack.pop());
            } else if ("*".equals(token)) {
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(token)) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push( num1 / num2 );
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static int evalRPN2(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();

        for (String token : tokens) {
            if ("+".equals(token)) {
                stack.addLast(stack.pollLast() + stack.pollLast());
            } else if ("-".equals(token)) {
                stack.addLast(-(stack.pollLast() - stack.pollLast()));
            } else if ("*".equals(token)) {
                stack.addLast(stack.pollLast() * stack.pollLast());
            } else if ("/".equals(token)) {
                int num2 = stack.pollLast();
                int num1 = stack.pollLast();
                stack.addLast( num1 / num2 );
            } else {
                stack.addLast(Integer.parseInt(token));
            }
        }
        return stack.pollLast();
    }

    public static void main(String[] args) {

    }

}
