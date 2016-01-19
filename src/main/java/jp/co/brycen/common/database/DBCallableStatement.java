package jp.co.brycen.common.database;

import java.sql.CallableStatement;
import java.sql.SQLException;

import jp.co.brycen.common.exception.DBException;

public class DBCallableStatement extends DBStatement {

	/**
	 * 準備済み命令文
	 */
	protected CallableStatement cs;

	/**
	 * コンストラクタ
	 *
	 * @param dba DB接続
	 * @param sql ＳＱＬ文字列
	 * @throws DBException
	 */
	public DBCallableStatement(DBAccessor dba, String sql) throws DBException {
		try {
			this.dba = dba;
			this.sql = sql;
			this.cs = dba.getConnection().prepareCall(sql);
			this.ps = cs;
			params = new StringBuilder().append(" param:");
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * OUTパラメーターを設定します。
	 * @param parameterIndex index
	 * @param sqlType 型
	 * @throws DBException
	 */
	public void registerOutParameter(int parameterIndex, int sqlType) throws DBException {
		try {
			params.append("[" + parameterIndex + "-OUT]");
			cs.registerOutParameter(parameterIndex, sqlType);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * OUTパラメータを取得します。
	 * @param parameterIndex index
	 * @return
	 * @throws DBException
	 */
    public String getString(int parameterIndex) throws DBException {
		try {
			return cs.getString(parameterIndex);
		} catch (SQLException e) {
			throw new DBException(e);
		}
    }

}
