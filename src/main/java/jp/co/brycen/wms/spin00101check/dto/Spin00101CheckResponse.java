package jp.co.brycen.wms.spin00101check.dto;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.wms.spin00101.dto.Spin00101ExecHeaderDto;

/**
 * 画面：入荷予定登録
 * 概要：入力チェック
 */
public class Spin00101CheckResponse extends AbstractResponse {

	public Spin00101ExecHeaderDto spin00101ExecHeader = new Spin00101ExecHeaderDto();
}
