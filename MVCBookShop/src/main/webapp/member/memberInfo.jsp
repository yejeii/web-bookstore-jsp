<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zxx" class="no-js">

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

  <!-- CSS -->
  <link rel="stylesheet" href="/css/linearicons.css">
  <link rel="stylesheet" href="/css/owl.carousel.css">
  <link rel="stylesheet" href="/css/themify-icons.css">
  <link rel="stylesheet" href="/css/font-awesome.min.css">
  <link rel="stylesheet" href="/css/nice-select.css">
  <link rel="stylesheet" href="/css/nouislider.min.css">
  <link rel="stylesheet" href="/css/bootstrap.css">
  <link rel="stylesheet" href="/css/main.css">
  <link rel="stylesheet" href="/css/manager/style.css">
</head>

<body>

  <!-- Start Header Area -->
	<%@ include file="/common/header.jsp" %>
	<!-- End Header Area -->

  <!-- Start Banner Area -->
  <section class="banner-area organic-breadcrumb">
      <div class="container">
          <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
              <div class="col-first">
                  <h1>회원정보</h1>
                  <nav class="d-flex align-items-center">
                      <a href="${request.getContextPath() }/bookShopMain.ok">홈<span class="lnr lnr-arrow-right"></span></a>
                      <a href="${request.getContextPath() }/memberLogin.me">로그인<span class="lnr lnr-arrow-right"></span></a>
                      <a href="#">회원정보</a>
                  </nav>
              </div>
          </div>
      </div>
  </section>
  <!-- End Banner Area -->

  <!-- Member Info Area -->
  <section class="checkout_area section_gap">
      <div class="container">
          <div class="billing_details">
              <div class="row">
              		<div class="col-lg-4">
                      <div class="order_box">
                          <h2>회원 관리</h2>
                          <ul class="list">
                              <li><a href="${request.getContextPath() }/memberView.me?isLogin=true&userId=${member.m_id}">회원 정보</a></li>
                              <li><a href="${request.getContextPath() }/memberModifyPw.me?isLogin=true&userId=${member.m_id}">비밀번호 관리</a></li>
                          </ul>
                          <div class="payment_item">
                              <div class="radion_btn">
                                  <input type="radio" id="f-option5" name="selector">
                                  <label for="f-option5">Check payments</label>
                                  <div class="check"></div>
                              </div>
                              <p>Please send a check to Store Name, Store Street, Store Town, Store State / County,
                                  Store Postcode.</p>
                          </div>
                          <div class="payment_item active">
                              <div class="radion_btn">
                                  <input type="radio" id="f-option6" name="selector">
                                  <label for="f-option6">Paypal </label>
                                  <img src="img/product/card.jpg" alt="">
                                  <div class="check"></div>
                              </div>
                              <p>Pay via PayPal; you can pay with your credit card if you donât have a PayPal
                                  account.</p>
                          </div>
                          <div class="creat_account">
                              <input type="checkbox" id="f-option4" name="selector">
                              <label for="f-option4">Iâve read and accept the </label>
                              <a href="#">terms & conditions*</a>
                          </div>
                          <a class="primary-btn" href="#">Proceed to Paypal</a>
                      </div>
                  </div>
              
                  <div class="col-lg-8">
                  	<!-- 기본 회원 정보 -->
                  	<div class="mx-auto border border-dark mb-5" style="width: 600px;">
                  		<div class="m-4">
		                    <h3 style="margin-bottom: 0px !important;">기본 회원 정보란</h3> <!-- .billing_details h3 {margin-bottom: 30px; } 제거 -->
                  		</div>
                  		<div class="mx-4 d-flex flex-row-reverse bd-highlight">
                  			<div class="p-2">
			                    <a class="btn-sm text-primary" data-toggle="collapse" href="#addInfoDiv" role="button" 
			                    		aria-expanded="false" aria-controls="addInfoDiv">
		    										&#9752;&nbsp;주소 정보 추가
												  </a>
										  	</div>
										  </div>
 											
	                    <form class="form-row contact_form mx-3" id="infoForm" novalidate="novalidate">
	                    	<div class="w-70 mx-auto">
	                    		<div class="row">
		                        <div class="col form-group p_star">
		                        	<label for="m_name">이름</label>
		                          <input type="text" class="form-control" id="m_name" name="m_name" value="${member.m_name }" readonly="readonly">
		                        </div>
		                        <div class="col form-group p_star">
		                        	<label class="w-100">성별</label>
		                        	<div class="form-check form-check-inline">	
	                        		  <label class="form-check-label" for="m_genderM">
		                            	<input type="radio" class="form-check-input" id="m_genderM" name="m_gender" value="M"
		                            		<c:if test="${member.m_gender eq 'M'}"> checked="checked"</c:if>>&nbsp;남자
		                           	</label>
		                          </div>
		                          <div class="form-check form-check-inline">	
		                          	<label class="form-check-label" for="m_genderW">
		                            	<input type="radio" class="form-check-input" id="m_genderW" name="m_gender" value="W"
		                            		<c:if test="${member.m_gender eq 'W'}"> checked="checked"</c:if>>&nbsp;여자
		                           	</label>
		                          </div>
		                        </div>
	                        </div>
	                        <div class="row">
		                        <div class="col form-group p_star">
		                        	<label for="m_id">아이디</label>
															<input type="text" class="form-control" id="m_id" name="m_id" value="${member.m_id }" readonly="readonly">
		                        </div>
		                        <div class="col form-group p_star">
		                        	<label for="m_pw">비밀번호</label>
		                          <input type="password" class="form-control" id="m_pw" name="m_pw" value="${member.m_pw }" readonly="readonly">
		                        </div>
	                        </div>
	                        <div class="form-group">
	                        	<label for="m_age">나이</label>
	                          <input type="text" class="form-control" id="m_age" name="m_age" value="${member.m_age }">
	                        </div>
	                        <div class="form-group">
	                        	<label for="m_email">이메일</label>
	                        	<input type="email" class="form-control" id="m_email" name="m_email" value="${member.m_email }">
	                        </div>
	                        <div class="row">
		                        <div class="form-group">
		                        	<label for="m_phone0" class="ml-3 w-100">연락처</label>
		                        	<div class="col w-30 p_star">
		                            <input type="text" class="form-control" id="m_phone0" name="m_phone0" value="010" readonly="readonly">
		                        	</div>
		                        	&nbsp;-&nbsp;
		                        	<div class="col w-30 p_star">
			                        	<input type="text" class="form-control" id="m_phone1" name="m_phone1" value="${member.m_phone1 }">
		                        	</div>
		                        	&nbsp;-&nbsp;
			                        <div class="col w-30 p_star">
			                          <input type="text" class="form-control" id="m_phone2" name="m_phone2" value="${member.m_phone2 }">
			                        </div>
		                       	</div>
	                        </div>
	                    	</div>
	                    </form>
                    </div>
                    <!-- 기본 회원 정보 -->
                    
                    <!-- 추가 회원 정보 -->
                    <div class="collapse mx-auto border border-primary card" id="addInfoDiv" style="width: 600px; background-color:#f8f9fe;">
                    	<div class="m-4">
		                    <h3 style="margin-bottom: 0px !important;">회원 주소 정보란</h3> <!-- .billing_details h3 {margin-bottom: 30px; } 제거 -->
                  		</div>
  										<div class="card-body">
  											<form class="form-row contact_form mx-3" id="addForm" novalidate="novalidate">
  												<div class="w-70 mx-auto">
	  												<div class="form-row">
													    <div class="form-group w-50 p_star">
													      <label for="m_postcode">우편번호</label>
													      <input type="text" class="form-control" id="m_postcode" name="m_postcode" value="${member.m_postcode }">
													    </div>
													    <div class="form-group w-40 align-self-end d-flex flex-row-reverse">
													    	<button type="button" class="btn btn-outline-primary" id="postcodeBtn" onclick="daumPostcode();">우편번호 찾기</button>
													    </div>
													    <div class="form-group w-100">
													      <label for="m_addr1">도로명 주소</label>
													      <input type="text" class="form-control" id="m_addr1" name="m_addr1" placeholder="도로명 주소" value="${member.m_addr1 }">
													    </div>
													  	<div class="form-group w-60">
														    <label for="m_addr3">상세 주소</label>
														    <input type="text" class="form-control" id="m_addr2" name="m_addr2" placeholder="상세주소" value="${member.m_addr2 }">
													  	</div>
													    <div class="form-group w-40">
													      <label for="m_addr2">참고항목</label>
													      <input type="text" class="form-control" id="m_addr3" name="m_addr3" placeholder="참고항목" value="${member.m_addr3 }">
													    </div>
													  </div>
												  </div>
  											</form>
  										</div>
                    </div>
                    <!-- 추가 회원 정보 -->
                    
                    <!-- 버튼 -->
                    <div class="mx-auto w-100 mt-5" style="width: 600px; height: 50px;"><!-- div 영역의 전체 높이를 50px로 고정 -->
                    	<div class="w-70 mx-auto">
                    		<div class="row">
			                    <button type="button" class="mr-3 col btn btn-outline-warning" id="modifyBtn" onclick="javascript:modifyForm();">수정하기</button>
			            				<!-- Button trigger modal -->
			                    <button type="button" class="ml-3 col btn btn-outline-danger" data-toggle="modal" data-target="#deleteModal">탈퇴하기</button>
	                    				
			                   	<!-- Modal -->
													<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
													  <div class="modal-dialog">
													    <div class="modal-content">
													      <div class="modal-header">
													        <h5 class="modal-title" id="deleteModalLabel">탈퇴 전 마지막 절차입니다!</h5>
													        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
													          <span aria-hidden="true">&times;</span>
													        </button>
													      </div>
													      <div class="modal-body">
													      	<form id="deleteCheck" action="/memberDeletePro.me" method="post">
													      		<input type="hidden" name="m_id" value="${member.m_id }">
													          <div class="form-group">
													            <label for="checkPw" class="col-form-label">비밀번호를 입력하세요.</label>
													            <input type="password" class="form-control" id="checkPw" name="checkPw">
													          </div>
													          <div class="form-group">
			                              	<label for="form-check" class="col-form-label">탈퇴하는 사유를 선택해주세요(중복가능).</label>
													          	<div class="form-check mb-2">
			                                    <input type="checkbox" class="form-check-input" id="check1" name="leaveChk" value="0" checked>
			                                    <label class="form-check-label" for="check1">도서가 다양하지 않다.</label>
			                                </div>
			                                <div class="form-check mb-2">
			                                    <input type="checkbox" class="form-check-input" id="check2" name="leaveChk" value="1">
			                                    <label class="form-check-label" for="check2">사용하기 불편하다.</label>
			                                </div>
			                                <div class="form-check disabled">
			                                    <input type="checkbox" class="form-check-input" id="check3" name="leaveChk" value="2">
			                                    <label class="form-check-label" for="check3">원하는 상품이 없다. </label>
			                                </div>
													          </div>
													        </form>
													      </div>
													      <div class="modal-footer">
													        <button type="button" class="btn btn-white" data-dismiss="modal">취소</button>
													        <button type="button" class="btn btn-warning" onclick="javascript:deleteMember();">탈퇴</button>
													      </div>
													    </div>
													  </div>
													</div>
													<!-- Modal -->
												</div>
											</div>
										</div>
										<!-- 버튼 -->
								  </div>
							</div>
					</div>
  		</div>
  </section>
  <!--================End Checkout Area =================-->

  <!-- start footer Area -->
	<%@ include file="/common/footer.jsp" %>
  <!-- End footer Area -->


  <script src="/js/vendor/jquery-2.2.4.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
      crossorigin="anonymous"></script>
  <script src="/js/vendor/bootstrap.min.js"></script>
  <script src="/js/jquery.ajaxchimp.min.js"></script>
  <script src="/js/jquery.nice-select.min.js"></script>
  <script src="/js/jquery.sticky.js"></script>
  <script src="/js/nouislider.min.js"></script>
  <script src="/js/jquery.magnific-popup.min.js"></script>
  <script src="/js/owl.carousel.min.js"></script>
  <!--gmaps Js-->
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
  <script src="/js/gmaps.min.js"></script>
  <script src="/js/main.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
    function daumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    $('#m_addr3').val(extraAddr);
                
                } else {
                    $("#m_addr3").val('');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $("#m_postcode").val(data.zonecode);
                $("#m_addr1").val(addr);
                // 커서를 상세주소 필드로 이동한다.
                $("#m_addr2").focus();
            }
        }).open();
    }
	</script>
  <script type="text/javascript">
	  
  	// modal창에서 버튼 클릭
	  function deleteMember() {
  		
			// Check if the value is null or empty
		  if($('#checkPw').val() == null || $('#checkPw').val() === '') {
			  alert('비밀번호를 입력하세요.');
			  return;
		  }
			
			$('#deleteCheck').submit();
			  
	  }
  
	  function modifyForm() {
		  var infoForm = $('#infoForm').serializeArray();
		  var addForm = $('#addForm').serializeArray();
		  var mergedData = $.merge(infoForm, addForm);
		  var m_id = $('#m_id').val();

		  var formData = {};
		  $.each(mergedData, function() {
		      formData[this.name] = this.value;
		  });
		  console.log(formData);

		  $.ajax({
			    url: "/memberModifyPro.ax",
			    method: "POST",
			    data: formData,
			    dataType: "TEXT",
			    success: function(data) {
			      if(data === 'not-logined') {
			    	  alert('접근 권한이 없습니다.');
			    	  location.href='/bookShopMain.ok';
			      }
			      else if(data === 'rollback') {
			    	 	alert('수정처리 중 문제가 발생했습니다.');
			    	 	history.back(-1);
			      } 
			      else {
			    	  alert('회원정보가 수정되었습니다.');
			    	  location.href = '/memberView.me?isLogin=true&userId=' + m_id;
			      }
			    },
			    error: function(error) {
			      // Handle error response from server
			    }
			});			
	  }
		 
	  function reload() {
		  alert('click');
	  }
  </script>
</body>

</html>