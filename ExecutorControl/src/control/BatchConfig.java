package control;

public class BatchConfig {
	private int chunkSize;
	private String inputFile;
	private String outputFile;
	
	public BatchConfig(int chunkSize, String inputFile, String outputFile) {
		this.setChunkSize(chunkSize);
		this.setInputFile(inputFile);
		this.setOutputFile(outputFile);
	}

	public int getChunkSize() {
		return chunkSize;
	}

	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
}
