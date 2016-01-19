package jp.co.brycen.common;

/**
 * ログ出力用インターフェース
 */
public interface ILogSender {

	/**
	 * ログ出力用
	 *
	 * @param level レベル
	 * @param e 例外オブジェクト
	 */
	public void logSend(String level, Throwable e);

	/**
	 * ログ出力用
	 *
	 * @param level レベル
	 * @param message メッセージ
	 */
	public void logSend(String level, String message);

	/**
	 * ログ出力レベルを取得します。
	 *
	 * @return ログ出力レベル
	 */
	public String getOutputLevel();

	/**
	 * ログ出力レベルを設定します。
	 *
	 * @param outputLevel ログ出力レベル
	 */
	public void setOutputLevel(String outputLevel);

}
