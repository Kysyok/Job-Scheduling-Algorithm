//Veronika Cherkasova
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        cheapestWay(map, N, M);
    }

    public static void cheapestWay(int[][] map, int N, int M) {
        int[][] dp = new int[N][M];
        dp[0][0] = map[0][0];
        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + map[0][j];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + map[i][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = map[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        ArrayList<Integer> path = new ArrayList<>();
        int i = N - 1, j = M - 1;
        while (i > 0 || j > 0) {
            path.add(map[i][j]);
            if (i == 0) {
                j--;
            } else if (j == 0) {
                i--;
            } else {
                if (dp[i - 1][j] < dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        path.add(map[0][0]);
        Collections.reverse(path);
        System.out.println(dp[N - 1][M - 1]);
        for (int c : path) {
            System.out.print(c + " ");
        }
    }
}