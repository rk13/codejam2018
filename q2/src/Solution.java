import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Vladimir Kotov
 */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            int n = Integer.parseInt(in.nextLine());

            int odds = (int) Math.ceil((double) n / 2);
            int[] odd = new int[odds];
            int[] even = new int[n - odds];

            for (int j = 0; j < odds - 1; j++) {
                odd[j] = in.nextInt();
                even[j] = in.nextInt();
            }
            odd[odds - 1] = in.nextInt();
            if (odds == n - odds) {
                even[odds - 1] = in.nextInt();
            }
            in.nextLine();

            int r = process(odd, even);
            System.out.println("Case #" + i + ": " + (r < 0 ? "OK" : r));
        }
    }

    public static int process(int[] odd, int[] even) {
        Arrays.sort(odd);
        Arrays.sort(even);

        for (int i = 0; i < even.length; i++) {
            if (odd[i] > even[i]) {
                return i * 2;
            }
            if (odd.length > i + 1 && even[i] > odd[i + 1]) {
                return i * 2 + 1;
            }
        }
        return -1;
    }
}