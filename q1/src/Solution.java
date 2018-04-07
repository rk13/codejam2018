import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author Vladimir Kotov
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            long d = Long.parseLong(s[0]);
            int r = process(d, s[1].toCharArray());
            System.out.println("Case #" + i + ": " + (r < 0 ? "IMPOSSIBLE" : r));
        }
    }

    private static int lastActiveLength;
    private static void reCalculateActiveLength(char[] s) {
        for (int i = lastActiveLength; i > 0; i--) {
            if (s[i - 1] == 'S') {
                lastActiveLength = i;
                return;
            }
        }
        lastActiveLength = 0;
    }

    public static int process(long d, char[] s) {
        lastActiveLength = s.length;
        reCalculateActiveLength(s);

        int count = 0;
        boolean changed;

        do {
            changed = false;

            if (possible(d, s)) {
                return count;
            }

            for (int i = lastActiveLength - 1; i > 0; i--) {
                if (s[i] == 'S' && s[i - 1] == 'C') {
                    s[i - 1] = 'S';
                    s[i] = 'C';
                    reCalculateActiveLength(s);
                    changed = true;
                    count++;
                    break;
                }
            }
        } while (changed);

        return -1;
    }

    private static boolean possible(long d, char[] s) {
        long sum = 0;
        long beam = 1;

        for (int i = 0; i < lastActiveLength; i++) {
            switch (s[i]) {
                case 'C':
                    beam *= 2;
                    if (beam > d) {
                        return false;
                    }
                    break;
                case 'S':
                    sum += beam;
                    if (sum > d) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

}