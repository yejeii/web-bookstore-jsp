<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="email-left-box px-0 mb-5">
	<div class="p-0">
  	<a href="${request.getContextPath() }/manager/emailManage/sendMail.ma" class="btn btn-primary btn-block">메일 쓰기</a>
  </div>
 	<div class="mail-list mt-4">
  	<a href="${request.getContextPath() }/manager/emailManage/list.ma?li=a" class="list-group-item"> <!-- class: active -->
  		<i class="fa fa-inbox font-18 align-middle mr-2"></i>전체 메일함
  		<span class="badge badge-light badge-sm float-right" data-toggle="tooltip" data-placement="top" title="아직 읽지 않음">${applicationScope.appNotReadListCount}</span> 
  	</a>
    <a href="${request.getContextPath() }/manager/emailManage/list.ma?li=g" class="list-group-item">
    	<i class="fa fa-get-pocket font-18 align-middle mr-2"></i>받은 메일함
    </a> 
    <a href="${request.getContextPath() }/manager/emailManage/list.ma?li=s" class="list-group-item">
    	<i class="fa fa-paper-plane font-18 align-middle mr-2"></i>보낸 메일함
    </a> 
    <a href="${request.getContextPath() }/manager/emailManage/list.ma?li=i" class="list-group-item">
    	<i class="fa fa-star font-18 align-middle mr-2"></i>중요 메일함 
    	<span class="badge badge-danger text-white badge-sm float-right" data-toggle="tooltip" data-placement="top" title="중요한 메일">${applicationScope.appStarListCount}</span>
    </a>
    <a href="${request.getContextPath() }/manager/emailManage/templist.ma" class="list-group-item">
    	<i class="mdi mdi-file-document-box font-18 align-middle mr-2"></i>임시 보관함
    </a>
    <a href="javascript:void()" class="list-group-item">
    	<i class="fa fa-trash font-18 align-middle mr-2"></i>휴지통
    </a>
  </div>
  <div class="intro-title d-flex justify-content-between">
  	<h5>Categories</h5>
    <i class="icon-arrow-down" aria-hidden="true"></i>
  </div>
  <div class="mail-list mt-4">
  	<a href="email-inbox.html" class="list-group-item">
  		<span class="icon-warning"><i class="fa fa-circle" aria-hidden="true"></i></span>Work 
  	</a>
    <a href="email-inbox.html" class="list-group-item">
    	<span class="icon-primary"><i class="fa fa-circle" aria-hidden="true"></i></span>Private 
    </a>
    <a href="email-inbox.html" class="list-group-item">
    	<span class="icon-success"><i class="fa fa-circle" aria-hidden="true"></i></span>Support 
    </a>
    <a href="email-inbox.html" class="list-group-item">
    	<span class="icon-dpink"><i class="fa fa-circle" aria-hidden="true"></i></span>Social 
    </a>
  </div>
</div>