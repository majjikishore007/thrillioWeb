<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
	<meta charset="utf-8">

	<link rel="stylesheet" href="<%=request.getContextPath()%>/nav.css">
	<script src="https://kit.fontawesome.com/a076d05399.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<nav>
	<div class="logo">thrill.io</div>
	<input type="checkbox" id="click">
	<label for="click" class="menu-btn">
		<i class="fas fa-bars"></i>
	</label>
	<ul>
		<li><a href="<%=request.getContextPath()%>/bookmark" >Browse</a></li>
		<li><a href="<%=request.getContextPath()%>/auth/logout" >Logout</a></li>
		<li><a href="#">about</a></li>
	</ul>
</nav>

<br><br>
<section class="section">

	<c:choose>
		<c:when test="${!empty(books)}">
			<table>

				<c:forEach var = "book" items="${books}">
					<tr>
						<td>
							<img src="${book.imageUrl}" width="175" height="200">
						</td>

						<td style="color:gray;">
							By <span style="color: #B13100;">${book.authors[0]}</span>
							<br><br>
							Rating: <span style="color: #B13100;">${book.amazonRating}</span>
							<br><br>
							Publication Year: <span style="color: #B13100;">${book.publicationYear}</span>

							<a href = "<%=request.getContextPath()%>/bookmark/remove?bid=${book.id}" style="font-size:18px;color:#0058A6;font-weight:bold;text-decoration:none">remove</a>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>

				</c:forEach>

			</table>
		</c:when>
		<c:otherwise>
			<br><br>
			<span style="font-size: 24px;color: #333333;">Empty</span>
		</c:otherwise>
	</c:choose>


</section>

</body>
<footer class="footer">
	<p class="cpoyright">
		2020 &#169 thrill.io <br> <span>contact info <a
			href="email">majjikishore031@gmail.com</a></span> contact 9573800396<span> studying at ITER</span>

	</p>
</footer>
</html>