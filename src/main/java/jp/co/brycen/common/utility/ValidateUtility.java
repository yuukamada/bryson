package jp.co.brycen.common.utility;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.brycen.common.ConstantValue;

import java.text.ParseException;

public class ValidateUtility {

	// 半角英数字
	private static final String regAlphaNume = "[a-zA-Z0-9]+";

	// 半角英数字+記号
	private static final String regNumeColonSemi = "([0-9:;])+";

	// h:m:s形式
	private static final String regHourMinuteSecond = "([0-9])+(:[0-9]+)(:[0-9]+)";

	// 数字
	private static final String regNume = "[0-9]+";
	private static final String regAlphaNumeSym = "[A-Za-z0-9_+-.,!@#$%^?=: &*();\\/|<>\"']+";

	// パス
	private static final String regPath = "([a-zA-Z]:)?(/[a-zA-Z0-9_.-]+)+/?";

	/**
	 * コンストラクタ
	 * newできないようにprivateで宣言
	 */
	private ValidateUtility() {
	}

	/**
	 * CheckNull
	 *
	 * @param str
	 * @return
	 */
	public static Boolean CheckNull(String str) {
		return ("".equals(str)) || (str == null);
	}

	// TODO:いらない？
	public static Boolean CheckNullParentGr(Integer  id) {
		return ("".equals(id)) || (id == null);
	}


	/**
	 * 半角数字 or 半角アルファベットをチェック
	 * @param str
	 * @return
	 */
	public static Boolean IsAlphaNumber(String str) {
		return !str.matches(regAlphaNume);
	}

	/**
	 * 一定文字数より小さいかをチェック
	 * @param str
	 * @param min
	 * @return
	 */
	public static Boolean IsMinLength(String str, int min) {
		if (str.length() < min) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *  一定文字数より大きいかをチェック
	 * @param str
	 * @param max
	 * @return
	 */
	public static Boolean CheckMaxLength(String str, int max) {
		if (str.length() > max) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 半角カタカナチェック
	 * @param str
	 * @return
	 */
	public static Boolean CheckHalfKata(String str) {

		for (char c : str.toCharArray()) {
			if (String.valueOf(c).matches("[\uff65-\uff9f]"))
				return true;
		}
		return false;
	}

	/**
	 * CheckMaxByteInput
	 * TODO:いらない？
	 * @param str
	 * @param max
	 * @return
	 */
	public static Boolean CheckMaxByteInput(String str, int max) {
		int count = 0;
		for (char c : str.toCharArray()) {
			if (("".equals(str) || (str == null))
					|| !Character.toString(c).matches(regAlphaNumeSym)) {
				count = count + 3;

			} else {
				count = count + 1;
			}
		}
		if (count > max) {

			return true;
		}
		return false;
	}

	/**
	 * CheckNumber
	 * TODO:後で消す
	 * @param str
	 * @return
	 */
	public static Boolean CheckNumber(String str) {
		return !str.matches(regNume);
	}

	/**
	 * CheckNumberColon number + colon check 0-9
	 * TODO:いらない？
	 * @param str
	 * @return
	 */
	public static Boolean CheckNumberColon(String str) {
		return !str.matches(regNumeColonSemi);
	}

	/**
	 * CheckHourFormat HH:MM:SS check
	 * TODO:いらない？
	 * @param str
	 * @return
	 */
	public static Boolean CheckHourFormat(String str) {
		return !str.matches(regHourMinuteSecond);
	}

	/**
	 * CheckAlphaNumberSymbol
	 * TODO:いらない？
	 * @param str
	 * @return
	 */
	public static Boolean CheckAlphaNumberSymbol(String str) {
		return !str.matches(regAlphaNumeSym);
	}

	/**
	 * CheckDirectoryPath directory path check
	 * TODO:いらない？
	 * @param str
	 * @return
	 */
	public static Boolean CheckDirectoryPath(String str) {
		return !str.matches(regPath);
	}


	/**
	 * 数字型をチェック（整数・小数・マイナス）
	 * @param num
	 * @return
	 */
	public static Boolean IsFloat(String num) {
	    String regex = "^\\-?[0-9]*\\.?[0-9]+$";
	    Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(num);
	    return m.find();
	}

	/**
	 * マイナスの値であるかをチェック
	 * @param num
	 * @return
	 */
	public static Boolean IsMinux(String num) {
		String firstChar = num.substring(0,1);
	    if(firstChar.equals("-")){
	    	return true;
	    }
	    return false;
	}

	/**
	 * 小数値であるかをチェック
	 * @param num
	 * @return
	 */
	public static Boolean IsDecimals(String num) {
		Integer pos = num.indexOf(".");
	    if(pos == -1){
	    	return false;
	    }
	    return true;
	}

	/**
	 * 整数部の桁数を取得
	 * @param num
	 * @return
	 */
	public static Integer GetIntPartDigit(String num) {
		String numList[] = num.split("\\.", 0);

		if(numList.length == 0){
			// 整数
			return num.length();
		} else{
			return numList[0].length();
		}
	}

	/**
	 * 小数部の桁数を取得
	 * @param num
	 * @return
	 */
	public static Integer GetDecimalPartDigit(String num) {
		String numList[] = num.split("\\.");

		if(numList.length == 1){
			// 整数のみ
			return 0;
		} else {
			return numList[1].length();
		}
	}

	/**
	 * 日付として正しいかチェック
	 * @param num
	 * @return
	 */
	public static Boolean IsDate(String date) {

		if(date == null || "".equals(date)){
			return false;
		}
		DateFormat format=new SimpleDateFormat("yyyy/MM/dd");

		try {
			// 型だけではなくて、日付として不正なもの(2015/05/32など)もエラーにする
		    format.setLenient(false);
		    format.parse(date);

		    return true;
		} catch (ParseException e) {
		    return false;
		}
	}

	/**
	 * バイト数を取得
	 * @param num
	 * @return
	 */
	public static Integer GetBytes(String val) {
	    if ( val == null || val.length() == 0 ){
	        return 0;
	    }

	    int ret = 0;
	    try {
	        ret = val.getBytes(ConstantValue.DB_ENC).length;
	    } catch ( UnsupportedEncodingException e ) {
	        ret = 0;
	    }
	    return ret;
	}
}