
public class Sorts {
	
	public static int getMax(int[] a, int range){
		int max = a[0];
		
		for(int i = 1; i < range; i++){
			if(a[i] > max){
				max = a[i];
			}
		}
		
		return max;
	}

	public static void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}
	
	public static int makePartition(int[] a, int index1, int index2){
		int pivotPoint = a[index1];
		int border = index1;
		
		for(int i = index1 + 1; i <= index2 ; i++){
			if (a[i] <= pivotPoint){
				border++;
				swap(a, border, i);
			}
		}
		
		swap(a, index1, border);
		
		return border;
		
	}
	
	public static void merge(int a[] , int x, int y, int mid){
		int sizeOfArray = y - x + 1;
		int leftPointer = x;
		int rightPointer = mid + 1;
		int[] tempArray = new int[a.length];
		
		
		for(int i = 0 ; i < sizeOfArray ; i++){
			if(leftPointer > mid){
				tempArray[i] = a[rightPointer++];
			}
			
			else{
				if(rightPointer > y ||	a[leftPointer] < a[rightPointer]){
					tempArray[i] = a[leftPointer++];
				}
				
				else{
					tempArray[i] = a[rightPointer++];
				}
			}
			
		}
		
		for (int j = 0 ; j <sizeOfArray ; j++){
			a[x + j] = tempArray[j];
		}
	}

	public static void BubbleSort(int[] a) {
		// outer gives us the bounds for the unsorted portion of the list
		// basically find largest element in unsorted array and append it to the
		// beginning of the sorted list
		// after each iteration, the sorted list will expand towards the
		// beginning of the original list

		int allSwaps = 0;
		for (int outer = a.length - 1; outer >= 0; outer--) {
			for (int inner = 0; inner < outer; inner++) {
				if (a[inner] > a[inner + 1]) {
					swap(a, inner, inner + 1);
					allSwaps++;
				}
			}

			if (allSwaps == 0) {
				return;
			}

			allSwaps = 0;

		}
	}

	public static void SelectionSort(int[] a) {
		// basically find the smallest element in unsorted array and append it
		// to the end of the sorted list
		// (the oppossite of bubblesort)

		for (int outer = 0; outer < a.length - 1; outer++) {
			int minIndex = outer;

			for (int inner = outer + 1; inner < a.length; inner++) {
				if (a[inner] < a[minIndex]) {
					minIndex = inner;
				}
			}

			swap(a, outer, minIndex);
		}

	}

	public static void InsertionSort(int[] a) {
		// iterate through the array and create a sorted list with the elements
		// you already have


		for (int i = 1; i < a.length; i++) {
			int valToInsert = a[i];
			int j;

			for (j = i - 1; j >= 0; j--) {
				if (a[j] > valToInsert) {
					a[j + 1] = a[j];
				} else {
					break;
				}
			}

			a[j + 1] = valToInsert;
		}
	}
	
	public static void QuickSort(int[] a, int x, int y){
		int pivot; 
		
		if( y - x > 0){
			pivot = makePartition(a, x, y);
			// pivot is now sorted
			QuickSort(a, x, pivot - 1);
			QuickSort(a, pivot + 1, y);
		}
	}
	
	public static void MergeSort(int[] a , int x, int y){
		int mid = (x+y) / 2;
		
		if(x != y){
			MergeSort(a, x, mid);
			MergeSort(a , mid + 1, y);
			merge(a, x, y, mid);
		}
	}
	
	// aux method in radix sort. Not really the actual counting sort
	public static void countSort(int[] a, int n, int position){
		int[] output = new int[n];
		int[] count = new int[10];
		
		for(int i = 0; i < n ; i++){
			count[(a[i] / position) % 10]++;
		}
		
		// calculate positions in the final array
		// basically counting how many times smaller keys occur before the current one
		for (int i = 1; i < 10 ; i++){
			count[i] += count[i - 1];
		}
		
		for(int i = n-1; i >= 0 ; i--){
			output[count[(a[i] / position ) % 10] - 1] = a[i];
			count[(a[i]/ position) % 10]--;
		}
		
		for(int i = 0; i < n; i++){
			a[i] = output[i];
		}
	}
	
	
	public static void radixSort(int[] a, int n){
		
		int m = getMax(a, n);
		
		for(int i = 1; m/i > 0 ; i*=10){
			countSort(a, n, i);
		}
	}

	public static void main(String[] args) {
		int[] a = { 12, 2, 0, 9, 7, 8, 13 };

		BubbleSort(a);

		for (int i : a) {
			System.out.print(i + " ");
		}

		System.out.println("");

		a = new int[] { 12, 2, 0, 9, 7, 8, 13 };

		SelectionSort(a);

		for (int i : a) {
			System.out.print(i + " ");
		}
		
		System.out.println("");
		
		a = new int[] { 12, 2, 0, 9, 7, 8, 13 };

		InsertionSort(a);

		for (int i : a) {
			System.out.print(i + " ");
		}
		
		System.out.println("");
		
		a = new int[] { 12, 2, 0, 9, 7, 8, 13 };

		QuickSort(a, 0 , a.length-1);

		for (int i : a) {
			System.out.print(i + " ");
		}
		
		System.out.println("");
		
		a = new int[] { 12, 2, 0, 9, 7, 8, 13 };

		MergeSort(a, 0 , a.length-1);

		for (int i : a) {
			System.out.print(i + " ");
		}
		
		System.out.println("");

		a = new int[] { 12, 2, 0, 9, 7, 8, 13 };

		radixSort(a, a.length);

		for (int i : a) {
			System.out.print(i + " ");
		}
	}

}
