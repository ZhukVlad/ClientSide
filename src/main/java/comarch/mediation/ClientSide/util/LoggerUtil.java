package comarch.mediation.ClientSide.util;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class LoggerUtil {
	private LoggerUtil() {
	}

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LoggerUtil.class);

	public static void error(String message, Throwable e) {
		LOGGER.error(message, e);
	}

	public static void info(String message) {
		LOGGER.info(message);
	}
	
	public static void debug(String message) {
		LOGGER.debug(message);
	}
	
	public static void debug(String message, Object object) {
		LOGGER.debug(message, object);
	}
}
