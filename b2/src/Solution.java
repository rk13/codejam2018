import java.io.BufferedReader;
import java.io.InputStreamReader;
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


    public static String process() {

        long longest = 0;
        long longestVal = 0;

        for (int i = 0; i < S; i++) {

            int m1 = i;
            while (m1 + 1 < S && M[m1 + 1] == M[i]) {
                m1++;
            }

            int k1 = m1;
            while (k1 + 1 < S && (N[m1 + 1] == N[k1 + 1] || M[i] == M[k1 + 1])) {
                k1++;
            }
            int len1 = k1 - i + 1;


            int j2 = i;
            while (j2 + 1 < S && N[j2 + 1] == N[i]) {
                j2++;
            }

            int k2 = j2;
            while (k2 + 1 < S && (M[j2 + 1] == M[k2 + 1] || N[i] == N[k2 + 1])) {
                k2++;
            }
            int len2 = k2 - i + 1;

            int len = Math.max(len1, len2);
            if (len > longest) {
                longest = len;
                longestVal = 1;
            } else if (len == longest) {
                longestVal++;
            }
        }
        return longest + " " + longestVal;
    }


}