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
           if (cur == nums.length) {
               // 记录答案
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

   
