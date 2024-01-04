/**
 * bookList 페이지에서 사용되는 스크립트
 */
$(document).ready(function() {

  const floatPosition = parseInt($(".sideBanner").css('top'));	// 기본 위치(top)값
  const params = getQueryString();
	let mnName = params.mnName;
  let mnCode = new Number();	// mnCode를 저장할 Number 객체
  let mdCode = new Number();
  let page = params.page;
  let limit = params.limit;
  let sort = params.sort;
  let year = params.year;
  let month = params.month;
  let week = params.week;
   
  switch(params.mnName) {
  	case 'KOR':
  		mnCode = 100000;
  		break;
  	default: 
  		mnCode = 200000;
  }
  
  console.log('mnCode : '+mnCode);
  
  // nav, a 태그로 대/중분류 생성
	banner_main_catgy($(".main-categories"), $.main_catgy_arr);
	a_middle_catgy($('.filter-bar').first(), mnCode, $.middle_catgy_arr);
	
  // scroll 인식
  $(window).scroll(function() {
    
      // 현재 스크롤 위치
      const currentTop = $(window).scrollTop();
      if (currentTop > 0) {
    	  const bannerTop = (200 + currentTop) + "px";
      
	      //이동 애니메이션
	      $(".sideBanner").stop().animate({
	        "top" : bannerTop
	      }, 500);
      }

  }).scroll(); 
  
  /* 소분류 <a> 태그 클릭 */
  $('.filter-bar').first().find('a').on('click', function(e) {
	  e.preventDefault();
	  
/*	외부 JS 파일이라 EL 표현식 인식 불가. 우회
	  let mnName = '${mnName}';
	  let mdCode = $(this).attr('id').substring(3);
	  let page = '${pageInfo.page}';
	  let limit = '${limit}';
	  let sort = '${filter.sort}';
	  let year = '${filter.year}';
	  let month = '${filter.month}';
	  let week = '${filter.week}';*/
	  
	  mdCode = $(this).attr('id').substring(3);
	  
	  let q = 'page='+page+'&mnName='+mnName+'&mdCode='+mdCode+'&year='+year+'&month='+month+'&week='+week+'&sort='+sort+'&limit='+limit;
	  console.log(q);
	  
	  // 서버로 전송
	  location.href='/bookList.ok?'+q;
	  
	 });
  
  /* 필터링 후 검색 버튼 클릭 */
  $('#filter_search').on('click', function(e) {
	  e.preventDefault();
		//let mnName = '${mnName}';
		//let mdCode = '${mdCode}';
		//console.log(typeof mnName);
	  	
	  // q 형식 : page=1&mnName=KOR&mdCode=103700&year=2023&month=06&week=01&sort=new&limit=9
	  let q = 'page=1&mnName='+mnName+'&mdCode='+mdCode;
	  
		// 선택한 선택 상자와 다른 선택 상자의 선택된 값 가져오기
	  $('.filter-select .list').children('li.option.selected').each(function() {
		  let selectId = $(this).closest('div').siblings().first().attr('id');
		  let splitIndex = selectId.indexOf('-');
		  let param = selectId.substring(splitIndex + 1).trim();	// 앞뒤 공백 제거
		  selectedValue = $(this).data('value');
		  console.log('현재 선택 상자: ' + selectedValue);
		  console.log('파라미터 명: ' + param);
		  
		  q += '&' + param + '=' + selectedValue;
	  });
	
	  // 선택된 값 출력하기
	  console.log('q: ' + q);
	  
	  // 서버로 전송
	  location.href='/bookList.ok?'+q;
	});
	
	// 최신 도서 nav 클릭 이벤트
	$('#bookList_nav').on('click', function(e) {
		e.preventDefault();
		bookListURL();
	});
});

/* 현재 페이지의 쿼리 문자열을 가져오는 함수 */
function getQueryString() {
	
  let params = {};	// 쿼리 문자열을 저장할 객체
	console.log(typeof params);

	// 현재 페이지의 쿼리 문자열 가져오기
  let queryString = window.location.search;
  queryString = queryString.substr(1).split("&");
  console.log('queryString : '+queryString);
	
	// 쿼리 문자열에서 원하는 정보 가져오기	
  queryString.forEach(function(item) {
  	// console.log(item);
    let keyValue = item.split("=");
    console.log(keyValue);
    params[keyValue[0]] = decodeURIComponent(keyValue[1]);
  });
  
  console.log(params);
  console.log("mnName:", params.mnName);
  
  return params;
}