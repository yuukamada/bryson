package jp.co.brycen.common.utility;

import java.util.ResourceBundle;

public class MessageUtility {

	private static final String logMsgFile = "logmessage";
	private static final String msgFile = "message";
	private static final String systemMstFile = "systemmaster";
	private static final String systemErrFile = "systemerror";


	/**
	 * コンストラクタ
	 * newできないようにprivateで宣言
	 */
	private MessageUtility() {
	}

	/**
	 * バンドルを取得
	 * @param fileName				プロパティファイル名
	 * @return
	 */
	private static ResourceBundle getFileStream(String fileName) {
        try {
            return ResourceBundle.getBundle(fileName);
        } catch (Exception e) {
            return null;
        }
    }


	/**
	 * ログメッセージから文字列を取得
	 * @param key
	 * @return
	 */
	private static String getLogMessageString (String key){
		String ret = "";
		ResourceBundle  bundle = getFileStream(logMsgFile);
		try {
			if(bundle != null){
				ret = bundle.getString(key);
			}
		} catch (Exception e) {
			// 何もしない
		}

		return ret;
	}


	/**
	 * ログメッセージのレベルを取得
	 * @param key
	 * @return
	 */
	public String getLogMessageLevel(String key){
		String str = getLogMessageString(key);
		if(("").equals(str)){
			return str;
		}

		String[] msg = str.split(",", 0);
		return msg[0];
	}


	/**
	 * ログメッセージの文言を取得
	 * @param key
	 * @return
	 */
	public static String getLogMessageMsg(String key){
		String str = getLogMessageString(key);
		if(("").equals(str)){
			return str;
		}

		String[] msg = str.split(",", 0);
		return msg[1];
	}


	 /** メッセージから文字列を取得
	 * @param key
	 * @param cstmcd		荷主コード
	 * @return
	 */
	private static String getMessageString(String key, String cstmcd){
		String ret = "";
		ResourceBundle  bundle = getFileStream(cstmcd + msgFile);
		try {
			if(bundle != null){
				ret = bundle.getString(key);
			}
		} catch (Exception e) {
			// 何もしない
		}

		return ret;
	}

	/**
	 * メッセージのレベルを取得
	 * @param key
	 * @param cstmcd		荷主コード
	 * @return
	 */
	public static String getMessageLevel(String key, String cstmcd){
		String str = getMessageString(key, cstmcd);
		if(("").equals(str)){
			return str;
		}

		String[] msg = str.split(",", 0);
		return msg[0];
	}


	/**
	 * メッセージの文言を取得
	 * @param key
	 * @param cstmcd		荷主コード
	 * @return
	 */
	public static String getMessageMsg(String key, String cstmcd){
		String str = getMessageString(key, cstmcd);
		if(("").equals(str)){
			return str;
		}

		String[] msg = str.split(",", 0);
		return msg[1];
	}


	/**
	 * メッセージのボタン位置を取得
	 * @param key
	 * @param cstmcd		荷主コード
	 * @return
	 */
	public static String getMessageDefaultBtn(String key, String cstmcd){
		String str = getMessageString(key, cstmcd);
		if(("").equals(str)){
			return str;
		}

		String[] msg = str.split(",", 0);
		return msg[2];
	}


	/**
	 * システムマスタから文字列を取得
	 * @param key
	 * @param cstmcd		荷主コード
	 * @return
	 */
	private static String getSystemMstString(String key, String cstmcd){
		String ret = "";
		ResourceBundle bundle = getFileStream(cstmcd + systemMstFile);
		try {
			if(bundle != null){
				ret = bundle.getString(key);
			}
		} catch (Exception e) {
			// 何もしない
		}

		return ret;
	}


	/**
	 * システムマスタの文言を取得
	 * @param key
	 * @param cstmcd		荷主コード
	 * @return
	 */
	public static String getSystemMstMsg(String key, String cstmcd){
		return getSystemMstString(key, cstmcd);
	}


	/**
	 * システムエラーから文字列を取得
	 * @param key
	 * @return
	 */
	private static String getSystemErrString(String key){
		String ret = "";
		ResourceBundle bundle = getFileStream(systemErrFile);
		try {
			if(bundle != null){
				ret = bundle.getString(key);
			}
		} catch (Exception e) {
			// 何もしない
		}

		return ret;
	}


	/**
	 * システムエラーの文言を取得
	 * @param key
	 * @return
	 */
	public static String getSystemErrMsg(String key){
		return getSystemErrString(key);
	}
}