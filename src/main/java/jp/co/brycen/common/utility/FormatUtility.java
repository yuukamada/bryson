package jp.co.brycen.common.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class FormatUtility {

	/**
	 * 文字列を日付型に変換
	 * @param str
	 * @return 日付型データ
	 */
	public Date strToDate(String str) {
		try {
			return  DateFormat.getDateInstance().parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}