package jp.co.brycen.wms.tmt280search.dto;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.tmt280.dto.Tmt280SearchConditionDto;

/**
 * 概要：[受払区分]テーブル
 */
public class Tmt280SearchRequest extends AbstractRequest{
	// 検索条件
	public Tmt280SearchConditionDto tmt280SearchCondition = new Tmt280SearchConditionDto();
}
