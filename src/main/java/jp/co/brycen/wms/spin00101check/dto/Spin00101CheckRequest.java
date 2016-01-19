package jp.co.brycen.wms.spin00101check.dto;

import java.util.ArrayList;

import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.wms.spin00101.dto.Spin00101ExecHeaderDto;

/**
 * 画面：入荷予定登録
 * 概要：入力チェック
 */
public class Spin00101CheckRequest extends AbstractRequest{

	// ヘッダ
	public Spin00101ExecHeaderDto spin00101ExecHeader = new Spin00101ExecHeaderDto();

	// 商品コード配列
	public ArrayList<String> itemCdList;
}
