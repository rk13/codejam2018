import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

    @Test
    public void process() {
        String result = Solution.process(new int[]{6, 2, 0, 2, 0, 2, 0});
        assertThat(result, anyOf(is("RYRBYB"), is("RYBRBY")));
    }

    @Test
    public void process2() {
        String result = Solution.process(new int[]{3, 1, 0, 2, 0, 0, 0});
        assertThat(result, is("IMPOSSIBLE"));
    }

    @Test
    public void process3() {
        String result = Solution.process(new int[]{6, 2, 0, 1, 1, 2, 0});
        assertThat(result, is("RGRBYB"));
    }

    @Test
    public void process4() {
        String result = Solution.process(new int[]{4, 0, 0, 2, 0, 0, 2});
        assertThat(result, is("YVYV"));
    }


}