package control;

import java.io.BufferedReader;
import java.io.Externalizable;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileReader;
import javax.batch.*;
import javax.batch.api.chunk.ItemReader;


@SuppressWarnings("rawtypes")
public class StringReader implements ItemReader{
	private BufferedReader lineReader;
	private int lineNumber;
	private String fileName;
	
	public StringReader(String fileName) {
		this.fileName = fileName;
	}

	
	@Override
	public void close() throws Exception {
		lineReader.close();	
	}

	@Override
	public Object readItem() throws Exception {
		++lineNumber;
		String line = lineReader.readLine();
		
		if(line == null) {
			System.out.println("Returning null from reader at "
					+ new SimpleDateFormat("hh:mm:ss").format(new Date()));
		}
		
		return line;
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return lineNumber;
	}

	@Override
	public void open(Serializable checkpoint) throws Exception {
		lineReader = new BufferedReader(new FileReader(new File(fileName)));
		
		if(checkpoint != null) {
			
			lineNumber = (Integer)checkpoint;
			for (int i = 0 ; i < lineNumber;i++) {
				lineReader.readLine();
			}
		}
	}
}
