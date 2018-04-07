import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {

    @Test
    public void process0() {
        int[] a = {5, 6, 8, 4, 3, 9};
        tsort(a);
        int r = Solution.process(new int[]{5, 8, 3}, new int[]{6, 4, 9});
        Assert.assertThat(r, CoreMatchers.is(-1));
    }

    @Test
    public void process1() {
        int[] a = {8, 9, 7, 9, 6, 9};
        tsort(a);
        int r = Solution.process(new int[]{8, 7, 6}, new int[]{9, 9, 9});
        Assert.assertThat(r, CoreMatchers.is(1));
    }

    @Test
    public void process2() {
        int r = Solution.process(new int[]{5, 6, 3}, new int[]{6, 4});
        Assert.assertThat(r, CoreMatchers.is(-1));
    }

    @Test
    public void process3() {
        int[] a = {5, 6, 8, 4, 3, 7};
        tsort(a);
        int r = Solution.process(new int[]{5, 8, 3}, new int[]{6, 4, 7});
        Assert.assertThat(r, CoreMatchers.is(4));
    }

    @Test
    public void process10() {
        int[] odd = new int[25000];
        int[] even = new int[25000];
        for (int i = 0; i < 25000; i++) {
            odd[i] = 50000 - i * 2 - 1;
            even[i] = 50000 - i * 2;
        }

        int r = Solution.process(odd, even);
        Assert.assertThat(r, CoreMatchers.is(-1));
    }

    public static void tsort(int[] a) {
        boolean done = false;
        while (!done) {
            done = true;
            for (int i = 0; i < a.length - 2; i++) {
                if (a[i] > a[i + 2]) {
                    done = false;
                    int tmp = a[i];
                    a[i] = a[i + 2];
                    a[i + 2] = tmp;
                }
            }
        }
    }
}