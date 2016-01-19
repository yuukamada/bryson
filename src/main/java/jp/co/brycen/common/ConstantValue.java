package jp.co.brycen.common;


public class ConstantValue {

	/**
	 * インスタンス
	 */
	private static ConstantValue _instance = new ConstantValue();

	/**
	 * ログ出力レベル
	 */
	private String _LOG_OUTPUT_LEVEL = "FEWITD";

	/**
	 * ログ出力レベル
	 */
	public static String LOG_OUTPUT_LEVEL = _instance._LOG_OUTPUT_LEVEL;

	/**
	 * 行ロックの待ち時間
	 */
	public static Integer FOR_UPDATE_TIME = 0;

	/**
	 * DBの文字コード
	 */
	public static String DB_ENC = "UTF-8";

	/**
	 * エラー種別
	 */
	public static Integer NORMAL_ERROR = 0;
	public static Integer FATAL_ERROR = 1;

	/**
	 * 更新区分
	 */
	public static String UPDKBN_UPDATE = "1";
	public static String UPDKBN_DELETE = "2";

	public static String CSV_PATH = "C:\\Users\\yijiri\\Desktop\\";
}
