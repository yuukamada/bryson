package jp.co.brycen.wms.spin00101search.dto;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.wms.spin00101.dto.Spin00101SearchHeaderDto;

/**
 * 画面：入荷予定登録
 * 概要：検索
 */
public class Spin00101SearchResponse extends AbstractResponse {

	// ヘッダ
	public Spin00101SearchHeaderDto spin00101Search = new Spin00101SearchHeaderDto();

}
