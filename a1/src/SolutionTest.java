import org.junit.Test;

/**
 * @author Vladimir Kotov
 */
public class SolutionTest {

    @Test
    public void process() {

        int[][] w = new int[][]{
                {0, 1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1}
        };

        Solution.process(w, 3, 6,1,1);
    }

    @Test
    public void process1() {

        int[][] w = new int[][]{
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 0, 1, 1}
        };

        Solution.process(w, 3, 4,1,1);
    }

    3 4 2 2
    @.@@
    @@.@
    @.@@
}