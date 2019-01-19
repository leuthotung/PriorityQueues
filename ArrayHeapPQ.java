
public class ArrayHeapPQ {
	Job[] jobs; 
	int size;
	public ArrayHeapPQ(int x) {
		 jobs = new Job[x];
		
		
	}
	protected void resize(int capacity) {
		Job[] temp= new Job[capacity];
		for(int i =0;i<size;i++) {
			temp[i]=jobs[i];
		}
		jobs = temp;
	}
	public boolean isEmpty() {
		return size ==0;
	}
	public int getLeft(int index) {
		return 2*index + 1;
	}
	public int getRight(int index) {
		return 2*index + 2;
	}
	public int getParent(int index) {
		return (index-1)/2;
		
	}
	public boolean hasParent(int index) {
		return getParent(index)>= 0;	
	}
	public boolean hasLeft(int index) {
		return getLeft(index)<size;
	}
	public boolean hasRight(int index) {
		return getRight(index)<size;
	}
	public void swap(int x, int y) {
		Job temp = jobs[x];
		jobs[x] = jobs[y];
		jobs[y]= temp;
		
	}
	public Job removeMin() {
		Job min = jobs[0];
		jobs[0]= jobs[size-1];
		size--;
		bubbledown();
		return min;
		
		
	}
	public void insert(Job x) {
		if(size == jobs.length)
			resize(2*jobs.length);
		jobs[size]= x;
		size++;
		bubbleup();
		
		
	}
	public boolean morePriority(Job x, Job y) {
		if(x.finalPriority>y.finalPriority)
			return true;
		else if(x.finalPriority==y.finalPriority) {
			if(x.entryTime>y.entryTime)
			return true ;
			else
				return false;
		
		}
		else
			return false;
	}
	public void bubbleup() {
		int index = size-1;
		while (hasParent(index) && jobs[getParent(index)].morePriority(jobs[index])) {
			swap(getParent(index),index);
			index = getParent(index);
		}
		
	}
	public void bubbledown() {
		int index = 0;
		while (hasLeft(index)) {				
			int smallerindex= getLeft(index);
			if (hasRight(index)&& jobs[getRight(index)].morePriority(jobs[getLeft(index)]))
				smallerindex = getRight(index);
			if( jobs[index].morePriority(jobs[smallerindex]))
				break;
			else {
				swap(index, smallerindex);
				
			}
			index = smallerindex;
		}
	}
	public Job highestEntry() {
		int highest = 0;
		for(int i =0;i<size;i++) {
			if(jobs[i].entryTime>jobs[highest].entryTime)
				highest = i;
		}
		return jobs[highest];
	}
	
}
