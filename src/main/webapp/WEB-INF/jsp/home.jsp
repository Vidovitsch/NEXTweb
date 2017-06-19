<%-- 
    Document   : landingPage
    Created on : 31-mei-2017, 20:21:34
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <br><br><br>
            <div id="header">
                <div class="slideshow-container">
                    <div class="mySlides fade">
                        <img src="/images/default_workshop.jpg" style="width:100%">
                    </div>
                    <div class="mySlides fade">
                        <img src="/images/map.jpg" style="width:100%">
                    </div>
                    <div class="mySlides fade">
                        <img src="/images/default_workshop.jpg" style="width:100%">
                    </div>
                </div>
                <div class="header-container">
                    <h1>Welcome to NEXT</h1>
                    <div class="header-buttons">
                        <div class="button_base b03_skewed_slide_in">
                            <div>Go to Events</div>
                            <div></div>
                            <div>Go to Events</div>
                        </div>
                        <br><br>
                        <div class="button_base b03_skewed_slide_in">
                            <div>Go to Map</div>
                            <div></div>
                            <div>Go to Map</div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div id="content">
                <table>
                    <tr>
                        <td class="content-tabledata">
                            <div class="content-block">
                                <div class="content-block-header" id="content-block-header1">
                                    Announcement
                                </div>
                                <div class="content-block-content" id="content-block-content1">
                                    <c:forEach var="ann" items="${announcements}">
                                        <div class="announcement">${ann.content}</div><hr>
                                    </c:forEach>
                                </div>
                            </div>
                        </td>
                        <td class="content-tabledata">
                            <div class="content-block">
                                <div class="content-block-header" id="content-block-header2">
                                    Go any ideas?
                                </div>
                                <div class="content-block-content" id="content-block-content2">
                                    <p>
                                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.
                                        Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.
                                        Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.
                                    </p>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="footer">
            </div>
        </div>
        
        <script>
            //Image Slideshow
            var slideIndex = 0;
            showSlides();

            //Automatic slide change
            function showSlides() {
                var i;
                var slides = document.getElementsByClassName("mySlides");
                for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none"; 
                }
                slideIndex++;
                if (slideIndex > slides.length) { 
                    slideIndex = 1;
                } 
                slides[slideIndex - 1].style.display = "block"; 
                setTimeout(showSlides, 6000); // Change image every 5 seconds
            }
        </script>
    </body>
</html>
