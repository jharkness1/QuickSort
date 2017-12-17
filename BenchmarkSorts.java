/* Jason Harkness
 * 10/10/2017
 * Project 1
 */

public class BenchmarkSorts {

	private int x;
	private int nSize[];
	private static int maxSize = 15000;
	private double iterativeData[][];
	private double recursiveData[][];
	

	public BenchmarkSorts(int sizes[]) {
		
		// Create 2d arrays to store count/time data
		x = sizes.length;
		nSize = sizes;
		iterativeData = new double[x][4];
		recursiveData = new double[x][4];
	}

	public void runSorts() throws UnsortedException{
		
		int sizeNum;
		int testNum;

		/* - For each data set size sort 50 test cases of randomly
		 *   generated numbers
		 * - Create variables and arrays to store critical count
		 *   and time measurements for each calculation
		 */
		
		for (sizeNum = 0; sizeNum < x; sizeNum++) {
			
			double iCount[] = new double[50];
			double avgIC = 0.0;
			double iTime[] = new double[50];
			double avgIT = 0.0;
			double rCount[] = new double[50];
			double avgRC = 0.0;
			double rTime[] = new double[50];
			double avgRT = 0.0;


			for (testNum = 0; testNum < 50; testNum++) {
				
				int s = nSize[sizeNum];
				int s1[] = new int[s];
				int s2[] = new int[s];
				int s3[] = new int[s];
				for (int i = 0; i < s; i++) {
					s3[i] = (int) (Math.random() * maxSize);
					s1[i] = s2[i] = s3[i];
				}
				
				// Run iterative Quicksort to sort numbers
				QuickSort iterObj = new QuickSort();
				iterObj.iterativeQuickSort(s2, 0, s3.length - 1);
				iCount[testNum] = iterObj.getCount();
				iTime[testNum] = iterObj.getTime();
				avgIC += iCount[testNum];
				avgIT += iTime[testNum];

				for (int i = 0; i < s - 1; i++) {
					if (s2[i] > s2[i + 1])
						throw new UnsortedException("Numbers unsorted");
				}
				
				// Run recursive Quicksort to sort numbers
				QuickSort recurObj = new QuickSort();
				recurObj.recursiveStart(s1, 0, s3.length - 1);
				rCount[testNum] = recurObj.getCount();
				rTime[testNum] = recurObj.getTime();
				avgRC += rCount[testNum];
				avgRT += rTime[testNum];

		
				for (int i = 0; i < s - 1; i++) {
					if (s1[i] > s1[i + 1])
						throw new UnsortedException("Numbers unsorted");
				}
			}

			avgRC /= 50;
			avgRT /= 50;
			avgIC /= 50;
			avgIT /= 50;

			double iterSDT = 0.0; 
			double recurSDT = 0.0;
			double iterSDC = 0.0; 
			double recurSDC = 0.0;

			// Caculate standard deviation for time and count
			for (testNum = 0; testNum < 50; testNum++) {
				iterSDT += (avgIT - iTime[testNum]) * (avgIT - iTime[testNum]);
				iterSDC += (avgIC - iCount[testNum]) * (avgIC - iCount[testNum]);
				recurSDT += (avgRT - rTime[testNum]) * (avgRT - rTime[testNum]);
				recurSDC += (avgRC - rCount[testNum]) * (avgRC - rCount[testNum]);
			}

			iterSDT = Math.sqrt(iterSDT / 50);
			iterSDC = Math.sqrt(iterSDC / 50);
			recurSDT = Math.sqrt(recurSDT / 50);
			recurSDC = Math.sqrt(recurSDC / 50);

			iterativeData[sizeNum][0] = avgIC;
			iterativeData[sizeNum][1] = iterSDC;
			iterativeData[sizeNum][2] = avgIT;
			iterativeData[sizeNum][3] = iterSDT;
			recursiveData[sizeNum][0] = avgRC;
			recursiveData[sizeNum][1] = recurSDC;
			recursiveData[sizeNum][2] = avgRT;
			recursiveData[sizeNum][3] = recurSDT;

		}
	}

	//Print out data set size and calculations for each
	public void displayReports() {
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------- \n"
				+ "| Data |            Iterative                              |              Recursive                            |\n"
				+ "| Set  |                                                   |                                                   |\n"
				+ "| Size |                                                   |                                                   |\n"
				+ "| n    |                                                   |                                                   |\n"
				+ "|------|---------------------------------------------------|---------------------------------------------------|\n"
				+ "|      | Average    | Standard   | Average    | Standard   | Average    | Standard   | Average    | Standard   |\n"
				+ "|      | Critical   | Deviation  | Execution  | Deviation  | Critical   | Deviation  | Execution  | Deviation  |\n"
				+ "|      | Operation  | of Count   | Time       | of Time    | Operation  | of Count   | Time       | of Time    |\n"
				+ "|      | Count      |            |            |            | Count      |            |            |            |\n"
				+ "|------|---------------------------------------------------|---------------------------------------------------|");

		for (int i = 0; i < x; i++) {
			System.out.printf("| %4d | ", nSize[i]);
			for (int j = 0; j < 4; j++) {
				System.out.printf("%10.2f | ", iterativeData[i][j]);
			}
			for (int j = 0; j < 4; j++) {
				System.out.printf("%10.2f | ", recursiveData[i][j]);
			}
			System.out.println();
			System.out.println(
				  "----------------------------------------------------------------------------------------------------------------");
		}

	}

}
