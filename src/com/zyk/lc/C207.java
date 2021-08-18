package com.zyk.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class C207 {

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
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Course[] courses = new Course[numCourses];
        for (int[] ints : prerequisites) {
            int to = ints[0];
            int from = ints[1];
            if(courses[to] == null) {
                courses[to] = new Course(to);
            }
            if(courses[from] == null) {
                courses[from] = new Course(from);
            }
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
            cnt++;
            for (Course next : c.nexts) {
                if(--next.in == 0) {
                    q.add(next);
                }
            }
        }
        return cnt == need;
    }


    // 拓扑排序实现2。代码优化
    public static boolean canFinish2(int courses, int[][] relation) {
        if (relation == null || relation.length == 0) {
            return true;
        }
        // 3 :  0 1 2
        // nexts :   0   {}
        //           1   {}
        //           2   {}
        //           3   {0,1,2}
        ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();
        for (int i = 0; i < courses; i++) {
            nexts.add(new ArrayList<>());
        }
        // 3 入度 1  in[3] == 1
        int[] in = new int[courses];
        for (int[] arr : relation) {
            // arr[1] from   arr[0] to
            nexts.get(arr[1]).add(arr[0]);
            in[arr[0]]++;
        }

        // 队列
        int[] zero = new int[courses];
        // 该队列有效范围是[l,r)
        // 新来的数，放哪？r位置，r++
        // 出队列的数，从哪拿？l位置，l++
        // l == r 队列无元素  l < r 队列有元素
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
            for (int next : nexts.get(zero[l++])) {
                if (--in[next] == 0) {
                    zero[r++] = next;
                }
            }
        }
        return count == nexts.size();
    }


}
