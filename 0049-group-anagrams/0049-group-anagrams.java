class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            int[] chara = new int[26];
            for(char ch:s.toCharArray()){
                chara[ch-'a']++;
            }
            String ss = Arrays.toString(chara);
            if(!map.containsKey(ss)){
                map.put(ss,new ArrayList<>());
            }
            map.get(ss).add(s);
        }
        return new ArrayList<>(map.values());
    }
}