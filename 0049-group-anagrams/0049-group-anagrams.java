class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String ss = new String(ch);
            if(!map.containsKey(ss)){
                map.put(ss,new ArrayList<>());
            }
            map.get(ss).add(s);
        }
        return new ArrayList<>(map.values());
    }
}