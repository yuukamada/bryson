package jp.co.brycen.wms.spin00301search.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.wms.spin00301search.dto.Spin00301SearchResponse;

/**
 * 画面：入荷予定照会
 * 概要：検索(件数)
 */
public class Spin00301SearchRecCntProcess extends Spin00301SearchAllRecProcess {

	public Spin00301SearchRecCntProcess(ILogSender logSender) {
		super(logSender);
	}

	/**
	 * レスポンスのインスタンスを取得
	 * @return
	 */
	protected AbstractResponse getResponse()
	{
		return new Spin00301SearchResponse();
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
	protected void setResponse(ResultSet rs, Spin00301SearchResponse resSpin00301Search) throws SQLException
	{
		while (rs.next()) {
			resSpin00301Search.DATACNT = rs.getLong("CNT");
		}
	}
}
