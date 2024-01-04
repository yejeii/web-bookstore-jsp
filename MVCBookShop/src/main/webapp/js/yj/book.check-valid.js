/**
 * 도서 유효성 검사
 */
$(document).ready(function() {
		
	$(document).on("click", ".regist_btn", function(e) {
	  		
			e.preventDefault();
			
			let isValid = false;
			
			/* select 유효성 검사 
			 * 1. 값 존재 확인
			 * val().trim() === "" : 선택한 옵션의 값을 앞뒤로 공백을 제거한 후 비어있는지 확인
			 * index() === 0 : 선택한 옵션의 인덱스가 0인지 확인하여, 첫 번째 옵션을 선택했는지 확인 */
		  $(".wrapper").find('select').each(function() {
		    let value = $(this).val().trim();
		    // let selectedIndex = $(this).find('option:selected').index();
		    if (value === '') {
		    	alert("대/중/소분류를 필수로 선택하세요.");
		      return false; // 해당 루프에서 out
		    } else isValid = true;
		  });
		  
			if(isValid) {
				
				/* 2. 대분류 값이 동일한지 확인 
				 * map() : 각 <select> 박스의 값을 배열로 추출
				 * every() : 배열의 모든 요소가 주어진 조건을 만족할 때 true를 반환. 여기선 첫 번째 값과 동일한지 비교
				 */
				let selectMainValues = $(".main_select").map(function() {
					return $(this).val();
				}).get();
				
				// 모든 값이 동일한지 확인
				let allEqual = selectMainValues.every(function(value) {
					return value === selectMainValues[0];
				})

				if(allEqual) {
					// 값이 모두 동일하고 옵션이 모두 선택된 경우
					// console.log($('form:first'));
					$('form[name="bookForm"]').submit();
				} else {
					alert("대분류 옵션은 동일해야 합니다.");
					return false;
				}
			}
			
	});
});