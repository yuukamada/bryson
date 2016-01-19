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
			String siplnno = reqSpin00101Search.spin00101SearchCondition.SIPLNNO;
			String cstmcd = reqSpin00101Search.accessInfo.CSTMCD;
			String brnchcd = reqSpin00101Search.accessInfo.BRNCHCD;

			// ------------- ヘッダ情報 ------------- //

			// SQL
			StringBuilder strSql = new StringBuilder();
			strSql.append("SELECT ");
			strSql.append("	 TIN020_PLANHED.SIPLNNO  ");
			strSql.append("	,TMT050_NAME.DATANM AS PLANSTSNM ");
			strSql.append("	,DATE_FORMAT(TIN020_PLANHED.ARVLPLNDATE, '%Y/%m/%d') AS ARVLPLNDATE");
			strSql.append("	,TIN020_PLANHED.DIVKBN ");
			strSql.append("	,TMT280_DIV.DIVNM ");
			strSql.append("	,TIN020_PLANHED.SPPLYCD ");
			strSql.append("	,TMT140_SPPLY.SPPLYNM ");
			strSql.append("	,TIN020_PLANHED.SIREMARK ");
			strSql.append("	,STS.UPDDATETIME ");
			strSql.append("	,STS.STSCD ");
			strSql.append("FROM ");
			strSql.append("	TIN020_PLANHED ");
			strSql.append("	INNER JOIN ( ");
			strSql.append("		SELECT ");
			strSql.append("			 CSTMCD ");
			strSql.append("			,BRNCHCD ");
			strSql.append("			,SIPLNNO ");
			strSql.append("			,UPDDATETIME ");
			strSql.append("			,CASE ");
			strSql.append("				WHEN ARVLCOMPFLG = '0' AND SICOMPFLG = '0' THEN '01' ");
			strSql.append("				WHEN ARVLCOMPFLG = '1' AND SICOMPFLG = '0' THEN '03' ");
			strSql.append("				WHEN ARVLCOMPFLG = '2' AND SICOMPFLG = '0' THEN '02' ");
			strSql.append("				WHEN SICOMPFLG = '1' THEN '05' ");
			strSql.append("				WHEN SICOMPFLG = '2' THEN '04' ");
			strSql.append("			END AS STSCD ");
			strSql.append("	 	FROM TIN010_STS ");
			strSql.append("		) AS STS ");
			strSql.append("		ON TIN020_PLANHED.CSTMCD = STS.CSTMCD ");
			strSql.append("		AND TIN020_PLANHED.BRNCHCD = STS.BRNCHCD ");
			strSql.append("		AND TIN020_PLANHED.SIPLNNO = STS.SIPLNNO ");
			strSql.append("	LEFT JOIN TMT050_NAME ");
			strSql.append("		ON TIN020_PLANHED.CSTMCD = TMT050_NAME.CSTMCD ");
			strSql.append("		AND TMT050_NAME.RCDKBN = '0250' ");
			strSql.append("		AND STS.STSCD = TMT050_NAME.DATACD ");
			strSql.append("	LEFT JOIN TMT280_DIV ");
			strSql.append("		ON TIN020_PLANHED.CSTMCD = TMT280_DIV.CSTMCD ");
			strSql.append("		AND TIN020_PLANHED.DIVKBN = TMT280_DIV.DIVKBN ");
			strSql.append("	LEFT JOIN TMT140_SPPLY ");
			strSql.append("		ON TIN020_PLANHED.CSTMCD = TMT140_SPPLY.CSTMCD ");
			strSql.append("		AND TIN020_PLANHED.SPPLYCD = TMT140_SPPLY.SPPLYCD ");
			strSql.append("WHERE TIN020_PLANHED.CSTMCD = ? ");
			strSql.append("		AND TIN020_PLANHED.BRNCHCD = ? ");
			strSql.append("		AND TIN020_PLANHED.SIPLNNO = ? ");

			System.out.println(strSql);

			// バインド変数セット
			ps = dba.prepareStatement(strSql);
			ps.setString(1,cstmcd);
			ps.setString(2,brnchcd);
			ps.setString(3,siplnno);

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
			strSql.append(" SELECT ");
			strSql.append("	 TIN040_PLANDTL.SIDTLNO ");
			strSql.append("	,TIN040_PLANDTL.ITEMGRPCD ");
			strSql.append("	,ITEMGRP.DATANM AS ITEMGRPNM ");
			strSql.append("	,TIN040_PLANDTL.ITEMCD ");
			strSql.append("	,TIN040_PLANDTL.ITEMNM ");
			strSql.append("	,TIN040_PLANDTL.ARVLPLNQTY ");
			strSql.append("	,TIN040_PLANDTL.QLTYCD ");
			strSql.append("	,QLTY.DATANM AS QLTYNM ");
			strSql.append("	,DATE_FORMAT(TIN040_PLANDTL.LIMITDATE, '%Y/%m/%d') AS LIMITDATE ");
			strSql.append("	,TIN040_PLANDTL.STCKMNGKEY1 ");
			strSql.append("	,TIN040_PLANDTL.STCKMNGKEY2 ");
			strSql.append("	,TIN040_PLANDTL.STCKMNGKEY3 ");
			strSql.append("	,TIN040_PLANDTL.SIPRICE ");
			strSql.append("	,TIN040_PLANDTL.SIDTLREMARK ");
			strSql.append(" FROM ");
			strSql.append("	TIN040_PLANDTL ");
			strSql.append("	LEFT JOIN TMT050_NAME ITEMGRP ");
			strSql.append("		ON TIN040_PLANDTL.CSTMCD = ITEMGRP.CSTMCD ");
			strSql.append("		AND ITEMGRP.RCDKBN = '0050' ");
			strSql.append("		AND TIN040_PLANDTL.ITEMGRPCD = ITEMGRP.DATACD ");
			strSql.append("	LEFT JOIN TMT050_NAME QLTY ");
			strSql.append("		ON TIN040_PLANDTL.CSTMCD = QLTY.CSTMCD ");
			strSql.append("		AND QLTY.RCDKBN = '0040' ");
			strSql.append("		AND TIN040_PLANDTL.QLTYCD = QLTY.DATACD ");
			strSql.append(" WHERE TIN040_PLANDTL.CSTMCD = ? ");
			strSql.append("  AND TIN040_PLANDTL.BRNCHCD = ? ");
			strSql.append("  AND TIN040_PLANDTL.SIPLNNO = ? ");
			strSql.append(" ORDER BY ");
			strSql.append("	TIN040_PLANDTL.SIDTLNO ");

			System.out.println(strSql);

			// バインド変数セット
			ps = dba.prepareStatement(strSql);
			ps.setString(1,cstmcd);
			ps.setString(2,brnchcd);
			ps.setString(3,siplnno);

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
			resSpin00101Search.spin00101Search.SIPLNNO = rs.getString("SIPLNNO");
			resSpin00101Search.spin00101Search.PLANSTSNM = rs.getString("PLANSTSNM");
			resSpin00101Search.spin00101Search.ARVLPLNDATE = rs.getString("ARVLPLNDATE");
			resSpin00101Search.spin00101Search.DIVKBN = rs.getString("DIVKBN");
			resSpin00101Search.spin00101Search.DIVNM = rs.getString("DIVNM");
			resSpin00101Search.spin00101Search.SPPLYCD = rs.getString("SPPLYCD");
			resSpin00101Search.spin00101Search.SPPLYNM = rs.getString("SPPLYNM");
			resSpin00101Search.spin00101Search.SIREMARK = rs.getString("SIREMARK");
			resSpin00101Search.spin00101Search.UPDDATETIME = rs.getString("UPDDATETIME");
			resSpin00101Search.spin00101Search.STSCD = rs.getString("STSCD");
		}
	}
}
