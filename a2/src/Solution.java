import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class Solution {

    static int R, B, C;
    static int[][] CS;

    static long MIN;


    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
    public static void main1(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.valueOf(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");

            R = Integer.parseInt(s[0]);
            B = Integer.parseInt(s[1]);
            C = Integer.parseInt(s[2]);
            MIN = Integer.MAX_VALUE;

            CS = new int[C][3];
            for (int j = 0; j < C; j++) {
                CS[j] = Stream.of(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            //process();

            System.out.println("Case #" + i + ": " + MIN);
        }
    }

//    private static int process() {
//
//        Set<Integer> availableC = new HashSet<>();
//        for (int i = 0; i < c; i++) {
//            availableC.add(i);
//        }
//
//        find(R, B, availableC, 0);
//        return min;
//    }
//
//
//    private static void find(int r, int b, Set<Integer> availableC, int sum) {
//        if (b <= 0) {
//            if (min > sum) {
//                min = sum;
//            }
//            return;
//        }
//
//        if (r <= 0) {
//            return;
//        }
//
//        if (sum > min) {
//            return;
//        }
//
//        for (int i = 1; i <= b; i++) {
//
//            for (int j = 0; j < C; j++) {
//
//                if (availableC.contains(j) && i <= cs[j][0]) {
//
//                    //for (int k = 1; k <= Math.min(i, cs[j][0]); k++) {
//
//                    int sum2 = Math.max(sum, (i * cs[j][1] + cs[j][2]));
//                    availableC.remove(j);
//
//                    find(r - 1, b - i, availableC, sum2);
//
//                    availableC.add(j);
//                    //}
//                }
//            }
//        }
//    }

}
