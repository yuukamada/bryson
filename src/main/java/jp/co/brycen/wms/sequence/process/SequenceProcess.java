package jp.co.brycen.wms.sequence.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import jp.co.brycen.wms.sequence.dto.SequenceRequest;
import jp.co.brycen.wms.sequence.dto.SequenceResponse;

/**
 * 概要：シーケンス取得
 */
public class SequenceProcess extends AbstractProcess {

	public SequenceProcess(ILogSender logSender) {
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

			SequenceResponse resSequence = (SequenceResponse)response;

			// シーケンス番号を取得
			resSequence.sequence.SEQUENCE = getSequence(dba, request);

			// シーケンスをカウントアップ
			updateSequence(dba, request);

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


	/**
	 * シーケンス番号をカウントアップする
	 * @param dba
	 * @param request
	 * @throws DBException
	 */
	private void updateSequence(DBAccessor dba, AbstractRequest request) throws DBException{

		DBStatement ps = null;

		// 検索条件
		SequenceRequest reqSequence = (SequenceRequest) request;
		String prefix = reqSequence.sequenceCondition.PREFIX;

		// SQL
		StringBuilder strSql = new StringBuilder();
		strSql.append(" UPDATE ");
		strSql.append("	TCC030_SEQNOADM");
		strSql.append(" SET SEQNO = SEQNO + 1");
		strSql.append(" WHERE PREFIX = ? ");

		System.out.println(strSql);
		ps = dba.prepareStatement(strSql);

		// バインド変数セット
		ps.setString(1, prefix);

		// SQL実行
		ps.executeUpdate();
	}

	/**
	 * シーケンス番号を取得
	 * @param dba
	 * @param request
	 * @return
	 * @throws DBException
	 * @throws SQLException
	 */
	private String getSequence(DBAccessor dba, AbstractRequest request) throws DBException, SQLException{

		ResultSet rs = null;
		DBStatement ps = null;
		String sequence = "";

		// 検索条件
		SequenceRequest reqSequence = (SequenceRequest) request;
		String prefix = reqSequence.sequenceCondition.PREFIX;

		// SQL
		StringBuilder strSql = new StringBuilder();
		strSql.append(" SELECT ");
		strSql.append("	SEQNO");
		strSql.append("	,MAXDIGIT");
		strSql.append(" FROM");
		strSql.append("	TCC030_SEQNOADM");
		strSql.append(" WHERE PREFIX = ? ");

		System.out.println(strSql);
		ps = dba.prepareStatement(strSql);

		// バインド変数セット
		ps.setString(1, prefix);

		// SQL実行
		rs = ps.executeQuery();

		// 実行結果をレスポンスにつめる
		if (rs.next()) {
			// 本日日付を取得
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
	        String yymmdd = sdf.format(cal.getTime());

	        // 桁を0埋めする
	        String padSeqno = String.format("%0" + rs.getInt("MAXDIGIT") + "d", rs.getInt("SEQNO")+1);
			sequence = prefix + yymmdd + padSeqno;
		}

		return sequence;
	}

	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request){
		return new SequenceResponse();
	}
}
