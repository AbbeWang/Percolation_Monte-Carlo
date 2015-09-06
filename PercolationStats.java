import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
//	int N;
	private int T;	
	private double[] results;
	
	public PercolationStats(int N, int T) throws IllegalArgumentException {
		// perform T independent experiments on an N-by-N grid
		
//		this.N = N;
		
		if(N<=0 || T<=0){
			throw new IllegalArgumentException();
		}
		
		this.T = T;
		this.results = new double[T];
		
		for(int k=0; k<T; k++){
			int countOpen = 0;
			Percolation sampleGrid = new Percolation(N);
			
			while(!sampleGrid.percolates()){
				int i,j;
				
				do{
					i = StdRandom.uniform(1, N+1);
					j = StdRandom.uniform(1, N+1);
				} while (sampleGrid.isOpen(i, j));
				
				sampleGrid.open(i, j);
				countOpen ++;
			}
			
			results[k] = 1.0 * countOpen/(N*N);
		}
	}
	
	public double mean(){
		return StdStats.mean(results);
		// sample mean of percolation threshold
	}
	
	public double stddev(){		
		return StdStats.stddev(results);
		// sample standard deviation of percolation threshold
	}
	
	public double confidenceLo(){
		double confHi = mean() - 1.96*stddev()/Math.sqrt(T);		
		return confHi;
		// low  endpoint of 95% confidence interval
	}
	
	public double confidenceHi(){
		double confHi = mean() + 1.96*stddev()/Math.sqrt(T);		
		return confHi;
		// high endpoint of 95% confidence interval
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PercolationStats test = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
//		PercolationStats test = new PercolationStats(10,5);
		Stopwatch stopwatch = new Stopwatch();
		
		System.out.println("mean                    = " + test.mean());
		System.out.println("stddev                  = " + test.stddev());
		System.out.println("95% confidence interval = " + test.confidenceLo() +", "+ test.confidenceHi());
		
		double time = stopwatch.elapsedTime();
		System.out.println("\nElapsed time: " + time);
	}

}
