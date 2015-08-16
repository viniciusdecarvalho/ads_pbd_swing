package br.edu.ftlf.ads.pbd.main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {
	
	private static Logger logger = LoggerFactory.getLogger(AppLogger.class);
	private static FileLogger fileLogger = null;
	private static boolean isDebug = true;

	private static FileLogger getFileLogger() {
		if (fileLogger == null) {
			fileLogger = new FileLogger("log.txt");
		}
		File file = fileLogger.getFile();
		if (file.length() > (1024 * 1000)) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			Date now = Calendar.getInstance().getTime();
			String fileName = String.format("log%s.txt", dateFormat.format(now));
			file.renameTo(new File(fileName));
			fileLogger = new FileLogger("log.txt");
		}
		return fileLogger;
	}
	
	public static void setDebug(boolean isDebug) {
		AppLogger.isDebug = isDebug;
	}
	
	public static boolean isEnabled() {
		return getFileLogger().isEnabled();
	}
	
	public static void setEnabled(boolean enabled) {
		getFileLogger().setEnabled(enabled);
	}
	
	public static void error(Exception e) {
		if (isDebug)
			logger.debug(e.getMessage(), e);
		else if (isEnabled())
			getFileLogger().error(e);
	}

	public static void info(String message) {
		if (isDebug)
			logger.info(message);
		else if (isEnabled())
			getFileLogger().info(message);
	}
}