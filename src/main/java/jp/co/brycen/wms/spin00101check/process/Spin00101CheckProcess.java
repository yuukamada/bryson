package jp.co.brycen.wms.spin00101check.process;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.dto.ErrorDto;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.exception.ProcessCheckErrorException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.utility.MessageUtility;
import jp.co.brycen.common.utility.ValidateUtility;
import jp.co.brycen.wms.spin00101check.dto.Spin00101CheckRequest;
import jp.co.brycen.wms.spin00101check.dto.Spin00101CheckResponse;
import jp.co.brycen.wms.spin00301.dto.Spin00301SearchRowDto;
import jp.co.brycen.wms.spin00301search.dto.Spin00301SearchResponse;

/**
 * 画面：入荷予定登録
 * 概要：入力チェック
 */
public class Spin00101CheckProcess extends AbstractProcess {

	public Spin00101CheckProcess(ILogSender logSender) {
		super(logSender);
	}

	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest request, AbstractResponse response, AbstractResponse parentResponse)
			throws FatalException, DBException, ProcessCheckErrorException {

		List<ErrorDto> msg = new ArrayList<ErrorDto>();

		// レスポンス
		Spin00101CheckResponse resSpin00101Check = (Spin00101CheckResponse)response;

		// リクエスト
		Spin00101CheckRequest reqSpin00101Check = (Spin00101CheckRequest)request;

		String cstmcd = reqSpin00101Check.accessInfo.CSTMCD;

//		// 備考
//		if(ValidateUtility.GetBytes(siremark) > 200){
//			ErrorDto ret = new ErrorDto();
//			ret.controlID = "siremark";
//			ret.errMsg = MessageUtility.getMessageMsg("ME000050", cstmcd).replace("%1", "200");
//			msg.add(ret);
//		}

		try {
			createPdf((Spin00101CheckRequest)request);
		} catch (DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		resSpin00101Check.addFatalErrorList(msg);

		return resSpin00101Check;
	}

	//PDF出力
		public String createPdf(Spin00101CheckRequest request) throws DocumentException, IOException{
			// リクエスト
			Spin00101CheckRequest reqSpin00101Check = (Spin00101CheckRequest)request;

			String id = String.valueOf(reqSpin00101Check.spin00101ExecHeader.ID);
			String name = reqSpin00101Check.spin00101ExecHeader.NAME;
			String age = String.valueOf(reqSpin00101Check.spin00101ExecHeader.AGE);
			String address = reqSpin00101Check.spin00101ExecHeader.ADDRESS;
			String experience = String.valueOf(reqSpin00101Check.spin00101ExecHeader.EXPERIENCE);
			String communication = String.valueOf(reqSpin00101Check.spin00101ExecHeader.COMMUNICATION);
			String coding = String.valueOf(reqSpin00101Check.spin00101ExecHeader.CODING);
			String design = String.valueOf(reqSpin00101Check.spin00101ExecHeader.DESIGN);
			String test = String.valueOf(reqSpin00101Check.spin00101ExecHeader.TEST);
			String physical = String.valueOf(reqSpin00101Check.spin00101ExecHeader.PHYSICAL);

			//文書オブジェクトを生成
			Document doc = new Document(PageSize.A4, 10, 10, 50, 0);

			//出力先(アウトプットストリーム)の生成
			FileOutputStream fos = new FileOutputStream("C:\\temp\\test.pdf");

			//フォントの設定
			Font font = new Font(BaseFont.createFont("HeiseiMin-W3", "UniJIS-UCS2-HW-H", BaseFont.NOT_EMBEDDED),11);
			Font fontTitle = new Font(BaseFont.createFont("HeiseiMin-W3", "UniJIS-UCS2-HW-H", BaseFont.NOT_EMBEDDED),20);

			//アウトプットストリームをPDFWriterに設定
			PdfWriter pdfwriter = PdfWriter.getInstance(doc, fos);

			//文章オブジェクト オープン
			doc.open();

			//ヘッダー
			Paragraph header = new Paragraph(new Paragraph("スキルシート", fontTitle));
			header.setAlignment(Element.ALIGN_CENTER);

			//日付
			String nowDate = getNowDate();
			Paragraph date = new Paragraph(nowDate);
			date.setAlignment(Element.ALIGN_RIGHT);

			Paragraph space = new Paragraph(" ");


			//表の作成
			PdfPTable table = new PdfPTable(2);

			//表の要素を作成
			PdfPCell cell1_1 = new PdfPCell(new Paragraph("タレント番号", font));
			cell1_1.setGrayFill(0.8f);		//セルを灰色に設定
			table.addCell(cell1_1);
			PdfPCell cell1_2 = new PdfPCell(new Paragraph(id, font));
			table.addCell(cell1_2);

			PdfPCell cell2_1 = new PdfPCell(new Paragraph("名前", font));
			cell2_1.setGrayFill(0.8f);		//セルを灰色に設定
			table.addCell(cell2_1);
			PdfPCell cell2_2 = new PdfPCell(new Paragraph(name, font));
			table.addCell(cell2_2);

			PdfPCell cell3_1 = new PdfPCell(new Paragraph("年齢", font));
			cell3_1.setGrayFill(0.8f);		//セルを灰色に設定
			table.addCell(cell3_1);
			PdfPCell cell3_2 = new PdfPCell(new Paragraph(age, font));
			table.addCell(cell3_2);

			PdfPCell cell4_1 = new PdfPCell(new Paragraph("住所", font));
			cell4_1.setGrayFill(0.8f);		//セルを灰色に設定
			table.addCell(cell4_1);
			PdfPCell cell4_2 = new PdfPCell(new Paragraph(address, font));
			table.addCell(cell4_2);

			PdfPCell cell5_1 = new PdfPCell(new Paragraph("経験", font));
			cell5_1.setGrayFill(0.8f);		//セルを灰色に設定
			table.addCell(cell5_1);
			PdfPCell cell5_2 = new PdfPCell(new Paragraph(experience, font));
			table.addCell(cell5_2);

			PdfPCell cell6_1 = new PdfPCell(new Paragraph("コミュニケーション能力", font));
			cell6_1.setGrayFill(0.8f);		//セルを灰色に設定
			table.addCell(cell6_1);
			PdfPCell cell6_2 = new PdfPCell(new Paragraph(communication, font));
			table.addCell(cell6_2);

			PdfPCell cell7_1 = new PdfPCell(new Paragraph("コーディング能力", font));
			cell7_1.setGrayFill(0.8f);		//セルを灰色に設定
			table.addCell(cell7_1);
			PdfPCell cell7_2 = new PdfPCell(new Paragraph(coding, font));
			table.addCell(cell7_2);

			PdfPCell cell8_1 = new PdfPCell(new Paragraph("設計能力", font));
			cell8_1.setGrayFill(0.8f);		//セルを灰色に設定
			table.addCell(cell8_1);
			PdfPCell cell8_2 = new PdfPCell(new Paragraph(design, font));
			table.addCell(cell8_2);

			PdfPCell cell9_1 = new PdfPCell(new Paragraph("テスト能力", font));
			cell9_1.setGrayFill(0.8f);		//セルを灰色に設定
			table.addCell(cell9_1);
			PdfPCell cell9_2 = new PdfPCell(new Paragraph(test, font));
			table.addCell(cell9_2);

			PdfPCell cell10_1 = new PdfPCell(new Paragraph("体力", font));
			cell10_1.setGrayFill(0.8f);		//セルを灰色に設定
			table.addCell(cell10_1);
			PdfPCell cell10_2 = new PdfPCell(new Paragraph(physical, font));
			table.addCell(cell10_2);


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
	protected AbstractResponse createNewResponse(AbstractRequest request) {
		return new Spin00101CheckResponse();
	}
}
