package jp.co.brycen.wms.spin00301search.process;

import jp.co.brycen.common.ILogSender;

/**
 * 画面：入荷予定照会
 * 概要：検索(取得件数を制限)
 */
public class Spin00301SearchProcess extends Spin00301SearchAllRecProcess {

	public Spin00301SearchProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	protected String setLimit(Integer pageNum, Integer dispNum)
	{
		Integer startNum = (pageNum -1) * dispNum;
		return " LIMIT " + dispNum + " OFFSET " + startNum + " ";
	}
}
