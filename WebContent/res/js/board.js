// 글 번호 클릭 시  해당 url로 이동
function clickArticle(typ, i_board){
		var url = `/bDetail?typ=${typ}&i_board=${i_board}`;
		location.href = url; 
}

// 제목 혹은 내용이 빈 공란일 경우 알람 표시
function chk(){
		if(chkEmptyEle(frm.title, '제목') || chkEmptyEle(frm.ctnt, '내용')){
			return false;
		}
	}