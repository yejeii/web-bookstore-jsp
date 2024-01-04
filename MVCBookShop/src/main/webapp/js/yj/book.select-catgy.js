/**
 * 도서 카테고리 관련 
 */
$(document).ready(function() {

	// console.log($.catgys);
	
	/* 전역 변수 선언 및 초기화 */
	$.main_catgy_arr = new Array();
	$.middle_catgy_arr = new Array();
	$.sub_catgy_arr = new Array();
	
	let num = 1; 
	
	/* 데이터 분류 */
	sort_catgys($.catgys);
	
	/* 대분류 select 박스에 데이터 삽입 */
	$.each($.main_catgy_arr, function(index, value) {
		$(".main_select").append("<option value='" + value.code + "'>" + value.name + "</option>");
	});
	
	/** 중분류 option 설정
	 * 대분류 select 요소가 변경되었을 때 실행 */
	$(document).on("change", "select.main_select", function() {
		
		// <select class="main_select"> 요소들 중 선택한 <select> 요소의 인덱스 가져오기
		let selected_wrapper = $(this).closest("div.wrapper");
		let selected_middle_select = $(selected_wrapper).find(".middle_select");
		let selected_sub_select = $(selected_wrapper).find(".sub_select");
		
		// console.log(selected_wrapper);
		
		/* 대분류 미선택시 중/소분류 옵션 표시 */
		if($(this).val() == "") {
			selected_middle_select.html("<option value=''>--</option>");
			selected_sub_select.html("<option value=''>--</option>");
		}
		
		else {
			let main_code = $(this).val();
			
			// 기존 대분류에 따른 중분류 및 소분류 옵션 삭제
			selected_sub_select.html("<option value=''>--</option>");
			
			/* 중분류 옵션 추가 */
			let html = "<option value=''>선택</option>";
			
			$.each($.middle_catgy_arr, function(index, value) {
				if(value.code_ref_mn == main_code) {
					html += "<option value='" + value.code + "'>" + value.name + "</option>";
				}
			});
			
			selected_middle_select.html(html);
		}
	});
	
	/** 소분류 option 설정
	 * 중분류 select 요소가 변경되었을 때 실행 */
	$(document).on("change", "select.middle_select", function() { 
		
		/* <select class="middle_select"> 요소들 중 선택한 <select> 요소의 인덱스 가져오기 */
		let selected_wrapper = $(this).closest("div.wrapper");
		let selected_middle_select = $(selected_wrapper).find(".middle_select");
		let selected_sub_select = $(selected_wrapper).find(".sub_select");
		
		let main_code = $(selected_wrapper).find(".main_select").val();
		let middle_code = $(this).val();
		
		// 기존 중분류에 따른 소분류 옵션 삭제
		selected_sub_select.html("<option value=''>--</option>");
		
		// 소분류 select 박스에 옵션 추가
		let html = "<option value=''>선택</option>";
		
		$.each($.sub_catgy_arr, function(index, value) {
			if(value.code_ref_mn == main_code && value.code_ref_md == middle_code) {
				html += "<option value='" + value.code + "'>" + value.name + "</option>";
			}
		});
		
		selected_sub_select.html(html);
		
	});
	
	/** 카테고리 추가 */
	$(document).on("click", ".add_catgy_btn", function(e) {
		
		e.preventDefault();
		
		let add_div = $(".add_div"); 
		
		/* 필요한 element 생성 */
		let wrapper_div = $("<div>", {
		  class: "row col-12 wrapper wrapper_div" + num
		}).appendTo(add_div);
		
		// 대분류
		$("<div>", {
		  class: "col-md-6 col-xl-3 mb-2"
		}).append(
		  $("<label>", { for: "b_main_catgy", text: "카테고리 대분류" }),
		  $("<select>", { class: "form-control main_select", name: "b_main_catgy" }).append(
		    $("<option>", { value: "", text: "전체" })
		  )
		).appendTo(wrapper_div);
		
		// 중분류
		$("<div>", {
		  class: "col-md-6 col-xl-3 mb-2"
		}).append(
		  $("<label>", { for: "b_middle_catgy", text: "카테고리 중분류" }),
		  $("<select>", { class: "form-control middle_select", name: "b_middle_catgy" }).append(
		    $("<option>", { value: "", text: "--" })
		  )
		).appendTo(wrapper_div);
		
		// 소분류
		$("<div>", {
		  class: "col-md-6 col-xl-3 mb-2"
		}).append(
		  $("<label>", { for: "b_sub_catgy", text: "카테고리 소분류" }),
		  $("<select>", { class: "form-control sub_select", name: "b_sub_catgy" }).append(
		    $("<option>", { value: "", text: "--" })
		  )
		).appendTo(wrapper_div);
		
		// 버튼
		$("<div>", {
		  class: "col-md-6 col-xl-3 mb-2 mt-4"
		}).append(
		  $("<button>", {
		    type: "button",
		    class: "btn btn-warning delete_catgy_btn",
		    text: "삭제"
		  }).append(
		    $("<span>", { class: "btn-icon-right" }).append(
		      $("<i>", { class: "fa fa-close" })
		    )
		  )
		).appendTo(wrapper_div);
		
		add_div.removeClass("d-none");


		/** 새로운 div class="wrapper" 가 생겼을 때 대분류 카테고리 옵션 추가 */
		select_wrapper_main(num);

		num ++;
		
		//console.log(main_select);
	});

	/** 카테고리 삭제 */
	$(document).on("click", ".delete_catgy_btn", function(e) {
		
		e.preventDefault(); 
		
		/* 요소 삭제 */
		let deleteElement = $(this).closest("div.wrapper");
		deleteElement.remove(); 
		
		/* class="wrapper"의 wrapper_div 재설정 */
		let wrapperElements = $(".add_div").children(".wrapper");
		//console.log(wrapperElements);
		if(wrapperElements.length >= 1) {
			let wrapperLength = wrapperElements.length;
			//console.log("wrapperLength:"+wrapperLength);
			
			for(let i=0; i<wrapperLength; i++) {
				let newClassName = "row col-12 wrapper wrapper_div"+(i+1);
				//console.log("newClassName: "+newClassName);
				wrapperElements.eq(i).removeClass().addClass(newClassName);
				
			}
			
			num = wrapperLength+1;
			//console.log("next num:"+num);
		} else {
			$(".add_div").addClass("d-none");
			num = 1;
		}
	});
	
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
	
	function select_wrapper_main(num) {
		
		let className = "wrapper_div"+num;
		let new_wrapper = $("div."+className);
		let new_main_select = $(new_wrapper).find(".main_select");
		
		/* 대분류 select 박스에 데이터 삽입 */
		$.each($.main_catgy_arr, function(index, value) {
			new_main_select.append("<option value='" + value.code + "'>" + value.name + "</option>");
		});
	}
});
