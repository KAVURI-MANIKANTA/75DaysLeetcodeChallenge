class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char task : tasks) {
            freq[task - 'A']++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int f : freq) {
            if(f > 0) {
                pq.offer(f);
            }
        }
        Queue<int[]> cooldown = new LinkedList<>();
        int time = 0;
        while(!pq.isEmpty() || !cooldown.isEmpty()) {
            time++;
            if(!pq.isEmpty()) {
                int count = pq.poll();
                count--;
                if(count > 0) {
                    cooldown.offer(
                        new int[]{count, time + n}
                    );
                }
            }
            if(!cooldown.isEmpty() &&
               cooldown.peek()[1] == time) {

                pq.offer(cooldown.poll()[0]);
            }
        }
        return time;
    }
}