 /**
 * 도서 상세 페이지 카테고리 분류
 */
$(document).ready(function() {

//	console.log($.catgys);
	// console.log(typeof $.b_bc_code);  number 
	
	/* 숫자(Number)를 문자열(String)로 변환 */
	$.b_bc_code = $.b_bc_code.toString();
	// console.log(typeof $.b_bc_code);		String
	
	/* 전역 변수 선언 및 초기화 */
	$.main_catgy_arr = new Array();
	$.middle_catgy_arr = new Array();
	$.sub_catgy_arr = new Array();
	
	/* DOM 요소 */
	let select_div = $(".default-select");
	
	/* 데이터 분류 */
	sort_catgys($.catgys);
	
	/* ${book.b_bc_code}의 대/중/소 분류 코드 찾기 */
	$.b_bc_code_ref_mn = $.b_bc_code.substr(0,2) + "0000";
	$.b_bc_code_ref_md = $.b_bc_code.substr(0,4) + "00";
//	console.log("b_bc_code_ref_mn : "+$.b_bc_code_ref_mn);
//	console.log("b_bc_code_ref_md : "+$.b_bc_code_ref_md);
//	console.log("b_bc_code : "+$.b_bc_code);
	
	/* ${book.b_bc_code}로 대/중/소 <select> 박스 <option> 설정 */
	$.each(select_div, function(index,element) {
		
//		console.log(index);
//		console.log(element);
		
		// <select>, <span>, <ul> 요소
		let select = $(element).find("select").eq(0);
		let ul = $(element).find(".list");
		let span = $(element).find(".current");
		
		switch(index) {
			case 0:			
				/* 대분류 select 박스에 데이터 삽입 */
				insertData(select, ul, span, $.main_catgy_arr, $.b_bc_code_ref_mn);
				break;
			
			case 1:
				/* 중분류 select 박스에 데이터 삽입 */
				insertData(select, 
										ul, 
										span, 
										$.middle_catgy_arr.filter(function(value) {
											return value.code_ref_mn.toString() === $.b_bc_code_ref_mn;
										}), 
										$.b_bc_code_ref_md);
				break;
				
			default:
				/* 소분류 select 박스에 데이터 삽입 */
				insertData(select, 
										ul, 
										span, 
										$.sub_catgy_arr.filter(function(value) {
											return value.code_ref_mn.toString()=== $.b_bc_code_ref_mn &&
															value.code_ref_md.toString() === $.b_bc_code_ref_md;
										}), 
										$.b_bc_code);
				break;
		}
		
	});
	
	/** 도서 상세정보 영역에 메인 분류 데이터 넣기 
	 * 현재 요소가 마지막 요소가 아닌 경우에만 " > "이 붙도록 설정 */
	let current_span = $(".current");
	let text = "";
	$.each(current_span, function(i, value) {
		// console.log(value);
		text += value.outerText;
		if (i !== current_span.length - 1) {
			text += " > ";
		}
	});
	
	$("#profile > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > h5:nth-child(1)").text(text);
	
	/** ${book.b_bc_code}로 대/중/소 <select> 박스 <option> 설정하는 함수  */
	function insertData(select, ul, span, dataArr, selectedCode) {
	
		dataArr.forEach(function(value) {
			if(value.code.toString() === selectedCode) {
				select.append("<option value='" + value.code + "' selected='selected'>" + value.name + "</option>");
				ul.append("<li data-value='" + value.code + "' class='option selected focus'>" + value.name + "</li>");
				span.text(value.name);
			} else {
				select.append("<option value='" + value.code + "'>" + value.name + "</option>");
				ul.append("<li data-value='" + value.code + "' class='option'>" + value.name + "</li>");
			}
		});
	}
	
	/** 변수 catgys를 대/중/소분류로 분류(배열 변수에 저장)하는 함수 */
	function sort_catgys(obj) {
		
		$.each(obj["catgys"], function(index, value) {
			
			// 대분류
			if(value.code_ref_md == 0 && value.code_ref_mn == 0) {
				
				// init
				let main_catgy = new Object();
				main_catgy.code = value.code;
				main_catgy.name = value.name;
				
				// 전역 변수에 push
	      $.main_catgy_arr.push(main_catgy);
			} 
			
			// 중분류
			else if(value.code_ref_md == 0 && value.code_ref_mn !== 0) {
					
				let middle_catgy = new Object(); 	
				middle_catgy.code = value.code;
				middle_catgy.name = value.name;
				middle_catgy.code_ref_mn = value.code_ref_mn;
				
				$.middle_catgy_arr.push(middle_catgy);
			}
			
			// 소분류
			else {
					
				let sub_catgy = new Object(); 	
				sub_catgy.code = value.code;
				sub_catgy.name = value.name;
				sub_catgy.code_ref_md = value.code_ref_md;
				sub_catgy.code_ref_mn = value.code_ref_mn;
				
				$.sub_catgy_arr.push(sub_catgy);
			}
			
		});	
	}
	
});
	
