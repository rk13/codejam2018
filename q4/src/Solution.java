import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * @author Vladimir Kotov
 */
public class Solution {

    private static final double EPS = 0.0000000000001;
    private static double A = 0.0;

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            A = Double.parseDouble(in.nextLine());
            String[][] r = process();
            System.out.println("Case #" + i + ": ");
            for (int j = 0; j < r.length; j++) {
                System.out.println(r[j][0] + " " + r[j][1] + " " + r[j][2]);
            }
        }
    }

    private static String[][] process() {
        double x = findAngle();
        return new String[][]{
                {format(Math.cos(x) * 0.5), format(Math.sin(x) * 0.5), "0.000000000000"},
                {format(-Math.sin(x) * 0.5), format(Math.cos(x) * 0.5), "0.000000000000"},
                {"0.000000000000", "0.000000000000", "0.500000000000"}};
    }

    private static double findAngle() {
        for (double angle = 0; ; ) {
            double shadow = calculateShadow(angle);
            double anglePlus = calculateAnglePlus(angle, shadow);
            if (anglePlus < EPS) {
                return angle;
            }
            angle += anglePlus;
        }
    }

    private static double calculateAnglePlus(double angle, double shadow) {
        double uncovered = A - shadow;
        double side = Math.sin(Math.PI / 2 - angle);
        return Math.atan(uncovered / side);
    }

    private static double calculateShadow(double angle) {
        return Math.sin(Math.PI / 2 - angle) + Math.sin(angle);
    }

    private static String format(double d) {
        return new BigDecimal(d).setScale(12, RoundingMode.FLOOR).toPlainString();
    }
}