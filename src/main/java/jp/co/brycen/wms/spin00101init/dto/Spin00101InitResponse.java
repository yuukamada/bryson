package jp.co.brycen.wms.spin00101init.dto;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.wms.tmt050.dto.Tmt050searchDto;

/**
 * 画面：入荷予定登録
 * 機能：初期表示
 */
public class Spin00101InitResponse extends AbstractResponse {

	// ステータス
	public Tmt050searchDto tmt050search = new Tmt050searchDto();
}
