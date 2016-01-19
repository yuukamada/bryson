package jp.co.brycen.wms.spin00101.dto;

import jp.co.brycen.common.dto.AbstractDto;

/**
 * 概要：入荷予定登録内容
 */
public class Spin00101ExecHeaderDto extends AbstractDto{

	// 入荷伝票番号
	public String SIPLNNO;

	// 入荷予定日
	public String ARVLPLNDATE;

	// 受払区分コード
	public String DIVKBN;

	// 仕入先コード
	public String SPPLYCD;

	// 伝票備考
	public String SIREMARK;

	// 更新日時
	public String UPDDATETIME;

}
