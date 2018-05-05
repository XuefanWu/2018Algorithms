//Heap
313. Super Ugly Number
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Number> pq = new PriorityQueue<Number>((Number a, Number b)->(a.val-b.val));
        for(int i = 0; i < primes.length; i++){
            pq.offer(new Number(primes[i],1,primes[i]));
        }
        int[] res = new int[n];
        res[0] = 1;
        for(int i = 1; i < n; i++){
            res[i] = pq.peek().val;
            while(pq.peek().val == res[i]){
                Number next = pq.poll();
                pq.offer(new Number(next.prime*res[next.pos],next.pos+1,next.prime));
            }
        }
        return res[n-1];
    }

    class Number{
        int val;
        int pos;
        int prime;
        Number(int val, int pos, int prime){
            this.val = val;
            this.pos = pos;
            this.prime = prime;
        }
    }
}

//(Two pointer)
42. Trapping Rain Water
class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length-1;
        int leftMax = 0, rightMax = 0;
        int res = 0;
        while(left<=right){
            leftMax = Math.max(height[left],leftMax);
            rightMax = Math.max(height[right],rightMax);
            if(leftMax < rightMax){ //for this cube, b/c rightMax > leftMax, so the right wall is there, it can at least store height[left]-leftMax
                res+=leftMax-height[left];
                left++;
            }else{
                res+=rightMax-height[right];
                right--;
            }
        }
        return res;
    }
}

407. Trapping Rain Water II
class Solution {
    class Cell{
        int row;
        int col;
        int height;
        public Cell(int row, int col, int height){
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        int res = 0;
        if(heightMap == null || heightMap.length == 0 || heightMap[0].length == 0 ) return res;
        PriorityQueue<Cell> pq = new PriorityQueue<Cell>((Cell a, Cell b)->(a.height-b.height));
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            visited[i][0] = true;
            visited[i][n-1] = true;
            pq.offer(new Cell(i,0,heightMap[i][0]));
            pq.offer(new Cell(i,n-1,heightMap[i][n-1]));
        }
        for(int i = 0; i < n; i++){
            visited[0][i] = true;
            visited[m-1][i] = true;
            pq.offer(new Cell(0,i,heightMap[0][i]));
            pq.offer(new Cell(m-1,i,heightMap[m-1][i]));
        }
        int[][] dirs = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
       
        while(!pq.isEmpty()){
            Cell temp = pq.poll();
            for(int[] dir : dirs){
                int row = temp.row+dir[0];
                int col = temp.col+dir[1];
                if(row >=0 && row <m && col >=0 && col < n && !visited[row][col]){
                    visited[row][col] = true;
                    res += Math.max(0,temp.height-heightMap[row][col]);
                    pq.offer(new Cell(row,col,Math.max(heightMap[row][col],temp.height)));
                }
            }
        } 
        return res;
    }
    
}