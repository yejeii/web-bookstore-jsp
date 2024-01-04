<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                  <h1>회원가입</h1>
                  <nav class="d-flex align-items-center">
                      <a href="${request.getContextPath() }/bookShopMain.ok">홈<span class="lnr lnr-arrow-right"></span></a>
                      <a href="${request.getContextPath() }/memberLogin.me">로그인<span class="lnr lnr-arrow-right"></span></a>
                      <a href="#">회원가입</a>
                  </nav>
              </div>
          </div>
      </div>
  </section>
  <!-- End Banner Area -->

  <!-- Member Info Area -->
  <section class="checkout_area section_gap">
      <div class="container">
          <div class="billing_details align-self-stretch">
              <div class="align-self-stretch">
                  <div class="row">
                  	<div class="mx-auto border border-dark" style="width: 600px;">
                  		<div class="m-4">
		                    <h3 style="margin-bottom: 0px !important;">회원 정보 기입란</h3> <!-- .billing_details h3 {margin-bottom: 30px; } 제거 -->
                  		</div>
                  		
                      <form class="form-row contact_form m-5" action="${request.getContextPath() }/memberJoinPro.me" method="post" 
                      			name="joinForm" novalidate="novalidate">
												<div class="mx-auto">
													<div class="row">
														<div class="col form-group p_star">
		                        	<label for="m_name">이름</label>
		                          <input type="text" class="form-control" id="m_name" name="m_name">
		                        </div>  
		                        <div class="col form-group p_star">
		                        	<label class="w-100">성별</label>
		                        	<div class="form-check form-check-inline">	
	                        		  <label class="form-check-label" for="m_genderM">
		                            	<input type="radio" class="form-check-input" id="m_genderM" name="m_gender" value="M" checked="checked"/>남자
		                           	</label>
		                          </div>
		                          <div class="form-check form-check-inline">	
		                          	<label class="form-check-label" for="m_genderW">
		                            	<input type="radio" class="form-check-input" id="m_genderW" name="m_gender" value="W"/>여자
		                           	</label>
		                          </div>
		                        </div>
		                      </div>
		                      <div class="row">
		                        <div class="col form-group p_star">
		                        	<label for="m_id">아이디</label>
															<input type="text" class="form-control" id="m_id" name="m_id">
		                        </div>
		                        <div class="col form-group p_star">
		                        	<label for="m_pw">비밀번호</label>
		                          <input type="password" class="form-control" id="m_pw" name="m_pw">
		                        </div>
	                        </div>
	                        <div class="form-group">
	                        	<label for="m_age">나이</label>
	                          <input type="text" class="form-control" id="m_age" name="m_age">
	                        </div>
	                        <div class="form-group">
	                        	<label for="m_email">이메일</label>
	                        	<input type="email" class="form-control" id="m_email" name="m_email">
	                        </div>
	                        <div class="row">
	                        	<label for="m_phone0" class="ml-3 w-100">연락처</label>
		                        <div class="col form-group p_star">
		                          <input type="text" class="form-control" id="m_phone0" name="m_phone0" value="010" readonly="readonly">
		                       	</div>
		                        	&nbsp;-&nbsp;
		                        <div class="col form-group p_star">
			                        	<input type="text" class="form-control" id="m_phone1" name="m_phone1">
		                        </div>
		                        	&nbsp;-&nbsp;
			                      <div class="col form-group p_star">
			                          <input type="text" class="form-control" id="m_phone2" name="m_phone2">
		                       	</div>
	                        </div>
	                    	          
	                    	  <div class="row p-2 mt-4">
                          	<a class="col primary-btn m-2" href="javascript:joinForm.submit()">가입</a>
                          	<a class="col primary-btn m-2" href="javascript:joinForm.reset()">다시 작성</a>
	                    	  </div>
                          
	                    	</div>
                      </form>
                    </div>
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
</body>

</html>