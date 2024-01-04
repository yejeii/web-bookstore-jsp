/**
 * main 페이지에서 실행되는 스크립트
 */
$(document).ready(function() {
	
	// 최신 도서 nav 클릭 이벤트
	$('#bookList_nav').on('click', function(e) {
		e.preventDefault();
		bookListURL();
	});
});