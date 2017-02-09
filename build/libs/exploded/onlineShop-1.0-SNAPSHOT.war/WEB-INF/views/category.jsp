<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShoppingKart</title>
<link href="resources/bootstrap/css/shop-homepae.css" rel="stylesheet" />
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
<script src="resources/bootstrap/scripts/jquery-1.7.1.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.js"></script>
</head>
<body>
	<!--Header-->
	<nav class="navbar navbar-inverse" role="navigation">
	<div class="navbar-inner">
		<a class="brand" href="home">Online Shop</a>

		<div class="nav-collapse collapse">
			<ul class="nav">
				<c:forEach var="categories" items="${categories}">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"> <c:out value="${categories.key}" />
					</a>

						<ul class="dropdown-menu">
							<c:forEach var="subcategories" items="${categories.value}">
								<c:url var="url" value="/category">

									<c:param name="subcat" value="${subcategories.idsubCategory}" />
									<c:param name="categ" value="${categories.key}" />
								</c:url>
								<li><a tabindex="-1" href="${url}"><c:out
											value="${subcategories.subCategoryName}" /></a></li>
							</c:forEach>
						</ul></li>
				</c:forEach>
			</ul>
			<ul class="nav pull-right">
				<li class="dropdown"><a id="cartitem" class="dropdown-toggle"
										data-toggle="dropdown" href="#"><span id="itemcount"
																			  class="btn btn-default btn-sm"><i
						class="icon-shopping-cart icon-red"></i> <c:set var="cartItems"
																		scope="session" value="${cart.numberOfItems}" /> <span
						class="headerCartItemsCount"> <c:choose>
					<c:when test="${empty cartItems}">0
					</c:when>
					<c:otherwise>
						<c:out value="${cartItems}" />
					</c:otherwise>
				</c:choose>
						</span> <span class="headerCartItemsCountWord"><c:out
						value="${cartItems==1?'item':'items'}" /></span> <b class="caret"></b></span></a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="cart">View Cart</a></li>
						<li><a tabindex="-1" href="checkout">Checkout Cart</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
										data-toggle="dropdown" href="#"><span id="welcome"
																			  class="btn btn-default btn-sm"> <c:choose>
					<c:when test="${empty email}">
						<c:out value="Hello Guest" />
					</c:when>
					<c:otherwise>
						<c:out value="Hello ${email}" />
					</c:otherwise>
				</c:choose>
					</span></a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="/">Account</a></li>
						<li><a tabindex="-1" href="/">Orders</a></li>
						<c:url var="logout" value="/logout" />
						<li><a tabindex="-1" href="${logout}">Sign out</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>

	<!-- Body -->
	<div class="container">
		<ul class="breadcrumb">
			<li class="active"><c:url var="url" value="/category">
					<c:param name="categ" value="${catName}" />
				</c:url> <a href="${url}">${catName}</a> <span class="divider">/</span></li>
			<li class="active">${subCat}</li>
		</ul>
		<div class="row">
			<div class="span12">
				<c:choose>
					<c:when test="${not empty categoryProducts}">
						<c:forEach var="products" items="${categoryProducts}">
							<c:url var="url" value="/product">
								<c:param name="productId" value="${products.idproduct}" />
							</c:url>
							<div class="span3">
								 </br> <a href="${url}">${products.productName}</a>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="products" items="${productsCategoryList}">
							<c:url var="url" value="/product">
								<c:param name="productId" value="${products.productId}" />
							</c:url>
							<div class="span3">
								<img src="images/meluha-small.jpg" /> </br> <a href="${url}">${products.productName}</a>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>