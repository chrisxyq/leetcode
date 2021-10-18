package dp;

/**
 * @author chrisxu
 * @create 2021-05-30 21:00
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
public class ClimbStairs {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            int[] dp = new int[n];
            dp[0] = 1;
            dp[1] = 2;
            for (int i = 2; i < n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n - 1];
        }
    }

    public int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            int dp = 0;
            int dpBefore = 2;
            int dpBefore2 = 1;
            for (int i = 2; i < n; i++) {
                dp = dpBefore + dpBefore2;
                dpBefore2 = dpBefore;
                dpBefore = dp;
            }
            return dp;
        }
    }
}
