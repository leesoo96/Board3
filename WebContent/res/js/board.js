// 글 번호 클릭 시  해당 url로 이동
function clickArticle(i_board){
		var url = `bDetail?i_board=${i_board}`;
		location.href = url; 
}

function clickDel(i_board, typ){
	if(confirm('정말 삭제하시겠습니까?')){
		location.href = `bDel?i_board=${i_board}&typ=${typ}`;
	}
}

// 댓글 수정버튼 클릭 
function cmtMod(i_cmt){
	var modFrm = document.querySelector('#mod_'+i_cmt);
	modFrm.classList.remove('cmt_mod_form');	
}
function cmtModClose(i_cmt){
	var modFrm = document.querySelector('#mod_'+i_cmt);
	modFrm.classList.add('cmt_mod_form');
}

// 제목 혹은 내용이 빈 공란일 경우 알람 표시 - 현재 사용X 비속어확인용도로 사용
function chk(){
	var frm = document.querySelector('#frm');
	if(chkEmptyEle(frm.title, '제목') || chkEmptyEle(frm.ctnt, '내용')){
		return false;
	}
}