/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-auth.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-database.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.2/firebase.js"></script>

// Initialize Firebase
var config = {
    apiKey: "AIzaSyCRi0Ma5ekQxhwg-BfQCa6684hMzvR3Z1o",
    authDomain: "nextweek-b9a58.firebaseapp.com",
    databaseURL: "https://nextweek-b9a58.firebaseio.com",
    storageBucket: "nextweek-b9a58.appspot.com",
    messagingSenderId: "488624254338"
};
firebase.initializeApp(config);

var postMessage = function(uid) {
    console.log("Im here!");
    var content = document.getElementById("txtareamsg").value;
    var dateTime = getCurrentDateTime();

    writeToFirebase(dateTime, content, uid);
    document.getElementById("txtaremsg").value = '';
};

function getCurrentDateTime() {
    var currentDate = new Date();
    var year = currentDate.getYear().toString().substr(1, 2);
    var dateTime = currentDate.getDay() + "-" +
            currentDate.getMonth() + "-" +
            year + ":" +
            currentDate.getHours() + ":" +
            currentDate.getMinutes() + ":" +
            currentDate.getSeconds() + ":" +
            currentDate.getMilliseconds();
    return dateTime;
};

function writeToFirebase(dateTime, content, uid) {
    var groupID = getGroupID(uid);
    firebase.database().ref('Group/' + groupID + '/Messages/' + dateTime).set({
        Content: content,
        UID: uid
    });
};

function getGroupID(uid) {
    return 0; 
};
