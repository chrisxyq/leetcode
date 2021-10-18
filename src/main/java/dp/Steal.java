package dp;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author chrisxu
 * @create 2021-05-31 20:33
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 * <p>
 */
public class Steal {
    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     */
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int dp_before = nums[0];
        int dp_before2 = 0;
        int res = dp_before;
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(dp_before, dp_before2 + nums[i]);
            dp_before2 = dp_before;
            dp_before = res;
        }
        return res;
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 题目二
     * 1.使用：Arrays.copyOfRange的工具方法
     * 解决索引导致的麻烦的问题
     * 2.分成不偷第一个nums[1:]和不偷最后一个nums[:n−1]两种情况
     * 而不能分成不偷第一个和偷第一个两种情况
     */
    public int rob2(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int start = 0;
        int end = nums.length - 1;
        return Math.max(rob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                rob(Arrays.copyOfRange(nums, 1, nums.length)));

    }

    /**
     在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，
     我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，
     聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

     计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     https://leetcode-cn.com/problems/house-robber-iii/

     */

    /**
     * 爷爷节点获取到最大的偷取的钱数呢
     * <p>
     * 首先要明确相邻的节点不能偷，也就是爷爷选择偷，儿子就不能偷了，但是孙子可以偷
     * 二叉树只有左右两个孩子，一个爷爷最多 2 个儿子，4 个孙子
     * 根据以上条件，我们可以得出单个节点的钱该怎么算
     * 4 个孙子偷的钱 + 爷爷的钱 VS 两个儿子偷的钱 哪个组合钱多，就当做当前节点能偷的最大钱数。这就是动态规划里面的最优子结构
     * 我们这一步针对重复子问题进行优化，我们在做斐波那契数列时，使用的优化方案是记忆化，但是之前的问题都是使用数组解决的，
     * 把每次计算的结果都存起来，下次如果再来计算，就从缓存中取，不再计算了，这样就保证每个数字只计算一次。
     * 由于二叉树不适合拿数组当缓存，我们这次使用哈希表来存储结果，TreeNode 当做 key，能偷的钱当做 value
     */
    public int rob3sol1(TreeNode root) {
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robInternalsol1(root, memo);
    }

    private int robInternalsol1(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int money = root.val;

        if (root.left != null) {
            money += (robInternalsol1(root.left.left, memo) + robInternalsol1(root.left.right, memo));
        }
        if (root.right != null) {
            money += (robInternalsol1(root.right.left, memo) + robInternalsol1(root.right.right, memo));
        }
        int result = Math.max(money, robInternalsol1(root.left, memo) + robInternalsol1(root.right, memo));
        memo.put(root, result);
        return result;
    }

    /**
     每个节点可选择偷或者不偷两种状态，根据题目意思，相连节点不能一起偷

     当前节点选择偷时，那么两个孩子节点就不能选择偷了
     当前节点选择不偷时，两个孩子节点只需要拿最多的钱出来就行(两个孩子节点偷不偷没关系)
     我们使用一个大小为 2 的数组来表示 int[] res = new int[2] 0 代表不偷，1 代表偷
     任何一个节点能偷到的最大钱的状态可以定义为

     当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
     当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数

     root[0] = Math.max(rob(root.left)[0], rob(root.left)[1]) + Math.max(rob(root.right)[0], rob(root.right)[1])
     root[1] = rob(root.left)[0] + rob(root.right)[0] + root.val;

     作者：reals
     链接：https://leetcode-cn.com/problems/house-robber-iii/solution/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}