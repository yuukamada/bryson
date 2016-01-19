

/**
 * 半角数字チェック
 * @param str
 * @returns {Boolean}
 */
function fnIsNumber(str){
	if(str == "" || str == undefined){
		return true;
	}

	if(str.match(/[^0-9]+/)){
		return false;
	}
	return true;
}


/**
 * 日付型チェック
 * @param str
 * @returns {Boolean}
 */
function fnIsDate(str){
	if(str == "" || str == undefined){
		return true;
	}

	if ( !str.match(/^\d{4}\/[\d]+\/[\d]+$/) ) {
		return false;
	} else {
		var parts = str.split( "/" );
		var year = Number(parts[0]);
		var month = Number(parts[1]) - 1;
		var day = Number(parts[2]);

		dt=new Date(year ,month, day);
	    return(dt.getFullYear()==year && dt.getMonth()==month && dt.getDate()==day);
	}
}