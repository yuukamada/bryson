
//
package jp.co.brycen.common.exception;

import java.util.ArrayList;
import java.util.List;

import jp.co.brycen.common.dto.ErrorDto;

/**
 * その他例外クラス
 */
public class ProcessCheckErrorException extends Exception {

	protected static final long serialVersionUID = 8027046016920567409L;

	List<ErrorDto> lstNormalError = new ArrayList<ErrorDto>();
	List<ErrorDto> lstFatalError = new ArrayList<ErrorDto>();


	/**
	 * コンストラクタ
	 */
	public ProcessCheckErrorException() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param e 例外
	 */
	public ProcessCheckErrorException(Exception e) {
		super(e);
	}

	/**
	 * コンストラクタ
	 * @param msg		エラーDTO配列
	 */
	public ProcessCheckErrorException(List<ErrorDto> msg, Integer errorType) {
		super();

		switch (errorType){
			case 0:setNormalError(msg);break;
			case 1:setFatalError(msg);break;
		}
	}

	/**
	 * 致命的なエラーを設定します。
	 * @param エラーDTO配列
	 */
	public void setFatalError(List<ErrorDto> msg) {
		lstFatalError = msg;
	}

	/**
	 * 致命的なエラーを取得します。
	 * @return
	 */
	public List<ErrorDto> getFatalError() {
		return lstFatalError;
	}

	/**
	 * 通常のエラーを設定します。
	 * @param エラーDTO配列
	 */
	public void setNormalError(List<ErrorDto> msg) {
		lstNormalError = msg;
	}

	/**
	 * 通常のエラーを取得します。
	 * @return
	 */
	public List<ErrorDto> getNormalError() {
		return lstNormalError;
	}
}
