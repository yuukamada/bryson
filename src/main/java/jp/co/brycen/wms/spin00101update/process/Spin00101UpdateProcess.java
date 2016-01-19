package jp.co.brycen.wms.spin00101update.process;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.FuncID;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.database.DBStatement;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.wms.spin00101update.dto.Spin00101UpdateRequest;
import jp.co.brycen.wms.spin00101update.dto.Spin00101UpdateResponse;

/**
 * 画面：入荷予定登録
 * 概要：更新
 */
public class Spin00101UpdateProcess extends AbstractProcess {

	public Spin00101UpdateProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public void beforeProcess(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse) throws ProcessCheckErrorException {
	}


	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse)
			throws FatalException, DBException, ProcessCheckErrorException{

		Spin00101UpdateRequest reqSpin00101Update = (Spin00101UpdateRequest)request;

		// 入庫済かどうかを判断する
		String siCompFlg ="2";

		// [入荷ステータス]テーブルに登録
		updateSts(dba, reqSpin00101Update, siCompFlg);

		// [入荷予定ヘッダ]テーブルに登録
		updatePlanHead(dba, reqSpin00101Update);

		// レスポンス
		Spin00101UpdateResponse resSpin00101Update = (Spin00101UpdateResponse)response;
		resSpin00101Update.spin00101ExecResult.SIPLNNO = reqSpin00101Update.spin00101ExecHeader.SIPLNNO;

		return resSpin00101Update;
	}


	/**
	 * [入荷ステータス]テーブル更新
	 * @param dba
	 * @param reqSpin00101Update
	 * @param compFlg
	 * @throws DBException
	 */
	private void updateSts(DBAccessor dba, Spin00101UpdateRequest reqSpin00101Update, String siCompFlg) throws DBException{

		DBStatement ps = null;

		// 入荷完了フラグ
		String arvlCompFlg = "";
		if(siCompFlg == "2"){
			arvlCompFlg = "1";
		} else {
			arvlCompFlg = siCompFlg;
		}

		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" UPDATE TIN010_STS SET ");
			strSql.append("  ARVLCOMPFLG = ? ");
			strSql.append(" ,SICOMPFLG= ?  ");
			strSql.append(" ,UPDUSRCD = ? ");
			strSql.append(" ,UPDDATETIME = SYSDATE(6) ");
			strSql.append(" ,UPDPRG = ? ");
			strSql.append(" WHERE CSTMCD = ? ");
			strSql.append("     AND BRNCHCD = ? ");
			strSql.append("     AND SIPLNNO = ? ");

			// バインド変数セット
			ps = dba.prepareStatement(strSql);
			ps.setString(1, arvlCompFlg);
			ps.setString(2, siCompFlg);
			ps.setString(3, reqSpin00101Update.accessInfo.USRCD);
			ps.setString(4, FuncID.FPIN0010);
			ps.setString(5, reqSpin00101Update.accessInfo.CSTMCD);
			ps.setString(6, reqSpin00101Update.accessInfo.BRNCHCD);
			ps.setString(7, reqSpin00101Update.spin00101ExecHeader.SIPLNNO);

			// SQL実行
			ps.executeUpdate();
		} finally {
			if (ps != null) ps.close();
		}
	}


	/**
	 * 入荷予定ヘッダをupdateする
	 * @param dba
	 * @param reqSpin00101Update
	 * @param siplnno
	 * @throws DBException
	 */
	private void updatePlanHead(DBAccessor dba, Spin00101UpdateRequest reqSpin00101Update) throws DBException{

		DBStatement ps = null;

		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" UPDATE TIN020_PLANHED SET ");
			strSql.append("  SPPLYCD = ? ");
			strSql.append(" ,DIVKBN = ?");
			strSql.append(" ,SIREMARK = ? ");
			strSql.append(" ,UPDUSRCD = ? ");
			strSql.append(" ,UPDDATETIME = SYSDATE(6) ");
			strSql.append(" ,UPDPRG = ? ");
			strSql.append(" WHERE CSTMCD = ? ");
			strSql.append("     AND BRNCHCD = ? ");
			strSql.append("     AND SIPLNNO = ? ");

			System.out.println(strSql);

			// バインド変数セット
			ps = dba.prepareStatement(strSql);
			ps.setString(1, reqSpin00101Update.spin00101ExecHeader.SPPLYCD);
			ps.setString(2, reqSpin00101Update.spin00101ExecHeader.DIVKBN);
			ps.setString(3, reqSpin00101Update.spin00101ExecHeader.SIREMARK);
			ps.setString(4, reqSpin00101Update.accessInfo.USRCD);
			ps.setString(5, FuncID.FPIN0010);
			ps.setString(6, reqSpin00101Update.accessInfo.CSTMCD);
			ps.setString(7, reqSpin00101Update.accessInfo.BRNCHCD);
			ps.setString(8, reqSpin00101Update.spin00101ExecHeader.SIPLNNO);

			// SQL実行
			ps.executeUpdate();
		} finally {
			if (ps != null) ps.close();
		}
	}


	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request){
		return new Spin00101UpdateResponse();
	}
}
