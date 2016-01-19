package jp.co.brycen.wms.spin00101search.dto;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.spin00101.dto.Spin00101SearchConditionDto;

/**
 * 画面：入荷予定登録
 * 概要：検索
 */
public class Spin00101SearchRequest extends AbstractRequest{

	// 検索条件
	public Spin00101SearchConditionDto spin00101SearchCondition = new Spin00101SearchConditionDto();

}
