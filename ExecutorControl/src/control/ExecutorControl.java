package control;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorControl {
	
	public static void main(String args[]) {
		BatchConfig cfg = new BatchConfig(1,"d:\\words.txt", "d:\\output.txt");
		StringBatchController batchController = new StringBatchController(cfg);
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		String userInput;
		Scanner sn = new Scanner(System.in);
		
		
		
		while(true){
			//Print the options for the user to choose from
			System.out.println("*****Available Options*****");
			System.out.println("*. Press 1 To start new batch process");
			System.out.println("*. Press 2 To GET STATUS of running batch process");
			System.out.println("*. Press 3 To PAUSE running batch process");
			System.out.println("*. Press 4 To RESTART PAUSED batch process");
			System.out.println("*. Press 5 To ABORT batch process");
			System.out.println("*. Press 6 To STOP batch process");
			System.out.println("*. Press 7 to exit program");
			// Prompt the use to make a choice
			System.out.println("Enter your choice:");
			
			//Capture the user input in scanner object and store it in a pre decalred variable
			userInput = sn.next();
			
			//Check the user input
			switch(userInput){
			case "1":				
				executorService.execute(batchController);
				
				System.out.println("Started batch process");
				break;
			case "2":
				//do the job number 2
				System.out.println("GET STATUS of running batch process:" + batchController.status());
				break;
			case "3":
				//do the job number 2
				System.out.println("PAUSE running batch process");
				break;
			case "4":
				//do the job number 2
				System.out.println("RESTART PAUSED batch process");
				break;
			case "5":
				//do the job number 2
				System.out.println("ABORT batch process");
				break;
			case "6":
				//do the job number 2
				executorService.shutdownNow();
				System.out.println("STOP batch process");
				break;
			case "7":
				//exit from the program
				System.out.println("Exiting...");
				System.exit(0);
			default:
				//inform user in case of invalid choice.
				System.out.println("Invalid choice. Read the options carefully...");
			}
		}
	}
}