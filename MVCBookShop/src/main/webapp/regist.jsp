<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="CodePixar">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	<!-- Site Title -->
	<title>MVCBook Shop</title>
	<!-- CSS  -->
	<link rel="stylesheet" href="/css/manager/style.css">
</head>
<body>

	<!-- Preloader Start -->
	<div id="preloader">
	    <div class="sk-three-bounce">
	        <div class="sk-child sk-bounce1"></div>
	        <div class="sk-child sk-bounce2"></div>
	        <div class="sk-child sk-bounce3"></div>
	    </div>
	</div>
	<!-- Preloader End -->
	
	<!-- Main wrapper start -->
	<div id="main-wrapper">
		
		<!-- Nav-Header / Header start -->
		<%@ include file="/common/manager/header.jsp" %>
    <!-- Nav-Header / Header end -->

	  <!-- Sidebar start -->
		<%@ include file="/common/manager/sidebar.jsp" %>
	  <!-- Sidebar end -->

		<!-- Content body Area -->
		<div class="content-body">
			<div class="container-fluid">
        <div class="row page-titles mx-0">
          <div class="col-sm-6 p-md-0">
            <div class="welcome-text">
              <h4>도서 등록</h4>
              <span class="ml-1">Element</span>
            </div>
        	</div>
        	<div class="col-sm-6 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="javascript:void(0)">Form</a></li>
              <li class="breadcrumb-item active"><a href="javascript:void(0)">Element</a></li>
            </ol>
        	</div>
    		</div>
    		
    		<!-- Register row -->
				<div class="row">
					<div class="col-xxl-12">
						<div class="card">
							<div class="card-header">
                <h4 class="card-title">도서 정보 기입</h4>
              </div>
              <div class="card-body">
              	<div class="basic-form">
              		<form action="/manager/bookManage/bookRegistPro.ma" method="post" name="registForm" enctype="multipart/form-data">
              			<div class="form-row">
                      <div class="form-group col-md-4">
                      	<label>도서 이미지</label>
                      	<div id="uploadResult">
                      		<!-- 도서 이미지가 들어가는 곳 -->
                      		<img id="bookImage" width="150px;">
                      		<!-- 도서 이미지가 들어가는 곳 -->
                      	</div>
                        <div class="custom-file">
                          <input type="file" class="custom-file-input" name="b_image" id="b_image" required="required"
                          				accept=".jpg,.png" onchange="setThumbnail(event);">
                          <label class="custom-file-label" for="b_image">파일 선택</label>
                        </div>
                      </div>
                      
                      <div class="form-row col-md-8 form_row_8_div">
                      	<div class="col-12">
		                      <div class="form-group">
		                      	<label for="b_name">도서명</label>
		                        <input type="text" class="form-control" placeholder="도서명을 입력하세요" name="b_name" id="b_name" required="required">
		                     	</div>
		                    </div>
	                     	
	                     	<!-- Columns are always 33.3% wide, on mobile and desktop -->
	                     	<div class="col-4">
		                      <div class="form-group">
		                      	<label for="b_price">정가</label>
			                      <div class="input-group input-group-merge">
										          <input type="text" class="form-control" placeholder="정가를 입력하세요" 
										          				aria-label="Amount (to the nearest dollar)" name="b_price" id="b_price" 
										          				required="required"/>
										          <span class="input-group-text"><i class="fa fa-krw" aria-hidden="true" style="font-size: medium;"></i></span>
								          	</div>
	                     		</div>
                     		</div>
												<div class="col-4">				        	
								        	<div class="form-group">
			                      <label>재고 현황</label>
			                      <select id="inputState" class="form-control">
			                          <option selected>선택하세요</option>
			                          <option>미입고</option>
			                          <option>입고</option>
			                          <option>품절</option>
			                          <option>절판</option>
			                      </select>
			                    </div>
		                    </div>
		                    <div class="col-4">
		                    	<div class="form-group">
		                        <label for="b_page">쪽수</label>
		                        <div class="input-group input-group-merge">
			                        <input type="text" class="form-control" name="b_page" id="b_page" required="required">
			                        <span class="input-group-text">쪽</span>
		                        </div>
	                      	</div>
	                      </div>
							        	
							        	<!-- Columns are always 50% wide, on mobile and desktop -->
						        		<div class="col-6">	
								        	<div class="form-group">
		                      	<label for="b_writer">저자</label>
		                        <input type="text" class="form-control" placeholder="저자가 2명이상인 경우 ,를 써주세요" 
		                        				name="b_writer" id="b_writer" required="required">
		                      </div>
	                      </div>
	                      <div class="col-6">	
		                      <div class="form-group">
		                        <label for="b_translator">옮긴이</label>
		                        <input type=text" class="form-control" placeholder="옮긴이를 적어주세요" 
		                        				name="b_translator" id="b_translator">
		                      </div>
	                      </div>
	                      
	                      <div class="col-4">
		                      <div class="form-group">
		                        <label for="b_publisher">출판사</label>
		                        <input type="text" class="form-control" name="b_publisher" id="b_publisher" required="required">
	                     	 	</div>
	                     	</div>
	                     	<div class="col-4">
	                     	 	<div class="form-group">
		                        <label for="b_publish_date">출판일</label>
		                        <input type="date" class="form-control" value="2021-06-18" 
		                        				name="b_publish_date" id="b_publish_date" required="required"/>
		                      </div>
	                      </div>
	                      
	                      <!-- <div class="row col-12"> : 위의 col-4 에서 벗어나 분류 3개를 한 줄에 묶기 위한 설정 -->
	                      <!-- <div class="col-md-6 col-xl-3"> : 큰화면에선 3등분, 작은 크기엔 50% 등분 -->
	                      <div class="row col-12 mb-3 wrapper">
		                      <div class="col-md-6 col-xl-3">
		                      	<div class="form-group">
		                      		<label for="b_main_catgy">카테고리 대분류</label>
		                      		<select class="form-control main_select" name="b_main_catgy">
		                      			<option value="">전체</option>
		                      		</select>
		                      	</div>
		                      </div>
		                      <div class="col-md-6 col-xl-3">
		                      	<div class="form-group">
		                      		<label for="b_middle_catgy">카테고리 중분류</label>
		                      		<select class="form-control middle_select" name="b_middle_catgy">
		                      			<option value="">--</option>
		                      		</select>
		                      	</div>
		                      </div>
		                      <div class="col-md-6 col-xl-3">
		                      	<div class="form-group">
		                      		<label for="b_sub_catgy">카테고리 소분류</label>
		                      		<select class="form-control sub_select" name="b_sub_catgy">
		                      			<option value="">--</option>
		                      		</select>
		                      	</div>
		                      </div>
		                      <div class="col-md-6 col-xl-3">
		                      	<div class="form-group mt-4">
															<button type="button" class="btn btn-secondary add_catgy_btn">분류 추가 
																<span class="btn-icon-right"><i class="fa fa-plus"></i></span>
															</button>
		                      	</div>
		                      </div>
		                    </div>
	                      
	                      <!-- 서브 카테고리 박스 -->
	                      <div class="col border rounded border-secondary mx-2 p-3 d-none add_div" 
	                      			style="box-shadow: 5px 10px #716a8f;">
		                      <!-- 서브 카테고리 추가 -->
												</div>
	                      <!-- 서브 카테고리 박스 -->
	                   	</div> 
	                  </div>
	                   	
		                <div class="form-row">
		                  <div class="form-group col-xxl-12">
		                  	<label for="b_content">책 설명</label>
		                  	<textarea class="form-control" rows="5" wrap="off" 
		                  						id="b_content" name="b_content" required="required"></textarea>
		                  </div>
		                  
		                  <div class="form-group">
		                    <div class="form-check">
		                      <input class="form-check-input" type="checkbox">
		                      <label class="form-check-label">
		                          Check me out
		                      </label>
		                    </div>
		                  </div>
		                </div>
	                	<button type="reset" class="btn btn-outline-warning btn-lg">초기화</button>
	                	<button type="button" class="btn btn-outline-info btn-lg" 
	                						onClick="window.location.href='/manager/bookManage/bookList.ma'">등록된 도서 목록</button>
	                	<button type="button" class="btn btn-outline-primary btn-lg regist_btn">등록</button>
            			</form>
              	</div>
              </div>
						</div>				
					</div>
				</div>				
    		<!-- Register row -->
			</div>
		</div>
		<!-- Content body Area -->

		<!-- start footer Area -->
		<div class="footer">
		  <div class="copyright">
		    <p>Copyright © Designed &amp; Developed by <a href="#" target="_blank">Quixkit</a> 2019</p>
		  </div>
		</div>
		<!-- End footer Area -->
	
	</div>
	<!-- Main Wrapper End -->

	<!-- Script -->
	<script src="/js/vendor/jquery-2.2.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	 crossorigin="anonymous"></script>
  <script src="/vendor/global/global.min.js"></script>
  <script src="/js/manager/quixnav-init.js"></script>
  <script src="/js/manager/custom.min.js"></script>
  <!-- YJ -->
  <script type="text/javascript">
		
		$(document).ready(function() {
			$.catgys = ${catgys};
			//console.log($.catgys);
			
	  	$(document).on("click", ".regist_btn", function(e) {
	  		
				e.preventDefault();
				
				let isValid = false;
				
				/* select 유효성 검사 
				 * 1. 값 존재 확인
				 * val().trim() === "" : 선택한 옵션의 값을 앞뒤로 공백을 제거한 후 비어있는지 확인
				 * index() === 0 : 선택한 옵션의 인덱스가 0인지 확인하여, 첫 번째 옵션을 선택했는지 확인 */
			  $('select').each(function() {
			    let value = $(this).val().trim();
			    let selectedIndex = $(this).find('option:selected').index();
			    if (value === '' || selectedIndex === 0) {
			    	alert("대/중/소분류를 필수로 선택하세요.");
			      return false; // 해당 루프에서 out
			    } else isValid = true;
			  });
			  
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

				if(allEqual && isValid) {
					// 값이 모두 동일하고 옵션이 모두 선택된 경우
					$("form[name='registForm']").submit();
				} else {
					alert("대분류 옵션은 동일해야 합니다.");
					return false;
				}
			});
	  	
	  	
		});
		
  	function setThumbnail(event) {
		  var reader = new FileReader();
		  
		  reader.onload = function(event) {
			  var img = $("#bookImage");
			  img.attr("src", event.target.result);
			  img.attr("class", "img-fluid rounded mx-auto d-block");
			  $("#uploadResult").appendChild(img);
		  };
		  
		  reader.readAsDataURL(event.target.files[0]);
		  
	  }
  	
  </script>
  <script language=JavaScript src="/js/yj/book.select-catgy.js"></script>
  <!-- Script -->

</body>
</html>