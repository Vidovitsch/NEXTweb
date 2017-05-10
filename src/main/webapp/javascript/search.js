/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var total = [];
var results = [];

function getStudents() {
    var database = firebase.database();
    
    var student;
    firebase.database().ref('/User').once("value", function (snapshot) {
        snapshot.forEach(function (childSnapshot) {
            var vname = childSnapshot.val().Name;
            var vlastname = childSnapshot.val().Lastname;
            var vgroupid = childSnapshot.val().GroupID;
            var vmail = childSnapshot.val().Mail;
            if (vlastname !== undefined)
            {
                student = {
                    name: vname,
                    lastname: vlastname,
                    groupid: vgroupid,
                    mail: vmail
                };
                total.push(student);
            }
        });
    });
}
getStudents();

function fill(mail) {
    document.getElementById('searchText').value = mail;
    search();
}


function search() {
    results = [];
    var input = document.getElementById('searchText').value;
    for (i=0; i <total.length; i++)
    {
        var totalname = total[i].name.toString() + total[i].lastname.toString();
        if ((input.length >= 3 && totalname.toUpperCase().indexOf(input.toUpperCase()) >= 0) || input == total[i].groupid)
        {
            results.push(total[i]);
        }
    }
    document.getElementById("tbsearchresults").innerHTML ="";
    for (i=0; i <results.length; i++)
    {   
        document.getElementById("tbsearchresults").innerHTML += "<tr class=\"trsearch\" onclick=\"fill('"+results[i].mail+"')\"><td>" + results[i].mail + "</td></tr>";
    }
    if (document.getElementById("tbsearchresults").innerHTML != "")
    {
        document.getElementById("tbsearchresults").style.display = "table";
    }
    else
    {
        document.getElementById("tbsearchresults").style.display = "none";
    }
}