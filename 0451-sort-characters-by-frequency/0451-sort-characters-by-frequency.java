class Solution {
    public String frequencySort(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        for(char ch:s.toCharArray()){
            hm.put(ch,hm.getOrDefault(ch,0)+1);
        }
        List<Character>[] bucket = new ArrayList[s.length()+1];
        for(char ch:hm.keySet()){
            int freq = hm.get(ch);
            if(bucket[freq]==null){
                bucket[freq]=new ArrayList<>();
            }
            bucket[freq].add(ch);
        }
        StringBuilder sbr = new StringBuilder();
        for(int i=bucket.length-1; i>=0; i--){
            if(bucket[i]!=null){
                for(char ch:bucket[i]){
                    int freq = i;
                    while(freq>0){
                        sbr.append(ch);
                        freq--;
                    }
                }
            }
        }
        return sbr.toString();
    }
}

/*
class Solution {
    public String frequencySort(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->(b.v-a.v));
        int n = s.length()-1;
        for(int i=0; i<=n; i++){
            char ch = s.charAt(i);
            hm.put(ch,hm.getOrDefault(ch,0)+1);
        }
        for(char ch:hm.keySet()){
            pq.add(new Pair(ch,hm.get(ch)));
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            int li = pq.peek().v;
            while(li>0){
                sb.append(pq.peek().ch);
                li--;
            }
            pq.poll();
        }
        return sb.toString();
    }
    class Pair{
        char ch;
        int v;
        Pair(char ch,int v){
            this.ch=ch;
            this.v=v;
        }
    }
}
*/

/*
class Solution {
    public String frequencySort(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        for(char ch:s.toCharArray()){
            hm.put(ch,hm.getOrDefault(ch,0)+1);
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((a,b)->(hm.get(b)-hm.get(a)));
        pq.addAll(hm.keySet());
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            char ch = pq.poll();
            int l = hm.get(ch);
            while(l>0){
                sb.append(ch);
                l--;
            }
        }
        return sb.toString();
    }
}
*/