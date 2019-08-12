package comarch.mediation.ClientSide.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormattingUtil {
	
	private static final String DATE_SHORT = "yyyyMMdd";
	private static final String DATE_FULL = "yyyyMMdd HHmmssSSS";
	
	private FormattingUtil() {
	}
	
	public static SimpleDateFormat getShortDateFormat() {
		return new SimpleDateFormat(DATE_SHORT);
	}
	
	public static SimpleDateFormat getFullDateFormat() {
		return new SimpleDateFormat(DATE_FULL);
	}
	
	public static Date getCurrentDate() {
		return new Date();
	}
}
