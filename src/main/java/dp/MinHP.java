package dp;

import java.util.Arrays;

/**
 * @author chrisxu
 * @create 2020-07-29 22:59
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 *
 * 174.地下城游戏
 */
public class MinHP {
    public int minHP(int[][] dungeon){
        int n=dungeon.length;
        int m=dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <=n ; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);


        }
        dp[n][m-1]=1;
        dp[n-1][m]=1;
        for (int x = n-1; x >=0; x--) {
            for (int y = m-1; y >=0 ; y--) {
                int min = Math.min(dp[x + 1][y], dp[x][y + 1]);
                dp[x][y] = Math.max((min - dungeon[x][y]), 1);
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.deepToString(dp));
        return dp[0][0];
    }
}

class Test2{
    public static void main(String[] args) {
        int[][] dungeon={{-2,-3,3},{-5,-10,1},{10,30,-5}};
        MinHP minHP = new MinHP();
        int res = minHP.minHP(dungeon);
        System.out.println(res);


    }
}
