class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {

        TrieNode root = buildTrie(words);

        List<String> result = new ArrayList<>();

        int rows = board.length;
        int cols = board[0].length;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    public void dfs(char[][] board, int i, int j,
                    TrieNode node, List<String> result) {

        // boundary
        if(i < 0 || j < 0 ||
           i >= board.length ||
           j >= board[0].length) {
            return;
        }

        char ch = board[i][j];

        // visited or char not present
        if(ch == '#' || node.children[ch - 'a'] == null) {
            return;
        }

        node = node.children[ch - 'a'];

        // word found
        if(node.word != null) {
            result.add(node.word);

            // avoid duplicates
            node.word = null;
        }

        // mark visited
        board[i][j] = '#';

        // 4 directions
        dfs(board, i + 1, j, node, result);
        dfs(board, i - 1, j, node, result);
        dfs(board, i, j + 1, node, result);
        dfs(board, i, j - 1, node, result);

        // backtrack
        board[i][j] = ch;
    }

    public TrieNode buildTrie(String[] words) {

        TrieNode root = new TrieNode();

        for(String word : words) {

            TrieNode curr = root;

            for(char ch : word.toCharArray()) {

                int index = ch - 'a';

                if(curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }

                curr = curr.children[index];
            }

            curr.word = word;
        }

        return root;
    }
}