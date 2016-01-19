package jp.co.brycen.common.dto.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.brycen.common.dto.ErrorDto;

@XmlRootElement(name="abs")
public class AbstractResponse {


	// エラーDTO配列
	protected List<ErrorDto> lstNormalError = new ArrayList<ErrorDto>();
	protected List<ErrorDto> lstFatalError = new ArrayList<ErrorDto>();


	/**
	 * 通常エラーを取得する
	 * @return
	 */
	public List<ErrorDto> getNormalError(){
		return lstNormalError;
	}


	/**
	 * 通常エラーを設定する
	 * @param inputCheckResult		エラーDTO配列
	 */
	public void setNormalError(List<ErrorDto> lstNormalError){
		this.lstNormalError = lstFatalError;
	}


	/**
	 * 通常エラーを追加する
	 * @param error						エラー
	 */
	public void addNormalError(ErrorDto error){
		this.lstNormalError.add(error);
	}


	/**
	 * 通常エラーを追加する
	 * @param lstFatalError			エラーリスト
	 */
	public void addNormalErrorList(List<ErrorDto> lstNormalError){
		this.lstNormalError.addAll(lstNormalError);
	}



	/**
	 * 致命的なエラーを取得する
	 * @return
	 */
	public List<ErrorDto> getFatalError(){
		return lstFatalError;
	}


	/**
	 * 致命的なエラーを設定する
	 * @param inputCheckResult		エラーDTO配列
	 */
	public void setFatalError(List<ErrorDto> lstFatalError){
		this.lstFatalError = lstFatalError;
	}


	/**
	 * 致命的なエラーを追加する
	 * @param error						エラー
	 */
	public void addFatalError(ErrorDto error){
		this.lstFatalError.add(error);
	}


	/**
	 * 致命的なエラーを追加する
	 * @param lstFatalError			エラーリスト
	 */
	public void addFatalErrorList(List<ErrorDto> lstFatalError){
		this.lstFatalError.addAll(lstFatalError);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(">" + this.getClass().getSimpleName());
		return sb.toString();
	}
}
