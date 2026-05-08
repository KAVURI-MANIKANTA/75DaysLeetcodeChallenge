class Solution {
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character,Integer> hm = new HashMap<>();
        HashMap<Character,Integer> hm1 = new HashMap<>();
        int sn1 = s1.length();
        int sn2 = s2.length();
        if(sn1>sn2) return false;
        for(int i=0; i<sn1; i++){
            char ch = s1.charAt(i);
            char ch1 = s2.charAt(i);
            hm.put(ch,hm.getOrDefault(ch,0)+1);
            hm1.put(ch1,hm1.getOrDefault(ch1,0)+1);
        }
        int le=0;
        if(hm.equals(hm1)) return true;
        for(int r=sn1; r<sn2; r++){
            char ch = s2.charAt(r);
            char ch1 = s2.charAt(le);
            hm1.put(ch,hm1.getOrDefault(ch,0)+1);
            hm1.put(ch1,hm1.get(ch1)-1);
            le++;
            if(hm1.get(ch1)==0) hm1.remove(ch1);
            if(hm.equals(hm1)) return true;
        }
        return false;
    }
}