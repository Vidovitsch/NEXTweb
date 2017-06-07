<%-- 
    Document   : landingPage
    Created on : 31-mei-2017, 20:21:34
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/css/home.css" var="homeCSS" />
        <link href="${homeCSS}" rel="stylesheet" />
        <title>Home</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="slideshow-container">
                <div class="mySlides fade">
                    <div class="numbertext">1 / 3</div>
                    <img src="/images/default_workshop.jpg" style="width:100%">
                    <div class="text">Caption Text</div>
                </div>
                <div class="mySlides fade">
                    <div class="numbertext">2 / 3</div>
                    <img src="/images/default_workshop.jpg" style="width:100%">
                    <div class="text">Caption Two</div>
                </div>
                <div class="mySlides fade">
                    <div class="numbertext">3 / 3</div>
                    <img src="/images/default_workshop.jpg" style="width:100%">
                    <div class="text">Caption Three</div>
                </div>
                <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                <a class="next" onclick="plusSlides(1)">&#10095;</a>
            </div>
            <br>
            <div style="text-align:center">
                <span class="dot" onclick="currentSlide(1)"></span> 
                <span class="dot" onclick="currentSlide(2)"></span> 
                <span class="dot" onclick="currentSlide(3)"></span> 
            </div>
        </div>
        
    </body>
</html>
