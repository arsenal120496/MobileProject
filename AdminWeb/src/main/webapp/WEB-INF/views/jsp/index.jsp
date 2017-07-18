<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Share Location</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" />
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/resources/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath}/resources/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="${pageContext.request.contextPath}/resources/vendors/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/resources/build/css/custom.min.css" rel="stylesheet">
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
          <spring:url value="login" var="loginUrl"/>
            <form action="${loginUrl}" method="post">
              <h1>Login Form</h1>
              <div>
                <input type="text" name="username" class="form-control" placeholder="Username" />
              </div>
              <div>
                <input type="password" name="password" class="form-control" placeholder="Password" />
              </div>
              <div>
              	<c:if test="${not empty error}">
              	<p>${error}</p>
              	</c:if>
                <input type="submit" class="btn btn-default submit"/>
              </div>

              <div class="clearfix"></div>

              <div class="separator">


                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-map-marker"></i> Share GPS!</h1>
                  <p>©2016 All Rights Reserved. Share GPS! is the best share gps application. Privacy and Terms</p>
                </div>
              </div>
            </form>
          </section>
        </div>

      </div>
    </div>
  </body>
</html>
