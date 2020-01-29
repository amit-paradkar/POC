package control;

import java.util.ArrayList;
import java.util.List;

public class StringRunner implements Runnable {
	
	private final String lineRead;
	private StringProcessor processor;
	private StringWritter writer;

	StringRunner(String line, StringProcessor processor, StringWritter writer) {
		this.lineRead = line;
		this.processor = processor;
		this.writer = writer;
	}

	@Override
	public void run() {
				
		try {
			String lineProcessed = (String) processor.processItem(lineRead);
			List<String> lines = new ArrayList<String>();
			lines.add(lineProcessed);
			writer.writeItems(lines);
			System.out.println("Line processed: " +lineProcessed );
		}
		catch (Exception e){
			System.out.println("Line processing failed " +e.getMessage());
		}
	}
}