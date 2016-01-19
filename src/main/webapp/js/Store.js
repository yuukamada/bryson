
// ログイン情報
function loginInfo(){
  this.usrcd = "";
  this.usrnm = "";
  this.cstmcd = "";
  this.cstmnm = "";
  this.brnchcd = "";
  this.brnchnm = "";
}

// グリッド情報
function gridInfoDto(){
	this.gridStr = "";
	this.dataCnt = 0;
}


//メッセージマスタ
function tmt060Msg(){
	this.MQ000001 = "";
}

// システム設定マスタ
function tmt030System(){
	this.SYSFLG1 = "";
	this.SYSFLG1 = ""
	this.SYSFLG2 = "";
	this.SYSFLG3 = "";
	this.SYSFLG4 = "";
	this.SYSFLG5 = "";
	this.SYSFLG6 = "";
	this.SYSFLG7 = "";
	this.SYSFLG8 = "";
	this.SYSFLG9 = "";
	this.SYSFLG10 = "";

	this.SYSNUMVAL1 = 200;
	this.SYSNUMVAL2 = 5;
	this.SYSNUMVAL3 = 0;
	this.SYSNUMVAL4 = 0;
	this.SYSNUMVAL5 = 0;
	this.SYSNUMVAL6 = 0;
	this.SYSNUMVAL7 = 0;
	this.SYSNUMVAL8 = 0;
	this.SYSNUMVAL9 = 0;
	this.SYSNUMVAL10 = 0;

	this.SIDIVKBN = "12";
	this.DFLTQLTYCD = "09";

	this.SYSKBN7 = "2";
}

// 権限区分
var authKbnList = [];
// メッセージ
var message = [];

var loginInfo = new loginInfo();
var tmt030System = new tmt030System();
var tmt060Msg = new tmt060Msg();

// TODO:ログイン画面でセットする
loginInfo.usrcd = "0001";
loginInfo.usrnm = "いじりゆうこ";
loginInfo.cstmcd = "00000001";
loginInfo.cstmnm = "いじり荷主";
loginInfo.brnchcd = "000001";
loginInfo.brnchnm = "葛西倉庫";

// TODO:ログイン画面でセットする
authKbnList ['SPIN00101'] = '2';
authKbnList ['SPIN00201'] = '2';
authKbnList ['SPIN00301'] = '2';

// TODO:後で消す
var MW000010 = "%1が%2件を超えています。よろしいですか？";
var ME000034 = "%1が%2件を超えています。検索条件を変更してください。";
var MQ000007 = "%1を削除します。よろしいですか？";

