package jp.co.brycen.wms.spin00101.dto;

import jp.co.brycen.common.dto.AbstractDto;

/**
 * 概要：検索結果(ヘッダ情報)
 */
public class Spin00101SearchHeaderDto extends AbstractDto{

		// タレント番号
		public int ID;

		// 氏名
		public String NAME;

		// 年齢
		public int AGE;

		// 住所
		public String ADDRESS;

		// 経験年数
		public int EXPERIENCE;

		// コミュニケーション能力
		public int COMMUNICATION;

		// コーディング能力
		public int CODING;

		// 設計能力
		public int DESIGN;

		// テスト能力
		public int TEST;

		// 体力
		public int PHYSICAL;
}
