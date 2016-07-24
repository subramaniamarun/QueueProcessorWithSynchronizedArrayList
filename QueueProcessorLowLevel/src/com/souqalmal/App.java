package com.souqalmal;

public class App {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		final QueueProcessor processor = new QueueProcessor();
		
		//Producer Thread
		Thread producer = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
					try {
						processor.produce();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Exception  :"+e.getMessage());
					}
			}
			
			
		});
		
		
		//Consumer Thread t1
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				try {
					processor.consume("t1");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("Exception  :"+e.getMessage());
				}
				
			}
			
		});
		
		
		//Consumer thread t2
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				try {
					processor.consume("t2");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("Exception  :"+e.getMessage());
				}
				
			}
			
		});
		
		producer.start();
		t1.start();
		t2.start();
		
		
		
		Thread.sleep(60000);// Pausing for a minute and closing as the loops are indefinite
		System.out.println("Ending Queue Operations...");
		System.exit(0);

	}

}
