package gdP_Projekt_InfoSys;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class Log {
	File logFile;
	String logSeparator;
	String timeStatementSeparator;
	
	public Log(String fileName, String logSeparator, String timeStatementSeparator) {
		this.logFile = new File(fileName);
		this.logSeparator = logSeparator;
		this.timeStatementSeparator = timeStatementSeparator;
	}

	void write(String logStatement) {
		PrintWriter printWriter;
		String logString = "";
		List<String> logList = new ArrayList<String>();
		
		try {
			//read old
			if(logFile.exists()) {
				logString = new String(Files.readAllBytes(logFile.toPath()));
				System.out.println(logString);
			}
			
			logString += (logSeparator + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + timeStatementSeparator + logStatement); //very rare separator
			
			//write new
			printWriter = new PrintWriter(logFile);
			printWriter.println(logString);
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
