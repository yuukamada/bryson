package jp.co.brycen.wms.tmt050search.process;

import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.LogLevel;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.database.DBStatement;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.utility.CommonUtility;
import jp.co.brycen.wms.tmt050.dto.Tmt050searchDto;
import jp.co.brycen.wms.tmt050search.dto.Tmt050SearchRequest;
import jp.co.brycen.wms.tmt050search.dto.Tmt050SearchResponse;

/**
 * 概要：[名称マスタ]テーブルから取得
 */
public class Tmt050SearchProcess extends AbstractProcess {

	public Tmt050SearchProcess(ILogSender logSender) {
		super(logSender);
	}


	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse)
			throws FatalException, DBException, ProcessCheckErrorException {

		ResultSet rs = null;
		DBStatement ps = null;

		try {

			Tmt050SearchRequest searchItem = (Tmt050SearchRequest)request;

			// SQL
			StringBuilder strSql = new StringBuilder();
			strSql.append("SELECT");
			strSql.append(" DATACD");
			strSql.append(",DATANM");
			strSql.append(" ,DATARNM");
			strSql.append(" FROM TMT050_NAME ");
			strSql.append(" WHERE CSTMCD = ? ");
			strSql.append("	AND RCDKBN = ?  ");
			strSql.append("	AND DATACD = ?  ");
			strSql.append(";");

			System.out.println(strSql);

			// バインド変数をセット
			ps = dba.prepareStatement(strSql);
			ps.setString(1, searchItem.accessInfo.CSTMCD);
			ps.setString(2, searchItem.tmt050SearchCondition.RCDKBN);
			ps.setString(3, searchItem.tmt050SearchCondition.DATACD);

			rs = ps.executeQuery();

			// DBから値を取得
			Tmt050searchDto tmt050search = new Tmt050searchDto();
			if (rs.next()) {
				tmt050search.DATACD = rs.getString("DATACD");
				tmt050search.DATANM = rs.getString("DATANM");
				tmt050search.DATARNM = rs.getString("DATARNM");
			}

			// 結果をレスポンスにつめる
			Tmt050SearchResponse resTmt050Search = (Tmt050SearchResponse)response;
			resTmt050Search.tmt050search = tmt050search;

			return resTmt050Search;

		} catch (SQLException e) {
			logSend(LogLevel.ERROR, CommonUtility.getStackTraceString(e));
			throw new DBException(e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			} catch (SQLException e) {
				// なにかあってもムシする
				logSend(LogLevel.ERROR, CommonUtility.getStackTraceString(e));
			}
		}
	}


	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request){
		return new Tmt050SearchResponse();
	}
}
