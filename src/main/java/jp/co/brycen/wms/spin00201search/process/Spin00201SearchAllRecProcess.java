package jp.co.brycen.wms.spin00201search.process;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Header;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.LogLevel;
import jp.co.brycen.common.ConstantValue;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.database.DBStatement;
import jp.co.brycen.common.dto.ErrorDto;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.utility.CommonUtility;
import jp.co.brycen.common.utility.FormatUtility;
import jp.co.brycen.common.utility.MessageUtility;
import jp.co.brycen.wms.spin00201.dto.Spin00201SearchRowDto;
import jp.co.brycen.wms.spin00201search.dto.Spin00201SearchRequest;
import jp.co.brycen.wms.spin00201search.dto.Spin00201SearchResponse;


/**
 * 画面：入荷予定照会
 * 概要：検索
 */
public class Spin00201SearchAllRecProcess extends AbstractProcess {

	public Spin00201SearchAllRecProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public void beforeProcess(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse) throws ProcessCheckErrorException {
		List<ErrorDto> msg = new ArrayList<ErrorDto>();

		Spin00201SearchRequest searchItemInfo = (Spin00201SearchRequest) request;
		String arvlplndateFrom = searchItemInfo.spin00201SearchCondition.ARVLPLNDATEFROM;
		String arvlplndateTo = searchItemInfo.spin00201SearchCondition.ARVLPLNDATETO;

		FormatUtility format = new FormatUtility();

		// 入荷予定日(To) < 入荷予定日(From)
		if (!"".equals(arvlplndateFrom) && arvlplndateFrom != null && !"".equals(arvlplndateTo) && arvlplndateTo != null) {
			// 日付型に変換
			Date  arvlplndateFromBuf = format.strToDate(arvlplndateFrom);
			Date  arvlplndateToBuf = format.strToDate(arvlplndateTo);

			if (arvlplndateToBuf.before(arvlplndateFromBuf) == true) {
				ErrorDto ret = new ErrorDto();
				ret.controlID = "date2";
				ret.errMsg = MessageUtility.getMessageMsg("ME000011", searchItemInfo.accessInfo.CSTMCD).replace("%1", "入荷予定日");
				msg.add(ret);
			}
		}

		// エラーがあったらエラーメッセージをセットしてthrow
		if (msg.size() > 0) {
			throw new ProcessCheckErrorException(msg, ConstantValue.FATAL_ERROR);
		}
	}

	//PDF出力
	public String createPdf() throws DocumentException, IOException{

		//文書オブジェクトを生成
		Document doc = new Document(PageSize.A4, 10, 10, 50, 0);

		//出力先(アウトプットストリーム)の生成
		FileOutputStream fos = new FileOutputStream("C:\\temp\\test.pdf");

		//フォントの設定
		Font font = new Font(BaseFont.createFont("HeiseiMin-W3", "UniJIS-UCS2-HW-H", BaseFont.NOT_EMBEDDED),11);

		//アウトプットストリームをPDFWriterに設定
		PdfWriter pdfwriter = PdfWriter.getInstance(doc, fos);

		//文章オブジェクト オープン
		doc.open();

		//ヘッダー
		Paragraph header = new Paragraph(new Paragraph("タイトル", font));
		header.setAlignment(Element.ALIGN_CENTER);

		//日付
		String nowDate = getNowDate();
		Paragraph date = new Paragraph(nowDate);
		date.setAlignment(Element.ALIGN_RIGHT);

		Paragraph space = new Paragraph(" ");

		//表の作成
		PdfPTable table = new PdfPTable(2);

		//表の要素(列タイトル)を作成
		PdfPCell cell1_1 = new PdfPCell(new Paragraph("商品名", font));
		cell1_1.setGrayFill(0.8f);		//セルを灰色に設定
		table.addCell(cell1_1);
		PdfPCell cell1_2 = new PdfPCell(new Paragraph("価格", font));
		cell1_2.setGrayFill(0.8f);		//セルを灰色に設定

		//表の要素を作成
		PdfPCell cell2_1 = new PdfPCell(new Paragraph("赤福もち", font));
		PdfPCell cell2_2 = new PdfPCell(new Paragraph("1,200円", font));

		//表の要素を表に追加する
		table.addCell(cell1_2);
		table.addCell(cell2_1);
		table.addCell(cell2_2);

		doc.add(header);
		doc.add(space);
		doc.add(date);
		doc.add(space);
		doc.add(table);

		//文章オブジェクト クローズ
		doc.close();

		//PDFWriter クローズ
		pdfwriter.close();

		return null;
	}

