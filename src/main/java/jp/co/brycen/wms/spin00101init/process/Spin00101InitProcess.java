package jp.co.brycen.wms.spin00101init.process;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.wms.spin00101init.dto.Spin00101InitRequest;
import jp.co.brycen.wms.spin00101init.dto.Spin00101InitResponse;
import jp.co.brycen.wms.tmt050search.dto.Tmt050SearchRequest;
import jp.co.brycen.wms.tmt050search.dto.Tmt050SearchResponse;
import jp.co.brycen.wms.tmt050search.process.Tmt050SearchProcess;


/**
 * 画面：入荷予定登録
 * 機能：初期表示
 */
public class Spin00101InitProcess extends AbstractProcess {

	public Spin00101InitProcess(ILogSender logSender) {
		super(logSender);
	}


	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse)
			throws FatalException, DBException, ProcessCheckErrorException {

		// リクエストを作成
		Spin00101InitRequest reqSpin00101Init = (Spin00101InitRequest)request;
		Tmt050SearchRequest reqTmt050Search = new Tmt050SearchRequest();
		reqTmt050Search.accessInfo = reqSpin00101Init.accessInfo;
		reqTmt050Search.tmt050SearchCondition = reqSpin00101Init.tmt050SearchCondition;

		// ステータスを取得
		Tmt050SearchProcess procTmt050Search = new Tmt050SearchProcess(this);
		Tmt050SearchResponse resTmt050Search = (Tmt050SearchResponse)procTmt050Search.execute(reqTmt050Search);

		// レスポンス作成
		Spin00101InitResponse resSpin00101Init = (Spin00101InitResponse)response;
		resSpin00101Init.tmt050search = resTmt050Search.tmt050search;

		return response;
	}


	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request){
		return new Spin00101InitResponse();
	}
}
