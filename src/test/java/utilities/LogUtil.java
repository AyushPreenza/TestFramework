package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Log util.
 *
 * @author TestLink
 */
public class LogUtil {

	/**
	 * Instantiates a new Log util.
	 */
	public LogUtil() {
	}

	private static final ThreadLocal<Logger> logger = new ThreadLocal<Logger>();

	/**
	 * @return the logger
	 */
	private static ThreadLocal<Logger> getLogger() {
		return logger;
	}

	/**
	 * @param logger the logger to set
	 */
	private static void setLogger(Class<?> c) {
		logger.set(LogManager.getLogger(c));
	}

	/**
	 * Info log.
	 *
	 * @param clazz   the clazz
	 * @param message the message
	 */
	public static void infoLog(Class<?> clazz, String message) {
		setLogger(clazz);
		getLogger().get().info(message);

	}

	/**
	 * Error log.
	 *
	 * @param clazz   the clazz
	 * @param message the message
	 * @param t       the t
	 */
	public static void errorLog(Class<?> clazz, String message, Throwable t) {
		setLogger(clazz);
		getLogger().get().error(message, t);
		getLogger().get().error("----------------------------------------------------------------------");

	}

	/**
	 * Error log.
	 *
	 * @param clazz   the clazz
	 * @param message the message
	 */
	public static void errorLog(Class<?> clazz, String message) {
		setLogger(clazz);
		getLogger().get().error(message);
		getLogger().get().error("-----------------------------------------------------------------------");

	}

}
