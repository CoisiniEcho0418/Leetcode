class Solution {
    public int trap(int[] height) {
        int total=0;
        int len = height.length;
        int left=-1,right = len;
        if(len<3){
            return 0;
        }
        // 从左往右循环，边计算左边能接的雨水量（相对于最高柱子而言），边记录最高的柱子下标
        for(int i=0;i<len;i++){
            if(left==-1 && i<len-1 && height[i]>height[i+1]){
                left=i;
                continue;
            }
            // 从左往右递增，找到后面比现在高的柱子，计算接雨量
            if(left>=0 && height[i]>=height[left]){
                for(int j=left+1;j<i;j++){
                    total+=height[left]-height[j];
                }
                left=i;
            }
        }
        if(left<0){
            return 0;
        }
        // 此时 left 即最高的下标(只需循环到最高柱子的下标即可)，从右往左计算右边的接雨量
        for(int i=len-1;i>=left;i--){
            if(right==len && i>0 && height[i]>height[i-1]){
                right=i;
                continue;
            }
            // 右边和左边的逻辑刚好相反，从右往左递增，找到前面比现在高的柱子，计算接雨量
            if(right<len&&height[i]>=height[right]){
                for(int j=i+1;j<right;j++){
                    total+=height[right]-height[j];
                }
                right=i;
            }
        }
        return total;
    }
}
