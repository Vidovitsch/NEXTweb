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
        <link href="${mainCSS}" rel="stylesheet" />
        <spring:url value="/css/workshops.css" var="workshopsCSS" />

        <link href="${containerCSS}" rel="stylesheet" />
        <link href="${workshopsCSS}" rel="stylesheet" />
    </head>
    <body>
        <div class="wrapper">
            <div class="headline">Assignments</div>
            <br>
            <ol id="assignmentlist" class="borderedlist" type="1">
            </ol>
            <script>
                function isEmpty(str) {
                    return (!str || 0 === str.length);
                }
                var message = '${message}';
                if (!isEmpty(message))
                {
                    alert(message);
                }
                var updatedrecords = 0;
                var assigmenthtml;
                var assignmentname;
                assignmentlist = [];
                document.getElementById('assignmentlist').innerHTML = "";
                firebase.database().ref('/Assignment').once("value", function (snapshot) {
                    snapshot.forEach(function (childSnapshot) {
                        assignmentname = childSnapshot.key;
                        assignmentdsc = childSnapshot.val().Description;1
                        assignment = {
                            name: assignmentname,
                            description: assignmentdsc}
                        assignmentlist.push(assignment);
                        updatedrecords++;
                        assigmenthtml = "<li id='" + assignmentname + "'><a href='#' onclick='showPopUp(" + '"' + assignmentname + '","' + assignmentdsc + '")' + "'" + "style='display : inline-block; width: 300px'>" + assignmentname + "</a></li>";
                        document.getElementById('assignmentlist').innerHTML += assigmenthtml;
                    });
                });

                function showPopUp(name, description) {
                    document.getElementById(name).innerHTML +=
                            '<div id="event" class="overlay"> \
                                <div class="popup"> \
                                        <div id="popup-wrapper-assignment"> \
                                            <span id="popup-assignment-name"><b style="color:black; font-size:36px">' + name.toString() + '</b></span></br> \
                                            <span class="close" onclick="removePopUp(); return false">Close</span> \
                                            <div class="content"> \
                                                <p style="color:black; font-size:20px">' + description.toString() + '</p> \
                                            </div> \
                                            <div class="button">\
                                            <p style="color:black">Google Drive Link: <input type="text" id="assignmentinput">\
                                            <button type="submit" onclick="submitAssignment(' + "'" + name.toString() + "')" + '">Submit</button>\
                                            </div>\
                                        </div> \
                                </div> \
                            </div>';
                }
                ;

                function removePopUp() {
                    var popup = document.getElementById("event");
                    popup.parentNode.removeChild(popup);
                }

                function submitAssignment(name)
                {
                    var uid = '${userUID}';
                    var link = document.getElementById("assignmentinput").value.toString();
                    var email = document.cookie;
                    email = email.replace("username=", "");
                    post("/submitassignment", {uid: uid, assignmentlink: link, name: name, email: email});
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
