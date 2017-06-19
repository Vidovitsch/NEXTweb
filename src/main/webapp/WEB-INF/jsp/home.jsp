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
                                    Announcements
                                </div>
                                <div class="content-block-content" id="content-block-content1">
                                    <div id="nav-recent" class="content-block-content1-navigation">
                                        <span id="recent" class="noselect" onclick="loadRecentAnnouncements();">Recent</span>
                                    </div>
                                    <div id="announcement-list"></div>
                                    <div id="nav-older" class="content-block-content1-navigation">
                                        <span id="older" class="noselect" onclick="loadOlderAnnouncements();">&or;</span>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td class="content-tabledata">
                            <div class="content-block">
                                <div class="content-block-header" id="content-block-header2">
                                    Got any ideas?
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
            <div id="footer"></div>
        </div>
        
        <script>
            var database = firebase.database();
            
            // ******* Promotions ******* //
            
            var slideIndex = 0;
            showSlides();
            
            // Automatic slide change
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
            
            // ******* Announcements ******* //
            
            // Fields needed for announcement selection change
            var announcementPageIndex = 0;
            var maxAnnouncementPages = 0;
            var announcements = new Array();
            fillAnnouncementList();
            loadRecentAnnouncements();
            announcementCreationListener();
            
            // Load 3 older announcements into a html element
            function loadOlderAnnouncements() {
                document.getElementById("announcement-list").innerHTML = '';
                announcementPageIndex++;
                
                // Check if it is the last page. If so, no new page will be loaded
                if (announcementPageIndex === maxAnnouncementPages) {
                    announcementPageIndex = maxAnnouncementPages - 1;
                }
                
                // Hide the 'older'-arrow on the last page
                if (announcementPageIndex + 1 === maxAnnouncementPages) {
                    changeArrowVisibility(false);
                }
                
                // Select the 3 announcements according to the page index
                for (i = announcementPageIndex * 3; i < announcementPageIndex * 3 + 3; i++) {
                    var announcementContent = announcements[i];
                    if (announcementContent !== undefined) {
                        document.getElementById("announcement-list").innerHTML += 
                            '<div class="announcement">' + announcementContent + '</div><hr>';
                    }
                }
            }
            
            // Load the 3 most recesnt announcement into a html element
            function loadRecentAnnouncements() {
                document.getElementById("announcement-list").innerHTML = '';
                announcementPageIndex = 0;
                
                // Always set the 'older'-arrow visible
                changeArrowVisibility(true);
                
                // Select the 3 announcements at the beginning of the list
                for (i = 0; i < 3; i++) {
                    var announcementContent = announcements[i];
                    if (announcementContent !== undefined) {
                        document.getElementById("announcement-list").innerHTML += 
                            '<div class="announcement">' + announcementContent + '</div><hr>';
                    }
                }
            }
            
            // Fill announcement lists (html and variables)
            function fillAnnouncementList() {
                var count = 0;
                <c:forEach var="announcement" items="${announcements}">
                    announcements.push('${announcement.content}');
                    count++;
                    
                    // Keep track of the amount of possible pages
                    if (count === 3) {
                        count = 0;
                        maxAnnouncementPages++;
                    }
                </c:forEach>
                    
                // Add last incomplete page
                if (count > 0) {
                    maxAnnouncementPages++;
                }
            }
            
            // Change the 'older'-arrow from visible to hidden and vice versa
            function changeArrowVisibility(visible) {
                var element = document.getElementById("nav-older");
                if (visible) {
                    element.style.visibility = 'visible';
                } else {
                    element.style.visibility = 'hidden';
                }
            }
            
            // Listens to the creation of announcements in firebase
            function announcementCreationListener() {
                var path = "Announcement";
                alert('begin listener');
                database.ref(path).on("child_added", function(snapshot) {
                    var content = snapshot.val().Text;
                    
                    // Check if announcement already exists. Refresh updated list.
                    // (prevent duplication of date while initializing this page)
                    if (announcements.indexOf(content) === -1) {
                        announcements.unshift(content);
                        loadRecentAnnouncements();
                    }
                });
            }
        </script>
    </body>
</html>
