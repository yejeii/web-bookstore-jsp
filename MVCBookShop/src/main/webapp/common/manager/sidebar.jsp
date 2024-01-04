<%@ page contentType="text/html; charset=UTF-8" %>

	  <div class="quixnav">
      <div class="quixnav-scroll">
        <ul class="metismenu" id="menu">
          <li class="nav-label first">Main Menu</li>
          <!-- <li><a href="index.html"><i class="icon icon-single-04"></i><span class="nav-text">Dashboard</span></a>
          </li> -->
          <li>
          	<a class="has-arrow" href="javascript:void()" aria-expanded="false">
          		<i class="icon icon-single-04"></i><span class="nav-text">Dashboard</span>
          	</a>
            <ul aria-expanded="false">
              <li><a href="${request.getContextPath() }/manager/dashboard.ma">Dashboard 1</a></li>
              <li><a href="./index2.html">Dashboard 2</a></li>
            </ul>
          </li>
          <li class="nav-label">도서 관리</li>
          <li>
          	<a class="has-arrow" href="javascript:void()" aria-expanded="false">
          		<i class="icon icon-app-store"></i><span class="nav-text">도서</span>
          	</a>
            <ul aria-expanded="false">
              <li><a href="${request.getContextPath() }/manager/bookManage/bookList.ma">등록 도서 관리</a></li>
              <li><a href="${request.getContextPath() }/manager/bookManage/bookRegistForm.ma">도서 등록</a></li>
              <li><a class="has-arrow" href="javascript:void()" aria-expanded="false">Email</a>
                <ul aria-expanded="false">
                  <li><a href="./email-compose.html">Compose</a></li>
                  <li><a href="./email-inbox.html">Inbox</a></li>
                  <li><a href="./email-read.html">Read</a></li>
                </ul>
              </li>
              <li><a href="./app-calender.html">Calendar</a></li>
            </ul>
          </li>
          
          <li>
          	<a class="has-arrow" href="javascript:void()" aria-expanded="false">
          		<i class="icon icon-chart-bar-33"></i><span class="nav-text">Author</span>
          	</a>
            <ul aria-expanded="false">
              <li><a href="./chart-flot.html">Flot</a></li>
              <li><a href="./chart-morris.html">Morris</a></li>
              <li><a href="./chart-chartjs.html">Chartjs</a></li>
              <li><a href="./chart-chartist.html">Chartist</a></li>
              <li><a href="./chart-sparkline.html">Sparkline</a></li>
              <li><a href="./chart-peity.html">Peity</a></li>
            </ul>
          </li>
          
          <li class="nav-label">회원 관리</li>
          <li>
          	<a class="has-arrow" href="javascript:void()" aria-expanded="false">
          		<i class="icon icon-world-2"></i><span class="nav-text">회원</span>
          	</a>
            <ul aria-expanded="false">
              <li><a href="${request.getContextPath() }/manager/memberManage/memberList.ma">회원 목록</a></li>
              <li><a href="./ui-alert.html">Alert</a></li>
              <li><a href="./ui-badge.html">Badge</a></li>
              <li><a href="./ui-button.html">Button</a></li>
              <li><a href="./ui-modal.html">Modal</a></li>
              <li><a href="./ui-button-group.html">Button Group</a></li>
              <li><a href="./ui-list-group.html">List Group</a></li>
              <li><a href="./ui-media-object.html">Media Object</a></li>
              <li><a href="./ui-card.html">Cards</a></li>
              <li><a href="./ui-carousel.html">Carousel</a></li>
              <li><a href="./ui-dropdown.html">Dropdown</a></li>
              <li><a href="./ui-popover.html">Popover</a></li>
              <li><a href="./ui-progressbar.html">Progressbar</a></li>
              <li><a href="./ui-tab.html">Tab</a></li>
              <li><a href="./ui-typography.html">Typography</a></li>
              <li><a href="./ui-pagination.html">Pagination</a></li>
              <li><a href="./ui-grid.html">Grid</a></li>
            </ul>
          </li>
          
          <li class="nav-label">이메일 관리</li>
          <li>
          	<a class="has-arrow" href="javascript:void()" aria-expanded="false">
          		<i class="icon icon-world-2"></i><span class="nav-text">이메일</span>
          	</a>
            <ul aria-expanded="false">
              <li><a href="${request.getContextPath() }/manager/emailManage/sendMail.ma">메일 쓰기</a></li>
              <li><a href="${request.getContextPath() }/manager/emailManage/list.ma?li=a">전체 메일함</a></li>
              <li><a href="${request.getContextPath() }/manager/emailManage/list.ma?li=g">받은 메일함</a></li>
              <li><a href="${request.getContextPath() }/manager/emailManage/list.ma?li=s">보낸 메일함</a></li>
              <li><a href="${request.getContextPath() }/manager/emailManage/templist.ma">임시 보관함</a></li>
              <li><a href="${request.getContextPath() }/manager/emailManage/trash.ma">휴지통</a></li>
            </ul>
          </li>
        </ul>
      </div>
	  </div>