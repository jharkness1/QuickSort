/* Jason Harkness
 * 10/10/2017
 * Project 1
 */

public class QuickSortMain {

	public static void main(String[] args) {
		
		try {
			// 10 different sizes of data sets
			int sizeN[] = { 5, 10, 25, 50, 100, 200, 500, 750, 1000, 2000 };

			BenchmarkSorts ob = new BenchmarkSorts(sizeN);
			ob.runSorts();
			ob.displayReports();

		} catch (UnsortedException e) {
			System.out.println(e.getMessage());
		}

	}
}
