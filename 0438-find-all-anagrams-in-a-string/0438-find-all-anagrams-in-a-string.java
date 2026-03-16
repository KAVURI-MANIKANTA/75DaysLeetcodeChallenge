class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if(p.length()>s.length()) return new ArrayList<>();
        HashMap<Character,Integer> hms = new HashMap<>();
        HashMap<Character,Integer> hmp = new HashMap<>();
        for(int i=0; i<p.length(); i++){
            hmp.put(p.charAt(i),hmp.getOrDefault(p.charAt(i),0)+1);
        }
        for(int i=0; i<p.length(); i++){
            hms.put(s.charAt(i),hms.getOrDefault(s.charAt(i),0)+1);
        }
        List<Integer> li = new ArrayList<>();
        if(hmp.equals(hms)) li.add(0);

        for(int i=p.length(); i<s.length(); i++){
            hms.put(s.charAt(i),hms.getOrDefault(s.charAt(i),0)+1);
            hms.put(s.charAt(i-p.length()),hms.get(s.charAt(i-p.length()))-1);
            if(hms.get(s.charAt(i-p.length()))==0){
                hms.remove(s.charAt(i-p.length()));
            }
            if(hms.equals(hmp)){
                li.add(i-p.length()+1);
            }
        }
        return li;
    }
}