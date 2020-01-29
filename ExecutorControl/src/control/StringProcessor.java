package control;

import javax.batch.api.chunk.ItemProcessor;

public class StringProcessor implements ItemProcessor{
	StringBuffer buffer = new StringBuffer();
	String temp;
	
	public StringProcessor() {};
	
	@Override
	public Object processItem(Object item) throws Exception {
		if (item == null)
			return null;
		
		buffer.setLength(0);
		buffer.append(item);
		
		return buffer.reverse().toString();
	}

}