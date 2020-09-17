package numberPlay.util;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileProcessor {
	private BufferedReader reader;
	private String line;

	public FileProcessor(String inputFilePath)  {
		
		if (!Files.exists(Paths.get(inputFilePath))) {
			System.out.println("invalid input file or input file in incorrect location");
			System.exit(0);
		}
		
		File newFile = new File(inputFilePath);
		if (newFile.length() == 0) {
			System.out.println("Given input file is empty");
			System.exit(0);
		}
		
		try {
			reader = new BufferedReader(new FileReader(new File(inputFilePath)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR : FileNotFoundException");
		}
	}

	public String poll() throws  IOException {
		line = reader.readLine();
		if (null == line) return null;
		return line;	
	}
	

	public void close() throws IOException {
		try {
			reader.close();
			line = null;
		} catch (IOException e) {
			throw new IOException("failed to close file", e);
		}
	}
}
