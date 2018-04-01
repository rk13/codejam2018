import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    @Test
    public void process() {
        String result = Solution.process(new int[] {2, 2}, 4, "");
        assertThat(result, is("AB AB"));
    }

    @Test
    public void process2() {
        String result = Solution.process(new int[] {3, 2, 2}, 7, "");
        assertThat(result, is("A A A BC BC"));
    }

    @Test
    public void process3() {
        String result = Solution.process(new int[] {1, 1, 2}, 4, "");
        assertThat(result, is("AC BC"));
    }
}