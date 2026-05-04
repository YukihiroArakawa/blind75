import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// 問題: 207. Course Schedule
// アプローチ: トポロジカルソート（Kahn's Algorithm）
//   各コースへの入次数（まだ必要な前提コース数）を数え、
//   入次数が 0 のコースから順に履修して依存関係を取り除いていく。
//   最終的に全コースを処理できれば閉路はなく、履修可能。
//   途中で処理できないコースが残るなら、依存関係に閉路がある。
// 時間計算量: O(V + E)  … V はコース数、E は prerequisite の数
// 空間計算量: O(V + E)  … 隣接リスト、入次数配列、キューを使う

class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> nextCoursesByCourse = new ArrayList<>();
    for (int course = 0; course < numCourses; course++) {
      nextCoursesByCourse.add(new ArrayList<>());
    }

    int[] indegree = new int[numCourses];
    for (int[] prerequisite : prerequisites) {
      int course = prerequisite[0];
      int requiredCourse = prerequisite[1];

      nextCoursesByCourse.get(requiredCourse).add(course);
      indegree[course]++;
    }

    Queue<Integer> availableCourses = new ArrayDeque<>();
    for (int course = 0; course < numCourses; course++) {
      if (indegree[course] == 0) {
        availableCourses.offer(course);
      }
    }

    int completedCourses = 0;
    while (!availableCourses.isEmpty()) {
      int currentCourse = availableCourses.poll();
      completedCourses++;

      for (int nextCourse : nextCoursesByCourse.get(currentCourse)) {
        indegree[nextCourse]--;

        if (indegree[nextCourse] == 0) {
          availableCourses.offer(nextCourse);
        }
      }
    }

    return completedCourses == numCourses;
  }
}
