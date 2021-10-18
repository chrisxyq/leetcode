package dp;

/**
 * @author chrisxu
 * @create 2021-05-30 20:32
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * dp:记录遍历过程中的以i结尾的最大和
 * dp_0：为dp辅助，是以i-1结尾的最大和
 * res：记录最大的dp值
 * 注意i要从1开始遍历，否则会重复加上nums[0]
 */
public class MaxSubArraySum {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int dp;
        int dp_0 = nums[0];
        int res = dp_0;
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(nums[i], dp_0 + nums[i]);
            dp_0 = dp;
            res = Math.max(res, dp);
        }
        return res;
    }
}
