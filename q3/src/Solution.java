import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Vladimir Kotov
 */
public class Solution {

    static int count;

    static int minX, maxX;
    static int minY, maxY;

    static Set<GoCell> prepared;

    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        int t = Integer.valueOf(in.nextLine());
        for (int i = 1; i <= t; i++) {

            count = Integer.parseInt(in.nextLine());

            int delta = (int) Math.floor(Math.sqrt(count)) / 2;

            minX = minY = count / 2 - delta;
            maxX = maxY = count / 2 + delta;
            prepared = new HashSet<>();

            process();
        }
    }

    public static void process() {
        for (; ; ) {
            GoCell candidateCell = findCandidate();
            System.out.println(candidateCell);

            int[] a = Stream.of(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            GoCell preparedCell = new GoCell(a[0], a[1]);

            if (preparedCell.isFinish()) {
                return;
            }

            if (preparedCell.isError()) {
                System.exit(0);
            }
            updatePrepareds(preparedCell);
        }
    }

    private static void updatePrepareds(GoCell preparedCell) {
        prepared.add(preparedCell);

        if (minX > preparedCell.x) {
            minX = preparedCell.x;
        }
        if (minY > preparedCell.y) {
            minY = preparedCell.y;
        }
        if (maxX < preparedCell.x) {
            maxX = preparedCell.x;
        }
        if (maxY < preparedCell.y) {
            maxY = preparedCell.y;
        }
    }

    public static GoCell findCandidate() {
        if (prepared.isEmpty()) {
            return new GoCell(count / 2, count / 2);
        }

        int maxScore = Integer.MIN_VALUE;
        List<GoCell> bestCandidates = new ArrayList<>();

        for (int x = Math.max(2, minX - 1); x <= Math.min(999, maxX + 1); x++) {
            for (int y = Math.max(2, minY - 1); y <= Math.min(999, maxY + 1); y++) {
                GoCell candidate = new GoCell(x, y);
                int score = score(candidate);
                if (score > maxScore) {
                    maxScore = score;
                    bestCandidates = new ArrayList<>();
                }
                if (score >= maxScore) {
                    bestCandidates.add(candidate);
                }
            }
        }
        return bestCandidates.get(new Random(System.currentTimeMillis()).nextInt(bestCandidates.size()));
    }

    private static int score(GoCell candidate) {
        int total = 0;
        boolean canHitNotPrepared = false;
        for (int x = candidate.x - 1; x <= candidate.x + 1; x++) {
            for (int y = candidate.y - 1; y <= candidate.y + 1; y++) {
                GoCell go = new GoCell(x, y);
                if (!prepared.contains(go)) {
                    canHitNotPrepared = true;
                }
                if (willFinish(go)) {
                    total += 1;
                }

                if (sameSquare(go)) {
                    total += 1;
                }

            }
        }
        return canHitNotPrepared ? total : 0;
    }

    private static boolean sameSquare(GoCell cell) {
        return minX <= cell.x && maxX >= cell.x && minY <= cell.y && maxY >= cell.y;
    }

    public static boolean willFinish(GoCell cell) {
        if (prepared.size() < count - 1) {
            return false;
        }

        int wminX = Math.min(cell.x, minX);
        int wmaxX = Math.max(cell.x, maxX);

        int wminY = Math.min(cell.y, minY);
        int wmaxY = Math.max(cell.y, maxY);

        return (prepared.size() + 1) == ((wmaxX - wminX + 1) * (wmaxY - wminY + 1));
    }

    private static class GoCell {
        public int x;
        public int y;

        public GoCell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GoCell goCell = (GoCell) o;
            return x == goCell.x && y == goCell.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return x + " " + y;
        }

        public boolean isFinish() {
            return x == 0 && y == 0;
        }

        public boolean isError() {
            return x < 0 && y < 0;
        }
    }
}