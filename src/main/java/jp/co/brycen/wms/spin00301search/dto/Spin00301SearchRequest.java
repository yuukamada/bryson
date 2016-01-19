package jp.co.brycen.wms.spin00301search.dto;

import jp.co.brycen.common.dto.PageInfoDto;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.spin00301.dto.Spin00301SearchConditionDto;

/**
 * 画面：入荷予定照会
 * 概要：検索
 */
public class Spin00301SearchRequest extends AbstractRequest{

	// 検索条件
	public Spin00301SearchConditionDto spin00301SearchCondition = new Spin00301SearchConditionDto();

	// ページ情報
	public PageInfoDto pageInfo = new PageInfoDto();

}
