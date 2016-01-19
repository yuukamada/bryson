package jp.co.brycen.wms.spin00101regist.process;

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
import jp.co.brycen.wms.sequence.dto.SequenceRequest;
import jp.co.brycen.wms.sequence.dto.SequenceResponse;
import jp.co.brycen.wms.sequence.process.SequenceProcess;
import jp.co.brycen.wms.spin00101regist.dto.Spin00101RegistRequest;
import jp.co.brycen.wms.spin00101regist.dto.Spin00101RegistResponse;


/**
 * 画面：入荷予定登録
 * 概要：登録
 */
public class Spin00101RegistProcess extends AbstractProcess {

	public Spin00101RegistProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public void beforeProcess(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse) throws ProcessCheckErrorException {
	}


	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse)
			throws FatalException, DBException, ProcessCheckErrorException{

		Spin00101RegistRequest reqSpin00101Regist = (Spin00101RegistRequest)request;

		// 入荷伝票番号を取得
		String siplnno =  getSequence(dba, reqSpin00101Regist);

		// [入荷ステータス]テーブルに登録
		insertSts(dba, reqSpin00101Regist, siplnno);

		// [入荷予定ヘッダ]テーブルに登録
		insertPlanHead(dba, reqSpin00101Regist, siplnno);

		// レスポンス
		Spin00101RegistResponse resSpin00101Regist = (Spin00101RegistResponse)response;
		resSpin00101Regist.spin00101ExecResult.SIPLNNO = siplnno;

		return resSpin00101Regist;
	}


	/**
	 * 入荷ステータスをinsertする
	 * @param dba
	 * @param reqSpin00101Regist
	 * @param siplnno
	 * @throws DBException
	 */
	private void insertSts(DBAccessor dba, Spin00101RegistRequest reqSpin00101Regist, String siplnno) throws DBException{

		DBStatement ps = null;

		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" INSERT INTO TIN010_STS ( ");
			strSql.append("  CSTMCD ");
			strSql.append(" ,BRNCHCD ");
			strSql.append(" ,SIPLNNO ");
			strSql.append(" ,ARVLCOMPFLG ");
			strSql.append(" ,SICOMPFLG ");
			strSql.append(" ,RSLTSENDFLG ");
			strSql.append(" ,STRRSRV1 ");
			strSql.append(" ,STRRSRV2 ");
			strSql.append(" ,STRRSRV3 ");
			strSql.append(" ,STRRSRV4 ");
			strSql.append(" ,STRRSRV5 ");
			strSql.append(" ,STRRSRV6 ");
			strSql.append(" ,STRRSRV7 ");
			strSql.append(" ,STRRSRV8 ");
			strSql.append(" ,STRRSRV9 ");
			strSql.append(" ,STRRSRV10 ");
			strSql.append(" ,ENTUSRCD ");
			strSql.append(" ,ENTDATETIME ");
			strSql.append(" ,ENTPRG ");
			strSql.append(" ,UPDUSRCD ");
			strSql.append(" ,UPDDATETIME ");
			strSql.append(" ,UPDPRG ");
			strSql.append(" ) values ( ");
			strSql.append("  ?");
			strSql.append(" ,?");
			strSql.append(" ,?");
			strSql.append(" ,0");
			strSql.append(" ,0");
			strSql.append(" ,0");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,?");
			strSql.append(" ,SYSDATE(6) ");
			strSql.append(" ,?");
			strSql.append(" ,?");
			strSql.append(" ,SYSDATE(6) ");
			strSql.append(" ,?");
			strSql.append(" ); ");

			// バインド変数セット
			ps = dba.prepareStatement(strSql);
			ps.setString(1, reqSpin00101Regist.accessInfo.CSTMCD);
			ps.setString(2, reqSpin00101Regist.accessInfo.BRNCHCD);
			ps.setString(3, siplnno);
			ps.setString(4, reqSpin00101Regist.accessInfo.USRCD);
			ps.setString(5, FuncID.FPIN0010);
			ps.setString(6, reqSpin00101Regist.accessInfo.USRCD);
			ps.setString(7, FuncID.FPIN0010);

			// SQL実行
			ps.executeUpdate();
		} finally {
			if (ps != null) ps.close();
		}
	}


	/**
	 * 入荷予定ヘッダをinsertする
	 * @param dba
	 * @param reqSpin00101Regist
	 * @param siplnno
	 * @throws DBException
	 */
	private void insertPlanHead(DBAccessor dba, Spin00101RegistRequest reqSpin00101Regist, String siplnno) throws DBException{

		DBStatement ps = null;

		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" INSERT INTO TIN020_PLANHED ( ");
			strSql.append("  CSTMCD ");
			strSql.append(" ,BRNCHCD ");
			strSql.append(" ,SIPLNNO ");
			strSql.append(" ,CSTMORDNO ");
			strSql.append(" ,SPPLYCD ");
			strSql.append(" ,ARVLPLNDATE ");
			strSql.append(" ,DIVKBN ");
			strSql.append(" ,SIREMARK ");
			strSql.append(" ,RTNSORSLTNO ");
			strSql.append(" ,RTNSOORDNO ");
			strSql.append(" ,MVSORSLTNO ");
			strSql.append(" ,MVSOORDNO ");
			strSql.append(" ,STRRSRV1 ");
			strSql.append(" ,STRRSRV2 ");
			strSql.append(" ,STRRSRV3 ");
			strSql.append(" ,STRRSRV4 ");
			strSql.append(" ,STRRSRV5 ");
			strSql.append(" ,STRRSRV6 ");
			strSql.append(" ,STRRSRV7 ");
			strSql.append(" ,STRRSRV8 ");
			strSql.append(" ,STRRSRV9 ");
			strSql.append(" ,STRRSRV10 ");
			strSql.append(" ,ENTUSRCD ");
			strSql.append(" ,ENTDATETIME ");
			strSql.append(" ,ENTPRG ");
			strSql.append(" ,UPDUSRCD ");
			strSql.append(" ,UPDDATETIME ");
			strSql.append(" ,UPDPRG ");
			strSql.append(" ) values ( ");
			strSql.append("  ?");
			strSql.append(" ,?");
			strSql.append(" ,?");
			strSql.append(" ,Null");
			strSql.append(" ,?");
			strSql.append(" ,SYSDATE(6)");
			strSql.append(" ,?");
			strSql.append(" ,?");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,Null");
			strSql.append(" ,?");
			strSql.append(" ,SYSDATE(6) ");
			strSql.append(" ,?");
			strSql.append(" ,?");
			strSql.append(" ,SYSDATE(6) ");
			strSql.append(" ,?");
			strSql.append(" ); ");

			// バインド変数セット
			ps = dba.prepareStatement(strSql);
			ps.setString(1, reqSpin00101Regist.accessInfo.CSTMCD);
			ps.setString(2, reqSpin00101Regist.accessInfo.BRNCHCD);
			ps.setString(3, siplnno);
			ps.setString(4, reqSpin00101Regist.spin00101ExecHeader.SPPLYCD);
			ps.setString(5, reqSpin00101Regist.spin00101ExecHeader.DIVKBN);
			ps.setString(6, reqSpin00101Regist.spin00101ExecHeader.SIREMARK);
			ps.setString(7, reqSpin00101Regist.accessInfo.USRCD);
			ps.setString(8, FuncID.FPIN0010);
			ps.setString(9, reqSpin00101Regist.accessInfo.USRCD);
			ps.setString(10, FuncID.FPIN0010);

			// SQL実行
			ps.executeUpdate();
		} finally {
			if (ps != null) ps.close();
		}
	}


	/**
	 * シーケンスを取得する
	 * @param dba
	 * @param request
	 * @return
	 * @throws FatalException
	 * @throws DBException
	 * @throws ProcessCheckErrorException
	 */
	private String getSequence(DBAccessor dba, Spin00101RegistRequest request) throws FatalException, DBException, ProcessCheckErrorException{
		// リクエストを作成
		SequenceRequest reqSequence = new SequenceRequest();
		reqSequence.sequenceCondition.PREFIX = "ID";
		reqSequence.accessInfo = request.accessInfo;

		// シーケンスを取得するプロセスを実行
		SequenceProcess procSequence = new SequenceProcess(this);
		SequenceResponse resSequence = (SequenceResponse)procSequence.execute(dba, reqSequence, null);
		return resSequence.sequence.SEQUENCE;
	}


	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request){
		return new Spin00101RegistResponse();
	}
}
