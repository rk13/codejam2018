import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {
    @Test
    public void process1() {
        String result = Solution.process(2525, new int[]{2400}, new int[]{5});
        assertThat(result, is("101.000000"));
    }

    @Test
    public void process2() {
        String result = Solution.process(300, new int[]{120, 60}, new int[]{60, 90});
        assertThat(result, is("100.000000"));
    }

    @Test
    public void process3() {
        String result = Solution.process(100, new int[]{80, 70}, new int[]{100, 10});
        assertThat(result, is("33.333333"));
    }
}