/**
 * 최신도서 페이지를 호출할때 URL 설정하는 함수
 */
function bookListURL() {
		
		const year = new Date().getFullYear();
	  const month = new Date().getMonth()+1;
	  const week = getWeekOfMonth(new Date());
	  
	  const q = 'page=1&mnName=KOR&mdCode=&year='+year+'&month='+month+'&week='+week+'&sort=new&limit=9';
	 
	  // 새로운 URL 요청
	  location.href='/bookList.ok?'+q;
}