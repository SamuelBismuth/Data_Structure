package hw2;

import java.util.concurrent.ArrayBlockingQueue;

public class hw2 {

	private static ArrayBlockingQueue<String> Merge(ArrayBlockingQueue<String> a, ArrayBlockingQueue<String> b) {
		if(a.size() == 0 && b.size() == 0) return a;
		if(a.size() != 0 && b.size() == 0) return a;
		if(a.size() == 0 && b.size() != 0) return a;
		int sizeOfTheNewQueue = a.size() + b.size();
		ArrayBlockingQueue<String> myQueue = new ArrayBlockingQueue<String>(sizeOfTheNewQueue);  
		while(a.peek() != null && b.peek() != null) {
			if(a.peek().compareTo(b.peek()) >= 0) myQueue.add(b.poll());
			else myQueue.add(a.poll());
		}
		while(a.peek() != null) myQueue.add(a.poll());
		while(b.peek() != null) myQueue.add(b.poll());
		return myQueue;
	}


	public static void MergeSort(ArrayBlockingQueue<String> a) {
		if(a.size() > 1) {
			int mid = a.size() / 2;
			ArrayBlockingQueue<String> temp = new ArrayBlockingQueue<String>(mid); 
			ArrayBlockingQueue<String> temp2 = new ArrayBlockingQueue<String>(a.size() - mid);
			for (int i = 0; i < mid; i++) temp.add(a.poll());
			while (a.peek() != null) temp2.add(a.poll());
			MergeSort(temp);
			MergeSort(temp2);
			ArrayBlockingQueue<String> answer = Merge(temp, temp2);
			while (answer.peek() != null) a.add(answer.poll());
		}
	}

	public static void main(String[] args) {
		ArrayBlockingQueue<String> a = new ArrayBlockingQueue<String>(1000); 
		ArrayBlockingQueue<String> b = new ArrayBlockingQueue<String>(1000); 
		for (int i = 0; i < 1000; i++) {
			int number = (int) (Math.random()*26) ;
			char random = (char)('a' + number);
			String random2 = Character.toString(random);
			a.add(random2);
		}
		MergeSort(a);
		for (int i = 0; i < 0; i++) {
			int number = (int) (Math.random()*26) ;
			char random = (char)('a' + number);
			String random2 = Character.toString(random);
			b.add(random2);
		}
		MergeSort(b);
		ArrayBlockingQueue<String> myQueue = Merge(a, b);
		System.out.println(myQueue);

		ArrayBlockingQueue<String> c = new ArrayBlockingQueue<String>(100); 
		for (int i = 0; i < 100; i++) {
			int number = (int) (Math.random()*26) ;
			char random = (char)('a' + number);
			String random2 = Character.toString(random);
			c.add(random2);
		}
		MergeSort(c);
		System.out.println(c);
	}
}
