import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Job> jobs = new ArrayList<>();
        int[] S = new int[N];
        int[] F = new int[N];
        int[] P = new int[N];

        for (int i = 0; i < N; i++) {
            S[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            F[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            P[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            jobs.add(new Job(S[i], F[i], P[i]));
        }
        InsertionSort(jobs);
        System.out.println(maxProfit(jobs));
    }

    private static void InsertionSort(ArrayList<Job> jobs) {
        int n = jobs.size();
        for (int i = 1; i < n; ++i) {
            Job key = jobs.get(i);
            int j = i - 1;
            while (j >= 0 && jobs.get(j).finish > key.finish) {
                jobs.set(j + 1, jobs.get(j));
                j = j - 1;
            }
            jobs.set(j + 1, key);
        }
    }

    static int maxProfit(ArrayList<Job> jobs) {
        int n = jobs.size();
        int[] dp = new int[n];
        dp[0] = jobs.get(0).profit;

        for (int i = 1; i < n; i++) {
            int profit = jobs.get(i).profit;
            int isOverlappingIndex = isOverlapping(jobs, i);
            if (isOverlappingIndex != -1) {
                profit += dp[isOverlappingIndex];
            }
            int profitOv = dp[i - 1];
            dp[i] = Math.max(profit, profitOv);
        }
        return dp[n - 1];
    }
    private static int isOverlapping(ArrayList<Job> jobs, int index) {
        int begin = 0, end = index - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (jobs.get(mid).finish <= jobs.get(index).start) {
                if (jobs.get(mid + 1).finish <= jobs.get(index).start) {
                    begin = mid + 1;
                } else {
                    return mid;
                }
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
class Job {
    int start;
    int profit;
    int finish;
    Job(int start, int finish, int profit) {
        this.start = start;
        this.profit = profit;
        this.finish = finish;
    }
}
