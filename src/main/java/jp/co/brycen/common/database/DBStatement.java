// Copyright(c) 2010 NCR Japan Ltd.
//
package jp.co.brycen.common.database;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.brycen.common.exception.DBException;

/**
 * DBステートメント
 */
public class DBStatement {

	/**
	 * 準備済み命令文
	 */
	protected PreparedStatement ps;

	/**
	 * DB接続
	 */
	protected DBAccessor dba;

	/**
	 * ＳＱＬ文字列
	 */
	protected String sql;

	/**
	 * パラメータ
	 */
	protected StringBuilder params;

	/**
	 * コンストラクタ
	 */
	protected DBStatement() {
	}

	/**
	 * コンストラクタ
	 *
	 * @param dba DB接続
	 * @param sql ＳＱＬ文字列
	 * @throws DBException
	 */
	public DBStatement(DBAccessor dba, String sql) throws DBException {
		try {
			this.dba = dba;
			this.sql = sql;
			this.ps = dba.getConnection().prepareStatement(sql);
			params = new StringBuilder().append(" param:");
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * closeします。
	 *
	 * @throws DBException
	 */
	public void close() throws DBException {
		try {
			ps.close();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * 検索系ＳＱＬを実行します。
	 *
	 * @return 検索結果
	 * @throws DBException
	 */
	public ResultSet executeQuery() throws DBException {
		try {
			dba.logSend("T", sql + "\n>" + params.toString());
			return ps.executeQuery();
		} catch (SQLException e) {
			DBException dbException = new DBException(e);
			dbException.setSQLErrorCode(e.getErrorCode());

			throw dbException;
		}
	}

	/**
	 * 更新系ＳＱＬを実行します。
	 *
	 * @return 更新件数
	 * @throws DBException
	 */
	public int executeUpdate() throws DBException {

		try {
			dba.logSend("T", sql + "\n>" + params.toString());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * String型パラメータを設定します。
	 *
	 * @param parameterIndex パラメータIndex
	 * @param x 設定する値
	 * @throws DBException
	 */
	public void setString(int parameterIndex, String x) throws DBException {
		try {
			params.append("[" + parameterIndex + "-" + x + "]");
			// 空文字はnullにする
			if(x == ""){
				ps.setString(parameterIndex, null);
			} else {
				ps.setString(parameterIndex, x);
			}
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * Long型パラメータを設定します。
	 *
	 * @param parameterIndex パラメータIndex
	 * @param x 設定する値
	 * @throws DBException
	 */
	public void setLong(int parameterIndex, long x) throws DBException {
		try {
			params.append("[" + parameterIndex + "-" + x + "]");
			ps.setLong(parameterIndex, x);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * Int型パラメータを設定します。
	 *
	 * @param parameterIndex パラメータIndex
	 * @param x 設定する値
	 * @throws DBException
	 */
	public void setInt(int parameterIndex, int x) throws DBException {
		try {
			params.append("[" + parameterIndex + "-" + x + "]");
			ps.setInt(parameterIndex, x);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * BigDecimal型パラメータを設定します。
	 *
	 * @param parameterIndex パラメータIndex
	 * @param x 設定する値
	 * @throws DBException
	 */
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws DBException {
		try {
			params.append("[" + parameterIndex + "-" + x + "]");
			ps.setBigDecimal(parameterIndex, x);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * Date型パラメータを設定します。
	 *
	 * @param parameterIndex パラメータIndex
	 * @param x 設定する値
	 * @throws DBException
	 */
	public void setDate(int parameterIndex, java.sql.Date x) throws DBException {
		try {
			params.append("[" + parameterIndex + "-" + x + "]");
			ps.setDate(parameterIndex, x);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * BigDecimal型パラメータを設定します。
	 *
	 * @param parameterIndex パラメータIndex
	 * @param x 設定する値
	 * @throws DBException
	 */
	public void setFloat(int parameterIndex, Float x) throws DBException {
		try {
			params.append("[" + parameterIndex + "-" + x + "]");
			ps.setFloat(parameterIndex, x);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

}
