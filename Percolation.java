import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] grid;
	private int N;
	private WeightedQuickUnionUF percolate;

	
	public Percolation(int N) throws IllegalArgumentException {
		// create N-by-N grid, with all sites blocked
		
		if(N <= 0){
			throw new IllegalArgumentException();
		}
		
		this.grid = new int[N][N];
		this.N = N;
		this.percolate = new WeightedQuickUnionUF(N*N);
		
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				grid[i][j] = 0;
			}
		}
		
	}               
	
	public void open(int i, int j) throws IndexOutOfBoundsException {
		// open site (row i, column j) if it is not open already
		
		if(i<1 || i>N || j<1 || j>N){
			throw new IndexOutOfBoundsException();
		}		
		
		grid[i-1][j-1] = 1;  //for point(i,j)
				
		if(i>1 && isOpen(i-1, j))
			percolate.union((i-1)*N+j-1, (i-2)*N+j-1);
		
		if(i<N && isOpen(i+1, j))
			percolate.union((i-1)*N+j-1, (i)*N+j-1);
		
		if(j>1 && isOpen(i, j-1))
			percolate.union((i-1)*N+j-1, (i-1)*N+j-2);
		
		if(j<N && isOpen(i, j+1))
			percolate.union((i-1)*N+j-1, (i-1)*N+j);
	}
	
	public boolean isOpen(int i, int j){		
		// is site (row i, column j) open?
		
		if(i<1 || i>N || j<1 || j>N){
			throw new IndexOutOfBoundsException();
		}
		
		if(grid[i-1][j-1] == 0)
			return false;
		else
			return true;
	}
	
	public boolean isFull(int i, int j){
		// is site (row i, column j) full?
		
		if(i<1 || i>N || j<1 || j>N){
			throw new IndexOutOfBoundsException();
		}
		
		if(isOpen(i,j)){
			if(i==1)
				return true;
			else{
				for(int k=1; k<=N; k++){
					if(isOpen(1,k) && percolate.connected(k-1, (i-1)*N+j-1))
						return true;
				}
			}
		}
		return false;
	}
	
	public boolean percolates(){
		// does the system percolate?
		
		for(int k=1; k<=N; k++){
			if(isFull(N,k))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
