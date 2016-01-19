package jp.co.brycen.wms.spin00101init.dto;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.tmt050.dto.Tmt050SearchConditionDto;

/**
 * 画面：入荷予定登録
 * 機能：初期表示
 */
public class Spin00101InitRequest extends AbstractRequest{

	// ステータスコード取得の条件
	public Tmt050SearchConditionDto tmt050SearchCondition = new Tmt050SearchConditionDto();
}
