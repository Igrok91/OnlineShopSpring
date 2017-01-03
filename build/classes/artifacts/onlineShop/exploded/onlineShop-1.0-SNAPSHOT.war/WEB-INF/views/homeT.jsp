<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Online Shop</title>
    <link href="bootstrap/css/shop-homepae.css" rel="stylesheet" />
    <link href="../bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link href="../bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
    <script src="../bootstrap/scripts/jquery-1.7.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.js"></script>
    <style>
        #cartitem {
            display: inline;
        }

        #welcome {
            display: inline;
            height: 20px;
        }

        #Login {
            display: inline;
            height: 20px;
        }
    </style>
</head>
<body>
<!--Header-->

<!-- Body -->

<div class="container">
    <div class="row">
        <div class="span12">
            <c:forEach var="products" items="${productsList}">
                <div class="span3">
                    <a href="${url}">${products.productName}</a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</div>
</body>
</html>
