package jp.co.brycen.wms.spin00301search.dto;

import java.util.ArrayList;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.wms.spin00301.dto.Spin00301SearchRowDto;

/**
 * 画面：入荷予定照会
 * 概要：検索
 */
public class Spin00301SearchResponse extends AbstractResponse {

	// 一覧データ
	public ArrayList<Spin00301SearchRowDto> rows;

	// レコード数
	public Long DATACNT;
}
