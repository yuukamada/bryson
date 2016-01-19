package jp.co.brycen.wms.tmt280search.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.brycen.common.FuncID;
import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.database.DBStatement;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.wms.tmt280.dto.Tmt280SearchRowDto;
import jp.co.brycen.wms.tmt280search.dto.Tmt280SearchRequest;
import jp.co.brycen.wms.tmt280search.dto.Tmt280SearchResponse;

/**
 * 概要：[受払区分]テーブルから取得
 */
public class Tmt280SearchProcess extends AbstractProcess {

	public Tmt280SearchProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse)
			throws FatalException, DBException, ProcessCheckErrorException {

		ResultSet rs = null;
		DBStatement ps = null;
		Tmt280SearchRequest searchItemInfo = (Tmt280SearchRequest) request;

		try {
			// SQL
			StringBuilder strSql = new StringBuilder();
			strSql.append("SELECT");
			strSql.append(" DIVKBN");
			strSql.append(",DIVNM");
			strSql.append(" ,DIVRNM");
			strSql.append(" FROM TMT280_DIV ");
			strSql.append(" WHERE CSTMCD = ? ");

			switch(searchItemInfo.tmt280SearchCondition.FUNCID)
			{
				case FuncID.FPIN0010:
				case FuncID.FPIN0020:
				case FuncID.FPIN0060:
					strSql.append("	AND SIFLG = '1'  ");
					break;
				case FuncID.FPOT0030:
					strSql.append(" AND ( SOFLG = '1' OR MKFLG = '1' ) ");
					strSql.append(" AND DIVKBN NOT IN ('14', '15') ");
					break;
				default:
					break;
			}

			strSql.append(" ORDER BY DIVKBN");
			strSql.append(";");

			System.out.println(strSql);

			// バインド変数をセット
			ps = dba.prepareStatement(strSql);
			ps.setString(1, request.accessInfo.CSTMCD);

			rs = ps.executeQuery();

			// DBから値を取得
			ArrayList<Tmt280SearchRowDto> lst = new ArrayList<Tmt280SearchRowDto>();
			while (rs.next()) {
				Tmt280SearchRowDto row = new Tmt280SearchRowDto();
				row.DIVKBN = rs.getString("DIVKBN");
				row.DIVNM = rs.getString("DIVNM");
				row.DIVRNM = rs.getString("DIVRNM");

				lst.add(row);
			}

			// 結果をレスポンスにつめる
			Tmt280SearchResponse result = (Tmt280SearchResponse)response;
			result.rows = lst;
			if (lst.toArray().length == 0) {
				result.rows = null;
			}

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e);
		} finally {
			if (ps != null)
				ps.close();
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException(e);
			}
		}
	}

	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request) {
		return new Tmt280SearchResponse();
	}
}
