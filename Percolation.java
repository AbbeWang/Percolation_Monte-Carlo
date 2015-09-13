import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] grid;
	private int N;
	private WeightedQuickUnionUF percolate;
	private WeightedQuickUnionUF full;

	
	public Percolation(int N) {
		// create N-by-N grid, with all sites blocked
		
		if (N <= 0) {
			throw new IllegalArgumentException();
		}
		
		this.grid = new int[N][N];
		this.N = N;
		this.percolate = new WeightedQuickUnionUF(N*N+2);
		this.full = new WeightedQuickUnionUF(N*N+1);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = 0;
			}
		}
		
	}               
	
	public void open(int i, int j) {
		// open site (row i, column j) if it is not open already
		
		if (i < 1 || i > N || j < 1 || j > N) {
			throw new IndexOutOfBoundsException();
		}		
		
		grid[i-1][j-1] = 1;  //for point(i,j)
				
		if (i > 1 && isOpen(i-1, j)) {
			percolate.union((i-1)*N+j-1, (i-2)*N+j-1);
			full.union((i-1)*N+j-1, (i-2)*N+j-1);
		}
		
		if (i == 1) {
			percolate.union(j-1, N*N);
			full.union(j-1, N*N);
		}
		
		if (i < N && isOpen(i+1, j)) {
			percolate.union((i-1)*N+j-1, (i)*N+j-1);
			full.union((i-1)*N+j-1, (i)*N+j-1);
		}	
		
		if (i == N) {
			percolate.union((i-1)*N+j-1, N*N+1);
		}
		
		if (j > 1 && isOpen(i, j-1)) {
			percolate.union((i-1)*N+j-1, (i-1)*N+j-2);
			full.union((i-1)*N+j-1, (i-1)*N+j-2);
		}
		
		if (j < N && isOpen(i, j+1)) {
			percolate.union((i-1)*N+j-1, (i-1)*N+j);
			full.union((i-1)*N+j-1, (i-1)*N+j);
		}	
	}
	
	public boolean isOpen(int i, int j) {		
		// is site (row i, column j) open?
		
		if (i < 1 || i > N || j < 1 || j > N) {
			throw new IndexOutOfBoundsException();
		}
		
		if (grid[i-1][j-1] == 0)
			return false;
		else
			return true;
	}
	
	public boolean isFull(int i, int j) {
		// is site (row i, column j) full?
		
		if (i < 1 || i > N || j < 1 || j > N) {
			throw new IndexOutOfBoundsException();
		}
		
		if (isOpen(i, j)) {
			if (i == 1)
				return true;
			else {
				
//				if (full.connected(N*N, (i-1)*N+j-1))
//					return true;
//				else
//					return false;
				
				return full.connected(N*N, (i-1)*N+j-1);

			}
			
		}
		else
			return false;
	}
	
	public boolean percolates() {
		// does the system percolate?
		
//		for (int k = 1; k <= N; k++) {
//			if (isFull(N, k))
//				return true;
//		}
//		if(percolate.connected(N*N, N*N+1))
//			return true;
		return percolate.connected(N*N, N*N+1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Percolation sampleGrid = new Percolation(3);
//		for(int i=0; i<11; i++){
//			System.out.println(sampleGrid.percolate.find(i));
//		}
//		
//		sampleGrid.open(1, 3);
//		System.out.println(sampleGrid.isFull(1, 3));
//		for(int i=0; i<11; i++){
//			System.out.println(sampleGrid.percolate.find(i));
//		}
//		
//		sampleGrid.open(2, 3);
//		System.out.println(sampleGrid.isFull(2, 3));
//		for(int i=0; i<11; i++){
//			System.out.println(sampleGrid.percolate.find(i));
//		}
//		
//		sampleGrid.open(3, 3);
//		System.out.println(sampleGrid.isFull(3, 3));		
//		for(int i=0; i<11; i++){
//			System.out.println(sampleGrid.percolate.find(i));
//		}
//		
//		
//		sampleGrid.open(3, 1);
//		System.out.println(sampleGrid.isFull(3, 1));
//		for(int i=0; i<11; i++){
//			System.out.println(sampleGrid.percolate.find(i));
//		}

	}

}
