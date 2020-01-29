package control;

import java.io.BufferedWriter;
import java.io.Externalizable;
import java.io.Serializable;
import java.io.FileWriter;
import java.io.File;
import java.util.List;

import javax.batch.api.chunk.ItemWriter;

public class StringWritter implements ItemWriter {
	private BufferedWriter writer;
	private int lineNumber;
	private String fileName;
	
	public StringWritter (String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public void close() throws Exception {
		writer.close();
	}
	
	@Override
	public Externalizable checkpointInfo() throws Exception {
		return null;
	}

	@Override
	public void writeItems(List arg0) throws Exception {
		for (Object line:arg0) {
			writer.write((String)line);
			writer.newLine();
		}
	}

	@Override
	public void open(Serializable checkpoint) throws Exception {
		writer = new BufferedWriter(new FileWriter(new File(fileName)));
		
	}
}