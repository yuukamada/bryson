package jp.co.brycen.wms.spin00101regist.dto;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.spin00101.dto.Spin00101ExecHeaderDto;

/**
 * 画面：入荷予定登録
 * 概要：登録
 */
public class Spin00101RegistRequest extends AbstractRequest{

	// ヘッダ
	public Spin00101ExecHeaderDto spin00101ExecHeader = new Spin00101ExecHeaderDto();

}
