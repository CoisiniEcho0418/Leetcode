import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length-k+1];
        Deque<Integer> deque = new LinkedList<>(); // 单调队列，存储下标
        for(int i=0;i<k;i++){
            while(!deque.isEmpty()&&nums[i]>nums[deque.getLast()]){
                deque.removeLast();
            }
            deque.addLast(i);
        }
        res[0]=nums[deque.getFirst()];
        for (int i=k;i< nums.length;i++){
            // 清理队尾
            while(!deque.isEmpty()&&nums[i]>nums[deque.getLast()]){
                deque.removeLast();
            }
            deque.addLast(i);
            // 判断队首是否还在滑动窗口内
            while (deque.getFirst()<=i-k){
                deque.removeFirst();
            }
            res[i-k+1]=nums[deque.getFirst()];
        }
        return res;
    }
}