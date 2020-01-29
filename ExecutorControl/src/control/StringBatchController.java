package control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StringBatchController implements Runnable {
	
	private BatchConfig config;
	private StringReader reader;
	private StringWritter writer;
	private StringProcessor processor;
	ExecutorService executor = Executors.newFixedThreadPool(5);

	public StringBatchController (BatchConfig cfg) {
		this.config = cfg;
		init();
	}
	
	public void init() {
		reader = new StringReader(config.getInputFile());
		
		writer = new StringWritter(config.getOutputFile());
		try {
			reader.open(null);
			writer.open(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		processor = new StringProcessor();
	}
	
	public void run() {
		int linesRead = 0;
		while(true) {
			String lineRead;
			try {
				lineRead = (String) reader.readItem();
				if(lineRead == null) {
					break;
				}
				
				linesRead++;
				System.out.println("Lines read: "+ linesRead);
				Runnable worker = new StringRunner(lineRead,processor,writer);
				System.out.println("Executing worker for lineRead: "+ worker.toString() + lineRead);
				executor.execute(worker);
				if(linesRead == 5) {
					System.out.println("Waiting for 5 workers to complete");
					executor.awaitTermination(20, TimeUnit.SECONDS);
					System.out.println("Wait completed");
					linesRead = 0;
				}
			}
			catch (InterruptedException intp) {
				System.out.println("Received interrupt");
				try {
					writer.close();
					System.out.println("Closed writer");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					System.out.println("Waiting for running processes to terminate on receiving interrupt");
					executor.awaitTermination(20, TimeUnit.SECONDS);
					System.out.println("Wait completed. On receiving interrupt");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Shutting down after interrupt");
				executor.shutdownNow();
				System.out.println("Shutdown completed after inturrupt");
				break;
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			System.out.println("Closing writer.All process complete.");
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Shutting down.All process complete.");
		executor.shutdown();
		System.out.println("Shutdown complete.All process complete.");
	}
	
	//stop
	public void stop () {
		executor.shutdownNow();
	}
	
	public String status() {
		if ( executor.isTerminated() ) {
			return "Shutdown";
		}
		else {
			return "Not Shutdown";
		}		
	}
	//restart
	
	//abort
	
	//pause
}