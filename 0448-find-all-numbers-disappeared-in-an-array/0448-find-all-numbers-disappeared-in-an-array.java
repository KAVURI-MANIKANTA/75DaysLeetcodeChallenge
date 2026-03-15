class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Arrays.sort(nums);
        List<Integer> li = new ArrayList<>();
        HashSet<Integer> hs = new HashSet<>();
        int n = nums.length;
        for(int i=0; i<n; i++){
            hs.add(nums[i]);
        }
        for(int i=0; i<n; i++){
            if(!hs.contains(i+1)){
                li.add(i+1);
            }
        }
        return li;
    }
}