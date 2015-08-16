package br.edu.ftlf.ads.pbd.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

class FileLogger {

	private boolean enabled = true;
	private Appender fileAppender;
	private File file;
	private Logger logger = Logger.getLogger(Logger.class);

	FileLogger(String path) {
		file = new File(path);
		try {
			BasicConfigurator.configure();
			this.logger = Logger.getLogger(Logger.class);
			this.fileAppender = new FileAppender(new PatternLayout(), file.getAbsolutePath());
			this.logger.addAppender(this.fileAppender);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getFile() {
		return file;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if (enabled) {
			if (this.logger.getAppender(this.fileAppender.getName()) == null) {
				this.logger.addAppender(this.fileAppender);
			}
		} else {
			this.logger.removeAllAppenders();
		}
	}

	private String getHeader() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm");
		return "[" + simpleDateFormat.format(calendar.getTime()) + "]";
	}

	public void error(Exception e) {
		String errorMessage = getHeader() + " ";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		e.printStackTrace(pw);
		pw.flush();
		sw.flush();
		this.logger.error(errorMessage + sw.toString());
	}

	public void info(String message) {
		this.logger.info(getHeader() + " " + message);
	}

}