	//現在日時の取得
	public String getNowDate(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}


	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse)
			throws FatalException, DBException, ProcessCheckErrorException{

		ResultSet rs = null;
		DBStatement ps = null;

		try {
			createPdf();
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
//		} catch (DocumentException e1) {
//			// TODO 自動生成された catch ブロック
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO 自動生成された catch ブロック
//			e1.printStackTrace();
//		}

		try {
			// 検索条件
			Spin00201SearchRequest searchItemInfo = (Spin00201SearchRequest) request;
			String arvlplndateFrom = searchItemInfo.spin00201SearchCondition.ARVLPLNDATEFROM;
			String arvlplndateTo = searchItemInfo.spin00201SearchCondition.ARVLPLNDATETO;
			String sipnno = searchItemInfo.spin00201SearchCondition.SIPLNNO;
			String stscd = searchItemInfo.spin00201SearchCondition.STSCD;
			String divkbn = searchItemInfo.spin00201SearchCondition.DIVKBN;
			String itemcd = searchItemInfo.spin00201SearchCondition.ITEMCD;
			String spplycd = searchItemInfo.spin00201SearchCondition.SPPLYCD;
			Integer pageNum = searchItemInfo.pageInfo.pageNum;
			Integer dispNum = searchItemInfo.pageInfo.dispNum;
			String cstmcd = searchItemInfo.accessInfo.CSTMCD;
			String brnchcd = searchItemInfo.accessInfo.BRNCHCD;


			// SQL
			StringBuilder strSql = new StringBuilder();
			strSql.append("SELECT ");

			// Select句をセット
			strSql.append(this.setSelectFields());

			strSql.append("	FROM");
			strSql.append("		TIN020_PLANHED");
			strSql.append("	LEFT JOIN (SELECT");
			strSql.append("						 CSTMCD");
			strSql.append("						,BRNCHCD");
			strSql.append("						,SIPLNNO");
			strSql.append("						,CASE");
			strSql.append("							 WHEN ARVLCOMPFLG = '0' AND SICOMPFLG = '0' THEN '01'");
			strSql.append("							 WHEN ARVLCOMPFLG = '1' AND SICOMPFLG = '0' THEN '03'");
			strSql.append("							 WHEN ARVLCOMPFLG = '2' AND SICOMPFLG = '0' THEN '02'");
			strSql.append("							 WHEN SICOMPFLG = '1' THEN '05'");
			strSql.append("				 			WHEN SICOMPFLG = '2' THEN '04'");
			strSql.append("						END AS STSCD");
			strSql.append("					FROM");
			strSql.append("						TIN010_STS");
			strSql.append("					) STS");
			strSql.append("		ON TIN020_PLANHED.CSTMCD = STS.CSTMCD");
			strSql.append("		AND TIN020_PLANHED.BRNCHCD = STS.BRNCHCD");
			strSql.append("		AND TIN020_PLANHED.SIPLNNO = STS.SIPLNNO");
			strSql.append("	LEFT JOIN TMT050_NAME");
			strSql.append("		ON  TIN020_PLANHED.CSTMCD = TMT050_NAME.CSTMCD");
			strSql.append("		AND TMT050_NAME.RCDKBN = '0250'");
			strSql.append("		AND STS.STSCD = TMT050_NAME.DATACD");
			strSql.append("	LEFT JOIN TMT280_DIV");
			strSql.append("		ON TIN020_PLANHED.CSTMCD = TMT280_DIV.CSTMCD");
			strSql.append("		AND TIN020_PLANHED.DIVKBN = TMT280_DIV.DIVKBN");
			strSql.append("	LEFT JOIN TMT140_SPPLY");
			strSql.append("		ON TIN020_PLANHED.CSTMCD = TMT140_SPPLY.CSTMCD");
			strSql.append("		AND TIN020_PLANHED.SPPLYCD = TMT140_SPPLY.SPPLYCD");
			strSql.append("	WHERE TIN020_PLANHED.CSTMCD = ? ");
			strSql.append("	   AND TIN020_PLANHED.BRNCHCD = ? ");

			// 入荷予定(From)
			if (!("".equals(arvlplndateFrom)) && arvlplndateFrom != null) {
				strSql.append(" AND TIN020_PLANHED.ARVLPLNDATE >= ? ");
			}
			// 入荷予定(To)
			if (!("".equals(arvlplndateTo)) && arvlplndateTo != null) {
				strSql.append(" AND TIN020_PLANHED.ARVLPLNDATE <= ? ");
			}
			// 入荷伝票番号
			if (!("".equals(sipnno)) && sipnno != null) {
				strSql.append(" AND TIN020_PLANHED.SIPLNNO = ? ");
			}
			// ステータス
			if (!("".equals(stscd)) && stscd != null && !("99".equals(stscd))) {
				strSql.append(" AND STS.STSCD =  ?  ");
			}
			// 受払区分
			if (!("".equals(divkbn)) && divkbn != null) {
				strSql.append(" AND TIN020_PLANHED.DIVKBN =  ?");
			}
			// 商品コード
			if (!("".equals(itemcd)) && itemcd != null) {
				strSql.append(" AND EXISTS (");
				strSql.append("			SELECT");
				strSql.append("				*");
				strSql.append("			FROM");
				strSql.append("				TIN040_PLANDTL");
				strSql.append("			WHERE TIN040_PLANDTL.CSTMCD = TIN020_PLANHED.CSTMCD");
				strSql.append("				AND TIN040_PLANDTL.BRNCHCD = TIN020_PLANHED.BRNCHCD");
				strSql.append("				AND TIN040_PLANDTL.SIPLNNO = TIN020_PLANHED.SIPLNNO");
				strSql.append("				AND TIN040_PLANDTL.ITEMCD = ?");
				strSql.append("		) ");
			}
			// 仕入先コード
			if (!("".equals(spplycd)) && spplycd != null) {
				strSql.append(" AND TIN020_PLANHED.SPPLYCD =  ? ");
			}

			// キー情報で取得件数を絞る場合
			strSql.append(this.setKey());

			// 取得件数をセット(一覧に表示をする分を取得したい場合にセットする)
			strSql.append(this.setLimit(pageNum, dispNum));

			System.out.println(strSql);
			ps = dba.prepareStatement(strSql);

			// バインド変数セット
			int index = 3;

			ps.setString(1, cstmcd);
			ps.setString(2, brnchcd);

			// 入荷予定(From)
			if (!("".equals(arvlplndateFrom)) && arvlplndateFrom != null) {
				ps.setString(index, arvlplndateFrom);
				index++;
			}
			// 入荷予定(To)
			if (!("".equals(arvlplndateTo)) && arvlplndateTo != null) {
				ps.setString(index, arvlplndateTo);
				index++;
			}
			// 入荷伝票番号
			if (!("".equals(sipnno)) && sipnno != null) {
				ps.setString(index, sipnno);
				index++;
			}
			// ステータス
			if (!("".equals(stscd)) && stscd != null && !("99".equals(stscd))) {
				ps.setString(index, stscd);
				index++;
			}
			// 受払区分
			if (!("".equals(divkbn)) && divkbn != null) {
				ps.setString(index, divkbn);
				index++;
			}
			// 商品コード
			if (!("".equals(itemcd)) && itemcd != null) {
				ps.setString(index, itemcd);
				index++;
			}
			// 仕入先コード
			if (!("".equals(spplycd)) && spplycd != null) {
				ps.setString(index, spplycd);
				index++;
			}

			// SQL実行
			rs = ps.executeQuery();

			// 実行結果をレスポンスにつめる
			Spin00201SearchResponse resSpin00201Search = (Spin00201SearchResponse)response;
			setResponse(rs, resSpin00201Search);

			// 1件もレコードがなかったらエラーにする
			if (resSpin00201Search.rows != null && resSpin00201Search.rows.size() == 0) {
				resSpin00201Search.rows = null;
			}

			return resSpin00201Search;
		} catch (SQLException e) {
			logSend(LogLevel.ERROR, CommonUtility.getStackTraceString(e));
			throw new DBException(e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			} catch (SQLException e) {
				// なにかあってもムシする
				logSend(LogLevel.ERROR, CommonUtility.getStackTraceString(e));
			}
		}
	}

	@Override
	protected AbstractResponse createNewResponse(AbstractRequest request){
		return new Spin00201SearchResponse();
	}


	/**
	 * Select句をセット
	 * @return
	 */
	protected String setSelectFields()
	{
		StringBuilder strSql = new StringBuilder();
		strSql.append("	TIN020_PLANHED.ARVLPLNDATE");
		strSql.append("	,TIN020_PLANHED.SIPLNNO");
		strSql.append("	,TMT050_NAME.DATANM AS PLANSTSNM");
		strSql.append("	,TMT280_DIV.DIVNM");
		strSql.append("	,TIN020_PLANHED.SPPLYCD");
		strSql.append("	,TMT140_SPPLY.SPPLYNM");

		return strSql.toString();
	}

	/**
	 * レスポンスをセット
	 * @return
	 * @throws SQLException
	 */
	protected void setResponse(ResultSet rs, Spin00201SearchResponse resSpin00201Search) throws SQLException
	{
		ArrayList<Spin00201SearchRowDto> lst = new ArrayList<Spin00201SearchRowDto>();

		while (rs.next()) {
			Spin00201SearchRowDto row = new Spin00201SearchRowDto();
			row.ARVLPLNDATE = rs.getString("ARVLPLNDATE");
			row.SIPLNNO = rs.getString("SIPLNNO");
			row.PLANSTSNM = rs.getString("PLANSTSNM");
			row.DIVNM = rs.getString("DIVNM");
			row.SPPLYCD = rs.getString("SPPLYCD");
			row.SPPLYNM = rs.getString("SPPLYNM");

			lst.add(row);
		}
		resSpin00201Search.rows = lst;
	}

	/**
	 * 取得したいレコードのキー情報をセット
	 * @return
	 */
	protected String setKey()
	{
		return "";
	}

	/**
	 * 取得件数をセットする
	 * @return
	 */
	protected String setLimit(Integer pageNum, Integer dispNum)
	{
		return "";
	}
}
