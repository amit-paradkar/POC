package control;

import java.util.ArrayList;
import java.util.List;

public class StringBatchRunner implements Runnable{

	private BatchConfig config;
	private StringReader reader;
	private StringWritter writer;
	private StringProcessor processor;
	

	public StringBatchRunner (BatchConfig cfg) {
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
	
	@Override
	public void run() {
		try {
			String lineRead = (String) reader.readItem();
			//read firstChunk
			
			//while no more rows
			//read next chunk
			//process chunk
			//writeChunks
			String lineProcessed = (String) processor.processItem(lineRead);
			List<String> lines = new ArrayList<String>();
			lines.add(lineProcessed);
			writer.writeItems(lines);
			writer.close();
		}
		catch (Exception e) {
			
		}
	}
}