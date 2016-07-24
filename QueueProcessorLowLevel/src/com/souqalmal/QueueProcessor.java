package com.souqalmal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

final public class QueueProcessor {
	// 
	private List<String> list = Collections.synchronizedList(new ArrayList<String>(100)); //Externally synchronizing the array list as going to use this as a mutex. Can have a separate lock object also for mutex
	private Integer counter = 0;
	private final Integer limit = 100; //Limit of Queue
	private  String queueItem;
	private final Integer minTimeout = 500;
	private final Integer maxTimeout = 1501;
	
	
	
	public void produce() throws InterruptedException {
		
		while (true) {
			
			counter ++;
			
			synchronized (list) {
			
				
				try{	
					while(list.size() == limit) {
						
						list.wait();
						
						}
					
				
					queueItem = "item " + " {" + counter+ "} ";
					System.out.println("Produced  :"+queueItem);
					
					list.add(queueItem);
					
					//System.out.println("Produced Queue Size   :"+list.size());
					}
				finally{   //Locks are released irrespective of any exceptions
					
						list.notify();		
					}
					
				
			}
			
			Thread.sleep(1000);
	
		}
		
		
	}
	
	
	public void consume (String threadName) throws InterruptedException {
		
			Random random = new Random();
			int x = random.nextInt(maxTimeout)+minTimeout;
			
			while (true){
				
				synchronized (list) {
					
					
			try{
					while(list.size()==0) {		// Waits till the producer fills the queue
						list.wait();
					}
					
				
					String value = list.remove(0);
					System.out.println(threadName +"   :"+value);
					//System.out.println("Consumed Queue Size   :"+list.size());
				
				}finally{			//Locks are released irrespective of any exceptions
					list.notify();
					
				}	
					
				}
				
				//System.out.println("Consumer thread "+threadName+" sleeping for "+x);
				Thread.sleep(x);
				
			}
		
		
	}

}
