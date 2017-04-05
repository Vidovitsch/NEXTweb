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
        <spring:url value="/css/container.css" var="containerCSS" />
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
                            <input class="form-control" type="text" id="email" name="email" required value="i336483@student.fontys.nl"></br>
                        </div>
                        <div class="form-section">
                            <span>Password:</span> </br>
                            <input class="form-control" id="password" type="password" name="password" required value="qwer1234"></br>
                            <input id="forgotpassword-button" class="form-control" type="button" value="Forgot password" />
                            <div id="passwordwrapper">
                                <input id="login-button" class="form-control" type="submit" value="Login" />
                                <input id="register-button" class="form-control" type="submit" value="Register" />
                            </div>
                            </br>
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
        <script>
            // Initialize Firebase
            var config = {
                apiKey: "AIzaSyCRi0Ma5ekQxhwg-BfQCa6684hMzvR3Z1o",
                authDomain: "nextweek-b9a58.firebaseapp.com",
                databaseURL: "https://nextweek-b9a58.firebaseio.com",
                storageBucket: "nextweek-b9a58.appspot.com",
                messagingSenderId: "488624254338"
            };
            firebase.initializeApp(config);
        </script>
        <script>
            firebase.auth().signOut().then(function () {
                // Sign-out successful.
            }).catch(function (error) {
                // An error happened.
            });

            var password;
            var email;
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
                            post("loggedin", {currentemail: user.email});
							document.cookie = "username="+user.email;
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

            document.getElementById("forgotpassword-button").onclick = function () {
                email = document.getElementById("email");
                var emailAddress = document.getElementsByName("email")[0].value;
                if (email.checkValidity() === true)
                {
                    if (validateEmail(emailAddress))
                    {
                        var auth = firebase.auth();
                        auth.sendPasswordResetEmail(emailAddress).then(function () {
                            alert("Mail verstuurd");
                        }, function (error) {
                            if (errorMessage === "A network error (such as timeout, interrupted connection or unreachable host) has occurred.")
                            {

                            } else
                            {
                                alert(errorMessage);
                            }
                        });
                    } else
                    {
                        email.setCustomValidity("This is not a valid email address");
                        email.reportValidity();
                    }

                } else {
                    email.setCustomValidity("Email cant be empty");
                    email.reportValidity();
                }
            }

            function validateEmail(email) {
                var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(email);
            }
            document.getElementById("register-button").onclick = function () {
                password = document.getElementById("password");
                email = document.getElementById("email");
                if (email.checkValidity() === true && password.checkValidity() === true)
                {
                    var confirmcontrols = '<span>Confirm password:</span> </br><input class="form-control" id="confirmpassword" type="password" name="confirmpassword" required> </br>\
                                    <input id="confirm-button" class="form-control" type="submit" value="Confirm" />';
                    document.getElementById('passwordwrapper').innerHTML = confirmcontrols;
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
                                if (errorMessage === "A network error (such as timeout, interrupted connection or unreachable host) has occurred.")
                                {

                                } else
                                {
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
                } else
                {
                    password.reportValidity();
                    email.reportValidity();
                }
            };

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

