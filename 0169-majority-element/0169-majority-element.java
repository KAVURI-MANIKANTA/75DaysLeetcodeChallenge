class Solution {
    public int majorityElement(int[] nums) {
        int cand = 0;
        int cnt = 0;
        for(int num:nums){
            if(cnt==0){
                cnt++;
                cand = num;
            }
            else if(cand==num){
                cnt++;
            }
            else{
                cnt--;
            }
        }
        return cand;
    }
}