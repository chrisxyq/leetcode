package dp;

/**
 * @author chrisxu
 * @create 2021-05-30 21:17
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 * <p>
 * 0605笔记：
 * 只许卖一次：dp[i天数]：第i天卖出的最大利润
 * 不限制卖的次数：dp[i天数][是否持股]
 * 最多卖两次：dp[i天数][是否持股][卖出次数]
 */
public class SaleStock {
    /**
     * 只许卖一次：dp[i天数]：第i天卖出的最大利润
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * <p>
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 是求差，而不是最小值。 例如： [2,5,1,3]，最小值1，按解释最大利润是2，实际是3.
     * low:容易让人误解成历史最低点； 真实是截止当前时间点的历史最低点，
     * 和最大子序和是同一道题：
     * 相同点：dp[i]：均是第i天/以i结尾的最值、最终的ans答案均是dp[i]的最值
     * 不同点：最大子序和的dp[i]：和dp[i-1]有关、股票的dp[i]：和第i天之前的价格最低点有关
     */
    public int maxProfit(int[] prices) {
        int profit = 0, low = prices[0];
        for (int i = 0; i < prices.length; i++) {
            low = Math.min(low, prices[i]);
            profit = Math.max(profit, prices[i] - low);
        }
        return profit;

    }

    /**
     * 不限制卖的次数：dp[i天数][是否持股]
     * <p>
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 考虑到「不能同时参与多笔交易」，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。
     * <p>
     * 定义状态 \textit{dp}[i][0]dp[i][0] 表示第 ii 天交易完后手里没有股票的最大利润，\textit{dp}[i][1]dp[i][1] 表示第 ii 天交易完后手里持有一支股票的最大利润（ii 从 00 开始）。
     * <p>
     * 考虑 \textit{dp}[i][0]dp[i][0] 的转移方程，如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股票，即 \textit{dp}[i-1][0]dp[i−1][0]，或者前一天结束的时候手里持有一支股票，即 \textit{dp}[i-1][1]dp[i−1][1]，这时候我们要将其卖出，并获得 \textit{prices}[i]prices[i] 的收益。
     * <p>
     * <p>
     * 由于全部交易结束后，持有股票的收益一定低于不持有股票的收益，因此这时候 \textit{dp}[n-1][0]dp[n−1][0] 的收益必然是大于 \textit{dp}[n-1][1]dp[n−1][1] 的，最后的答案即为 \textit{dp}[n-1][0]dp[n−1][0]。
     */
    public int maxProfit2(int[] prices) {
        //DP
        int n = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
        //贪心算法
//        int ans = 0;
//        int n = prices.length;
//        for (int i = 1; i < n; ++i) {
//            ans += Math.max(0, prices[i] - prices[i - 1]);
//        }
//        return ans;
    }

