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
                            <div id="content-block1" class="content-block">
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
                            <div id="content-block2" class="content-block">
                                <div class="content-block-header" id="content-block-header2"></div>
                                <div class="content-block-content" id="content-block-content2"></div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="footer"></div>
        </div>
        
        <script>
            var database = firebase.database();
            var uid = '${uid}';
            var submitted = ${submitted};
            
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
            
            // ******* Poll ******* //
            
            var ideas = new Array();
            fillIdeaList();
            setPoll();
            
            function fillIdeaList() {
                <c:forEach var="idea" items="${poll.ideas}">
                    var idea = new Array();
                    idea.push('${idea.ideaId}');
                    
                    // search for quotes in content, if so, quote gets temporary replaced
                    var content = '${idea.content}';
                    if (content.indexOf('\'') !== -1) {
                        content = content.replace('\'', '%%%');
                    } else if (content.indexOf('"') !== -1) {
                        content = content.replace('"', '%%$');
                    }
                    
                    idea.push(content);
                    idea.push('${idea.votes}');
                    ideas.push(idea);
                </c:forEach>
            }
            
            function setPoll() {
                var phase = ${poll.phase};
                if (submitted === true) {
                    submittedElement();
                } else if (phase === 0) {
                    // Set the header of the poll content block
                    document.getElementById("content-block-header2").innerHTML += '${poll.header}';
                    phase0Element();
                } else if (phase === 1) {
                    // Set the header of the poll content block
                    document.getElementById("content-block-header2").innerHTML += '${poll.header}';
                    phase1Element();
                } else {
                    // Set the header of the poll content block
                    document.getElementById("content-block-header2").innerHTML += '${poll.header}';
                    phase2Element();
                }
            }
            
            // Set html elements corresponding to phase 0 of the poll
            function phase0Element() {
                document.getElementById("content-block-content2").innerHTML = '';
                document.getElementById("content-block-content2").innerHTML += 
                    '<div id="idea-info">Note: you can only submit once</div>' +
                    '<textarea placeholder="Enter idea here..." id="idea-input"></textarea>' + 
                    '<input id="idea-submit-button" type="submit" onclick="submitIdea();" value="Submit" />';
            }
            
            // Set html elements corresponding to phase 1 of the poll
            function phase1Element() {
                document.getElementById("content-block-content2").innerHTML = '';
                for (var i = 0; i < 6; i++) {
                    // Format the database data to the visual data
                    var formatted = formatIdea(ideas[i][1], 50);
                    var content = formatted.replace('%%%', "\'");
                    content = content.replace('%%$', '"');
                    
                    document.getElementById("content-block-content2").innerHTML +=
                        '<div id="idea-votable"><span id="idea-content" onclick="showPopUp(&quot;' + ideas[i][1] + '&quot;);">' + (i + 1).toString() + '. ' + content + '</span>\n\
                        <div id="idea-vote" onclick="voteForIdea(&quot;' + ideas[i][0] + '&quot;);">Vote</div></div><br>';
                }
            }
            
            // Set html elements corresponding to phase 2 of the poll
            function phase2Element() {
                document.getElementById("content-block-content2").innerHTML = '';
                for (var i = 0; i < 6; i++) {
                    // Format the database data to the visual data
                    var formatted = formatIdea(ideas[i][1], 50);
                    var content = formatted.replace('%%%', "\'");
                    content = content.replace('%%$', '"');
                    
                    document.getElementById("content-block-content2").innerHTML +=
                        '<div id="idea-votable"><span id="idea-content" onclick="showPopUp(&quot;' + ideas[i][1] + '&quot;);">' + (i + 1).toString() + '. ' + content + '</span>\n\
                        <div id="idea-vote-result">' + ideas[i][2] + ' votes</div></div><br>';
                }
            }
            
            // Set html elements when the user has already submitted an idea or a vote
            function submittedElement() {
                document.getElementById("content-block-header2").innerHTML += '';
                document.getElementById("content-block-content2").innerHTML = '';
                document.getElementById("content-block-header2").innerHTML += 'NEXT';
                document.getElementById("content-block-content2").innerHTML += 
                '<div id="iframe-wrapper">\n\
                    <iframe width="550" height="235" src="https://www.youtube.com/embed/hCFGCrE3k60?autoplay=1" frameborder="0" allowfullscreen></iframe>\n\
                </div>';
            }
            
            function voteForIdea(ideaId) {
                if (confirm('Are you sure you want to vote for this idea?')) {
                    firebase.database().ref("Poll/Ideas/"+ideaId+"/Votes").once("value", function(snapshot) {
                        var votes = snapshot.val();
                        votes++;
                        firebase.database().ref("Poll/Ideas/"+ideaId).update({ Votes: votes });
                    });

                    setSubmitted(uid);
                } else {
                    // Do nothing!
                }
            }
            
            // Submit the new idea the current user has created
            function submitIdea() {
                if (confirm('Are you sure you want to submit this idea?')) {
                    var input = document.getElementById("idea-input").value;
                    
                    // Replace unreadable characters with readable characters
                    input = input.replace('/n', ';');
                    input = input.replace(/[']/g, "%%%");
                    input = input.replace(/["]/g, "%%$");
                    
                    if (input !== '') {
                        var postData = {
                            Content: input,
                            Votes: 0
                        };

                        // Create new random key
                        var newKey = firebase.database().ref().child('Poll/Ideas').push().key;

                        // Submit the data
                        var updates = {};
                        updates['/Poll/Ideas/' + newKey] = postData;
                        firebase.database().ref().update(updates);

                        setSubmitted(uid);
                    }
                } else {
                    // Do nothing!
                }
            }
            
            function setSubmitted(uid) {
                firebase.database().ref('User/' + uid).update({ Submitted: 1 });
                submittedElement();
            }
            
            function formatIdea(content, maxLength) {
                var formatted;
                if (content.length > maxLength) {
                    formatted = content.substring(0, maxLength) + '...';
                    return formatted;
                } else {
                    var index = content.indexOf(';');
                    if (index > -1) {
                        formatted = content.substring(0, index) + '...';
                        return formatted;  
                    }
                    return content;
                }
            }
            
            function showPopUp(content) {
                content = content.replace(';', '<br>');
                document.getElementById('content').innerHTML += 
                    '<div id="popup-content" class="overlay"> \
                        <div class="popup"> \
                            <div id="popup-wrapper"> \
                                <span class="close" onclick="removePopUp(); return false">Close</span><br><br> \
                                <div class="content"> \
                                    ' + content + ' \
                                </div> \
                            </div> \
                        </div> \
                    </div>'; 
            }
            
            function removePopUp() {
                var popup = document.getElementById("popup-content");
                popup.parentNode.removeChild(popup);
            }
        </script>
    </body>
</html>
