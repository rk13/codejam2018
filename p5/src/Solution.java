import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    private static String[] colors = new String[]{"X", "R", "O", "Y", "G", "B", "V"};
    private static int[] stalls;
    private static int[] unicorns;

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.valueOf(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            
            int[] u = Arrays.stream(s)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            String r = process(u);
            System.out.println("Case #" + i + ": " + r);
        }
    }

    public static String process(int[] u) {
        stalls = new int[u[0]];
        unicorns = u;

        if (process(0)) {
            return IntStream.of(stalls)
                    .mapToObj(i -> colors[i])
                    .collect(Collectors.joining());
        }
        return "IMPOSSIBLE";
    }


    public static boolean process(int pos) {

        if (pos == stalls.length) {
            return true;
        }

        int prev = pos == 0 ? stalls.length - 1 : pos - 1;
        int next = pos == stalls.length - 1 ? 0 : pos + 1;

        for (int i = 1; i <= 6; i++) {
            if (unicorns[i] > 0
                    && (stalls[prev] == 0 || Math.abs(i - stalls[prev]) > 1)
                    && (stalls[next] == 0 || Math.abs(i - stalls[next]) > 1)) {

                unicorns[i] = unicorns[i] - 1;
                stalls[pos] = i;

                if (process(pos + 1)) {
                    return true;
                }

                stalls[pos] = 0;
                unicorns[i] = unicorns[i] + 1;
            }
        }

        return false;
    }
}