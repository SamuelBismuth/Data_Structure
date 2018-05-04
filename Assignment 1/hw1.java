package hw1;

import java.util.Arrays;

public class hw1 {

	/**
	 * Compexity : O(r - l)
	 */
	private static int FindMax(int[] arr, int l, int r) {
		if((l > r) || (l < 0)) {
			System.out.println("You've made a mistake in your input !");
			return -1;
		}
		int ans = l;
		while(l != r - 1 && l < arr.length - 1) {
			if(arr[ans] < arr[l+1]) ans = l+1;
			l++;	
		}
		if(ans > arr.length - 1) ans = arr.length - 1;
		return ans;
	}
	
	/**
	 * Complexity : O(n*sqrt(n))
	 */
	public static void squarerootSort(int[] A1) { 
		int squareroot = (int) Math.round(Math.sqrt(A1.length)), numberOfTheSet = (int) Math.ceil((double)A1.length/Math.round(Math.sqrt(A1.length))), l = 0, r = squareroot;
		int[] A2 = new int[numberOfTheSet];
		if(A1.length == 0) {
			System.out.println("Your array is empty");
			return;
		}
		for(int i = 0; i < A2.length; i++) {
			A2[i] = A1[FindMax(A1, l, r)];
			if(l + squareroot <= A1.length) l = l + squareroot;
			else l = A1.length;
			if(r + squareroot <= A1.length) r = r + squareroot;
			else r = A1.length;
		}
		for(int i = 0; i < A1.length; i++) {
			System.out.println("#########################################################################################################");
			System.out.print("A1 :");
			System.out.println(Arrays.toString(A1));
			System.out.print("A2 :");
			System.out.println(Arrays.toString(A2));
			System.out.println("#########################################################################################################");
			System.out.println();
			int max = FindMax(A2, 0, A2.length);
			A1[FindMax(A1, max * squareroot, max * squareroot + squareroot)] = -1;
			A2[max] = A1[FindMax(A1, max * squareroot, max * squareroot + squareroot)];
		}
		System.out.println("#########################################################################################################");
		System.out.print("A1 :");
		System.out.println(Arrays.toString(A1));
		System.out.print("A2 :");
		System.out.println(Arrays.toString(A2));
		System.out.println("#########################################################################################################");
		System.out.println();
	}

	/**
	 * Complexity : O(n*n^(1/3))
	 */
	public static void thirdrootSort(int[] A1) { 
		int thirdroot = (int) Math.round(Math.pow(A1.length, (double) 1/3)), l = 0, r = thirdroot, numberOfSet = (int) Math.ceil((double) A1.length/Math.round(Math.pow(A1.length, (double) 1/3))), numberOfSet2 = (int) Math.ceil((double) numberOfSet/Math.round(Math.pow(A1.length, (double) 1/3)));
		int[] A2 = new int[numberOfSet], A3 = new int[numberOfSet2];
		if(A1.length == 0) {
			System.out.println("Your array is empty");
			return;
		}
		for(int i = 0; i < A2.length; i++) {
			A2[i] = A1[FindMax(A1, l, r)];
			if(l + thirdroot <= A1.length) l = l + thirdroot;
			else l = A1.length;
			if(r + thirdroot <= A1.length) r = r + thirdroot;
			else r = A1.length;
		}
		l = 0;
		r = thirdroot;
		for(int i = 0; i < A3.length; i++) {
			A3[i] = A2[FindMax(A2, l, r)];
			if(l + thirdroot <= A2.length) l = l + thirdroot;
			else l = A2.length;
			if(r + thirdroot <= A2.length) r = r + thirdroot;
			else r = A2.length;
		}
		for(int i = 0; i < A1.length; i++) {
			System.out.println("#########################################################################################################");
			System.out.print("A1 :");
			System.out.println(Arrays.toString(A1));
			System.out.print("A2 :");
			System.out.println(Arrays.toString(A2));
			System.out.print("A3 :");
			System.out.println(Arrays.toString(A3));
			System.out.println("#########################################################################################################");
			System.out.println();
			int max = FindMax(A3, 0, A3.length);
			int max2 = FindMax(A2, max * thirdroot, max * thirdroot + thirdroot);
			A1[FindMax(A1, max2 * thirdroot, max2 * thirdroot + thirdroot)] = -1;
			A2[max2] = A1[FindMax(A1, max2 * thirdroot, max2 * thirdroot + thirdroot)];
			A3[max] = A2[FindMax(A2, max * thirdroot, max * thirdroot + thirdroot)];
		}
		System.out.println("#########################################################################################################");
		System.out.print("A1 :");
		System.out.println(Arrays.toString(A1));
		System.out.print("A2 :");
		System.out.println(Arrays.toString(A2));
		System.out.print("A3 :");
		System.out.println(Arrays.toString(A3));
		System.out.println("#########################################################################################################");
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = new int[20];
		for(int i = 0; i < 20; i++){
			arr[i] = (int) (100*Math.random());
		}
		thirdrootSort(arr);
		for(int i = 0; i < 20; i++){
			arr[i] = (int) (100*Math.random());
		}
		squarerootSort(arr);
	}

}
