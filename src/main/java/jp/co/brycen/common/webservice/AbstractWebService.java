package jp.co.brycen.common.webservice;

import jp.co.brycen.common.ConstantValue;
import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.LogLevel;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.utility.CommonUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Webサービス基本クラス
 */
public abstract class AbstractWebService implements ILogSender {

	/**
	 * ログ出力レベル
	 */
	protected String OUTPUT_LEVEL = ConstantValue.LOG_OUTPUT_LEVEL;

	/**
	 * ログ出力レベルを取得します。
	 *
	 * @return ログ出力レベル
	 */
	public String getOutputLevel() {
		return OUTPUT_LEVEL;
	}

	/**
	 * ログ出力レベルを設定します。
	 *
	 * @param outputLevel
	 *            ログ出力レベル
	 */
	public void setOutputLevel(String outputLevel) {
		OUTPUT_LEVEL = outputLevel;
	}

	/**
	 * ログ出力用
	 */
	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * Webサービスの処理を実行します。
	 * @return レスポンス
	 */
	protected final AbstractResponse executeProcess(AbstractRequest request) {

		logSend("I", "クラス名：" + getClass().getSimpleName());

		AbstractResponse response = null;

		// プロセスを取得
		AbstractProcess process = getProcess();

		try {
			response =  process.execute(request);
		} catch (Exception e) {
			// ここに入る予定がないので、処理なし。
			// 念のためログ出力のみ記載。
			logSend(LogLevel.ERROR, CommonUtility.getStackTraceString(e));
		}

		return response;
	}

	/**
	 * プロセスを取得します。
	 * @return プロセスのインスタンス
	 */
	protected abstract AbstractProcess getProcess();


	/**
	 * ログを出力します。
	 * @param level		レベル
	 * @param e			例外オブジェクト
	 * @return
	 */
	public void logSend(String level, Throwable e) {

		if (OUTPUT_LEVEL.indexOf(level) == -1) {
			return;
		}

		if ("T".equals(level)) {
			log.debug("例外発生", e);
		} else if ("D".equals(level)) {
			log.debug("例外発生", e);
		} else if ("I".equals(level)) {
			log.info("例外発生", e);
		} else if ("W".equals(level)) {
			log.warn("例外発生", e);
		} else if ("E".equals(level)) {
			log.error("例外発生", e);
		} else if ("F".equals(level)) {
			log.fatal("例外発生", e);
		}
	}

	/**
	 * ログを出力します。
	 * @param level				レベル
	 * @param message			メッセージ
	 * @return
	 */
	public void logSend(String level, String message) {

		if (OUTPUT_LEVEL.indexOf(level) == -1) {
			return;
		}

		if ("T".equals(level)) {
			log.debug(message);
		} else if ("D".equals(level)) {
			log.debug(message);
		} else if ("I".equals(level)) {
			log.info(message);
		} else if ("W".equals(level)) {
			log.warn(message);
		} else if ("E".equals(level)) {
			log.error(message);
		} else if ("F".equals(level)) {
			log.fatal(message);
		}

	}
}
