package jp.co.brycen.wms.spin00201search.dto;

import java.util.ArrayList;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.wms.spin00201.dto.Spin00201SearchRowDto;

/**
 * 画面：入荷予定照会
 * 概要：検索
 */
public class Spin00201SearchResponse extends AbstractResponse {

	// 一覧データ
	public ArrayList<Spin00201SearchRowDto> rows;

	// レコード数
	public Long DATACNT;
}
