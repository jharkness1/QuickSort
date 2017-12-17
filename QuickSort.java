/* Jason Harkness
 * 10/10/2017
 * Project 1
 * Reference for Quicksort Algorithm: http://www.geeksforgeeks.org/iterative-quick-sort/
 */

import java.util.Stack;

public class QuickSort implements QuickSortInterface {
	
	private int count = 0;
	long startTime;
	
	    void swap(int arr[],int i,int j)
	    {
	        int t = arr[i];
	        arr[i] = arr[j];
	        arr[j] = t;
	    }
	 
	    /* This function is same in both iterative and
	       recursive*/
	    int partition (int arr[], int l, int h)
	    {
	        int x = arr[h];
	        int i = (l - 1);
	 
	        for (int j = l; j <= h- 1; j++)
	        {
	            if (arr[j] <= x)
	            {
	                i++;
	                // swap arr[i] and arr[j]
	                swap(arr,i,j);
	            }
	        }
	        // swap arr[i+1] and arr[h]
	        swap(arr,i+1,h);
	        return (i + 1);
	    }
	 
	    // Sorts arr[l..h] using iterative QuickSort
	    public void iterativeQuickSort(int arr[], int l, int h)
	    {
	    	// set start time for efficiency measurements
	    	startTime = System.currentTimeMillis();
	    	
	        // create auxiliary stack
	        int stack[] = new int[h-l+1];
	 
	        // initialize top of stack
	        int top = -1;
	 
	        // push initial values in the stack
	        stack[++top] = l;
	        stack[++top] = h;
	 
	        // keep popping elements until stack is not empty
	        while (top >= 0)
	        {
	            // pop h and l
	            h = stack[top--];
	            l = stack[top--];
	 
	            // set pivot element at it's proper position
	            int p = partition(arr, l, h);
		        count++;
	            
	            // If there are elements on left side of pivot,
	            // then push left side to stack
	            if ( p-1 > l )
	            {
	                stack[ ++top ] = l;
	                stack[ ++top ] = p - 1;
	            }
	 
	            // If there are elements on right side of pivot,
	            // then push right side to stack
	            if ( p+1 < h )
	            {
	                stack[ ++top ] = p + 1;
	                stack[ ++top ] = h;
	            }
	       }
	        
	    }
	    
	    public void recursiveStart(int arr[], int l, int h) 
	    {
	    	// set start time for efficiency measurements
	    	startTime = System.currentTimeMillis();
	    	recursiveQuickSort(arr, l, h);
	    }
	 
	    private void recursiveQuickSort(int arr[], int l, int h)
	    {
	    	
	    	
	        if (l < h)
	        {
	            /* pi is partitioning index, arr[pi] is
	              now at right place */
	            int pi = partition(arr, l, h);
		        count++;
	            // Recursively sort elements before
	            // partition and after partition
	            recursiveQuickSort(arr, l, pi-1);
	            recursiveQuickSort(arr, pi+1, h);
	        }

	    }
	 
	    public int getCount() {
	    	return count;
	    }
	    
	    public long getTime() {
	    	return System.currentTimeMillis() - startTime;
	    }
	}

	

