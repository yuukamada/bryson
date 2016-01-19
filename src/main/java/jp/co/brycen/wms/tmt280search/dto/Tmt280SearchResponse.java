package jp.co.brycen.wms.tmt280search.dto;

import java.util.ArrayList;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.wms.tmt280.dto.Tmt280SearchRowDto;

/**
 * 概要：[受払区分]テーブル
 */
public class Tmt280SearchResponse extends AbstractResponse {
	public ArrayList<Tmt280SearchRowDto> rows;
}
