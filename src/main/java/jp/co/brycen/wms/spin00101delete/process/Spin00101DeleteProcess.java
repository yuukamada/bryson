package jp.co.brycen.wms.spin00101delete.process;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.database.DBStatement;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.wms.spin00101delete.dto.Spin00101DeleteRequest;
import jp.co.brycen.wms.spin00101delete.dto.Spin00101DeleteResponse;


/**
 * 画面：入荷予定登録
 * 概要：伝票削除
 */
public class Spin00101DeleteProcess extends AbstractProcess {

	public Spin00101DeleteProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public void beforeProcess(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse) throws ProcessCheckErrorException {
	}


	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse)
			throws FatalException, DBException, ProcessCheckErrorException{

		// リクエスト
		Spin00101DeleteRequest reqSpin00101Delete = (Spin00101DeleteRequest)request;

		// [入荷ステータス]テーブルから削除
		deleteSts(dba, reqSpin00101Delete);

		// [入荷予定ヘッダ]テーブルから削除
		deletePlanHead(dba, reqSpin00101Delete);

		return response;
	}


	/**
	 * 入荷ステータスをdeleteする
	 * @param dba
	 * @param reqSpin00101Delete
	 * @throws DBException
	 */
	private void deleteSts(DBAccessor dba, Spin00101DeleteRequest reqSpin00101Delete) throws DBException{

		DBStatement ps = null;

		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" DELETE FROM TIN010_STS ");
			strSql.append(" WHERE CSTMCD = ? ");
			strSql.append("     AND BRNCHCD = ? ");
			strSql.append("     AND SIPLNNO = ? ");

			System.out.println(strSql);

			// バインド変数セット
			ps = dba.prepareStatement(strSql);
			ps.setString(1, reqSpin00101Delete.accessInfo.CSTMCD);
			ps.setString(2, reqSpin00101Delete.accessInfo.BRNCHCD);
//			ps.setString(3, reqSpin00101Delete.spin00101DeleteCondition.SIPLNNO);

			// SQL実行
			ps.executeUpdate();
		} finally {
			if (ps != null) ps.close();
		}
	}


	/**
	 * 入荷予定ヘッダをdeleteする
	 * @param dba
	 * @param reqSpin00101Delete
	 * @param siplnno
	 * @throws DBException
	 */
	private void deletePlanHead(DBAccessor dba, Spin00101DeleteRequest reqSpin00101Delete) throws DBException{

		DBStatement ps = null;

		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" DELETE FROM TIN020_PLANHED ");
			strSql.append(" WHERE CSTMCD = ? ");
			strSql.append("     AND BRNCHCD = ? ");
			strSql.append("     AND SIPLNNO = ? ");

			System.out.println(strSql);

			// バインド変数セット
			ps = dba.prepareStatement(strSql);
			ps.setString(1, reqSpin00101Delete.accessInfo.CSTMCD);
			ps.setString(2, reqSpin00101Delete.accessInfo.BRNCHCD);
//			ps.setString(3, reqSpin00101Delete.spin00101DeleteCondition.SIPLNNO);

			// SQL実行
			ps.executeUpdate();
		} finally {
			if (ps != null) ps.close();
		}
	}


	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request){
		return new Spin00101DeleteResponse();
	}
}
