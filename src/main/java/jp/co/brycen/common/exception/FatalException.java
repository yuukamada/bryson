
//
package jp.co.brycen.common.exception;

/**
 * その他例外クラス
 */
public class FatalException extends Exception {

	protected static final long serialVersionUID = 8027046016920567409L;

	/**
	 * コンストラクタ
	 */
	public FatalException() {
		super();
	}

	/**
	 * コンストラクタ
	 *
	 * @param e 例外
	 */
	public FatalException(Exception e) {
		super(e);
	}

	/**
	 * コンストラクタ
	 *
	 * @param message 例外メッセージ
	 */
	public FatalException(String message) {
		super(message);
	}

}
