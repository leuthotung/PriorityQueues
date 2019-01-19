
public class UnsortedPQ {


		private Job[] jobs;
		private int size;
		public UnsortedPQ(int x) {
			jobs = new Job[x];
			
		}
		
		public int size() {
			return size;
		}
		public void insert(Job j) {
			jobs[size] = j;
			size ++;
		}
		public boolean isEmpty() {
			return size ==0;
		}
		public Job removeMin() {
			int removeindex = 0;
			for(int i = 0; i< size;i++) {
				if(jobs[i].morePriority(jobs[removeindex]))
					removeindex = i;
				
			}
			Job jobremoved = jobs[removeindex];
			 size--;
			 jobs[removeindex]= jobs[size];
			
			
			return jobremoved;
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
		



