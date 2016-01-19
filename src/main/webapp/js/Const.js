// Define a variable for all controllers.

// Webサービス情報
var service = 'webapi/wms/';

var port = '8080';
var server = 'localhost';
var projectName = 'wms';

// 画面ID
var SPIN00301 = 'SPIN00301';
var SPIN00201 = 'SPIN00201';
var SPIN00101 = 'SPIN00101';

// 機能ID
var FPIN0020 = 'FPIN0020';

// Webサービス名
var sequenceWs = 'Sequence';
var authKbnWs = "AuthKbn";

var tmt120SearchWs = 'Tmt120Search';
var tmt050QltySearchWs = 'Tmt050QltySearch';
var tmt280SearchWs = 'Tmt280Search';
var tmt050LctnSearchWs = 'Tmt050LctnSearch';
var tmt050GrpkbnSearchWs = 'Tmt050GrpkbnSearch';
var tmt050SortSearchWs = 'Tmt050SortSearch';

var tmt050rsrvtypekbnSearchWs = 'Tmt050RsrvtypekbnSearch';
var tmt050lctnusekbnSearchWs = 'Tmt050LctnusekbnSearch';
var tmt050tempSearchWs = 'Tmt050TempSearch';
var tmt020SearchWs = 'Tmt020Search';

var spcm00901searchWs = 'spcm00901search';
var spin00101InitWs = 'Spin00101Init';
var spin00101SearchWs = 'Spin00101Search';
var spin00101RegistWs = 'Spin00101Regist';
var spin00101UpdateWs = 'Spin00101Update';
var spin00101DeleteWs = 'Spin00101Delete';
var spin00101CopyWs = 'Spin00101Copy';
var spin00101CheckDetailWs = 'Spin00101CheckDetail';
var spin00101CheckWs = 'Spin00101Check';

var spin00201InitWs = 'Spin00201Init';
var spin00201SearchWs = 'Spin00201Search';
var spin00201SearchCntWs = 'Spin00201SearchCnt';
var spin00201CsvCreateWs = 'Spin00201CsvCreate';
var spin00201CsvDownloadWs = 'Spin00201CsvDownload';

var spin00301InitWs = 'Spin00301Init';
var spin00301SearchWs = 'Spin00301Search';
var spin00301SearchCntWs = 'Spin00301SearchCnt';
var spin00301CsvCreateWs = 'Spin00301CsvCreate';
var spin00301CsvDownloadWs = 'Spin00301CsvDownload';

//Begin: Declare service name for common Modal
var spcm01101InitWs = 'Spcm01101Init';
var spcm01101SearchWs = 'Spcm01101Search';
var spcm01101SearchCntWs = 'Spcm01101SearchCnt';
var spcm01301InitWs = 'Spcm01301Init';
var spcm01301SearchWs = 'Spcm01301Search';
var spcm01301SearchCntWs = 'Spcm01301SearchCnt';
var spcm50101InitWs = 'Spcm50101Init';
var spcm50101SearchWs = 'Spcm50101Search';
var spcm50101SearchCntWs = 'Spcm50101SearchCnt';
var spcm50201InitWs = 'Spcm50201Init';
var spcm50201SearchWs = 'Spcm50201Search';
var spcm50201SearchCntWs = 'Spcm50201SearchCnt';
var spcm00601InitWs = 'Spcm00601Init';
var spcm00601SearchWs = 'Spcm00601Search';
var spcm00601SearchCntWs = 'Spcm00601SearchCnt';
var tmt050ItemGrpSearchWs = 'Tmt050ItemGrpSearch';
//End: Declare service name for common Modal

var csvWs = 'csv';

// DatePickerの初期値(0:初期時にブランク 1:初期時に本日日付)
var datePickerInitTypeBlank = '0';
var datePickerInitTypeValue = '1';

// 一覧のデータ型
var dataTypeString = "string";
var dataTypeNumber = "number";
var dataTypeCheckbox = "checkbox";
var dataTypeLink = "link";
var dataTypeDate = "date";
var dataTypeButton = "button";
var dataTypeAutoNumber = "autoNumber";

// 更新区分
var updKbnMerge = '1';
var updKbnDel = '2';

// 明細一覧追加フラグ
var modeIns = 'INS';
var modeUpd = 'UPD';
var modeDel = 'DEL';

// 変更フラグ
var changeFlgOn = 'ON';
var changeFlgOff = 'OFF';

// ポップアップタイトル
var infoTitle = "情報";
var warningTitle = "警告";
var errorTitle = "エラー";
var confirmTitle = "確認";

// ステータスコード
var stsCd01 = "01";			// 未入荷
var stsCd02 = "02";			// 入荷済
var stsCd03 = "03";			// 一部入荷
var stsCd04 = "04";			// 一部入庫
var stsCd05 = "05";			// 入庫済

// 初期化タイプ
var setTypeScreen = "01";
var setTypeStock = "02";

var wmsController = angular.module('wmsController', []);

// web service path
var managementID = 5;

// services
var insertWS = 'insert';
var updateWS = 'update';
var deleteWS = 'delete';
var searchWS = 'search';
var getWS = 'get';
var checkrecordexist = 'checkrecordexist';

var instanceTypeInsertWS = 'instancetypeinsert';
var instanceTypeSearchWS = 'instancetypesearch';
var instanceGroupSearchWS = 'instancegroupsearch';
var instanceGroupInsertWS = 'instancegroupinsert';
var instanceGroupUpdateWS = 'instancegroupupdate';

var getDetailViewResource = 'detailview';
var deleteDetailViewResource = 'deletedetail';
var downloadDetailViewResource = 'download';

var instanceTypeInsertWS = 'instancetypeinsert';
var instanceTypeSearchWS = 'instancetypesearch';

var updateDetailViewResource = 'updatedetailview';
var insertDetailViewResource = 'insertdetailview';

var getGroupDetailResource = 'groupdetail';
var deleteGroupDetailResource = 'deletegroupDetail';

var getDeviceInstanceResource="devicedetail"
var updateDeviceInstanceResource="deviceupdate"
var deleteDeviceInstanceResource="deletedevicedetail"

var instanceDeviceSearchWS = 'instancedevicesearch';

var repScheduleSearchWS = 'repschedulesearch';
var getReplicationScheduleResource="scheduleget";
var updateReplicationScheduleResource="scheduleupdate";
var insertReplicationScheduleResource="scheduleinsert";
var replicationManagerSearch="replicationsearch"
var	replicationManagerHistory = 'replicationhistory';
var	replicationManagerStop = 'replicationstop';
var	replicationManagerStart = 'replicationstart';
var	replicationManagerCreate = 'replicationcreate';
var	replicationManagerPause = 'replicationpause';
var	replicationManagerResume = 'replicationpauserresume';
var deleteReplicationScheduleResource='scheduledelete';

var scheduleSettingsGetResource="schedulesettingsget";
var scheduleSettingsGroupSearchResource="schedulesettingsgroupsearch";
var scheduleSettingsDeviceSearchResource="schedulesettingsdevicesearch";

var userManagementSearchResource="usermanagementsearch";
var userManagementGetResource="usermanagementdetail";
var userManagementDeleteResource="usermanagementdelete";
var userManagementUpdateResource="usermanagementupdate";
var userManagementInsertResource="usermanagementinsert";

var Login="Login";

var rowNum = 10;
var rowList = [10, 20, 50];
