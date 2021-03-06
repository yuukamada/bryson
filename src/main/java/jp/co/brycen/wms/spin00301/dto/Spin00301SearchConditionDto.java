package jp.co.brycen.wms.spin00301.dto;

import jp.co.brycen.common.dto.AbstractDto;

/**
 * 画面：入荷予定照会
 * 概要：検索条件
 */
public class Spin00301SearchConditionDto extends AbstractDto{

	// 入荷予定(From)
	public String ARVLPLNDATEFROM;

	// 入荷予定(To)
	public String ARVLPLNDATETO;

	// 入荷伝票番号
	public String SIPLNNO;

	// ステータス
	public String STSCD;

	// 受払区分
	public String DIVKBN;

	// 商品コード
	public String ITEMCD;

	// 仕入先コード
	public String SPPLYCD;

}
