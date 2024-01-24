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

   
