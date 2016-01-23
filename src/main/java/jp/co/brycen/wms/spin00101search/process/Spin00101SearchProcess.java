package jp.co.brycen.wms.spin00101search.process;

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
import jp.co.brycen.wms.spin00101search.dto.Spin00101SearchRequest;
import jp.co.brycen.wms.spin00101search.dto.Spin00101SearchResponse;

/**
 * 画面：入荷予定登録
 * 概要：検索
 */
public class Spin00101SearchProcess extends AbstractProcess {

	public Spin00101SearchProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public void beforeProcess(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse) throws ProcessCheckErrorException {
	}

	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse)
			throws FatalException, DBException, ProcessCheckErrorException{

		ResultSet rs = null;
		DBStatement ps = null;

		try {
			// 検索条件
			Spin00101SearchRequest reqSpin00101Search = (Spin00101SearchRequest) request;
			String id = String.valueOf(reqSpin00101Search.spin00101SearchCondition.ID);
			String cstmcd = reqSpin00101Search.accessInfo.CSTMCD;
			String brnchcd = reqSpin00101Search.accessInfo.BRNCHCD;

			// ------------- ヘッダ情報 ------------- //

			// SQL
			StringBuilder strSql = new StringBuilder();
			strSql.append("SELECT ");
			strSql.append("	 ID, NAME, AGE, ADDRESS, EXPERIENCE, COMMUNICATION, CODING, DESIGN, TEST, PHYSICAL ");
			strSql.append("	FROM ");
			strSql.append("	TMS ");
			strSql.append("WHERE ID = ? ");

			System.out.println(strSql);

			// バインド変数セット
			ps = dba.prepareStatement(strSql);
			ps.setString(1,id);

			// SQL実行
			rs = ps.executeQuery();

			// 実行結果をレスポンスにつめる
			setHeader(rs, (Spin00101SearchResponse)response);


			// ヘッダ情報の取得処理終了
			rs.close();
			ps.close();

			// ------------- 明細情報 ------------- //

			// SQL
			strSql.setLength(0);
			strSql.append("SELECT ");
			strSql.append("	 ID, NAME, AGE, ADDRESS, EXPERIENCE, COMMUNICATION, CODING, DESIGN, TEST, PHYSICAL ");
			strSql.append("	FROM ");
			strSql.append("	TMS ");
			strSql.append("WHERE ID = ? ");

			System.out.println(strSql);

			// バインド変数セット
			ps = dba.prepareStatement(strSql);
			ps.setString(1,id);

			// SQL実行
			rs = ps.executeQuery();



			return response;

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
		return new Spin00101SearchResponse();
	}


	/**
	 * ヘッダ情報をセット
	 * @return
	 * @throws SQLException
	 */
	protected void setHeader(ResultSet rs, Spin00101SearchResponse resSpin00101Search) throws SQLException
	{
		if (rs.next()) {
			resSpin00101Search.spin00101Search.ID = Integer.parseInt(rs.getString("ID"));
			resSpin00101Search.spin00101Search.NAME = rs.getString("NAME");
			resSpin00101Search.spin00101Search.AGE = Integer.parseInt(rs.getString("AGE"));
			resSpin00101Search.spin00101Search.ADDRESS = rs.getString("ADDRESS");
			resSpin00101Search.spin00101Search.EXPERIENCE = Integer.parseInt(rs.getString("EXPERIENCE"));
			resSpin00101Search.spin00101Search.COMMUNICATION = Integer.parseInt(rs.getString("COMMUNICATION"));
			resSpin00101Search.spin00101Search.CODING = Integer.parseInt(rs.getString("CODING"));
			resSpin00101Search.spin00101Search.DESIGN = Integer.parseInt(rs.getString("DESIGN"));
			resSpin00101Search.spin00101Search.TEST = Integer.parseInt(rs.getString("TEST"));
			resSpin00101Search.spin00101Search.PHYSICAL = Integer.parseInt(rs.getString("PHYSICAL"));
		}
	}
}
