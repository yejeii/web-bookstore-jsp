/**
 * ${catgys} 데이터를 분류하는 함수
 */
function sort_catgys(obj, main_arr, middle_arr, sub_arr) {
		
	$.each(obj["catgys"], function(index, value) {
		
		// 대분류
		if(value.code_ref_md == 0 && value.code_ref_mn == 0) {
			
			// init
			let main_catgy = new Object();
			main_catgy.code = value.code;
			main_catgy.name = value.name;
			
			// 전역 변수에 push
      main_arr.push(main_catgy);
		} 
		
		// 중분류
		else if(value.code_ref_md == 0 && value.code_ref_mn !== 0) {
				
			let middle_catgy = new Object(); 	
			middle_catgy.code = value.code;
			middle_catgy.name = value.name;
			middle_catgy.code_ref_mn = value.code_ref_mn;
			
			middle_arr.push(middle_catgy);
		}
		
		// 소분류
		else {
				
			let sub_catgy = new Object(); 	
			sub_catgy.code = value.code;
			sub_catgy.name = value.name;
			sub_catgy.code_ref_md = value.code_ref_md;
			sub_catgy.code_ref_mn = value.code_ref_mn;
			
			sub_arr.push(sub_catgy);
		}
		
	});	
}

/**
 * 대분류 카테고리를 분류해 왼쪽 배너에 add하는 함수
 */
function banner_main_catgy(domObj, main_arr) {
	const year = new Date().getFullYear();
  const month = new Date().getMonth()+1;
  const week = getWeekOfMonth(new Date());
	let mnName = '';

  $.each(main_arr, function(index, value) {
		switch(value.code) {
			case 200000:
				mnName = 'ENG';
			break;
			
			default:
				mnName = 'KOR';
		}
		
		let li = $('<li>', {
			class: 'main-nav-list'
		}).append(
			$('<a>', {
				'href': '/bookList.ok?page=1&mnName='+mnName+'&mdCode=&year='+year+'&month='+month+'&week='+week+'&sort=new&limit=9',
				'text': value.name
			})
		).appendTo($(domObj));	// jQuery의 메소드를 사용했으므로 그냥 DOM 요소인 domObj를 jQuery객체로 변환하여 사용.
	});
}

/**
 * 중분류 카테고리를 <a> 태그로 붙이는 함수
 * <a href="javascript:0" class="genric-btn default circle medium" id="md_0">전체</a>
 */
function a_middle_catgy(domObj, mnCode, middle_arr) {
  console.log(domObj);
  console.log(middle_arr);
	$.each(middle_arr, function(index, value) {
		if(value.code_ref_mn == mnCode) {
			console.log(value);
			let a = $('<a>', {
				'href': 'javascript:0',
				'class': 'genric-btn default circle medium', 
				'id': 'md_'+value.code,
				'text': value.name
			}).appendTo($(domObj));
		}
	});
}
