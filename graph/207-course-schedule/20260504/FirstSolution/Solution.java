import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
  // 次数が全て0になれば、全て受講可能.
  // このコースを受けたらこのコースの次数を下げるみたいなアプローチを取れればOK
  // prerequisites [コース][前提となるコース]
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    // コースの隣接リスト, 前提コース, コース
    List<List<Integer>> nextCoursesByCourse = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      nextCoursesByCourse.add(new ArrayList<>());
    }

    // コースを受講するために必要なコース数、次数
    int[] indegree = new int[numCourses];

    for (int[] pre : prerequisites) {
      int course = pre[0];
      int prerequisite = pre[1];

      // このコースを受ければこのコースを受けられる
      nextCoursesByCourse.get(prerequisite).add(course);
      // コースを受講するために必要な次数をインクリメント設定
      indegree[course]++;
    }

    // 受講可能なコースをキューで管理
    Queue<Integer> availableCourses = new ArrayDeque();
    for (int i = 0; i < indegree.length; i++) {
      if (indegree[i] == 0) {
        availableCourses.offer(i);
      }
    }

    // 受講可能なコースをpollしては、次数を下げる・受講可能なコースをキューに詰めるを繰り返す
    int completed = 0;
    while (!availableCourses.isEmpty()) {
      completed++;
      int availableCourse = availableCourses.poll();

      // 前提となるコースが受講完了したとして、次数を下げる
      List<Integer> courses = nextCoursesByCourse.get(availableCourse);
      for (Integer course : courses) {
        indegree[course]--;

        // 受講可能になったらキューに詰める
        if (indegree[course] == 0) {
          availableCourses.offer(course);
        }
      }
    }

    // 完了数とコース数が一致していたら全部受講できたとしてOK
    // 全て消化したらOK、キューがなくなったが消化できなかった場合は受講できないものがあったためNG
    return completed == numCourses;
  }
}
