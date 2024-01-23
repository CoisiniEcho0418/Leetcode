# Leetcode做题笔记

------

## 哈希

1. [49. 字母异位词分组 - 力扣（LeetCode）](https://leetcode.cn/problems/group-anagrams/description/) 

   **题目简述：**将属于同一单词的不同字母排序结果整理到一起，输出一个`List<List<String>>`。

   **解题思路1：**对每个输入的字符串按字母排序（sort）之后，再比对已有的Hash表，来判断是否属于同一个单词。这里排序是关键，可以省去一个个字母比对的时间。**（在字符串和数组当中，当每个元素的顺序不重要时，可以使用排序后的字符串或数组作为键）**

   > - **注意，sort的关键是用`Arrays.sort(char[])`**
   > - 个人题解放在hash目录下`.\哈希\group-anagrams.java`

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

   

