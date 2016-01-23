package jp.co.brycen.common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.exception.DBException;

/**
 * DB接続
 */
public final class DBAccessor {

	// コネクション
	protected Connection conn;

	/**
	 * コネクション取得
	 * @return
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * コンストラクタ
	 * @throws ConnectOverException
	 * @throws DBException
	 */
	public DBAccessor() throws DBException {
		conn = null;
		connect();
	}

	@Override
	protected void finalize() {
		try {
			disconnect();
		} catch (Exception e) {
		}
	}

	/**
	 * 接続します。
	 * @throws DBException
	 * @throws ConnectOverException
	 */
	public void connect() throws DBException{

		try {
			// JNDIを使用して接続
			// TODO:後でJNDIにする

			if(conn == null){
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wms", "wms_user", "wms_pass");
			}

			// オートコミットにしない
			conn.setAutoCommit(false);

		} catch (SQLException e) {
			throw new DBException(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 切断します。
	 *
	 * @throws DBException
	 */
	public void disconnect() throws DBException {

		try {
			if (conn != null) {
				if (conn.isClosed() == true) {
					return;
				}

				conn.close();
				conn = null;
			}
			return;
		} catch (Exception e) {
			throw new DBException(e);
		}

	}

	/**
	 * コミットします。
	 * @throws DBException
	 */
	public void commit() throws DBException {

		try {
			if (conn != null) {
				conn.commit();
			}
			return;
		} catch (Exception e) {
			throw new DBException(e);
		}

	}

	/**
	 * ロールバックします。
	 * @throws DBException
	 */
	public void rollback() throws DBException {

		try {
			if (conn != null) {
				conn.rollback();
			}
			return;
		} catch (Exception e) {
			throw new DBException(e);
		}

	}

	/**
	 * ＤＢステートメントを準備します。
	 *
	 * @param sql
	 *            SQL
	 * @return Dステートメント
	 * @throws DBException
	 */
	public DBStatement prepareStatement(String sql) throws DBException {
		return new DBStatement(this, sql);
	}

	/**
	 * ＤＢステートメントを準備します。
	 *
	 * @param sql	SQL
	 * @return DBステートメント
	 * @throws DBException
	 */
	public DBStatement prepareStatement(StringBuilder sql) throws DBException {
		return new DBStatement(this, sql.toString());
	}

	/**
	 * Callステートメントを準備します。
	 *
	 * @param sql	SQL
	 * @return Callステートメント
	 * @throws DBException
	 */
	public DBCallableStatement prepareCall(String sql) throws DBException {
		return new DBCallableStatement(this, sql);
	}

	/**
	 * Callステートメントを準備します。
	 *
	 * @param sql	SQL
	 * @return Callステートメント
	 * @throws DBException
	 */
	public DBCallableStatement prepareCall(StringBuilder sql)
			throws DBException {
		return new DBCallableStatement(this, sql.toString());
	}

	/**
	 * ログ出力クラス
	 */
	protected ILogSender logSender = null;

	/**
	 * ログ出力クラスを取得します。
	 * @return ログ出力クラス
	 */
	public ILogSender getLogSender() {
		return logSender;
	}

	/**
	 * ログ出力クラスを設定します。
	 * @param logSender	ログ
	 */
	public void setLogSender(ILogSender logSender) {
		this.logSender = logSender;
	}

	/**
	 * ログを出力します。
	 * @param level	レベル
	 * @param e		例外オブジェクト
	 */
	public void logSend(String level, Throwable e) {
		if (logSender != null) {
			logSender.logSend(level, e);
		}
	}

	/**
	 * ログを出力します。
	 * @param level			レベル
	 * @param message		メッセージ
	 */
	public void logSend(String level, String message) {
		if (logSender != null) {
			logSender.logSend(level, message);
		}
	}
}