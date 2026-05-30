class Solution {

    int[][] directions = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        // Pacific DFS
        for(int r = 0; r < rows; r++) {
            dfs(heights, pacific, r, 0);
            dfs(heights, atlantic, r, cols - 1);
        }

        for(int c = 0; c < cols; c++) {
            dfs(heights, pacific, 0, c);
            dfs(heights, atlantic, rows - 1, c);
        }

        List<List<Integer>> result = new ArrayList<>();

        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {

                if(pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights,
                     boolean[][] visited,
                     int row,
                     int col) {

        if(visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        for(int[] dir : directions) {

            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if(newRow < 0 || newRow >= heights.length ||
               newCol < 0 || newCol >= heights[0].length) {
                continue;
            }

            if(heights[newRow][newCol] < heights[row][col]) {
                continue;
            }

            dfs(heights, visited, newRow, newCol);
        }
    }
}