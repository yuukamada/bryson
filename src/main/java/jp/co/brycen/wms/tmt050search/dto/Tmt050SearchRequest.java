package jp.co.brycen.wms.tmt050search.dto;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.tmt050.dto.Tmt050SearchConditionDto;

/**
 * 概要：[名称マスタ]テーブル
 */
public class Tmt050SearchRequest extends AbstractRequest{

	// 検索条件
	public Tmt050SearchConditionDto tmt050SearchCondition = new Tmt050SearchConditionDto();
}
