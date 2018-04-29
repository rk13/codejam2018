import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

public class Solution {

    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    static int N, L;

    static int[] C;


    public static void main(String[] args) {
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            N = Integer.parseInt(s[0]);
            L = Integer.parseInt(s[1]);

            String[] s1 = in.nextLine().split(" ");
            C = Stream.of(s1).mapToInt(Integer::parseInt).toArray();

            long r = process();
            System.out.println("Case #" + i + ": " + r);
        }
    }


    static int[] langs;
    static int[] copy;
    static long iters;

    public static long process() {

         langs = new int[N];
         copy = new int[N];
        int answered = 0;
        iters = 0;

        for (int i = 0; i < C.length; i++) {
            langs[i] = C[i];
            answered += C[i];
        }

        int remains = N - answered;


        return next(remains, 0);

    }

    private static long next(int remains, int language) {
        iters++;

        if (iters > 5000000) {
            return 100;
        }
        if (remains <= 0) {
            return percents(langs);
        }

        if (language == N) {
            return 0;
        }

        long max = 0;

        for (int i = 0; i <= remains; i++) {
            langs[language] += i;
            long tmp = next(remains - i, language + 1);
            langs[language] -= i;
            if (tmp > max) {
                max = tmp;
            }
        }

        return max;
    }


    static Map<String, Long> cached = new HashMap<>();

    public static long percents(int[] votes) {

        System.arraycopy(votes, 0, copy, 0, votes.length);
        Arrays.sort(copy);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < copy.length; i++) {
            sb.append(copy[i]).append(',');
        }
        String ss = sb.toString();
        if (cached.containsKey(ss)) {
            return cached.get(ss);
        }

        long sum = 0;
        for (int i = 0; i < votes.length; i++) {
            long pct = Math.round(((double) votes[i] * 100) / N);
            sum += pct;
        }

        cached.put(ss,sum);
        return sum;
    }


}