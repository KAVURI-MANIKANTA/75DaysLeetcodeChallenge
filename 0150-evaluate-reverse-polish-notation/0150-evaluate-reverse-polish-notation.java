class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> sti = new Stack<>();
        for(String t:tokens){
            if(isOperator(t)){
                int b = sti.pop();
                int a = sti.pop();
                int res = result(a,b,t);
                sti.push(res);
            }
            else sti.push(Integer.parseInt(t));
        }
        return sti.pop();
    }
    public boolean isOperator(String t){
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }
    public int result(int a, int b, String op){
        if(op.equals("+")) return a+b;
        if(op.equals("-")) return a-b;
        if(op.equals("*")) return a*b;
        return a/b;
    }
}