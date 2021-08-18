package com.zyk.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class C210 {

    public static class Course {
        int no;
        int in;
        ArrayList<Course> nexts;
        Course(int no) {
            this.no = no;
            nexts = new ArrayList<>();
        }
    }

    // 拓扑排序
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        if(prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                res[i] = i;
            }
            return res;
        }
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < courses.length; i++) {
            courses[i] = new Course(i);
        }
        for (int[] ints : prerequisites) {
            int to = ints[0];
            int from = ints[1];
            courses[from].nexts.add(courses[to]);
            courses[to].in++;
        }

        int need = 0;
        Queue<Course> q = new LinkedList<>();
        for (Course cours : courses) {
            if(cours != null) {
                if(cours.in == 0) {
                    q.add(cours);
                }
                need++;
            }
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            Course c = q.poll();
            res[cnt++] = c.no;
            for (Course next : c.nexts) {
                if(--next.in == 0) {
                    q.add(next);
                }
            }
        }
        return cnt == need ? res : new int[]{};
    }


    // 拓扑排序实现2。代码优化
    public static int[] findOrder2(int courses, int[][] relation) {
        int[] res = new int[courses];
        if(relation.length == 0) {
            for (int i = 0; i < courses; i++) {
                res[i] = i;
            }
            return res;
        }
        ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();
        for (int i = 0; i < courses; i++) {
            nexts.add(new ArrayList<>());
        }
        int[] in = new int[courses];
        for (int[] arr : relation) {
            nexts.get(arr[1]).add(arr[0]);
            in[arr[0]]++;
        }

        // 队列
        int[] zero = new int[courses];
        int l = 0;
        int r = 0;
        for (int i = 0; i < courses; i++) {
            if (in[i] == 0) {
                zero[r++] = i;
            }
        }
        int count = 0;
        while (l != r) {
            count++; // zero[l] 出队列   l++
            res[count] = zero[l];
            for (int next : nexts.get(zero[l++])) {
                if (--in[next] == 0) {
                    zero[r++] = next;
                }
            }
        }
        return count == nexts.size() ? res : new int[]{};
    }

    // for test
    public static void main(String[] args) {
        int courses = 3;
        int[][] relation = {
                {1, 0}
        };
        System.out.println(Arrays.toString(findOrder(courses, relation)));

    }

}
