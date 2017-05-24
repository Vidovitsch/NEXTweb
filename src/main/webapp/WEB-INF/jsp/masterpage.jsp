<%-- 
    Document   : masterpage
    Created on : Mar 20, 2017, 10:27:23 AM
    Author     : mmjan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fn" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html> 
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <spring:url value="/css/containerMaster.css" var="containerCSS" />
            <link href="${containerCSS}" rel="stylesheet" />
            <!--ddd-->
            <decorator:head />

            <title>
                <decorator:title />
            </title>

        </head>
        <body>
            <div id="next-header">
                <a href="index.htm"><img id="nav-logo" src="/images/next_logo.png" /></a>
                <ul class="nav-list">
                    <li class="nav-item"><a href="/contact.htm">Contact</a></li>
                    <li class="nav-item"><a href="/events.htm">Events</a></li>
                    <li class="nav-item"><a href="/group.htm">Group</a></li>
                    <li class="nav-item"><a href="/map.htm">Map</a></li>
                    <li class="nav-item"><a href="/schedule.htm">Schedule</a></li>
                </ul>
            </div>
            <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-app.js"></script>
            <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-auth.js"></script>
            <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-database.js"></script>
            <script src="https://www.gstatic.com/firebasejs/3.7.2/firebase.js"></script>
            <script src="/javascript/inifirebase.js"></script>
            <script>
                var user = firebase.auth().currentUser;
                firebase.auth().onAuthStateChanged(function (user) {
                    if (user) {
                        // User is signed in.
                    } else {
                        // No user is signed in.
                        post("login.htm", null, "get");
                    }
                });
                function post(path, params, method) {
                    method = method || "post"; // Set method to post by default if not specified.

                    // The rest of this code assumes you are not using a library.
                    // It can be made less wordy if you use one.
                    var form = document.createElement("form");
                    form.setAttribute("method", method);
                    form.setAttribute("action", path);
                    for (var key in params) {
                        if (params.hasOwnProperty(key)) {
                            var hiddenField = document.createElement("input");
                            hiddenField.setAttribute("type", "hidden");
                            hiddenField.setAttribute("name", key);
                            hiddenField.setAttribute("value", params[key]);
                            form.appendChild(hiddenField);
                        }
                    }
                    document.body.appendChild(form);
                    form.submit();
                }
            </script>
            <decorator:body />
        </body>
    </html>