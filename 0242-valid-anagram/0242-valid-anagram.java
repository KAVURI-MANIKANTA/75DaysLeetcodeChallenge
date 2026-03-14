class Solution {
    public boolean isAnagram(String s, String t) {
        int sl = s.length();
        int tl = t.length();
        if(sl!=tl){
            return false;
        }
        HashMap<Character,Integer> shm = new HashMap<>();
        HashMap<Character,Integer> thm = new HashMap<>();
        for(int i=0; i<sl; i++){
            char ch = s.charAt(i);
            shm.put(ch,shm.getOrDefault(ch,0)+1);
        }
        for(int i=0; i<tl; i++){
            char ch = t.charAt(i);
            thm.put(ch,thm.getOrDefault(ch,0)+1);
        }
        if(shm.size()!=thm.size()){
            return false;
        }
        for(char ch:shm.keySet()){
            if(!thm.containsKey(ch)){
                return false;
            }
            if(!shm.get(ch).equals(thm.get(ch))){
                return false;
            }
        }
        return true;
    }
}