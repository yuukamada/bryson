package jp.co.brycen.common.utility;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CommonUtility {

	/**
	 * スタックトレースを文字列で取得
	 * @param e			Exception
	 * @return
	 */
	public static String getStackTraceString(Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}