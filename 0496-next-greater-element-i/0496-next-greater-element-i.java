class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        for(int i=0; i<n1; i++){
            int temp = nums1[i];
            for(int j=0; j<n2; j++){
                if(temp==nums2[j]){
                    for(int k=j; k<n2; k++){
                        if(nums2[k]>nums2[j]){
                            nums1[i] = nums2[k];
                            break;
                        }
                    }
                }
            }
            if(temp==nums1[i]){
                nums1[i] = -1;
            }
        }
        return nums1;
    }
}