    /**
     * 不限制卖的次数，有手续费：dp[i天数][是否持股]
     *
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     *
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     *
     * 返回获得利润的最大值。
     *
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     * 使用两个变量 \textit{sell}sell 以及 \textit{buy}buy 分别表示 \textit{dp}[..][0]dp[..][0] 和 \textit{dp}[..][1]dp[..][1] 直接进行状态转移即可
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-han-sh-rzlz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int sell = 0, buy = -prices[0];
        for (int i = 1; i < n; ++i) {
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = Math.max(buy, sell - prices[i]);
        }
        return sell;
    }

    /**
     * 最多卖两次：dp[i天数][是否持股][卖出次数]
     * <p>
     * 一天结束时，可能有持股、可能未持股、可能卖出过1次、可能卖出过2次、也可能未卖出过
     * <p>
     * 所以定义状态转移数组dp[天数][当前是否持股][卖出的次数]
     * <p>
     * 具体一天结束时的6种状态：
     * <p>
     * 未持股，未卖出过股票：说明从未进行过买卖，利润为0
     * dp[i][0][0]=0
     * 未持股，卖出过1次股票：可能是今天卖出，也可能是之前卖的（昨天也未持股且卖出过）
     * dp[i][0][1]=max(dp[i-1][1][0]+prices[i],dp[i-1][0][1])
     * 未持股，卖出过2次股票:可能是今天卖出，也可能是之前卖的（昨天也未持股且卖出过）
     * dp[i][0][2]=max(dp[i-1][1][1]+prices[i],dp[i-1][0][2])
     * 持股，未卖出过股票：可能是今天买的，也可能是之前买的（昨天也持股）
     * dp[i][1][0]=max(dp[i-1][0][0]-prices[i],dp[i-1][1][0])
     * 持股，卖出过1次股票：可能是今天买的，也可能是之前买的（昨天也持股）
     * dp[i][1][1]=max(dp[i-1][0][1]-prices[i],dp[i-1][1][1])
     * 持股，卖出过2次股票：最多交易2次，这种情况不存在
     * dp[i][1][2]=float('-inf')
     * <p>
     * 作者：marcusxu
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/tong-su-yi-dong-de-dong-tai-gui-hua-jie-fa-by-marc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int[][][] dp = new int[prices.length][2][3];
        int MIN_VALUE = Integer.MIN_VALUE / 2;//因为最小值再减去1就是最大值Integer.MIN_VALUE-1=Integer.MAX_VALUE
        //初始化
        dp[0][0][0] = 0;//第一天休息
        dp[0][0][1] = dp[0][1][1] = MIN_VALUE;//不可能
        dp[0][0][2] = dp[0][1][2] = MIN_VALUE;//不可能
        dp[0][1][0] = -prices[0];//买股票
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Math.max(dp[i - 1][1][0] + prices[i], dp[i - 1][0][1]);
            dp[i][0][2] = Math.max(dp[i - 1][1][1] + prices[i], dp[i - 1][0][2]);
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
            dp[i][1][1] = Math.max(dp[i - 1][0][1] - prices[i], dp[i - 1][1][1]);
            dp[i][1][2] = MIN_VALUE;
        }
        return Math.max(0, Math.max(dp[prices.length - 1][0][1], dp[prices.length - 1][0][2]));
    }

    /**
     * 冷冻期
     * 所以对于每一天i，都有可能是三种状态：
     * 0.不持股且当天没卖出,定义其最大收益dp[i][0];
     * 1.持股,定义其最大收益dp[i][1]；
     * 2.不持股且当天卖出了，定义其最大收益dp[i][2]；
     *
     * 只关注卖出的那一天！只关注卖出的那一天！只关注卖出的那一天！
     * 题目中定义的“冷冻期”=卖出的那一天的后一天，题目设置冷冻期的意思是，如果昨天卖出了，今天不可买入，那么关键在于哪一天卖出，只要在今天想买入的时候判断一下前一天是不是刚卖出，即可，所以关键的一天其实是卖出的那一天，而不是卖出的后一天
     * <p>
     * 正文：
     * 因为当天卖出股票实际上也是属于“不持有”的状态，那么第i天如果不持有，那这个“不持有”就有了两种状态：1.本来就不持有，指不是因为当天卖出了才不持有的；2.第i天因为卖出了股票才变得不持有
     * <p>
     * 而持有股票依旧只有一种状态
     * <p>
     * 所以对于每一天i，都有可能是三种状态：
     * 0.不持股且当天没卖出,定义其最大收益dp[i][0];
     * 1.持股,定义其最大收益dp[i][1]；
     * 2.不持股且当天卖出了，定义其最大收益dp[i][2]；
     * <p>
     * 初始化：
     * dp[0][0]=0;//本来就不持有，啥也没干
     * dp[0][1]=-1*prices[0];//第0天只买入
     * dp[0][2]=0;//可以理解成第0天买入又卖出，那么第0天就是“不持股且当天卖出了”这个状态了，其收益为0，所以初始化为0是合理的
     * <p>
     * 重头戏：
     * <p>
     * 一、第i天不持股且没卖出的状态dp[i][0]，也就是我没有股票，而且还不是因为我卖了它才没有的，那换句话说是从i-1天到第i天转移时，它压根就没给我股票！所以i-1天一定也是不持有，那就是不持有的两种可能：i-1天不持股且当天没有卖出dp[i-1][0]；i-1天不持股但是当天卖出去了dp[i-1][2]；
     * 所以： dp[i][0]=max(dp[i-1][0],dp[i-1][2])
     * <p>
     * 二、第i天持股dp[i][1]，今天我持股，来自两种可能：
     * 1、要么是昨天我就持股，今天继承昨天的，也就是dp[i-1][1]，这种可能很好理解；
     * 2、要么：是昨天我不持股，今天我买入的，但前提是昨天我一定没卖！因为如果昨天我卖了，那么今天我不能交易！也就是题目中所谓“冷冻期”的含义，只有昨天是“不持股且当天没卖出”这个状态，我今天才能买入！所以是dp[i-1][0]-p[i]
     * 所以： dp[i][1]=max(dp[i-1][1],dp[i-1][0]-p[i])
     * <p>
     * 三、i天不持股且当天卖出了，这种就简单了，那就是说昨天我一定是持股的，要不然我今天拿什么卖啊，而持股只有一种状态，昨天持股的收益加上今天卖出得到的新收益，就是dp[i-1][1]+p[i]啦
     * 所以：dp[i][2]=dp[i-1][1]+p[i]
     * <p>
     * 总结：最后一天的最大收益有两种可能，而且一定是“不持有”状态下的两种可能，把这两种“不持有”比较一下大小，返回即可
     * <p>
     * 作者：jin-ai-yi
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/fei-zhuang-tai-ji-de-dpjiang-jie-chao-ji-tong-su-y/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int maxProfit4(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;
        int[][] dp = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = -1 * prices[0];
        dp[0][2] = 0;
        for (int i = 1; i < n; i++) {//从[1]...[n-1]
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = dp[i - 1][1] + prices[i];

        }
        return Math.max(dp[n - 1][0], dp[n - 1][2]);
    }
}
