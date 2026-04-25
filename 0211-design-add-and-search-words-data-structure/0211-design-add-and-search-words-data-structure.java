class WordDictionary {
    class TrieNode{
        TrieNode[] next;
        boolean isEnd;
        TrieNode(){
            next = new TrieNode[26];
            isEnd = false;
        }
    }
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for(char ch:word.toCharArray()){
            int index = ch-'a';
            if(node.next[index]==null){
                node.next[index] = new TrieNode();
            }
            node = node.next[index];
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        return dfs(word,0,root);
    }
    public boolean dfs(String word, int i, TrieNode root){
        if(root==null) return false;
        if(i==word.length()) return root.isEnd;
        char ch = word.charAt(i);
        if(ch!='.'){
            return dfs(word,i+1,root.next[ch-'a']);
        }
        else{
            for(int k=0; k<26; k++){
                if(root.next[k]!=null){
                    if(dfs(word,i+1,root.next[k])) return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */