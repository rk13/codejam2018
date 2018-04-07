import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vladimir Kotov
 */
public class SolutionTest {

    @Test
    public void process1() {
        int result = Solution.process(6, "SCCSSC".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(2));
    }

    @Test
    public void process2() {
        int result = Solution.process(1, "CS".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(1));
    }

    @Test
    public void process3() {
        int result = Solution.process(2, "CS".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(0));
    }

    @Test
    public void process4() {
        int result = Solution.process(1, "SS".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(-1));
    }

    @Test
    public void process5() {
        int result = Solution.process(1, "CC".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(0));
    }

    @Test
    public void process6() {
        int result = Solution.process(3, "CSCSS".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(5));
    }

    @Test
    public void process7() {
        int result = Solution.process(3, "CSCSCS".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(6));
    }

    @Test
    public void process8() {
        int result = Solution.process(1, "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCC".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(0));
    }

    @Test
    public void process9() {
        int result = Solution.process(1, "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCS".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(29));
    }

    @Test
    public void process10() {
        int result = Solution.process(2, "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCS".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(28));
    }

    @Test
    public void process11() {
        int result = Solution.process(30, "SSSSSSSSSSSSSSSSSSSSSSSSSSSSSS".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(0));
    }

    @Test
    public void process12() {
        int result = Solution.process(29, "SSSSSSSSSSSSSSSSSSSSSSSSSSSSSS".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(-1));
    }

    @Test
    public void process13() {
        int result = Solution.process(29, "SCSSSSSSSSSSSSSSSSSSSSSSSSSSSS".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(28));
    }

    @Test
    public void process14() {
        int result = Solution.process(Integer.MAX_VALUE, "CCCCCCCCCCCCCCCCCCCCCCCCCCCCS".toCharArray());
        Assert.assertThat(result, CoreMatchers.is(0));
    }
}