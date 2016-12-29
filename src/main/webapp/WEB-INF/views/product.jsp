<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShoppingKart</title>
<link href="bootstrap/css/shop-homepae.css" rel="stylesheet" />
<link href="../bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="../bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
<link
	href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.1/css/bootstrap-combined.min.css"
	rel="stylesheet">
<script src="../bootstrap/scripts/jquery-1.7.1.min.js"></script>
<script src="../bootstrap/js/bootstrap.js"></script>
<style>
body {
	background-color: white;
}

#cartitem {
	display: inline;
}

#welcome {
	display: inline;
	height: 20px;
}
</style>
</head>
<body>
	<!--Header-->
	<header> <nav class="navbar navbar-inverse" role="navigation">
	<div class="navbar-inner">
		<a class="brand" href="home.jsp">Online Shop</a>

		<div class="nav-collapse collapse">
			<ul class="nav">
				<c:forEach var="categories" items="${categories}">
					<li class="dropdown"><a class="dropdown-toggle"
											data-toggle="dropdown" href="#"> <c:out value="${categories.key}" />
					</a>

						<ul class="dropdown-menu">
							<c:forEach var="subcategories" items="${categories.value}">
								<c:url var="url" value="/category">

									<c:param name="subcat" value="${subcategories.categoryid}" />
									<c:param name="categ" value="${categories.key}" />
								</c:url>
								<li><a tabindex="-1" href="${url}"><c:out
										value="${subcategories.productCategory}" /></a></li>
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
						<li><a tabindex="-1" href="/">Sign out</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav> </header>

	<!-- Body -->
	<section>
	<div class="container">
		<ul class="breadcrumb">
			<li class="active"><c:url var="url" value="/category">
					<c:param name="categ" value="${productCategory}" />
				</c:url> <a href="${url}">${productCategory}</a> <span class="divider">/</span></li>
			<li class="active"><c:url var="url" value="/category">
					<c:param name="subcat" value="${productSubCategory}" />
				</c:url><a href="${url}">${productSubCategory}</a> <span class="divider">/</span></li>
			<li class="active">${product.productName}</li>
		</ul>
		<div class="row">
			<div class="span6">
				<input type="hidden" value="${productID}" name="id">
				<p class="lead">${product.productName}</p>
				<a href="#themodal" role="button" id="btn-buynow"
					class="btn btn-primary pull-right" data-toggle="modal">Buy Now</a>
				<p>${product.productPrice}</p>
				<p>${product.productManufacturer}</p>
				<br>
				<p>${product.description}</p>
			</div>
			<div id="themodal" class="modal hide fade">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3>Add Item to the Cart</h3>
				</div>

				<div class="modal-body">
							<p class="text-success">Product - ${product.productName} will
								be added to your Shopping Cart.</p>
					<hr />
					<div>
						<table class="table table-condensed">
							<thead>
								<tr>
									<td>Product</td>
									<td>Price</td>
								</tr>
							</thead>
							<tbody>
								<tr class="active">
									<td>${product.productName}</td>
									<td class="price">${product.productPrice}</td>
								</tr>
							</tbody>
						</table>
					</div>

					<div class="modal-footer">
						<form method="post" action="addProducts" class="addCartForm">
									<button class="btn btn-primary pull-left" id="addtocart">Add
										to Cart</button>
							<a href="#" class="btn" data-dismiss="modal">Continue
								Shopping</a>
							<a id="yesbutton" href="/checkout" class="btn btn-primary">Place
								Order</a>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<footer> </footer>
	<script>
		var productPrice = "${product.productPrice}";
		var productId = "${productID}";

		function updateHeaderCartItemsCount(newCount) {
			$('.headerCartItemsCount').html(newCount);
			$('.headerCartItemsCountWord').html(
					(newCount == 1) ? ' item' : ' items');
		}

		function ajax(options, callback) {
			var defaults = {
				success : function(data) {
					if (!redirectIfNecessary($(data))) {
						var extraData = getExtraData($(data));
						callback(data, extraData);
					}
				}
			};

			$.extend(options, defaults);
			$.ajax(options);
		}

		function serializeObject($object) {
			var o = {};
			var a = $object.serializeArray();
			$.each(a, function() {
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		}


		var form = $('addCartForm');
		var itemRequest = serializeObject(form);
		var cartItems = "${cart.numberOfItems}";
		$(function() {
			$('#addtocart').click(function(event) {
				$('#themodal').modal('toggle');

				$.ajax({
					url : $form.attr('action'),
					type : "POST",
					success : function(data) {
						updateHeaderCartItemsCount(cartItems);
						alert(cartItems);
					}
				});
			});

		});

		// Disables a button
		$(function disableButton() {
			jQuery.fn.extend({
				disable : function(state) {
					return this.each(function() {
						this.disabled = state;
					});
				}
			});

			$('#disabledbutton').disable(true);
		});
	</script>
</body>
</html>