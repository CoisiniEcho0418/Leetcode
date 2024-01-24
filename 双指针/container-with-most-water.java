class Solution {
    public int maxArea(int[] height) {
        int maxV=0;
        // 初始情况下容器底最大
        int left=0, right=height.length-1;
        // 左右指针往里缩的时候底变小，所以要期望高变大
        while(left<right){
            int minHeight = Math.min(height[left], height[right]);
            maxV=Math.max(maxV,minHeight*(right-left));
            // 总是移动高度较小那一边的指针，以期获得更大的高
            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxV;
    }
}