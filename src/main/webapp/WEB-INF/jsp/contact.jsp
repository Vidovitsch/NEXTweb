<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%! int fontSize;%> 
<%! int maxfontSize = 3;%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
    <head>
        <spring:url value="/css/index.css" var="mainCSS" />
        <style>
            #map {
                height: 600px;
                width: 80%;
            }
        </style>
        <link href="${mainCSS}" rel="stylesheet" />
    </head>
    <body>
        <div class="wrapper">
            <div class="headline">Contactgegevens</div>
            <h3>Naam locatie: Klokgebouw Eindhoven,<br>Adres: Klokgebouw 50 <br>Postcode: 5617 AB<br>Plaats: Eindhoven</h3>
            <h2>Openingstijden pand:</h2>
            <h3>Maandag: 8:00 - 18:00<br>Dinsdag: 8:00 - 18:00<br>Woensdag: 8:00 - 18:00<br>Donderdag: 8:00 - 18:00<br>Vrijdag: 8:00 - 18:00</h3>
            <h2>Organisatie:</h2>
            <h3>Mail: <a href="#">info@next.nl</a><br>Vertrouwenspersoon: Carli Kleijnen</h3>
            <div id="map"></div>
            <script>
                function initMap() {
                    var uluru = {lat: 51.4469572, lng: 5.4572333};
                    var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 11,
                        center: uluru
                    });
                    var marker = new google.maps.Marker({
                        position: uluru,
                        map: map
                    });
                }
            </script>
            <script async defer
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDOf6oDhz7Wca5BudI7aSEdBF9JB8bVRDw&callback=initMap">
            </script>
            <br>
            <script>
                var user = firebase.auth().currentUser;
                firebase.auth().onAuthStateChanged(function (user) {
                    if (user) {
                        // User is signed in.il;
                    } else {
                        // No user is signed in.
                        //post("login.htm", null, "get"); 
                    }
                });
                document.getElementById("logout-button").onclick = function () {
                    firebase.auth().signOut().then(function () {
                        post("login.htm", null, "get");
                    }).catch(function (error) {
                        // An error happened.
                    });
                }

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
    </body>
</html>
