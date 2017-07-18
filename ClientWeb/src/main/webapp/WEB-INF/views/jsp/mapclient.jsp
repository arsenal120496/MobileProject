<!DOCTYPE html>
<html lang="en">
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Location</title>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="${pageContext.request.contextPath}/resources/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link
	href="${pageContext.request.contextPath}/resources/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link
	href="${pageContext.request.contextPath}/resources/vendors/nprogress/nprogress.css"
	rel="stylesheet">
<!-- iCheck -->
<link
	href="${pageContext.request.contextPath}/resources/vendors/iCheck/skins/flat/green.css"
	rel="stylesheet">
<!-- Datatables -->
<link
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link
	href="${pageContext.request.contextPath}/resources/build/css/custom.min.css"
	rel="stylesheet">
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVkZRJgnQHNSGiCaM0wrmJR9PH_oIVnEQ&callback=initMap">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
</script>

</script>
<script>
	var map;
	var markers = [];

	function initMap() {
		var uluru = {
			lat : 10.863805,
			lng : 106.444377
		};

		map = new google.maps.Map(document.getElementById('map'), {
			zoom : 14,
			center : uluru,
			mapTypeId : 'terrain'
		});

		// This event listener will call addMarker() when the map is clicked.

		// Adds a marker at the center of the map.
		addMarker(uluru);
	}

	// Adds a marker to the map and push to the array.
	function addMarker(location) {
		var marker = new google.maps.Marker({
			position : location,
			map : map
		});
		markers.push(marker);
	}

	// Sets the map on all markers in the array.
	function setMapOnAll(map) {
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(map);
		}
	}

	// Removes the markers from the map, but keeps them in the array.
	function clearMarkers() {
		setMapOnAll(null);
	}

	// Shows any markers currently in the array.
	function showMarkers() {
		setMapOnAll(map);
	}

	// Deletes all markers in the array by removing references to them.
	function deleteMarkers() {
		clearMarkers();
		markers = [];
	}
	function addmaker(x, y) {

		var ob = new Object();
		ob.lat = x;
		ob.lng = y;

		var jsonString = JSON.stringify(ob);
		var uluru = {
			lat : 11.863805,
			lng : 106.444377
		};
		deleteMarkers();

		map = new google.maps.Map(document.getElementById('map'), {
			zoom : 13,
			center : ob,
			mapTypeId : 'terrain'
		});
		addMarker(ob);
	}
</script>


</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="" class="site_title"><i class="fa fa-map"></i> <span>GPS
								Sharing </span></a>
					</div>

					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile clearfix">

						<div class="profile_info">
							<span>Welcome,</span>
							<h2>${username}</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>General</h3>
							<ul class="nav side-menu">

								<li><a><i class="fa fa-map-marker"></i> Location <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<c:forEach var="listValue" items="${listLocation}">
											<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"
												prefix="fmt"%>
											<jsp:useBean id="dateValue" class="java.util.Date" />
											<jsp:setProperty name="dateValue" property="time"
												value="${listValue.time}" />
											<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy"
												var="day" />
											<li onclick="addmaker(11.863805,106.444377);">lat:
												${listValue.latitude} log: ${listValue.longitude} <br>
												day:${day}</br>
											</li>
										</c:forEach>
									</ul></li>

							</ul>
						</div>


					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<div class="sidebar-footer hidden-small">

						<a data-toggle="tooltip" data-placement="top" title="Logout"
							href="login.html"> <span class="glyphicon glyphicon-off"
							aria-hidden="true"></span>
						</a>
					</div>
					<!-- /menu footer buttons -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav>
						<div class="nav toggle" style="width: 10%">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>
						<div style="width: 70%; float: left;">
							<div class="title_right">
								<div
									class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
									<div class="input-group"
										style="margin-top: 2 0px; left: 50%; top: 50%; transform: translateX(-200%) translateY(30%);">


										<span class="input-group-btn"> <spring:url
												value="/searchFriend" var="addNewUserUrl" />
											<form action="${addNewUserUrl}" method="get">
												<input type="text" id="value" class="form-control"
													placeholder="Search friend by email..." name="value">
												<button class="btn btn-default" type="submit" value="Submit">Search!</button>
											</form>
										</span>
									</div>
								</div>
							</div>
						</div>
						<div style="width: 20%; height: 100%; float: right;">
							<ul class="nav navbar-nav navbar-right">
								<li class=""><a href="javascript:;"
									class="user-profile dropdown-toggle" data-toggle="dropdown"
									aria-expanded="false"> ${username} <span
										class=" fa fa-angle-down"></span>
								</a>
									<ul class="dropdown-menu dropdown-usermenu pull-right">
										<li><a href="javascript:;"> Profile</a></li>
										<li><a href="javascript:;" data-toggle="modal"
											data-target="#myModal"><span>Change Password</span> </a></li>
										<li><a href="javascript:;">Help</a></li>
										<li><a href="login.html"><i
												class="fa fa-sign-out pull-right"></i> Log Out</a></li>
									</ul></li>


							</ul>
						</div>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div id="map" style="width: 100%; height: 540px"></div>

			</div>
			<!-- /page content -->

			<!-- footer content -->
			<footer>
				<div class="pull-right">GPS Sharing</div>
				<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
		</div>
	</div>


	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Change Password</h4>
				</div>
				<div class="modal-body">

					<spring:url value="/changePass" var="changePassUrl" />
					<form class="form-horizontal form-label-left"
					 action="${changePassUrl}" method="post" novalidate>

						<div class="item form-group">

							<div class="item form-group">
								<label for="password" class="control-label col-md-3">Password</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="password" type="password" name="cpass"
										data-validate-length="6,8"
										class="form-control col-md-7 col-xs-12" required="required">
								</div>
							</div>
							<br />
							<div class="item form-group">
								<label for="password2"
									class="control-label col-md-3 col-sm-3 col-xs-12">Repeat
									Password</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input id="password2" type="password" name="npass"
										
										class="form-control col-md-7 col-xs-12" required="required">
								</div>
							</div>


						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" value="Submit">Change</button>
						</div>
					</form>

				</div>

			</div>

		</div>
	</div>



	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/resources/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script
		src="${pageContext.request.contextPath}/resources/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script
		src="${pageContext.request.contextPath}/resources/vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script
		src="${pageContext.request.contextPath}/resources/vendors/nprogress/nprogress.js"></script>
	<!-- iCheck -->
	<script
		src="${pageContext.request.contextPath}/resources/vendors/iCheck/icheck.min.js"></script>
	<!-- Datatables -->
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/jszip/dist/jszip.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/pdfmake/build/pdfmake.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/pdfmake/build/vfs_fonts.js"></script>

	<!-- Custom Theme Scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/build/js/custom.min.js"></script>

</body>
</html>