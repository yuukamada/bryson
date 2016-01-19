package jp.co.brycen.wms.spin00101.dto;

import jp.co.brycen.common.dto.AbstractDto;

/**
 * 概要：検索結果(ヘッダ情報)
 */
public class Spin00101SearchHeaderDto extends AbstractDto{

	// 入荷伝票番号
	public String SIPLNNO;

	// ステータス名
	public String PLANSTSNM;

	// ステータスコード
	public String STSCD;

	// 入荷予定日
	public String ARVLPLNDATE;

	// 受払区分コード
	public String DIVKBN;

	// 受払区分
	public String DIVNM;

	// 仕入先コード
	public String SPPLYCD;

	// 仕入先名
	public String SPPLYNM;

	// 伝票備考
	public String SIREMARK;

	// 更新日時
	public String UPDDATETIME;
}
