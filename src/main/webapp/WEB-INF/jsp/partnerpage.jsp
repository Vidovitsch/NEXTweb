<%-- 
    Document   : partnerpage
    Created on : May 22, 2017, 9:56:50 AM
    Author     : Youri van der Ceelen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/partnerpage.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="leftpart">
                <div class="create-modify-event bordereddiv">
                    <h3> Create workshop</h3>
                    <c:forEach var="row" items="${fields}">
                        <div class="inputarea">
                            <span class="fieldspan">${row}:</span>
                            <input class="fieldInput" type="text" id="${row}" name="${row}" required></br>
                        </div>
                    </c:forEach>   
                    <div class="inputarea">
                        <span class="fieldspan">Description:</span><br>
                        <textarea class="textarea" id="descriptionworkshop" name="description"></textarea></br>
                    </div>
                    <div class="btnwrapper">
                        <br>
                        <input id="create-buttonworkshop" class="button_base b01_simple_rollover" type="submit" value="create workshop"/>  
                    </div>
                </div>
            </div>
            <div class="rightpart">
                <div id='workshops' class="bordereddiv">
                    <h3 id="selectedworkshop">workshops</h3>
                    <ol id="workshoplist" class="borderedlist" type="1">
                    </ol>
                </div>
                <div class="createPromotion bordereddiv">
                    <h3 id="promotionheader"> Create promotion</h3>
                    <div class="inputarea">
                        <span class="fieldspan">Image URL:</span><br>
                        <input class="fieldInput" type="text" id="imageurl" name="imageurl" required></br>
                    </div>
                    <div class="btnwrapper">
                        <br>
                        <input id="create-buttonpromotion" class="button_base b01_simple_rollover" type="submit" value="create promotion" onclick="createPromotion()"/>  
                    </div>
                </div>
            </div>
        </div>
        <script>
            
            var selectedWorkshop = null;
            
            function validateEventDateFields(eventName, startTime, endTime, date, locationName, description) {
                var re = /^0[0-9]|1[0-9]|2[0-3]:[0-5][0-9]$/;
                var timestamp = Date.parse(date);
                if (eventName === "") {
                    alert("EventName cannot be null");
                } else if (description === "") {
                    alert("Give the event a description");
                } else if (locationName === "") {
                    alert("provice the name of the location where the event takes place");
                } else if (startTime === "") {
                    alert("The startTime cannot be null");
                } else if (!re.test(startTime)) {
                    alert("The format of the startime should be hh:mm")
                } else if (endTime === "") {
                    alert("The endTime cannot be null");
                } else if (!re.test(endTime)) {
                    alert("The format of the endtime should be hh:mm");
                } else if (new Date("November 13, 2013 " + startTime) > new Date("November 13, 2013 " + endTime)) {
                    alert("The startTime should be before the EndTime");
                } else if (isNaN(timestamp)) {
                    alert("Invalid date");
                } else {
                    return true;
                }
                return false;
            }

            function validateEventFields(imageURL) {
                if (imageURL === "") {
                    alert("Assign an immage URL to the event that you want to create");
                } else {
                    return true;
                }
                return false;
            }
            
            function loadworkshops() {
                var elworkshop = document.getElementById('workshoplist');
                elworkshop.innerHTML = "";
                firebase.database().ref('/Event').once("value", function (snapshot) {
                    snapshot.forEach(function (childSnapshot) {
                        if (childSnapshot.val().EventType == ("Workshop"))
                        {
                             elworkshop.innerHTML += "<li id='" + childSnapshot.key + "' onclick='liWorkshopClicked(this)'>" + childSnapshot.val().EventName + "</li>";
                        }
                    });
                });
            }
            loadworkshops();
            
            function liWorkshopClicked(el) {
                selectedWorkshop = el.id;
                document.getElementById("promotionheader").innerHTML = "Create promotion for " + el.innerHTML;
            }
            
            function createPromotion() {
                var imageUrl = document.getElementById("imageurl").value;
                if (selectedWorkshop !== null)
                {
                    if (!imageUrl)
                    {
                        alert("Can't create promotion without image URL.");
                        return;
                    }
                    var newPromotionRef = firebase.database().ref('/Promotion').push({
                      imageUrl: imageUrl,
                      eventUid: selectedWorkshop
                    });
                    // Get the unique ID generated by push() by accessing its key
                    var announcementUID = newPromotionRef.key;
                    var promotion = {
                        URL: imageUrl,
                        UID: selectedWorkshop,
                        getlistitemhtml: function () {
                            return "<li id='"+this.uid+"' onclick='liPromotionClicked(this)'>" + this.text + "</li>";
                        }
                    };
                    selectedWorkshop = null;
                    document.getElementById("imageurl").value = "";
                    document.getElementById("promotionheader").innerHTML = "Create promotion";
                    alert("Promotion created!");
                }else{
                    alert("Please select a Workshop.");
                }
            }

            document.getElementById("create-buttonworkshop").onclick = function () {
                var eventName = document.getElementById("eventName").value;
                var startTime = document.getElementById("startTime").value;
                var endTime = document.getElementById("endTime").value;
                var date = document.getElementById("date").value;
                var locationName = document.getElementById("locationName").value;
                var imageURL = document.getElementById("imageURL").value;
                var presenter = document.getElementById("presenter").value;
                var maxUsers = document.getElementById("maxUsers").value;
                var description = document.getElementById("descriptionworkshop").value;
                if (validateEventDateFields(eventName, startTime, endTime, date, locationName, description)) {
                    if (validateEventFields(imageURL)) {
                        if (presenter === "") {
                            alert("enter the name of the one presenting the workshop.");
                        } else if (maxUsers === "") {
                            alert("enter the maximal amount of people that can attend the workshop");
                        } else if (!isFinite(String(maxUsers))) {
                            alert("enter a number in the maxUsers field");
                        } else {
                            alert("event is being created");
                            post('createWorkshop', {eventName: eventName, description: description, startTime: startTime, endTime: endTime, date: date, locationName: locationName, imageURL: imageURL, presenter: presenter, maxUsers: maxUsers});
                        }
                    }
                }
                loadworkshops();
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
