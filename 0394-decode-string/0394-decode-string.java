class Solution {
    public String decodeString(String s) {
        Stack<Integer> ns = new Stack<>();
        Stack<StringBuilder> ss = new Stack<>();
        int num = 0;
        StringBuilder curr = new StringBuilder();
        for(char ch:s.toCharArray()){
            if(Character.isDigit(ch)){
                num = num*10+(ch-'0');
            }
            else if(ch=='['){
                ns.push(num);
                ss.push(curr);
                num = 0;
                curr = new StringBuilder();
            }
            else if(ch==']'){
                int repeat = ns.pop();
                StringBuilder prev = ss.pop();
                for(int i=0; i<repeat; i++){
                    prev.append(curr);
                }
                curr = prev;
            }
            else{
                curr.append(ch);
            }
        }
        return curr.toString();
    }
}