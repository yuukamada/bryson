package jp.co.brycen.wms.spin00101delete.dto;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.spin00101.dto.Spin00101DeleteConditionDto;

/**
 * 画面：入荷予定登録
 * 概要：伝票削除
 */
public class Spin00101DeleteRequest extends AbstractRequest{

	// 表示しているデータのキー情報
	public Spin00101DeleteConditionDto spin00101DeleteCondition = new Spin00101DeleteConditionDto();

}
