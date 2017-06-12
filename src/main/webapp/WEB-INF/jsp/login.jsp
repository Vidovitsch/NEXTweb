<%-- 
    Document   : login.jsp
    Created on : 15-mrt-2017, 15:20:29
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/css/container.css" var="ßainerCSS" />
        <spring:url value="/css/login.css" var="loginCSS" />

        <link href="${containerCSS}" rel="stylesheet" />
        <link href="${loginCSS}" rel="stylesheet" />

    </head>

    <body>
        <div class="wrapper">
            <div class="login-page">
                <div id="login-form">
                    <h2>Login</h2>
                    <form>
                        <div class="form-section">
                            <span>E-mail:</span> </br>
                            <input class="form-control" type="text" id="email" name="email" required value="t.berkvens@student.fontys.nl"></br>
                        </div>
                        <div class="form-section" id="passwordstuff">
                            <span>Password:</span> </br>
                            <input class="form-control" id="password" type="password" name="password" required value="qwer1234"></br>
                            <div id="passwordwrapper">
                                <input id="login-button" class="form-control" type="submit" value="Login" />
                                <input id="register-button" class="form-control" type="submit" value="Register" />
                                <div style="color:white">forgot your password? <a href="javascript:forgotpassword()" style="color:white">click here</a></div>
                                </br>
                            </div>
                        </div>
                    </form>
                </div>
                <div id="login-image">
                    <img src="/images/next_wallpaper1.jpg" />
                </div>
            </div>
        </div>

        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-auth.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-database.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.2/firebase.js"></script>
        <script src="/javascript/initfirebase.js"></script>
        <script>
            var originalcontrols = '<span>Password:</span> </br><input class = "form-control" id = "password" type = "password" name = "password" required value = "qwer1234" > \n\
                                                    < /br><div id = "passwordwrapper" ><input id = "login-button" class = "form-control" type = "submit" value = "Login" / >\n\
                                                    <input id = "register-button" class = "form-control" type = "submit" value = "Register" / ><\n\
                                                    <div style = "color:white" > forgot your password? <a href = "javascript:forgotpassword()" style = "color:white" > click here </a>\n\
                                                    </div></br></div>';
            var confirmcontrols = '<span>Confirm password:</span> </br><input class="form-control" id="confirmpassword" type="password" name="confirmpassword" required> </br>\
                                                <input id="confirm-button" class="form-control" type="submit" value="Confirm" />\n\
                                                <input id="backtologin-button" class="form-control" type="submit" value="Go back to login" />';
            // Get a reference to the database service
            var database = firebase.database();
            firebase.auth().signOut().then(function () {
                // Sign-out successful.
            }).catch(function (error) {
                // An error happened.
            });
            var password;
            var email;
            function setloginandregisterlistener() {
                document.getElementById("login-button").onclick = function () {
                    password = document.getElementById("password");
                    email = document.getElementById("email");
                    if (password.checkValidity() === true && email.checkValidity() === true)
                    {
                        firebase.auth().signOut().then(function () {
                            // Sign-out successful.
                        }).catch(function (error) {
                            // An error happened.
                        });
                        var emailvalue = document.getElementsByName("email")[0].value;
                        var passwordvalue = document.getElementsByName("password")[0].value;
                        firebase.auth().onAuthStateChanged(function (user) {
                            if (user) {
                                document.cookie = "username=" + user.email;
                                if (user.email.indexOf("student.fontys.nl") == -1)
                                {
                                    window.location.href = "/pie/createworkshop.htm";
                                } else {
                                    post("loggedin", {currentemail: user.email});
                                }
                            }
                        });
                        firebase.auth().signInWithEmailAndPassword(emailvalue, passwordvalue).catch(function (error) {
                            // Handle Errors here.
                            var errorCode = error.code;
                            var errorMessage = error.message;
                            if (errorMessage === "A network error (such as timeout, interrupted connection or unreachable host) has occurred.")
                            {

                            } else
                            {
                                alert(errorMessage);
                            }
                        });
                        return false;
                    } else {
                        password.reportValidity();
                        email.reportValidity();
                    }
                };
                document.getElementById("register-button").onclick = function () {
                    password = document.getElementById("password");
                    email = document.getElementById("email");
                    if (!checkEmailValid(email.value)) {
                        return;
                    }
                    if (email.checkValidity() === true && password.checkValidity() === true) {
                        /*if (!validateEmail(email.value))
                         {
                         return
                         }*/
                        document.getElementById('passwordwrapper').innerHTML = confirmcontrols;
                        document.getElementById("backtologin-button").onclick = function () {
                            document.getElementById('passwordstuff').innerHTML = originalcontrols;
                            setloginandregisterlistener();
                        };
                        var confirm_password = document.getElementById("confirmpassword");
                        function validatePassword() {
                            if (password.value != confirm_password.value) {
                                confirm_password.setCustomValidity("Passwords Don't Match");
                            } else {
                                confirm_password.setCustomValidity('');
                            }
                        }
                        password.onchange = validatePassword;
                        confirm_password.onkeyup = validatePassword;
                        document.getElementById('confirm-button').onclick = function () {
                            var emailvalue = document.getElementsByName("email")[0].value;
                            var passwordvalue = document.getElementsByName("password")[0].value;
                            if (!checkEmailValid(emailvalue)) {
                                return;
                            }
                            if (password.checkValidity() === true && email.checkValidity() === true && confirm_password.checkValidity() === true) {
                                firebase.auth().onAuthStateChanged(function (user) {
                                    if (user) {
                                        post('loggedin', {currentemail: user.email});
                                    }
                                });
                                firebase.auth().createUserWithEmailAndPassword(emailvalue, passwordvalue).catch(function (error) {
                                    // Handle Errors here.
                                    var errorCode = error.code;
                                    var errorMessage = error.message;
                                    if (errorMessage === "A network error (such as timeout, interrupted connection or unreachable host) has occurred.") {

                                    } else {
                                        alert(errorMessage);
                                    }
                                });
                            } else {
                                confirm_password.reportValidity();
                                password.reportValidity();
                                email.reportValidity();
                            }
                            return false;
                        };
                    } else {
                        password.reportValidity();
                        email.reportValidity();
                    }
                };
            }

            setloginandregisterlistener();

            function checkEmailValid(email) {
                var myBool = email.indexOf('student.fontys.nl');
                if (myBool > 0)
                {
                    return true;
                }

                alert('Please enter a valid email for domain: student.fontys.nl')
                return false;
            }

            function forgotpassword() {
                var forgotpasswordcontrols = '<input id="forgotpassword-button" class="form-control" type="button" value="Reset password" />\n\
                                                <input id="backtologin-button" class="form-control" type="submit" value="Go back to login" />';
                document.getElementById('passwordstuff').innerHTML = forgotpasswordcontrols;
                document.getElementById("backtologin-button").onclick = function () {
                    document.getElementById('passwordstuff').innerHTML = originalcontrols;
                    setloginandregisterlistener();
                };
                document.getElementById("forgotpassword-button").onclick = function () {
                    email = document.getElementById("email");
                    var emailAddress = document.getElementsByName("email")[0].value;
                    if (email.checkValidity() === true) {
                        if (validateEmail(emailAddress)) {
                            var auth = firebase.auth();
                            var uid;
                            var lastpasswordreset;
                            try {
                                firebase.database().ref('/User').once("value", function (snapshot) {
                                    snapshot.forEach(function (childSnapshot) {
                                        var mail = childSnapshot.val().Mail;
                                        if (mail === emailAddress) {
                                            uid = childSnapshot.key;
                                            firebase.database().ref('/passwordrequest/').once('value').then(function (snapshot) {
                                                if (snapshot.hasChild(uid)) {
                                                    var childsnapshot = snapshot.child(uid);
                                                    var lastpasswordreset = childsnapshot.val().lastResetRequest;
                                                    var resetdate = new Date(lastpasswordreset);
                                                    var comparedate = new Date();
                                                    comparedate.setHours(comparedate.getHours() - 2);
                                                    if (comparedate.getTime() > resetdate) {
                                                        auth.sendPasswordResetEmail(emailAddress).then(function () {
                                                            firebase.database().ref('passwordrequest/' + uid).set({
                                                                lastResetRequest: new Date().getTime()
                                                            });
                                                            alert("Mail verstuurd");
                                                        }, function (error) {
                                                            var errorMessage = error.message;
                                                            if (errorMessage === "A network error (such as timeout, interrupted connection or unreachable host) has occurred.") {
                                                            } else {
                                                                alert(errorMessage);
                                                            }
                                                        });
                                                    } else {
                                                        alert("De laatste reset voor " + emailAddress + " is recentelijk al gedaan\nprobeer het later nog eens");
                                                    }
                                                } else {
                                                    auth.sendPasswordResetEmail(emailAddress).then(function () {
                                                        firebase.database().ref('passwordrequest/' + uid).set({
                                                            lastResetRequest: new Date().getTime()
                                                        });
                                                        alert("Mail verstuurd! Bekijk je inbox om een nieuw wachtwoord op te geven :)");
                                                    }, function (error) {
                                                        var errorMessage = error.message;
                                                        if (errorMessage === "A network error (such as timeout, interrupted connection or unreachable host) has occurred.") {
                                                        } else {
                                                            alert(errorMessage);
                                                        }
                                                    });
                                                }
                                            });
                                            throw new Error("found user");
                                        }
                                    });
                                });
                            } catch (err) {
                            }
                        } else {
                            email.setCustomValidity("This is not a valid email address");
                            email.reportValidity();
                        }
                    } else {
                        email.setCustomValidity("Email cant be empty");
                        email.reportValidity();
                    }
                };
            }

            function validateEmail(email) {
                var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                /*var domain = re.substring(re.indexOf("@"), re.length-1);
                 if (domain.indexOf("fontys" < 0))
                 {
                 return false;
                 }(*/
                return re.test(email);
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

