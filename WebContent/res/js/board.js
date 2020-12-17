// 글 번호 클릭 시  해당 url로 이동
function clickArticle(i_board){
		console.log('aa');
		var url = `bDetail?i_board=${i_board}`;
		location.href = url; 
}

function test() {
	console.log('aa');
}

// 제목 혹은 내용이 빈 공란일 경우 알람 표시 - 현재 사용X 비속어확인용도로 사용
function chk(){
	var frm = document.querySelector('#frm');
	if(chkEmptyEle(frm.title, '제목') || chkEmptyEle(frm.ctnt, '내용')){
		return false;
	}
}