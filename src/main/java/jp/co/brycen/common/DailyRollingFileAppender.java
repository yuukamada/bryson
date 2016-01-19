package jp.co.brycen.common;

import java.util.Calendar;

import org.apache.log4j.FileAppender;

/**
 * logファイルの日付付加
 * @author musui
 *
 */
public class DailyRollingFileAppender  extends FileAppender {

	 /**
	 * 生成されるファイル名に日付＋.logを付加するメソッド
	 * @param fileName String
	 */
	 public void setFile(String fileName) {

	 fileName = fileName + "_" + GetDay()+ ".log";
	 super.setFile( fileName );
	 }

	private String GetDay(){
		Calendar cal1 = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();

		sb.append(cal1.get(Calendar.YEAR));
		sb.append(String.format("%1$02d",cal1.get(Calendar.MONTH) + 1));
		sb.append(String.format("%1$02d",cal1.get(Calendar.DAY_OF_MONTH)));

		return sb.toString();
	}
}
