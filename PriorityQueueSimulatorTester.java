import java.util.Random;
import java.io.*;
public class PriorityQueueSimulatorTester {
	public static void main(String[] args) throws FileNotFoundException {
		Random rand = new Random();
		
	
		PrintWriter pw = new PrintWriter(new FileOutputStream("testrun.txt"));
		int n = 100;
		while(n<1000) {
		int maxNumberOfJobs =n;
		UnsortedPQ unsortedPQ = new UnsortedPQ(maxNumberOfJobs);
		ArrayHeapPQ arrayHeapPQ = new ArrayHeapPQ(maxNumberOfJobs);
		Job[] jobsInputArray = new Job[maxNumberOfJobs];
		 int currentTime = 0;
		 int HeapcurrentTime =0;
		 int numbOfPriorityChange = 0;
		 int numbOfHeapPriorityChange =0;
		 int totalWaittime=0;
		 int totalHeapWaittime = 0;
		for (int i=0; i<jobsInputArray.length;i++) {
			long a = 0;
			int length = rand.nextInt(70)+1;
			int priority = rand.nextInt(40)+1;
			jobsInputArray[i] = new Job("Job_"+(i+1)+"",length,length,priority,priority,a,a,a,a);
			
		}
		
		for(int i = 0;i<jobsInputArray.length;i++) {
			jobsInputArray[i].entryTime= i+1;
			jobsInputArray[i].initialentryTime = i+1;
			unsortedPQ.insert(jobsInputArray[i]);
			currentTime++;
		
		}
		
		int terminated = 0;
		long start = System.currentTimeMillis();
		while(!unsortedPQ.isEmpty()) {
			currentTime++;
			Job Jobcheck = unsortedPQ.removeMin();
			System.out.println("Now executing "+ Jobcheck.jobName +". Job length: "+Jobcheck.jobLength+ ". Current remaining length: "+ Jobcheck.currentJobLength+ " Initial priority: " +Jobcheck.jobPriority+"; Current priority: "+Jobcheck.finalPriority);
			Jobcheck.setCurrentJobLength(Jobcheck.currentJobLength-1);
		
			if(Jobcheck.currentJobLength>0) {
				Jobcheck.entryTime = currentTime;
				unsortedPQ.insert(Jobcheck);
				currentTime++;
			}
			else {
				terminated++;
				Jobcheck.waitTime = currentTime-  Jobcheck.initialentryTime ;
				totalWaittime += Jobcheck.waitTime;
				Jobcheck.endTime= currentTime;
				if(terminated %30 ==0 && terminated >0) {
					currentTime++;
					unsortedPQ.highestEntry().finalPriority= 1;
					numbOfPriorityChange++;
					
				}
			}
		}
		long end = System.currentTimeMillis();
		long duration = end - start;
		pw.println("");
		pw.println("UnsortedArray implementation:");
		pw.println("Current system time (cycles): " +currentTime);
		pw.println("Total number of jobs executed: " +terminated);
		pw.println("Average process waiting time: " +totalWaittime/terminated);
		pw.println("Total number of priority changes: " +numbOfPriorityChange);
		pw.println("Actual system time needed to execute all jobs: " + duration+" ms");
		//pw.close();
		for(int i = 0;i<jobsInputArray.length;i++) {
			jobsInputArray[i].entryTime= i+1;
			jobsInputArray[i].currentJobLength = jobsInputArray[i].jobLength;
			jobsInputArray[i].finalPriority = jobsInputArray[i].jobPriority;
			jobsInputArray[i].endTime =jobsInputArray[i].waitTime =0;
					
			arrayHeapPQ.insert(jobsInputArray[i]);
			HeapcurrentTime++;
		
		}
		int Heapterminated = 0;
		long heapstart = System.currentTimeMillis();
		while(!arrayHeapPQ.isEmpty()) {
			HeapcurrentTime++;
			Job HeapJobcheck = arrayHeapPQ.removeMin();
			System.out.println("Now executing "+ HeapJobcheck.jobName +". Job length: "+HeapJobcheck.jobLength+ ". Current remaining length: "+ HeapJobcheck.currentJobLength+ " Initial priority: " +HeapJobcheck.jobPriority+"; Current priority: "+HeapJobcheck.finalPriority);
			HeapJobcheck.currentJobLength--;
			
			if(HeapJobcheck.currentJobLength>0) {
				HeapJobcheck.entryTime = currentTime;
				arrayHeapPQ.insert(HeapJobcheck);
				HeapcurrentTime++;
			}
			else {
				Heapterminated++;
				HeapJobcheck.waitTime = HeapcurrentTime-  HeapJobcheck.initialentryTime ;
				totalHeapWaittime += HeapJobcheck.waitTime;
				HeapJobcheck.endTime= HeapcurrentTime;
				
				if(Heapterminated %30 ==0) {
					HeapcurrentTime++;
					arrayHeapPQ.highestEntry().setFinalPriority(1); 
					numbOfHeapPriorityChange++;
					
				}
			}
		}
		long heapend = System.currentTimeMillis();
		long heapduration = heapend-heapstart ;
		pw.println("");
		pw.println("ArrayHeap Implementation:");
		pw.println("Current system time (cycles): " +HeapcurrentTime);
		pw.println("Total number of jobs executed: " +Heapterminated);
		pw.println("Average process waiting time: " +totalHeapWaittime/terminated);
		pw.println("Total number of priority changes: " +numbOfHeapPriorityChange);
		pw.println("Actual system time needed to execute all jobs: " + heapduration+" ms");
		n = n*10;
		}
		pw.close();
	}
}
