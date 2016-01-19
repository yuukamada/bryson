package jp.co.brycen.wms.spin00201search.dto;

import jp.co.brycen.common.dto.PageInfoDto;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.spin00201.dto.Spin00201SearchConditionDto;

/**
 * 画面：入荷予定照会
 * 概要：検索
 */
public class Spin00201SearchRequest extends AbstractRequest{

	// 検索条件
	public Spin00201SearchConditionDto spin00201SearchCondition = new Spin00201SearchConditionDto();

	// ページ情報
	public PageInfoDto pageInfo = new PageInfoDto();

}
