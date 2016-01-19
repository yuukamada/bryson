package jp.co.brycen.wms.spin00101check.process;

import java.util.ArrayList;
import java.util.List;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.dto.ErrorDto;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.utility.MessageUtility;
import jp.co.brycen.common.utility.ValidateUtility;
import jp.co.brycen.wms.spin00101check.dto.Spin00101CheckRequest;
import jp.co.brycen.wms.spin00101check.dto.Spin00101CheckResponse;

/**
 * 画面：入荷予定登録
 * 概要：入力チェック
 */
public class Spin00101CheckProcess extends AbstractProcess {

	public Spin00101CheckProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse)
			throws FatalException, DBException, ProcessCheckErrorException {

		List<ErrorDto> msg = new ArrayList<ErrorDto>();

		// レスポンス
		Spin00101CheckResponse resSpin00101Check = (Spin00101CheckResponse)response;

		// リクエスト
		Spin00101CheckRequest reqSpin00101Check = (Spin00101CheckRequest)request;
		String siremark = reqSpin00101Check.spin00101ExecHeader.SIREMARK;
		String cstmcd = reqSpin00101Check.accessInfo.CSTMCD;

		// 備考
		if(ValidateUtility.GetBytes(siremark) > 200){
			ErrorDto ret = new ErrorDto();
			ret.controlID = "siremark";
			ret.errMsg = MessageUtility.getMessageMsg("ME000050", cstmcd).replace("%1", "200");
			msg.add(ret);
		}

		resSpin00101Check.addFatalErrorList(msg);

		return resSpin00101Check;
	}


	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request) {
		return new Spin00101CheckResponse();
	}
}
