import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    private static char[] ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.valueOf(in.nextLine());
        for (int i = 1; i <= t; i++) {
            in.nextLine();
            String[] s = in.nextLine().split(" ");
            int[] p = Stream.of(s).mapToInt(Integer::parseInt).toArray();
            int sum = IntStream.of(p).sum();

            String r = process(p, sum, "");
            System.out.println("Case #" + i + ": " + r);
        }
    }

    public static String process(int[] p, int sum, String plan) {
        for (int i = 0; i < p.length; i++) {
            if (p[i] != 0) {
                for (int j = i; j < p.length; j++) {
                    if (p[j] != 0) {

                        p[i] = p[i] - 1;
                        if (i != j) p[j] = p[j] - 1;

                        int dec = i != j ? 2 : 1;
                        boolean ok = !IntStream.of(p).filter(value -> value > (sum - dec) / 2).findAny().isPresent();
                        if (ok) {
                            String pair = "" + ABC[i] + (i != j ? ABC[j] : "");
                            if (sum - dec == 0) {
                                return plan + pair;
                            }

                            String result = process(p, sum - dec, plan + pair + " ");
                            if (!result.isEmpty()) {
                                return result;
                            }
                        }

                        p[i] = p[i] + 1;
                        if (i != j) p[j] = p[j] + 1;
                    }
                }
            }
        }
        return "";
    }
}