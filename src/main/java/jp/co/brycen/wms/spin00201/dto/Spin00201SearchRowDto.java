/*
 * InstanceType.java
 * it needs to be modified.
 */
package jp.co.brycen.wms.spin00201.dto;

import jp.co.brycen.common.dto.AbstractDto;

/**
 * 画面：入荷予定照会
 * 概要：検索結果
 */
public class Spin00201SearchRowDto extends AbstractDto{

	// 入荷予定日
	public String ARVLPLNDATE;

	// 入荷伝票番号
	public String SIPLNNO;

	// ステータス
	public String PLANSTSNM;

	// 受払区分
	public String DIVNM;

	// 仕入先コード
	public String SPPLYCD;

	// 仕入先名
	public String SPPLYNM;

}
