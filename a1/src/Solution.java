import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");

            int[] a = Stream.of(s).mapToInt(Integer::parseInt).toArray();
            int[][] w = new int[a[0]][a[1]];

            for (int j = 0; j < a[0]; j++) {
                char[] row = in.nextLine().toCharArray();
                for (int k = 0; k < row.length; k++) {
                    w[j][k] = row[k] == '@' ? 1 : 0;
                }
            }

            boolean r = process(w, a[0], a[1], a[2], a[3]);
            System.out.println("Case #" + i + ": " + (r ? "POSSIBLE" : "IMPOSSIBLE"));
        }
    }

    public static boolean process(int[][] w, int r, int c, int h, int v) {

        int chips = checkCount(0, r - 1, 0, c - 1, w);
        int count = chips / ((h + 1) * (v + 1));

        if (count * (h + 1) * (v + 1) != chips) {
            return false;
        }


        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (checkCount(0, i, 0, j, w) != count) {
                    continue;
                }
                if (checkCount(0, i, j + 1, c - 1, w) != count) {
                    continue;
                }
                if (checkCount(i + 1, r - 1, 0, j, w) != count) {
                    continue;
                }
                if (checkCount(i + 1, r - 1, j + 1, c - 1, w) != count) {
                    continue;
                }
                return true;
            }
        }

        return false;
    }

    private static int checkCount(int rmin, int rmax, int cmin, int cmax, int[][] w) {
        int sum = 0;
        for (int r = rmin; r <= rmax; r++) {
            for (int c = cmin; c <= cmax; c++) {
                sum += w[r][c];
            }
        }
        return sum;
    }
}
