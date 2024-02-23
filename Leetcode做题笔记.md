# Leetcode做题笔记

------

## 哈希

1. [49. 字母异位词分组 - 力扣（LeetCode）](https://leetcode.cn/problems/group-anagrams/description/) 

   **题目简述：**将属于同一单词的不同字母排序结果整理到一起，输出一个`List<List<String>>`。

   **解题思路1：**对每个输入的字符串按字母排序（sort）之后，再比对已有的Hash表，来判断是否属于同一个单词。这里排序是关键，可以省去一个个字母比对的时间。**（在字符串和数组当中，当每个元素的顺序不重要时，可以使用排序后的字符串或数组作为键）**

   > - **注意，sort的关键是用`Arrays.sort(char[])`**
   > - 个人题解放在哈希目录下：`.\哈希\group-anagrams.java`

   **解题思路2（未尝试）：**用质数表示26个字母，把字符串的各个字母相乘，这样可保证字母异位词的乘积必定是相等的。其余步骤就是用map存储。

   **解题思路3（未尝试）：**

   ```java
   public List<List<String>> groupAnagrams(String[] strs) {
          return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(s -> s.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())).values());
      }
   ```

2. 数组去重

   **思路：**利用set（可以set去重之后再转为数组，也可以直接用set来遍历）

   ```java
   // int[] nums
   Set<Integer> set = new HashSet<>();
   for(int num:nums){
       set.add(num);
   }
   int[] newList = new int[set.size()];
   int i=0;
   for(Integer n:set){
       newList[i++]=n;
   }
   ```

   

## 双指针

1. [11. 盛最多水的容器 - 力扣（LeetCode）](https://leetcode.cn/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**在横坐标上找出两条高度，使其围成的容器能够盛放最多的水（短板效应，短边为高）

   **解题思路：**`O(N)`算法，一开始两个指针一个指向开头一个指向结尾，此时容器的底是最大的，接下来随着指针向内移动，会造成容器的底变小，在这种情况下想要让容器盛水变多，就只有在容器的高上下功夫。 那我们该如何决策哪个指针移动呢？我们能够发现不管是左指针向右移动一位，还是右指针向左移动一位，容器的底都是一样的，都比原来减少了 1。这种情况下我们想要让指针移动后的容器面积增大，就要使移动后的容器的高尽量大，所以我们选择指针所指的高较小的那个指针进行移动，这样我们就保留了容器较高的那条边，放弃了较小的那条边，以获得有更高的边的机会。

   > 个人题解放在双指针目录下：`.\双指针\container-with-most-water.java`

2. [15. 三数之和 - 力扣（LeetCode）](https://leetcode.cn/problems/3sum/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**从一个整数数组里面找出三个数，要求这三个数加起来为0且结果不能有重复（对运行时间卡的比较死，要求做详尽的剪枝操作）

   **解题思路：**

   ![image-20240124185728764](D:\Desktop\Leetcode\assets\image-20240124185728764.png)

   **解题代码：**不多说了，直接放上别人的优秀题解

   ```java
   class Solution {
       public static List<List<Integer>> threeSum(int[] nums) {
           List<List<Integer>> ans = new ArrayList();
           int len = nums.length;
           if(nums == null || len < 3) return ans;
           Arrays.sort(nums); // 排序
           for (int i = 0; i < len ; i++) {
               if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
               if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
               int L = i+1;
               int R = len-1;
               while(L < R){
                   int sum = nums[i] + nums[L] + nums[R];
                   if(sum == 0){
                       ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                       while (L<R && nums[L] == nums[L+1]) L++; // 去重
                       while (L<R && nums[R] == nums[R-1]) R--; // 去重
                       L++;
                       R--;
                   }
                   else if (sum < 0) L++;
                   else if (sum > 0) R--;
               }
           }        
           return ans;
       }
   }
   
   作者：画手大鹏
   链接：https://leetcode.cn/problems/3sum/solutions/12307/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
   来源：力扣（LeetCode）
   著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
   ```

3. [42. 接雨水 - 力扣（LeetCode）](https://leetcode.cn/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目描述：**给定 `n` 个非负整数表示每个宽度为 `1` 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

   **解题思路：**先从左往右循环，找到高度递增的柱子，并计算相邻两根柱子之间的接雨量，此时循环完之后即可得知最高柱子的下标。然后再从右往左循环（循环到最高柱子的下标即可），按照从左往右循环相反的逻辑，找到从右往左递增的柱子，并计算相邻两根柱子之间的接雨量。如此汇总即可得出总的接雨量。

   > 个人题解放在双指针目录下：`.\双指针\trapping-rain-water.java`

   ![image-20240126223144361](D:\Desktop\Leetcode\assets\image-20240126223144361.png)



## 子串

1. [560. 和为 K 的子数组 - 力扣（LeetCode）](https://leetcode.cn/problems/subarray-sum-equals-k/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给你一个整数数组 `nums` 和一个整数 `k` ，请你统计并返回 *该数组中和为 `k` 的子数组的个数* 。子数组是数组中元素的连续非空序列。（O(N^2^)算法较易想到，下面主要介绍优化算法）

   **解题思路：**主要介绍`O(N)`算法（**前缀和 + 哈希表优化**）

   ![image-20240125192634016](D:\Desktop\Leetcode\assets\image-20240125192634016.png)

   ```java
   class Solution {
       public int subarraySum(int[] nums, int k) {
           int total = 0,pre=0;
           Map<Integer,Integer> map = new HashMap<>();
           map.put(0,1);
           for(int i=0;i< nums.length;i++){
               pre += nums[i];
               if(map.containsKey(pre-k)){
                   total+=map.get(pre-k);
               }
               map.put(pre, map.getOrDefault(pre-k,0)+1);
           }
   
           return  total;
       }
   }
   ```

2. [239. 滑动窗口最大值 - 力扣（LeetCode）](https://leetcode.cn/problems/sliding-window-maximum/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给定一个整数数组 `nums`，有一个大小为 `k` 的滑动窗口从数组的最左侧移动到数组的最右侧。求滑动过程中，每个窗口中的最大值

   **解题思路（单调队列）：**

   ![image-20240126223852331](D:\Desktop\Leetcode\assets\image-20240126223852331.png)

   > 个人题解放在子串目录下：`.\子串\sliding-window-maximum.java`

   

3. [76. 最小覆盖子串 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-window-substring/description/?envType=study-plan-v2&envId=top-100-liked)



## 数组

1. [238. 除自身以外数组的乘积 - 力扣（LeetCode）](https://leetcode.cn/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**一个整数数组 `nums`，返回 *数组 `answer` ，其中 `answer[i]` 等于 `nums` 中除 `nums[i]` 之外其余各元素的乘积* 。**不能使用除法，且在 `O(n)` 时间复杂度内完成此题。**

   **题目思路：**`answer[i]` = 其前缀积 * 后缀积

   **解题代码：**

   ```java
   class Solution {
       public int[] productExceptSelf(int[] nums) {
           int[] ans = new int[nums.length];
           Arrays.fill(ans,1);             // 给数组赋初值，不然全为0
           int left=1,right=1;                 // left：从左边累乘，right：从右边累乘
           // 将计算前缀积和后缀积放在一个for循环里面
           for(int i=0;i< nums.length;i++){    //最终每个元素其左右乘积进行相乘得出结果
               ans[i]*=left;                   //乘以其左边的乘积
               left*=nums[i];
   
               ans[nums.length-i-1]*=right;    //乘以其右边的乘积
               right*=nums[nums.length-i-1];
           }
           return ans;
       }
   }
   ```

2. [41. 缺失的第一个正数 - 力扣（LeetCode）](https://leetcode.cn/problems/first-missing-positive/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给定一个未排序的整数数组 `nums` ，请你找出其中没有出现的最小的正整数。**（要求`O(N)`的时间复杂度和`O(1)`的空间复杂度）**

   **解题思路（标志法，原地算法）：**先将所有的负数都表示成一个大于N的数，然后遍历数组，把所有在 [1,*N*] 范围内的数对应下标（N-1）的元素置为负数。然后再次遍历处理后的数组，得到的第一个大于0的元素其对应的整数（下标+1）即为缺失的第一个正数。（若数组元素都小于0，则说明 N+1 是缺失的第一个正数）

   ![image-20240126230706090](D:\Desktop\Leetcode\assets\image-20240126230706090.png)

   **解题代码：**

   ```java
   class Solution {
       public int firstMissingPositive(int[] nums) {
           int n = nums.length;
           for(int i=0;i<n;i++){
               if(nums[i]<=0){
                   nums[i]=n+1;
               }
           }
           for(int num:nums){
               int temp = Math.abs(num);
               if(temp<=n){
                   nums[temp-1]=-Math.abs(nums[temp-1]);
               }
           }
           for(int i=0;i<n;i++){
               if(nums[i]>0){
                   return i+1;
               }
           }
           return n+1;
       }
   }
   ```

   

## 矩阵

1. [240. 搜索二维矩阵 II - 力扣（LeetCode）](https://leetcode.cn/problems/search-a-2d-matrix-ii/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**编写一个高效的算法来搜索 `m x n` 矩阵 `matrix` 中的一个目标值 `target` 。该矩阵具有以下特性：

   - 每行的元素从左到右升序排列。
   - 每列的元素从上到下升序排列。

   **解题思路：**这题不是很难，下面提供一种我没想到的巧妙思路（**Z 字形查找**，时间复杂度`O(m+n)`，空间复杂度`O(1)`）

   ![image-20240127202834803](D:\Desktop\Leetcode\assets\image-20240127202834803.png)

   **解题代码：**

   ```java
   class Solution {
       public boolean searchMatrix(int[][] matrix, int target) {
           int m= matrix.length,n=matrix[0].length;
           int i=0,j=n-1;
           if(m==0||n==0) {
               return false;
           }
           // 从矩阵的右上角开始搜索
           while(i<m && j>=0){
               if(target==matrix[i][j]){
                   return true;
               }
               if(target>matrix[i][j]){
                   i++;
               }else{
                   j--;
               }
           }
   
           return false;
       }
   }
   ```

   

## 链表

- [146. LRU 缓存 - 力扣（LeetCode）](https://leetcode.cn/problems/lru-cache/description/?envType=study-plan-v2&envId=top-100-liked)

  **题目简述：**设计并实现一个满足 [LRU (最近最少使用) 缓存](https://baike.baidu.com/item/LRU) 约束的数据结构。要求实现 `LRUCache` 类：

  - `LRUCache(int capacity)` 以 **正整数** 作为容量 `capacity` 初始化 LRU 缓存
  - `int get(int key)` 如果关键字 `key` 存在于缓存中，则返回关键字的值，否则返回 `-1` 。
  - `void put(int key, int value)` 如果关键字 `key` 已经存在，则变更其数据值 `value` ；如果不存在，则向缓存中插入该组 `key-value` 。如果插入操作导致关键字数量超过 `capacity` ，则应该 **逐出** 最久未使用的关键字。

  **函数 `get` 和 `put` 必须以 `O(1)` 的平均时间复杂度运行。**

  **解题思路：**哈希表 + 双向链表。双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。哈希表即为普通的哈希映射（HashMap），通过缓存数据的键映射到其在双向链表中的位置。

  **解题代码：**

  ```java
  public class LRUCache {
      class DLinkedNode {
          int key;
          int value;
          DLinkedNode prev;
          DLinkedNode next;
          public DLinkedNode() {}
          public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
      }
  
      private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
      private int size;
      private int capacity;
      private DLinkedNode head, tail;
  
      public LRUCache(int capacity) {
          this.size = 0;
          this.capacity = capacity;
          // 使用伪头部和伪尾部节点
          head = new DLinkedNode();
          tail = new DLinkedNode();
          head.next = tail;
          tail.prev = head;
      }
  
      public int get(int key) {
          DLinkedNode node = cache.get(key);
          if (node == null) {
              return -1;
          }
          // 如果 key 存在，先通过哈希表定位，再移到头部
          moveToHead(node);
          return node.value;
      }
  
      public void put(int key, int value) {
          DLinkedNode node = cache.get(key);
          if (node == null) {
              // 如果 key 不存在，创建一个新的节点
              DLinkedNode newNode = new DLinkedNode(key, value);
              // 添加进哈希表
              cache.put(key, newNode);
              // 添加至双向链表的头部
              addToHead(newNode);
              ++size;
              if (size > capacity) {
                  // 如果超出容量，删除双向链表的尾部节点
                  DLinkedNode tail = removeTail();
                  // 删除哈希表中对应的项
                  cache.remove(tail.key);
                  --size;
              }
          }
          else {
              // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
              node.value = value;
              moveToHead(node);
          }
      }
  
      private void addToHead(DLinkedNode node) {
          node.prev = head;
          node.next = head.next;
          head.next.prev = node;
          head.next = node;
      }
  
      private void removeNode(DLinkedNode node) {
          node.prev.next = node.next;
          node.next.prev = node.prev;
      }
  
      private void moveToHead(DLinkedNode node) {
          removeNode(node);
          addToHead(node);
      }
  
      private DLinkedNode removeTail() {
          DLinkedNode res = tail.prev;
          removeNode(res);
          return res;
      }
  }
  ```

  

## 二叉树

1. [236. 二叉树的最近公共祖先 - 力扣（LeetCode）](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**找到二叉树两个结点的最近公共父节点

   **解题思路（递归）：**

   ![image-20240203191713250](D:\Desktop\Leetcode\assets\image-20240203191713250.png)

   **解题代码（递归）：**

   ```java
   // 未简化版
   class Solution {
       public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
           if (root == null || root == p || root == q) {
               //只要当前根节点是p和q中的任意一个，就返回（因为不能比这个更深了，再深p和q中的一个就没了）
               return root;
           }
           //根节点不是p和q中的任意一个，那么就继续分别往左子树和右子树找p和q
           TreeNode left = lowestCommonAncestor(root.left, p, q);
           TreeNode right = lowestCommonAncestor(root.right, p, q);
           //p和q都没找到，那就没有
           if(left == null && right == null) {
               return null;
           }
           //左子树没有p也没有q，就返回右子树的结果
           if (left == null) {
               return right;
           }
           //右子树没有p也没有q就返回左子树的结果
           if (right == null) {
               return left;
           }
           //左右子树都找到p和q了，那就说明p和q分别在左右两个子树上，所以此时的最近公共祖先就是root
           return root;
       }
   }
   
   
   // 简化版！
   class Solution {
       public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
           if (root == null || p == root || q == root) return root;
           TreeNode left = lowestCommonAncestor(root.left, p, q);
           TreeNode right = lowestCommonAncestor(root.right, p, q);
           if (left != null && right != null) return root;
           return left != null ? left : right;
       }
   }
   ```

   

   **解题思路（存储父节点）：**哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，并记录已经访问过的节点，再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先。

   **算法：**

   1. 从根节点开始遍历整棵二叉树，用哈希表记录每个节点的父节点指针。

   2. 从 p 节点开始不断往它的祖先移动，并用数据结构记录已经访问过的祖先节点。
   3. 同样，我们再从 q 节点开始不断往它的祖先移动，如果有祖先已经被访问过，即意味着这是 p 和 q 的深度最深的公共祖先，即 LCA 节点。

   **解题代码（存储父节点）：**

   ```java
   class Solution {
       Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
       Set<Integer> visited = new HashSet<Integer>();
   
       public void dfs(TreeNode root) {
           if (root.left != null) {
               parent.put(root.left.val, root);
               dfs(root.left);
           }
           if (root.right != null) {
               parent.put(root.right.val, root);
               dfs(root.right);
           }
       }
   
       public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
           dfs(root);
           while (p != null) {
               visited.add(p.val);
               p = parent.get(p.val);
           }
           while (q != null) {
               if (visited.contains(q.val)) {
                   return q;
               }
               q = parent.get(q.val);
           }
           return null;
       }
   }
   ```

   

2. [124. 二叉树中的最大路径和 - 力扣（LeetCode）](https://leetcode.cn/problems/binary-tree-maximum-path-sum/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**二叉树中的 **路径** 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 **至多出现一次** 。该路径 **至少包含一个** 节点，且不一定经过根节点。**路径和** 是路径中各节点值的总和。给你一个二叉树的根节点 `root` ，返回其 **最大路径和** 。

   **解题思路：**

   ![image-20240204201447927](D:\Desktop\Leetcode\assets\image-20240204201447927.png)

   ------

   ![image-20240204201949939](D:\Desktop\Leetcode\assets\image-20240204201949939.png)

   **解题代码：**

   ```java
   class Solution {
       int maxSum = Integer.MIN_VALUE;
   
       public int maxPathSum(TreeNode root) {
           if (root == null) {
               return 0;
           }
           maxGain(root);
           return maxSum;
       }
   
       /**
        * 返回经过root的单边分支最大和， 即Math.max(root, root+left, root+right)
        * @param root
        * @return
        */
       public int maxGain(TreeNode node) {
           if (node == null) {
               return 0;
           }
           //计算左边分支最大值，左边分支如果为负数还不如不选择
           int leftMax = Math.max(0, dfs(root.left));
           //计算右边分支最大值，右边分支如果为负数还不如不选择
           int rightMax = Math.max(0, dfs(root.right));
           //left->root->right 作为路径与已经计算过历史最大值做比较
           max = Math.max(max, root.val + leftMax + rightMax);
           // 返回经过root的单边最大分支给当前root的父节点计算使用
           return root.val + Math.max(leftMax, rightMax);
       }
   }
   ```

   

## 回溯

1. [78. 子集 - 力扣（LeetCode）](https://leetcode.cn/problems/subsets/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给定一个整数数组 `nums` ，数组中的元素 **互不相同** 。返回该数组所有可能的子集（幂集）。解集 **不能** 包含重复的子集。

   **解法一（迭代）：**可以把数组中的每一位与二进制串的一位相对应，用0和1来表示对应的整数是否出现在子集中，通过遍历0到2^n^-1即可得出所有子集。

   ```java
   class Solution {
       List<Integer> t = new ArrayList<Integer>();
       List<List<Integer>> ans = new ArrayList<List<Integer>>();
   
       public List<List<Integer>> subsets(int[] nums) {
           int n = nums.length;
           for (int mask = 0; mask < (1 << n); ++mask) {
               t.clear();
               for (int i = 0; i < n; ++i) {
                   if ((mask & (1 << i)) != 0) {
                       t.add(nums[i]);
                   }
               }
               // 要新建一个List来存储结果
               ans.add(new ArrayList<Integer>(t));
           }
           return ans;
       }
   }
   ```
   
   **解法二（递归官方版）：**采用深度优先搜索来递归，`dfs(cur,nums)`中的`cur`表示当前位置，通过根据当前位置对应的整数是否出现在子集中，可以分为两条支路，从而进行`dfs`递归，知道`cur==nums.length`，此时表示数组中的所有整数是否出现都已经被确认，然后将数组记录下来。
   
   ```java
   class Solution {
       List<Integer> t = new ArrayList<Integer>();
       List<List<Integer>> ans = new ArrayList<List<Integer>>();
   
       public List<List<Integer>> subsets(int[] nums) {
           dfs(0, nums);
           return ans;
       }
   
       public void dfs(int cur, int[] nums) {
           // 注意是nums.length而不是nums.length-1
           if (cur == nums.length) {
               // 记录答案（注意要新建一个List来存储结果，不然后面的操作会修改之前存入的答案）
               ans.add(new ArrayList<Integer>(t));
               return;
           }
           // 考虑选择当前位置
           t.add(nums[cur]);
           dfs(cur + 1, nums);
           // 考虑不选择当前位置
           t.remove(t.size() - 1);
           dfs(cur + 1, nums);
       }
   }
   ```

   **解法三（自己写的递归）：**为了避免添加重复的子集，规定子集中的整数要满足递增的顺序，否则就不添加到结果中。（在递归的每一层都可能产生新的子集添加到结果中，因为子集的大小从0增加到数组的大小）
   
   ```java
   class Solution {
       List<List<Integer>> res = new ArrayList<>();
       public List<List<Integer>> subsets(int[] nums) {
           // 记录数组中的整数是否已经被添加到子集中
           boolean[] visited = new boolean[nums.length];
           Arrays.fill(visited,false);
           res.add(new ArrayList<>());
           // 先排序
           Arrays.sort(nums);
           backtrack(nums,new ArrayList<>(),visited);
           return res;
       }
   
       private void backtrack(int[] nums,List<Integer> list,boolean[] visited){
           // 先判断子集的大小是否已经最大
           if(list.size()== nums.length){
               return;
           }
           for(int i=0;i< nums.length;i++){
               if(!visited[i]){
                   if(list.size()==0||nums[i]> list.get(list.size()-1)){// 确保子集中的元素递增
                       // 新创建一个List，作为存储新子集的容器
                       List<Integer> newList = new ArrayList<>(list);
                       newList.add(nums[i]);
                       visited[i]=true;
                       res.add(newList);
                       backtrack(nums,newList,visited);
                       // 回溯完撤销之前的操作
                       visited[i]=false;
                   }
               }
           }
   
       }
   }
   ```
   
   

## 二分查找

1. [4. 寻找两个正序数组的中位数 - 力扣（LeetCode）](https://leetcode.cn/problems/median-of-two-sorted-arrays/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给定两个大小分别为 `m` 和 `n` 的正序（从小到大）数组 `nums1` 和 `nums2`。请你找出并返回这两个正序数组的 **中位数** 。

   算法的时间复杂度应该为 `O(log (m+n))` 。

   **解题思路：**看官方题解——https://leetcode.cn/problems/median-of-two-sorted-arrays/solutions/258842/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/?envType=study-plan-v2&envId=top-100-liked

   **解题代码（二分查找）：**

   ```java
   class Solution {
       public double findMedianSortedArrays(int[] nums1, int[] nums2) {
           int length1 = nums1.length, length2 = nums2.length;
           int totalLength = length1 + length2;
           if (totalLength % 2 == 1) {
               int midIndex = totalLength / 2;
               double median = getKthElement(nums1, nums2, midIndex + 1);
               return median;
           } else {
               int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
               double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
               return median;
           }
       }
   
       public int getKthElement(int[] nums1, int[] nums2, int k) {
           /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
            * 这里的 "/" 表示整除
            * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
            * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
            * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
            * 这样 pivot 本身最大也只能是第 k-1 小的元素
            * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
            * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
            * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
            */
   
           int length1 = nums1.length, length2 = nums2.length;
           int index1 = 0, index2 = 0;
           int kthElement = 0;
   
           while (true) {
               // 边界情况
               if (index1 == length1) {
                   return nums2[index2 + k - 1];
               }
               if (index2 == length2) {
                   return nums1[index1 + k - 1];
               }
               if (k == 1) {
                   return Math.min(nums1[index1], nums2[index2]);
               }
               
               // 正常情况
               int half = k / 2;
               int newIndex1 = Math.min(index1 + half, length1) - 1;
               int newIndex2 = Math.min(index2 + half, length2) - 1;
               int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
               if (pivot1 <= pivot2) {
                   k -= (newIndex1 - index1 + 1);
                   index1 = newIndex1 + 1;
               } else {
                   k -= (newIndex2 - index2 + 1);
                   index2 = newIndex2 + 1;
               }
           }
       }
   }
   ```

   **解题代码（划分数组）：**

   ```java
   class Solution {
       public double findMedianSortedArrays(int[] nums1, int[] nums2) {
           if (nums1.length > nums2.length) {
               return findMedianSortedArrays(nums2, nums1);
           }
   
           int m = nums1.length;
           int n = nums2.length;
           int left = 0, right = m;
           // median1：前一部分的最大值
           // median2：后一部分的最小值
           int median1 = 0, median2 = 0;
   
           while (left <= right) {
               // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
               // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
               int i = (left + right) / 2;
               int j = (m + n + 1) / 2 - i;
   
               // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
               int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
               int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
               int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
               int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);
   
               if (nums_im1 <= nums_j) {
                   median1 = Math.max(nums_im1, nums_jm1);
                   median2 = Math.min(nums_i, nums_j);
                   left = i + 1;
               } else {
                   right = i - 1;
               }
           }
   
           return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
       }
   }
   ```



## 贪心

1. [45. 跳跃游戏 II - 力扣（LeetCode）](https://leetcode.cn/problems/jump-game-ii/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给定一个长度为 `n` 的 **0 索引**整数数组 `nums`，初始位置为 `nums[0]`。每个元素 `nums[i]` 表示从索引 `i` 向前跳转的最大长度。换句话说，如果你在 `nums[i]` 处，你可以跳转到任意 `nums[i + j]` 处。返回到达 `nums[n - 1]` 的最小跳跃次数。

   **解题思路：**每次跳跃前先计算跳跃范围，然后依次计算这个范围内的各个坐标所能到达的最远位置，选取能到达最远位置的坐标作为这次跳跃的目的地，以此类推，直至到达终点。

   **解题代码：**

   ```java
   class Solution {
       public int jump(int[] nums) {
           if(nums.length==1){
               return 0;
           }
           int max=0,maxIndex=0;
           int count=1;
           int begin=0,end=nums[0];
           while (end< nums.length-1){
               max=end;
               maxIndex=begin;
               for(int i=begin+1;i<=end;i++){
                   if(i+nums[i]>max){
                       max=i+nums[i];
                       maxIndex=i;
                   }
               }
               begin=maxIndex;
               end=max;
               count++;
           }
           return count;
       }
   }
   ```

   

## 动态规划

1. [198. 打家劫舍 - 力扣（LeetCode）](https://leetcode.cn/problems/house-robber/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给定一个代表每个房屋存放金额的非负整数数组，在不能偷相邻房子的前提条件下，计算能够偷到的最大金额

   **题解（有助于理解动态规划）**：[198. 打家劫舍 - 力扣（LeetCode）](https://leetcode.cn/problems/house-robber/solutions/138131/dong-tai-gui-hua-jie-ti-si-bu-zou-xiang-jie-cjavap/?envType=study-plan-v2&envId=top-100-liked)

   **解题思路：**![image-20240213000831896](D:\Desktop\Leetcode\assets\image-20240213000831896.png)

   **解题代码：**

   - 常规版

     ```java
     public int rob(int[] nums) {
         if (nums.length == 0) {
             return 0;
         }
         // 子问题：
         // f(k) = 偷 [0..k) 房间中的最大金额
     
         // f(0) = 0
         // f(1) = nums[0]
         // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }
     
         int N = nums.length;
         int[] dp = new int[N+1];
         dp[0] = 0;
         dp[1] = nums[0];
         for (int k = 2; k <= N; k++) {
             dp[k] = Math.max(dp[k-1], nums[k-1] + dp[k-2]);
         }
         return dp[N];
     }
     ```

     

   - 空间优化版

     ![image-20240213000924460](D:\Desktop\Leetcode\assets\image-20240213000924460.png)

     ```java
     public int rob(int[] nums) {
         int prev = 0;
         int curr = 0;
     
         // 每次循环，计算“偷到当前房子为止的最大金额”
         for (int i : nums) {
             // 循环开始时，curr 表示 dp[k-1]，prev 表示 dp[k-2]
             // dp[k] = max{ dp[k-1], dp[k-2] + i }
             int temp = Math.max(curr, prev + i);
             prev = curr;
             curr = temp;
             // 循环结束时，curr 表示 dp[k]，prev 表示 dp[k-1]
         }
     
         return curr;
     }
     ```

2. [279. 完全平方数 - 力扣（LeetCode）](https://leetcode.cn/problems/perfect-squares/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给你一个整数 `n` ，返回 *和为 `n` 的完全平方数的最少数量* 。

   **解题思路：**![image-20240215133252295](D:\Desktop\Leetcode\assets\image-20240215133252295.png)

   **解题代码：**

   ```java
   class Solution {
       public int numSquares(int n) {
           int[] dp=new int[n+1];
           dp[0]=0;
           for(int i=1;i<=n;i++){
               int minnum=Integer.MAX_VALUE;
               for(int j=1;j*j<=i;j++){
                   minnum=Math.min(minnum,dp[i-j*j]);
               }
               dp[i]=1+minnum;
           }
           return dp[n];
       }
   }
   ```

3. [139. 单词拆分 - 力扣（LeetCode）](https://leetcode.cn/problems/word-break/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给你一个字符串 `s` 和一个字符串列表 `wordDict` 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 `s` 则返回 `true`。

   **解题思路：**![image-20240216234251182](D:\Desktop\Leetcode\assets\image-20240216234251182.png)

   **解题代码：**

   ```java
   class Solution {
       public boolean wordBreak(String s, List<String> wordDict) {
           Set<String> set = new HashSet<>(wordDict);
           boolean[] dp=new boolean[s.length()+1];
           int maxLen=0;
           // 找到字典列表最长单词的长度
           for(String str:set){
               maxLen=Math.max(maxLen,str.length());
           }
           dp[0]=true;
           for(int i=1;i<=s.length();i++){
               for(int j=0;j<i;j++){
                   // 剪枝操作
                   if(i-j>maxLen){
                       continue;
                   }
                   if(dp[j]&&set.contains(s.substring(j,i))){
                       dp[i]=true;
                       break;
                   }
               }
           }
           return dp[s.length()];
       }
   }
   ```

4. [300. 最长递增子序列 - 力扣（LeetCode）](https://leetcode.cn/problems/longest-increasing-subsequence/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给你一个整数数组 `nums` ，找到其中最长严格递增子序列的长度。**子序列** 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，`[3,6,2,7]` 是数组 `[0,3,1,6,2,2,7]` 的子序列。

   **解题思路（解法一）：**![image-20240217192601972](D:\Desktop\Leetcode\assets\image-20240217192601972.png)

   **解题代码（解法一：动态规划）：**

   ```java
   class Solution {
       public int lengthOfLIS(int[] nums) {
           if (nums.length == 0) {
               return 0;
           }
           int[] dp = new int[nums.length];
           dp[0] = 1;
           int maxans = 1;
           for (int i = 1; i < nums.length; i++) {
               dp[i] = 1;
               for (int j = 0; j < i; j++) {
                   if (nums[i] > nums[j]) {
                       dp[i] = Math.max(dp[i], dp[j] + 1);
                   }
               }
               maxans = Math.max(maxans, dp[i]);
           }
           return maxans;
       }
   }
   ```

   **解题思路（解法二：贪心 + 二分查找）：**

   ![image-20240218195521318](D:\Desktop\Leetcode\assets\image-20240218195521318.png)

   **解题代码（解法二）：**

   ```java
   class Solution {
       public int lengthOfLIS(int[] nums) {
           if (nums.length == 0) {
               return 0;
           }
           int len=1,n= nums.length;
           int[] dp=new int[n+1];
           dp[len]=nums[0];
           for(int i=1;i< nums.length;i++){
               if(nums[i]>dp[len]){
                   dp[++len]=nums[i];
               }else{
                   int left=1,right=len-1;
                   while (left<=right){
                       int middle=(left+right)/2;
                       if(dp[middle]==nums[i]){
                           left=middle;
                           break;
                       } else if (dp[middle]>nums[i]) {
                           right=middle-1;
                       }else{
                           left=middle+1;
                       }
                   }
                   dp[left]=nums[i];
               }
           }
           return len;
       }
   
   }
   ```

5. [152. 乘积最大子数组 - 力扣（LeetCode）](https://leetcode.cn/problems/maximum-product-subarray/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给你一个整数数组 `nums` ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

   **解题思路（动态规划法）：**![image-20240218195200804](D:\Desktop\Leetcode\assets\image-20240218195200804.png)

   **解题代码：**

   ```java
   class Solution {
       public int maxProduct(int[] nums) {
           if(nums.length==0){
               return 0;
           }
           int[] min=new int[nums.length];
           int[] max=new int[nums.length];
           for(int i=0;i< nums.length;i++){
               min[i]=nums[i];
               max[i]=nums[i];
           }
           int maxn=max[0];
           for(int i=1;i< nums.length;i++){
               max[i]=Math.max(Math.max(max[i-1]*nums[i],min[i-1]*nums[i]),nums[i]);
               min[i]=Math.min(Math.min(max[i-1]*nums[i],min[i-1]*nums[i]),nums[i]);
               maxn=Math.max(maxn,max[i]);
           }
           return maxn;
       }
   }
   ```

6. [416. 分割等和子集 - 力扣（LeetCode）](https://leetcode.cn/problems/partition-equal-subset-sum/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给你一个 **只包含正整数** 的 **非空** 数组 `nums` 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

   **解题思路：**![image-20240219014555794](D:\Desktop\Leetcode\assets\image-20240219014555794.png)

   ![image-20240219014614621](D:\Desktop\Leetcode\assets\image-20240219014614621.png)

   **解题代码（未优化空间复杂度版）：**

   ```java
   class Solution {
       public boolean canPartition(int[] nums) {
           int n = nums.length;
           if (n < 2) {
               return false;
           }
           int sum = 0, maxNum = 0;
           for (int num : nums) {
               sum += num;
               maxNum = Math.max(maxNum, num);
           }
           if (sum % 2 != 0) {
               return false;
           }
           int target = sum / 2;
           if (maxNum > target) {
               return false;
           }
           boolean[][] dp = new boolean[n][target + 1];
           for (int i = 0; i < n; i++) {
               dp[i][0] = true;
           }
           dp[0][nums[0]] = true;
           for (int i = 1; i < n; i++) {
               int num = nums[i];
               for (int j = 1; j <= target; j++) {
                   if (j >= num) {
                       dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                   } else {
                       dp[i][j] = dp[i - 1][j];
                   }
               }
           }
           return dp[n - 1][target];
       }
   }
   ```

   **解题代码（优化版）：**

   ![image-20240219014750831](D:\Desktop\Leetcode\assets\image-20240219014750831.png)

   ```java
   class Solution {
       public boolean canPartition(int[] nums) {
           int n = nums.length;
           if (n < 2) {
               return false;
           }
           int sum = 0, maxNum = 0;
           for (int num : nums) {
               sum += num;
               maxNum = Math.max(maxNum, num);
           }
           if (sum % 2 != 0) {
               return false;
           }
           int target = sum / 2;
           if (maxNum > target) {
               return false;
           }
           boolean[] dp = new boolean[target + 1];
           dp[0] = true;
           for (int i = 0; i < n; i++) {
               int num = nums[i];
               for (int j = target; j >= num; --j) {
                   dp[j] |= dp[j - num];
               }
           }
           return dp[target];
       }
   }
   ```

   

## 多维动态规划

1. [5. 最长回文子串 - 力扣（LeetCode）](https://leetcode.cn/problems/longest-palindromic-substring/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给你一个字符串 `s`，找到 `s` 中最长的回文子串。

   **解题思路（动态规划）：**

   ![image-20240223170529318](D:\Desktop\Leetcode\assets\image-20240223170529318.png)

   ![image-20240223170546111](D:\Desktop\Leetcode\assets\image-20240223170546111.png)

   **解题代码：**

   ```java
   public class Solution {
   
       public String longestPalindrome(String s) {
           int len = s.length();
           if (len < 2) {
               return s;
           }
   
           int maxLen = 1;
           int begin = 0;
           // dp[i][j] 表示 s[i..j] 是否是回文串
           boolean[][] dp = new boolean[len][len];
           // 初始化：所有长度为 1 的子串都是回文串
           for (int i = 0; i < len; i++) {
               dp[i][i] = true;
           }
   
           char[] charArray = s.toCharArray();
           // 递推开始
           // 先枚举子串长度
           for (int L = 2; L <= len; L++) {
               // 枚举左边界，左边界的上限设置可以宽松一些
               for (int i = 0; i < len; i++) {
                   // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                   int j = L + i - 1;
                   // 如果右边界越界，就可以退出当前循环
                   if (j >= len) {
                       break;
                   }
   
                   if (charArray[i] != charArray[j]) {
                       dp[i][j] = false;
                   } else {
                       if (j - i < 3) {
                           dp[i][j] = true;
                       } else {
                           dp[i][j] = dp[i + 1][j - 1];
                       }
                   }
   
                   // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                   if (dp[i][j] && j - i + 1 > maxLen) {
                       maxLen = j - i + 1;
                       begin = i;
                   }
               }
           }
           return s.substring(begin, begin + maxLen);
       }
   }
   ```

   **解题思路（中心扩展算法）：**

   ![image-20240223170921998](D:\Desktop\Leetcode\assets\image-20240223170921998.png)

   **解题代码：**

   ```java
   class Solution {
       public String longestPalindrome(String s) {
           if(s.length()<=1){
               return s;
           }
           int begin=0,maxLen=1;
           for(int i=0;i<s.length();i++){
               int[] len1=expand(i,i,s);
               int[] len2=expand(i,i+1,s);
               if(len1[1]>=len2[1]&&len1[1]>maxLen){
                   maxLen=len1[1];
                   begin=len1[0];
               } else if (len2[1]>len1[1]&&len2[1]>maxLen) {
                   maxLen=len2[1];
                   begin=len2[0];
               }
           }
           return s.substring(begin,begin+maxLen);
       }
       
       // 返回以[left,right]为边界子串进行扩展的最长回文子串的信息，int[0]存储回文串的起始下标，int[1]存储回文串的长度
       private int[] expand(int left,int right,String s){
           int[] res=new int[2];
           while (left>=0&&right< s.length()&&s.charAt(left)==s.charAt(right)){
               left--;
               right++;
           }
           res[0]=left+1;
           res[1]=right-left-1;
           return res;
       }
   }
   ```

2. [1143. 最长公共子序列 - 力扣（LeetCode）](https://leetcode.cn/problems/longest-common-subsequence/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给定两个字符串 `text1` 和 `text2`，返回这两个字符串的最长 **公共子序列** 的长度。如果不存在 **公共子序列** ，返回 `0` 。

   一个字符串的 **子序列** 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

   - 例如，`"ace"` 是 `"abcde"` 的子序列，但 `"aec"` 不是 `"abcde"` 的子序列。

   两个字符串的 **公共子序列** 是这两个字符串所共同拥有的子序列。

   **解题思路：**用`DP[i][j]` 表示 `text1[0 ... i]` 和 `text2[0 ... j]` 的最长公共子序列。如果 text1[i] 等于 text2[j]，则 `DP[i][j] = DP[i - 1][j - 1] + 1`；否则，`DP[i][j] = max(DP[i - 1][j], DP[i][j - 1])`。边界条件：当 𝑖=0时，text1[0:𝑖]为空，空字符串和任何字符串的最长公共子序列的长度都是0，因此对于任意 0≤*j*≤*n*，有 `dp[0][𝑗]=0`。当 𝑗=0 时，text2[0:𝑗]为空，同理可得，对任意 0≤𝑖≤𝑚，有 `dp[𝑖][0]=0`。

   **解题代码：**

   ```java
   class Solution {
       public int longestCommonSubsequence(String text1, String text2) {
           int m = text1.length(), n = text2.length();
           int[][] dp = new int[m + 1][n + 1];
           for (int i = 1; i <= m; i++) {
               char c1 = text1.charAt(i - 1);
               for (int j = 1; j <= n; j++) {
                   char c2 = text2.charAt(j - 1);
                   if (c1 == c2) {
                       dp[i][j] = dp[i - 1][j - 1] + 1;
                   } else {
                       dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                   }
               }
           }
           return dp[m][n];
       }
   }
   ```

3. [72. 编辑距离 - 力扣（LeetCode）](https://leetcode.cn/problems/edit-distance/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给你两个单词 `word1` 和 `word2`， *请返回将 `word1` 转换成 `word2` 所使用的最少操作数* 。

   你可以对一个单词进行如下三种操作：

   - 插入一个字符
   - 删除一个字符
   - 替换一个字符

   **解题思路：**动态规划：
   ![image-20240223220238860](D:\Desktop\Leetcode\assets\image-20240223220238860.png)

   第一行，是 `word1` 为空变成 `word2` 最少步数，就是插入操作

   第一列，是 `word2` 为空，需要的最少步数，就是删除操作

   **解题代码：**

   ```java
   // 多维数组动态规划
   class Solution {
       public int minDistance(String word1, String word2) {
           int[][] dp=new int[word1.length()+1][word2.length()+1];
           dp[0][0]=0;
           for(int j=1;j<=word2.length();j++){
               dp[0][j]=dp[0][j-1]+1;
           }
           for(int i=1;i<=word1.length();i++){
               dp[i][0]=dp[i-1][0]+1;
           }
           for(int i=1;i<=word1.length();i++){
               for(int j=1;j<=word2.length();j++){
                   if(word1.charAt(i-1)==word2.charAt(j-1)){
                       dp[i][j]=dp[i-1][j-1];
                   }else{
                       dp[i][j]=Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1])+1;
                   }
               }
           }
           return dp[word1.length()][word2.length()];
       }
   }
   ```

   ```java
   // 优化之后的一维数组动态规划
   class Solution {
       public int minDistance(String word1, String word2) {
           char[] s=word1.toCharArray();
           char[] t=word2.toCharArray();
           int[] dp = new int[word2.length()+1];   // dp[i]表示从word1转换到word2前i个字符所需的最小次数
           for(int j=1;j<=word2.length();j++){    // 当word1为空时，转换成word2前i个字符所需的最小次数
               dp[j]=j;
           }
           for(int i=1;i<=word1.length();i++){ // 这里的i可以理解为二维dp[i][j]中的i
               int pre = dp[0]++;  // pre的实质是二维动归中的dp[i-1][j-1], dp[0]++表示 dp[i+1][0]=dp[i][0]+1
               for(int j=1;j<=word2.length();j++){
                   int temp=dp[j];
                   if(s[i-1]==t[j-1]){
                       dp[j]=pre;
                   }else{
                       dp[j]=Math.min((Math.min(pre,dp[j])),dp[j-1])+1;
                   }
                   pre=temp;
               }
           }
           return dp[word2.length()];
       }
   }
   // 优化成一维的实质是只存储二维中的一行，然后在外层遍历中慢慢更新。
   ```

   



## 堆

1. [215. 数组中的第K个最大元素 - 力扣（LeetCode）](https://leetcode.cn/problems/kth-largest-element-in-an-array/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给定整数数组 `nums` 和整数 `k`，请返回数组中第 `**k**` 个最大的元素。请注意，你需要找的是数组排序后的第 `k` 个最大的元素，而不是第 `k` 个不同的元素。必须设计并实现时间复杂度为 `O(n)` 的算法解决此问题。

   **解题思路一（基于快速排序的选择方法）：**

   ![image-20240220192235360](D:\Desktop\Leetcode\assets\image-20240220192235360.png)

   **解题代码：**

   ```java
   class Solution {
       public int findKthLargest(int[] nums, int k) {
           return quickSelect(nums, 0, nums.length - 1, nums.length - k);
       }
   
       private int quickSelect(int[] nums, int left, int right, int target) {
           int index = partition(nums, left, right);
           if (index == target) {
               return nums[index];
           } else {
               return index > target ? 
                   quickSelect(nums, left, index - 1, target) : 
                   quickSelect(nums, index + 1, right, target);
           }
       }
   
       private int partition(int[] nums, int left, int right) {
           swap(nums, left, left + new Random().nextInt(right - left + 1));
           int pivot = nums[left];
           while (left < right) {
               while (left < right && nums[right] > pivot) {
                   right--;
               }
               if (left < right) {
                   nums[left++] = nums[right];
               }
               while (left < right && nums[left] < pivot) {
                   left++;
               }
               if (left < right) {
                   nums[right--] = nums[left];
               }
           }
           nums[left] = pivot;
           return left;
       }
   
       private void swap(int[] nums, int i, int j) {
           int swap = nums[i];
           nums[i] = nums[j];
           nums[j] = swap;
       }
   }
   ```

   **解题思路二（计数排序）：**根据题目的约束条件，`-10000 <= nums[i] <= 10000`，可以构造一个大小为20001的桶，用来存放对应数在数组中的个数，然后从大往小依次遍历每个桶，输出第K大的元素。

   **解题代码：**

   ```java
   class Solution {
       public int findKthLargest(int[] nums, int k) {
           int[] buckets = new int[20001];
           for(int i=0;i< nums.length;i++){
               buckets[nums[i]+10000]++;
           }
           for (int i=20000;i>=0;i--){
               k-=buckets[i];
               if(k<=0){
                   return i-10000;
               }
           }
           return 0;
       }
   }
   ```

   **解题思路三（基于堆排序的选择方法）：**

   也可以使用堆排序来解决这个问题——建立一个大根堆，做 k−1次删除操作后堆顶元素就是我们要找的答案。在这道题中尤其要搞懂「建堆」、「调整」和「删除」的过程（自己维护一个堆）。时间复杂度为`O(nlogn)`，空间复杂度为`O(logn)`

   **解题代码：**

   ```java
   // 使用PriorityQueue来实现
   import java.util.Comparator;
   import java.util.PriorityQueue;
   
   public class Solution {
   
       public int findKthLargest(int[] nums, int k) {
           int len = nums.length;
           // 使用一个含有 k 个元素的最小堆，PriorityQueue 底层是动态数组，为了防止数组扩容产生消耗，可以先指定数组的长度
           PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
           // Java 里没有 heapify ，因此我们逐个将前 k 个元素添加到 minHeap 里
           for (int i = 0; i < k; i++) {
               minHeap.offer(nums[i]);
           }
   
           for (int i = k; i < len; i++) {
               // 看一眼，不拿出，因为有可能没有必要替换
               Integer topElement = minHeap.peek();
               // 只要当前遍历的元素比堆顶元素大，堆顶弹出，遍历的元素进去
               if (nums[i] > topElement) {
                   // Java 没有 replace()，所以得先 poll() 出来，然后再放回去
                   minHeap.poll();
                   minHeap.offer(nums[i]);
               }
           }
           return minHeap.peek();
       }
   }
   ```

   ```java
   // 使用PriorityQueue来实现的解法2
   class Solution {
       public int findKthLargest(int[] nums, int k) {
           PriorityQueue<Integer> heap = new PriorityQueue<>();
           for (int num : nums) {
               heap.add(num);
               if (heap.size() > k) {
                   heap.poll();
               }
           }
           return heap.peek();
       }
   }
   ```

   ```java
   // 手动构造一个堆
   class Solution {
       public int findKthLargest(int[] nums, int k) {
           buildHeap(nums,nums.length);
           int size=nums.length;
           for(int i=1;i<k;i++){
               swap(nums,0,size-1);
               size--;
               maxHeapify(nums,0,size);
           }
           return nums[0];
       }
   
       private void buildHeap(int[] nums,int size){
           for(int i=size/2-1;i>=0;i--){
               maxHeapify(nums,i,size);
           }
       }
   
       private void maxHeapify(int[] nums,int index, int size){
           int child=2*index+1;
           while (child<size){
               if(child<size-1&&nums[child]<nums[child+1]){
                   child++;
               }
               if(nums[index]>=nums[child]){
                   break;
               }
               swap(nums,index,child);
               index=child;
               child=2*child+1;
           }
       }
   
       private void swap(int[] nums,int i,int j){
           int temp=nums[i];
           nums[i]=nums[j];
           nums[j]=temp;
       }
   }
   ```

2. [347. 前 K 个高频元素 - 力扣（LeetCode）](https://leetcode.cn/problems/top-k-frequent-elements/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给你一个整数数组 `nums` 和一个整数 `k` ，请你返回其中出现频率前 `k` 高的元素。你可以按 **任意顺序** 返回答案。

   **解题思路：**首先遍历整个数组，并使用哈希表记录每个数字出现的次数，并形成一个「出现次数数组」。然后维护一个大小为K的小顶堆，然后遍历「出现次数数组」：

   - 如果堆的元素个数小于 k，就可以直接插入堆中。
   - 如果堆的元素个数等于 k，则检查堆顶与当前出现次数的大小。如果堆顶更大，说明至少有 k 个数字的出现次数比当前值大，故舍弃当前值；否则，就弹出堆顶，并将当前值插入堆中。

   **解题代码：**

   ```java
   // 官方的代码，把哈希表转换成一个大小为2的数组，第一个元素代表数组的值，第二个元素代表了该值出现的次数，然后再利用
   // PriorityQueue来优化
   class Solution {
       public int[] topKFrequent(int[] nums, int k) {
           Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
           for (int num : nums) {
               occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
           }
   
           // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
           PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
               public int compare(int[] m, int[] n) {
                   return m[1] - n[1];
               }
           });
           for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
               int num = entry.getKey(), count = entry.getValue();
               if (queue.size() == k) {
                   if (queue.peek()[1] < count) {
                       queue.poll();
                       queue.offer(new int[]{num, count});
                   }
               } else {
                   queue.offer(new int[]{num, count});
               }
           }
           int[] ret = new int[k];
           for (int i = 0; i < k; ++i) {
               ret[i] = queue.poll()[0];
           }
           return ret;
       }
   }
   ```

3. [295. 数据流的中位数 - 力扣（LeetCode）](https://leetcode.cn/problems/find-median-from-data-stream/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**实现 MedianFinder 类:

   - `MedianFinder() `初始化 `MedianFinder` 对象。
   - `void addNum(int num)` 将数据流中的整数 `num` 添加到数据结构中。
   - `double findMedian()` 返回到目前为止所有元素的中位数。与实际答案相差 `10-5` 以内的答案将被接受。

   **解题思路一（优先队列）：**

   ![image-20240221013842259](D:\Desktop\Leetcode\assets\image-20240221013842259.png)

   **解题代码：**

   ```java
   class MedianFinder {
       PriorityQueue<Integer> queMin;
       PriorityQueue<Integer> queMax;
   
       public MedianFinder() {
           queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
           queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
       }
       
       public void addNum(int num) {
           if (queMin.isEmpty() || num <= queMin.peek()) {
               queMin.offer(num);
               if (queMax.size() + 1 < queMin.size()) {
                   queMax.offer(queMin.poll());
               }
           } else {
               queMax.offer(num);
               if (queMax.size() > queMin.size()) {
                   queMin.offer(queMax.poll());
               }
           }
       }
       
       public double findMedian() {
           if (queMin.size() > queMax.size()) {
               return queMin.peek();
           }
           return (queMin.peek() + queMax.peek()) / 2.0;
       }
   }
   ```

   **解题思路二（有序集合 + 双指针）：**![image-20240221014009940](D:\Desktop\Leetcode\assets\image-20240221014009940.png)

   **解题代码：**

   ```java
   // 略……
   ```

   





## 技巧

1. [136. 只出现一次的数字 - 力扣（LeetCode）](https://leetcode.cn/problems/single-number/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给你一个 **非空** 整数数组 `nums` ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。

   **解题思路：**用异或运算：

   1. 交换律：a ^ b ^ c <=> a ^ c ^ b
   2. 任何数于0异或为任何数 0 ^ n => n
   3. 相同的数异或为0: n ^ n => 0

   **解题代码：**

   ```java
   class Solution {
       public int singleNumber(int[] nums) {
           int res=0;
           for(int num:nums){
               res^=num;
           }
           return res;
       }
   }
   ```

2. [169. 多数元素 - 力扣（LeetCode）](https://leetcode.cn/problems/majority-element/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给定一个大小为 `n` 的数组 `nums` ，返回其中的多数元素。多数元素是指在数组中出现次数 **大于** `⌊ n/2 ⌋` 的元素。

   **解题思路：**该题目的解法众多，这里只介绍最为巧妙的时间复杂度为 O(n)、空间复杂度为 O(1) 的算法(**Boyer-Moore 投票算法**)。![image-20240219183329622](D:\Desktop\Leetcode\assets\image-20240219183329622.png)

   **解题代码：**

   ```java
   class Solution {
       public int majorityElement(int[] nums) {
           int count = 0;
           Integer candidate = null;
   
           for (int num : nums) {
               if (count == 0) {
                   candidate = num;
               }
               count += (num == candidate) ? 1 : -1;
           }
   
           return candidate;
       }
   }
   ```

3. [287. 寻找重复数 - 力扣（LeetCode）](https://leetcode.cn/problems/find-the-duplicate-number/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**给定一个包含 `n + 1` 个整数的数组 `nums` ，其数字都在 `[1, n]` 范围内（包括 `1` 和 `n`），可知至少存在一个重复的整数。假设 `nums` 只有 **一个重复的整数** ，返回 **这个重复的数** 。你设计的解决方案必须 **不修改** 数组 `nums` 且只用常量级 `O(1)` 的额外空间

   **解题思路：**此题较难，直接看官方题解，三种思路（最后一种线性时间复杂度）[287. 寻找重复数 - 力扣（LeetCode）](https://leetcode.cn/problems/find-the-duplicate-number/solutions/261119/xun-zhao-zhong-fu-shu-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked)

   **解题代码：**

   ```java
   // 二分查找法
   class Solution {
       public int findDuplicate(int[] nums) {
           int n = nums.length;
           int l = 1, r = n - 1, ans = -1;
           while (l <= r) {
               int mid = (l + r) >> 1;
               int cnt = 0;
               for (int i = 0; i < n; ++i) {
                   if (nums[i] <= mid) {
                       cnt++;
                   }
               }
               if (cnt <= mid) {
                   l = mid + 1;
               } else {
                   r = mid - 1;
                   ans = mid;
               }
           }
           return ans;
       }
   }
   ```

   ```java
   // 二进制法
   class Solution {
       public int findDuplicate(int[] nums) {
           int n = nums.length, ans = 0;
           int bit_max = 31;
           while (((n - 1) >> bit_max) == 0) {
               bit_max -= 1;
           }
           for (int bit = 0; bit <= bit_max; ++bit) {
               int x = 0, y = 0;
               for (int i = 0; i < n; ++i) {
                   if ((nums[i] & (1 << bit)) != 0) {
                       x += 1;
                   }
                   if (i >= 1 && ((i & (1 << bit)) != 0)) {
                       y += 1;
                   }
               }
               if (x > y) {
                   ans |= 1 << bit;
               }
           }
           return ans;
       }
   }
   ```

   ```java
   // 快慢指针法
   class Solution {
       public int findDuplicate(int[] nums) {
           int slow = 0, fast = 0;
           do {
               slow = nums[slow];
               fast = nums[nums[fast]];
           } while (slow != fast);
           slow = 0;
           while (slow != fast) {
               slow = nums[slow];
               fast = nums[fast];
           }
           return slow;
       }
   }
   ```

4. [31. 下一个排列 - 力扣（LeetCode）](https://leetcode.cn/problems/next-permutation/description/?envType=study-plan-v2&envId=top-100-liked)

   **题目简述：**整数数组的 **下一个排列** 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 **下一个排列** 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

   - 例如，`arr = [1,2,3]` 的下一个排列是 `[1,3,2]` 。
   - 类似地，`arr = [2,3,1]` 的下一个排列是 `[3,1,2]` 。
   - 而 `arr = [3,2,1]` 的下一个排列是 `[1,2,3]` ，因为 `[3,2,1]` 不存在一个字典序更大的排列。

   给你一个整数数组 `nums` ，找出 `nums` 的下一个排列。

   必须**[ 原地 ](https://baike.baidu.com/item/原地算法)**修改，只允许使用额外常数空间

   **解题思路：**[31. 下一个排列 - 力扣（LeetCode）](https://leetcode.cn/problems/next-permutation/solutions/80560/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/?envType=study-plan-v2&envId=top-100-liked)

   ![image-20240219232201934](D:\Desktop\Leetcode\assets\image-20240219232201934.png)

   ![image-20240219232212766](D:\Desktop\Leetcode\assets\image-20240219232212766.png)

   <img src="D:\Desktop\Leetcode\assets\image-20240219232227788.png" alt="image-20240219232227788"  /><img src="D:\Desktop\Leetcode\assets\image-20240219232246705.png" alt="image-20240219232246705"  />

   ![image-20240219232425716](D:\Desktop\Leetcode\assets\image-20240219232425716.png)![image-20240219232439059](D:\Desktop\Leetcode\assets\image-20240219232439059.png)![image-20240219232455564](D:\Desktop\Leetcode\assets\image-20240219232455564.png)

   **解题代码：**

   ```java
   class Solution {
       public void nextPermutation(int[] nums) {
           if(nums.length<2){
               return;
           }
           int i= nums.length-2,j= nums.length-1;
           
           // find: A[i]<A[j]
           while (i>=0&&nums[i]>=nums[j]){
               i--;
               j--;
           }
           
           if(i>=0){ // 不是最后一个排列
               int k= nums.length-1;
               while (nums[i]>=nums[k]){
                   k--;
               }
               // swap A[i], A[k]
               int temp=nums[k];
               nums[k]=nums[i];
               nums[i]=temp;
           }
           
           // reverse A[j:end]
           i=j;
           j= nums.length-1;
           while (i<j){
               int temp=nums[j];
               nums[j]=nums[i];
               nums[i]=temp;
               i++;
               j--;
           }
       }
   }
   ```

   

