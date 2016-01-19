package jp.co.brycen.common.dto;

/**
 * 概要：CSV出力情報
 */
public class CsvInfoDto extends AbstractDto{

	// ファイル名
	public String fileName;

	// 出力文字列
	public String outputStr;

	// セパレータ(0:カンマ 1:タブ)
	public Integer separatorType = 0;

	// 囲み文字(0:なし 1:ダブルコート)
	public Integer kakomiType = 1;
}
