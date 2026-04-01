class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> sti = new Stack<>();
        String s2="+-*/";
        for(String token:tokens){
            if(s2.contains(token)){
                int b = sti.pop();
                int a = sti.pop();
                if(token.equals("+")){
                    sti.push(a+b);
                }
                else if(token.equals("-")){
                    sti.push(a-b);
                }
                else if(token.equals("*")){
                    sti.push(a*b);
                }
                else{
                    sti.push(a/b);
                }
            }
            else{
                sti.push(Integer.parseInt(token));
            }
        }   
        return sti.pop();
    }
}