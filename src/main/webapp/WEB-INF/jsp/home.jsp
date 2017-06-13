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
                        <td>
                            <div class="content-block">
                                <div class="content-block-header" id="content-block-header1">
                                    <div id="megaphone-border">
                                        <img src="/images/megaphone.png" />
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="content-block">
                                <div class="content-block-header" id="content-block-header2">
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="content-block">
                                <div class="content-block-header" id="content-block-header3">
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
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