// ログインで取得
message['MI000001'] = "該当データが存在しません。";
message['MI000002'] = "確定しました。";
message['MI000003'] = "削除しました。";
message['MI000004'] = "正常に取り込みました。";
message['MI000005'] = "%1が印刷されました。";
message['MI000006'] = "%1が出力されました。";
message['MI000007'] = "アップロードが完了しました。";
message['MI000008'] = "登録完了しました。";
message['MI000009'] = "削除完了しました。";
message['MI000010'] = "登録完了しました。変更内容は次回ログインから反映されます。";
message['MI000011'] = "引当が完了しました。（明細件数：%1）";
message['MI000012'] = "引当取消が完了しました。（明細件数：%1）";
message['MI000013'] = "明細の追加が完了しました。";
message['MI000014'] = "明細の更新が完了しました。";
message['MI000015'] = "%1を開始しました。";
message['MI000016'] = "%1を取消しました。";
message['MI000017'] = "%1を終了しました。";
message['MQ000001'] = "ログオフします。よろしいですか？";
message['MQ000002'] = "システムを終了します。よろしいですか？";
message['MQ000003'] = "%1に戻ります。よろしいですか？";
message['MQ000004'] = "確定します。よろしいですか？";
message['MQ000005'] = "取消します。よろしいですか？";
message['MQ000006'] = "削除します(%1件)。よろしいですか？";
message['MQ000007'] = "%1を削除します。よろしいですか？";
message['MQ000008'] = "表示内容をクリアします。よろしいですか？";
message['MQ000009'] = "%1を印刷します。よろしいですか？(%2：%3件)";
message['MQ000010'] = "%1を出力します。よろしいですか？(%2：%3件)";
message['MQ000011'] = "%1は未入力です。よろしいですか？";
message['MQ000012'] = "データを読み込みます。よろしいですか？";
message['MQ000013'] = "コピーします。よろしいですか？";
message['MQ000014'] = "%1を開始します。よろしいですか?";
message['MQ000015'] = "%1を解除します。よろしいですか?";
message['MQ000016'] = "%1を確定します。よろしいですか?";
message['MQ000017'] = "%1を更新します。よろしいですか?";
message['MQ000018'] = "変更内容を破棄します。よろしいですか？";
message['MQ000019'] = "%1を取消します。よろしいですか?";
message['MQ000020'] = "%1を終了します。よろしいですか?";
message['MQ000021'] = "%1を出力します。よろしいですか？";
message['MQ000022'] = "変更内容を破棄して登録します。よろしいですか？";
message['MW000001'] = "%1は存在していません。よろしいですか？";
message['MW000002'] = "%1は、存在しません。%2に自動登録を行います。よろしいですか？";
message['MW000003'] = "%1は存在しています。よろしいですか？";
message['MW000004'] = "%1が選択されていません。よろしいですか？";
message['MW000005'] = "%1が入力されていません。よろしいですか？";
message['MW000006'] = "この%1は処理済です。よろしいですか？";
message['MW000007'] = "この%1は%2未満です。よろしいですか？";
message['MW000008'] = "%1が%2より多いです。よろしいですか？";
message['MW000009'] = "%1の%2と%3の%2が異なります。よろしいですか？";
message['MW000010'] = "%1が%2件を超えています。よろしいですか？";
message['ME000001'] = "既に起動されています。";
message['ME000002'] = "%1は起動できません。";
message['ME000003'] = "対象データは他処理で更新されました。";
message['ME000004'] = "%1を入力して下さい。";
message['ME000005'] = "%1を選択して下さい。";
message['ME000006'] = "%1は日付型で入力して下さい。";
message['ME000007'] = "%1は数値で入力して下さい。";
message['ME000008'] = "%1は%2桁以内で入力して下さい。";
message['ME000009'] = "%1は%2桁以上で入力して下さい。";
message['ME000010'] = "%1は%2以上で入力して下さい。";
message['ME000011'] = "%1の範囲指定が誤っています。";
message['ME000012'] = "%1をスキャンして下さい。";
message['ME000013'] = "%1は既に存在しています。";
message['ME000014'] = "%1は存在しません。";
message['ME000015'] = "%1が重複しています。";
message['ME000016'] = "%1は入力不可です。";
message['ME000017'] = "この%1は処理済です。";
message['ME000018'] = "パスワードの有効期限が切れています。新しいパスワードを登録してください。";
message['ME000019'] = "取込対象ファイルが存在しません。";
message['ME000020'] = "取込ファイル内で重複しています。";
message['ME000021'] = "取込ファイルの項目数が不正です。";
message['ME000022'] = "この%1は処理済です。";
message['ME000023'] = "この%1は取消済です。";
message['ME000024'] = "この%1は検品対象外です。";
message['ME000025'] = "この%1は出荷済みです。";
message['ME000026'] = "この%1は移動対象外です。";
message['ME000027'] = "この%1は返品済みです。";
message['ME000028'] = "この%1は入荷済みです。";
message['ME000029'] = "この%1はピッキング済みです。";
message['ME000030'] = "このロケーションは%1できません。";
message['ME000031'] = "%1以上です。";
message['ME000032'] = "この%1は%2未満です。";
message['ME000033'] = "%1を選択した場合は、集計単位に商品、拠点倉庫・商品を選択してください。";
message['ME000034'] = "%1が%2件を超えています。検索条件を変更してください。";
message['ME000035'] = "出力対象データを検索してください。";
message['ME000036'] = "%1と異なります。";
message['ME000037'] = "ログインが失敗しました。";
message['ME000038'] = "正しいパスワードを入力して下さい。";
message['ME000039'] = "%1と%2が異なります。";
message['ME000040'] = "%1と%2が同じです。";
message['ME000041'] = "検索結果が0件です。";
message['ME000042'] = "%1は半角英数で入力して下さい。";
message['ME000043'] = "%1不可の設定となっています。";
message['ME000044'] = "現在のステータスでは%1できません。";
message['ME000045'] = "入荷残数がマイナス値になります。入荷予定数を変更してください。";
message['ME000046'] = "更新権限がありません。";
message['ME000047'] = "このユーザーは既に削除されています。";
message['ME000048'] = "セット商品のため追加できません。";
message['ME000049'] = "整数%1桁以内、小数%2桁以内で入力して下さい。";
message['ME000050'] = "%1桁以内で入力して下さい。";
message['ME000051'] = "加工品、セット品として登録されている為チェックを外すことはできません。";
message['ME000052'] = "%1に0より少ない数を入力することはできません。";
message['ME000053'] = "現在の在庫ステータスでは%1できません。";
message['ME000054'] = "%1は削除済の%2と重複しています。";
message['ME000055'] = "%1を全て削除することはできません。";
message['ME000056'] = "%1以外の商品が在庫されている為、変更する事ができません。";
message['ME000057'] = "複数の商品が在庫されている為、変更する事ができません。";
message['ME000058'] = "%1でない温度帯の在庫商品が存在する為、変更する事ができません。";
message['ME000059'] = "%1に小数を入力することはできません。";
message['ME000060'] = "レコードの必須項目が未入力な為、行を追加する事ができません。";
message['ME000061'] = "%1が1行も存在しない為、登録することができません。";
message['ME000062'] = "%1が入荷済の数量以下になる為、変更する事ができません。";
message['ME000063'] = "%1は既に削除されました。再検索します。";
message['ME000064'] = "%1は商品マスタの温度帯と異なります。";
message['ME000065'] = "同じ商品のみ設定可能です。";
message['ME000066'] = "%1件以上追加できません。";
message['ME000067'] = "ログインユーザ（自分自身）を削除することはできません。別のユーザでログインして削除して下さい。";
message['MS000001'] = "システムエラーです。:%1";
message['MS000002'] = "%1の取得に失敗しました。";
message['MS000003'] = "%1の確定に失敗しました｡";
message['MS000004'] = "%1の削除に失敗しました。";
message['MS000005'] = "%1の印刷に失敗しました。";
message['MS000006'] = "%1の出力に失敗しました。";
message['MS000007'] = "%1の取込に失敗しました。";
message['MC000001'] = "システムで想定外のエラーが発生しました。\n大変お手数ですが下記へお問い合わせ下さい。\nTEL:XXXX-XXXX   MAIL:****@*****";

// 一覧の表示件数
var pageNum = 1;
