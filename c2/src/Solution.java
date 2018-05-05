import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Stream;

public class Solution {

    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    static int X, Y;

    static int[] R;

    static int[][] F;

    public static void main(String[] args) {
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            X = Integer.parseInt(s[0]);
            Y = Integer.parseInt(s[1]);

//            String[] s1 = in.nextLine().split(" ");
//            R = Stream.of(s1).mapToInt(Integer::parseInt).toArray();
//
//            F = new int[Y][];
//            for (int j = 0; j < Y; j++) {
//                String s2 = in.nextLine();
//                F[j] = s2.chars().map(x -> (char) x == '*' ? 1 : 0).toArray();
//            }

            String r = process();
            System.out.println("Case #" + i + ": " + r);
        }
    }

    public static String process() {
        return X + " " + Y;
    }
}