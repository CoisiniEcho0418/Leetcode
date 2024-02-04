# Leetcode周赛笔记

------

## 前缀和

1. [最大好子数组和 - 力扣 (LeetCode) 竞赛](https://leetcode.cn/contest/biweekly-contest-123/problems/maximum-good-subarray-sum/)

   **题目：**给你一个长度为 `n` 的数组 `nums` 和一个 **正** 整数 `k` 。

   如果 `nums` 的一个子数组中，第一个元素和最后一个元素 **差的绝对值恰好** 为 `k` ，我们称这个子数组为 **好** 的。换句话说，如果子数组 `nums[i..j]` 满足 `|nums[i] - nums[j]| == k` ，那么它是一个好子数组。

   请你返回 `nums` 中 **好** 子数组的 **最大** 和，如果没有好子数组，返回 `0` 。

   **解题思路：**子数组最后一个元素为 `num`，则第一个元素为 `num-k` 或 `num+k` 。为了让子数组和最大，第一个元素的前缀和（不含元素本身）必须尽量小。用哈希表维护每种元素的最小前缀和即可。

   **解题代码：**

   ```java
   class Solution {
       public long maximumSubarraySum(int[] nums, int K) {
           // mp[x] 表示到元素 x，但不含 x 本身的最小前缀和
           Map<Integer, Long> mp = new HashMap<>();
           final long INF = (long) 1e18;
           long ans = -INF, sm = 0;
           for (int x : nums) {
               sm += x;
               // 子数组第一个元素是 x + K
               if (mp.containsKey(x + K)) ans = Math.max(ans, sm - mp.get(x + K));
               // 子数组第一个元素是 x - K
               if (mp.containsKey(x - K)) ans = Math.max(ans, sm - mp.get(x - K));
               // 更新元素 x 的前缀和
               if (mp.containsKey(x)) mp.put(x, Math.min(mp.get(x), sm - x));
               else mp.put(x, sm - x);
           }
           return ans > -INF ? ans : 0;
       }
   }
   ```

   