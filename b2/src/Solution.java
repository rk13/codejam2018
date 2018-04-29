import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    static int S;

    static int[] D;
    static int[] A;
    static int[] B;
    static int[] M;
    static int[] N;


    public static void main(String[] args) {
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            S = Integer.parseInt(s[0]);

            D = new int[S];
            A = new int[S];
            B = new int[S];
            M = new int[S];
            N = new int[S];

            for (int j = 0; j < S; j++) {
                String[] s1 = in.nextLine().split(" ");
                D[j] = Integer.parseInt(s1[0]);
                A[j] = Integer.parseInt(s1[1]);
                B[j] = Integer.parseInt(s1[2]);
                M[j] = D[j] + A[j];
                N[j] = D[j] - B[j];

            }

            String r = process();
            System.out.println("Case #" + i + ": " + r);
        }
    }


    static int longest = 0;
    static int longestVal = 0;

    public static String process() {

        longest = 0;
        longestVal = 0;

        for (int i = 0; i < S; i++) {

            boolean flag = true;
            int end = i;

            while(flag && end < S) {
                flag = (M[end] == M[i]) || (N[end] == N[i]);
                end++;
            }

            int len = end - i;
            if (!flag) len--;

            if (longest < len) {
                longest = len;
                longestVal = 1;
            } else if (longest == len) {
                longestVal++;
            }

        }

        return longest + " " + longestVal;
    }




}