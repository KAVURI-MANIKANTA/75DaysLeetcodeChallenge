class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        int cnt1 = 0, cnt2 = 0;
        int cand1 = 0, cand2 = 0;
        for(int num:nums){
            if(num==cand1){
                cnt1++;
            }
            else if(num==cand2){
                cnt2++;
            }
            else if(cnt1==0){
                cnt1++;
                cand1 = num;
            }
            else if(cnt2==0){
                cnt2++;
                cand2 = num;
            }
            else{
                cnt1--;
                cnt2--;
            }
        }
        cnt1=0;
        cnt2=0;
        for(int num:nums){
            if(num==cand1) cnt1++;
            else if(num==cand2) cnt2++;
        }
        if(cnt1>(n/3)) res.add(cand1);
        if(cnt2>(n/3)) res.add(cand2);
        return res;
    }
}