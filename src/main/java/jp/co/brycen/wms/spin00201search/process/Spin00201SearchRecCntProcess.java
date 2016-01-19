package jp.co.brycen.wms.spin00201search.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.wms.spin00201search.dto.Spin00201SearchResponse;

/**
 * 画面：入荷予定照会
 * 概要：検索(件数)
 */
public class Spin00201SearchRecCntProcess extends Spin00201SearchAllRecProcess {

	public Spin00201SearchRecCntProcess(ILogSender logSender) {
		super(logSender);
	}

	/**
	 * レスポンスのインスタンスを取得
	 * @return
	 */
	protected AbstractResponse getResponse()
	{
		return new Spin00201SearchResponse();
	}

	/**
	 * Select句をセット
	 * @return
	 */
	protected String setSelectFields()
	{
		StringBuilder strSql = new StringBuilder();
		strSql.append("	count(*) as CNT ");

		return strSql.toString();
	}

	/**
	 * レスポンスをセット
	 * @return
	 * @throws SQLException
	 */
	protected void setResponse(ResultSet rs, Spin00201SearchResponse resSpin00201Search) throws SQLException
	{
		while (rs.next()) {
			resSpin00201Search.DATACNT = rs.getLong("CNT");
		}
	}
}
