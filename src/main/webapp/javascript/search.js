/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var groups = [];
var users = [];

var tempGroup, tempUser;

var groupResults = [];
var studentResults = [];

/*function fill(mail) {
    document.getElementById('searchText').value = mail;
    search();
}*/

/**
 * This method searches for Users and Groups.
 * All the results will be shown in a result list which is split up in 2 sections.
 * Section ==USER==: If 1 or more Users are found, the User result list will be shown.
 * Section ==GROUP==: If 1 or more Groups are found, the Group result list will be shown.
 * If no users are found, no user result list will be shown. Same for groups.
 * @returns {undefined}
 */
function search() {
    var input = document.getElementById('searchText').value;
    console.log("Input: " + input);
    /*for (var i = 0; i < users.length; i++) {
        var totalname = users[i].name.toString() + users[i].lastName.toString();
        if ((input.length >= 3 && totalname.toUpperCase().indexOf(input.toUpperCase()) >= 0) || input === users[i].groupid) {
            studentResults.push(users[i]);
        }
    }*/
    findUsers(input);
    findGroups(input);
    
    var tbResults = document.getElementById("tbsearchresults")
    tbResults.innerHTML ="";
    if (studentResults.length > 0) {
        tbResults.innerHTML += "<tr class=\"trsearch\"><td>==USERS==</td></tr>";
        for (var i = 0; i < studentResults.length; i++) {   
            tbResults.innerHTML += "<tr class=\"trsearch\" onclick='selectTableById(\"" + studentResults[i].groupId + "\")'><td>" + studentResults[i].mail + "</td></tr>";
        }
    }
    if (groupResults.length > 0) {
        tbResults.innerHTML += "<tr class=\"trsearch\"><td>==GROUPS==</td></tr>";
        for (var i = 0; i < groupResults.length; i++) {
            tbResults.innerHTML += "<tr class=\"trsearch\" onclick='selectTableById(\"" + groupResults[i].id + "\")'><td>" + groupResults[i].name + "</td></tr>";
        }
    }
    // Check if the innerhtml is empty
    if (tbResults.innerHTML !== "") {
        tbResults.style.display = "table";
    } 
    else {
        tbResults.style.display = "none";
    }
}

/**
 * This method finds users based on the given input string.
 * The input string will be compared to usernames, lastnames or mails.
 * Every matching student will be added to the studentResults list.
 * @param {type} searchTxt
 * @returns {undefined}
 */
function findUsers(searchTxt) {
    studentResults = [];
    console.log("[FU] Check searchTxt: " + searchTxt + "(" + searchTxt.length + ")");
    if (searchTxt.length < 1) return;
    
    for (var i = 0; i < users.length; i++) {
        var user = users[i];
        if (!user.name || !user.lastName) continue;
        
        if (user.name.toUpperCase().indexOf(searchTxt.toUpperCase()) !== -1 || 
                user.lastName.toUpperCase().indexOf(searchTxt.toUpperCase()) !== -1 ||
                user.mail.toUpperCase().indexOf(searchTxt.toUpperCase()) !== -1) {
            //selectElementTableNr(user.tableNr);
            //console.log("Table found: " + user.fname + " " + user.lname + " at table " + user.tableNr);
            //break;
            studentResults.push(user);
            console.log("Found a user: " + studentResults.length);
        }
    }
}
/**
 * This methods finds groups based on the given input string.
 * The input string will be compared to groupnumber or groupname.
 * Every matching group will be added to the groupResults list.
 * @param {type} searchTxt
 * @returns {undefined}
 */
function findGroups(searchTxt) {
    groupResults = [];
    console.log("[FG] Check searchTxt: " + searchTxt + "(" + searchTxt.length + ")");
    if (searchTxt.length < 1) return;    
    
    for (var i = 0; i < groups.length; i++) {
        var group = groups[i];
        if (group.id.indexOf(searchTxt) !== -1 ||
                group.name.toUpperCase().indexOf(searchTxt.toUpperCase()) !== -1) {
            groupResults.push(group);
            console.log("Found a group: " + groupResults.length);
        }
    }
}

/**
 * This method finds groups by looking up the ID.
 * The GUI element will be created depending on the group which is found.
 * If no group can be found, the GUI element will be empty.
 * Else the group information, including members, will be shown.
 * @param {type} id
 * @returns {undefined}
 */
function findGroupById(id) {
    var group = null;
    if (!id) {
        createGroupElement(group);
        console.log("Id was invalid or empty.");
        return;
    }
    
    for (var i = 0; i < groups.length; i++) {
        if (groups[i].id === id) {
            console.log("Group found: " + groups[i].id);
            group = groups[i];
            break;
        }
    }
    
    createGroupElement(group);
    /*var groupInfo = "";
    if (group !== null) {
        for (var i = 0; i < group.members.length; i++) {
            groupInfo += group.members[i].mail;
            groupInfo += "\n";
            console.log("Looping through group members... " + i);
        }
    }
    if (groupInfo !== "") {
        alert(groupInfo);
    }*/
}
/**
 * This method finds a group by looking up the tableId.
 * The GUI element will be created depending on the group which is found.
 * If no group can be found, the GUI element will be empty.
 * Else the group information, including members, will be shown.
 * @param {type} tableId
 * @returns {undefined}
 */
function findGroupByTable(tableId) {
    var group = null;
    if (!tableId) {
        createGroupElement(group);
        console.log("Id was invalid or empty.");
        return;
    }
    
    for (var i = 0; i < groups.length; i++) {
        console.log("Looping through groups: " + groups[i]);
        if (groups[i].location !== undefined && String(groups[i].location) === String(tableId)) {
            console.log("Group found: " + groups[i].id);
            group = groups[i];
            break;
        }
    }
    
    createGroupElement(group);
    /*var groupInfo = "";
    if (group !== null) {
        for (var i = 0; i < group.members.length; i++) {
            groupInfo += group.members[i].mail;
            groupInfo += "\n";
            console.log("Looping through group members... " + i);
        }
    }
    if (groupInfo !== "") {
        alert(groupInfo);
    }*/
}

// This function selects a table element based on table number
/*function selectElementTableNr(tableNr) {
    if (!selectedLoc || !selectedLoc.selectedFloor) {
        alert("No location/floor selected.");
    }
    
    var elements = selectedLoc.selectedFloor.elements;
    for (var i = 0; i < elements.length; i++) {
        if (elements[i].type == "table" && elements[i].number == tableNr) {
            selectedLoc.selectedFloor.selectElement(elements[i]);
            redrawAll();
            break;
        } else {
            clearElementSelection();
        }
    }
}*/

/**
 * This method selects a table by group ID.
 * Based on group ID the tableId will be received. 
 * Afterwards, based on TableID the right table will be selected. (red border on map if found)
 * And based on group ID, the group will be selected.
 * @param {type} groupId
 * @returns {undefined}
 */
function selectTableById(groupId) {
    console.log("The given groupId is: " + groupId);
    document.getElementById("tbsearchresults").style.display = "none";
    if (!groupId) { 
        alert("User table cannot be found!");
        return;
    }
    if (!locations || locations.length === 0) {
        return;
    }
    
    var tableId;
    for (var i = 0; i < groups.length; i++) {
        if (groupId === groups[i].id) {
            tableId = groups[i].location;
            console.log("Found table location...");
            break;
        }
    }
    console.log("Found tableId: " + tableId);
    if (!tableId) {
        alert("User/Group is not found at a group or table");
        //return;
    }
    
    findTableById(tableId);
    findGroupById(groupId);
}
/**
 * This method looks up a table based on tableId.
 * If a table was found, it will be redrawn. 
 * Also, the right floor and location will be selected in order to match the found table.
 * @param {type} tableId
 * @returns {undefined}
 */
function findTableById(tableId) {
    var found = false;
    console.log("Looping through locations...");
    for (var i = 0; i < locations.length; i++) {
        if (found) break;
        console.log("Looping through floors...");
        var floors = locations[i].floors;
        for (var j = 0; j < floors.length; j++) {
            if (found) break;
            console.log("Looping through elements...");
            var elements = floors[j].elements;
            for (var k = 0; k < elements.length; k++) {
                //console.log("Check: " + elements[k].id + " vs " + tableId);
                //console.log("2nd check: " + (elements[k].id === tableId));
                //console.log("3rd check: " + (String(elements[k].id) === String(tableId)));
                //console.log("4th check: " + (String(elements[k].id) == String(tableId)));
                //console.log("5th check: " + (elements[k].id == tableId));
                if (elements[k].type === "table" && String(elements[k].id) === String(tableId)) {
                    console.log("Found element matching tableId!");
                    selectedLoc = locations[i];
                    selectedLoc.selectFloor(floors[j].id);
                    clearElementSelection();
                    changeLocation(selectedLoc);
                    changeFloor(selectedLoc.selectedFloor);
                    selectedLoc.selectedFloor.selectElement(elements[k]);
                    redrawAll();
                    console.log("Should be successfull");
                    found = true;
                    break;
                } else {
                    selectedLoc = locations[0];
                    selectedLoc.selectFloor(floors[0]);
                    clearElementSelection();
                }
            }
        }
    }
}
/**
 * This method is a help method for creating a group GUI element.
 * Every GUI element for the group has a groupname, groupinfo and 0 or more groupmembers.
 * @param {type} group
 * @returns {undefined}
 */
function createGroupElement(group) {
    var groupInfo = document.getElementById("groupinfo"); // none
    var groupName = document.getElementById("groupname"); // no info
    var groupMembers = document.getElementById("groupmembers");
    if (group === null) {
        groupName.innerHTML = "{ None }";
        groupInfo.innerHTML = "{ No Info }";
        groupMembers.innerHTML = "";
    } else {
        groupName.innerHTML = group.id + ". " + group.name;
        groupInfo.innerHTML = "This group has " + group.members.length + " members.\nThe group can be found at " + group.location;
        groupMembers.innerHTML = "";
        for (var i = 0; i < group.members.length; i++) {
            groupMembers.innerHTML += "<div id='" + group.members[i].id + "' class='soflow-regular-txt-limited'>" + group.members[i].mail + "</div>";
        }
    }
}

/**
 * Loads all the groups from firebase and stores them locally.
 * Members for each group will also be loaded.
 * @returns {undefined} void
 */
function loadGroups() {
    var groupRef = database.ref('Group');
    groupRef.once("value", function(snapshot) {
        snapshot.forEach(function(g) {
            var nr = g.key.toString();
            var name = g.val().Name;
            var location = g.val().Location;
            console.log("Nr: " + nr + " & location: " + location);
            tempGroup = new Group(nr, name, location);
            
            var memberRef = snapshot.child(nr + '/Members');
            memberRef.forEach(function(m) {
                var uid = m.key.toString();
                var tempUser = loadUserById(uid);
                console.log("tempUser: " + tempUser === null ? " success" : " failure");
                tempGroup.addMember(tempUser);
                console.log("Member pushed: " + tempGroup.members.length);
            });
            groups.push(tempGroup);
            console.log("Group pushed: " +  groups.length);
        });
    });
}
/**
 * Attempts to load a User from firebase based on the user id
 * @param {type} id of the user
 * @returns User object or null
 */
function loadUserById(id) {
    /*var userRef = database.ref('User/' + id);
    userRef.once("value", function(snapshot) {
        var course = snapshot.val().Course;
        var groupid = snapshot.val().GroupID;
        var lname = snapshot.val().Lastname;
        var fname = snapshot.val().Name;
        var mail = snapshot.val().Mail;
        var role = snapshot.val().Role;
        var semester = snapshot.val().Semester;
        var status = snapshot.val().Status;
        var user = new User(id, fname, lname, mail, role, semester, status, course, groupid);
        console.log("Member: " + user.toString());
        return user;
    });*/
    for (var i = 0; i < users.length; i++) {
        if (users[i].id === id) {
            return users[i];
        }
    }
    return null;
}
/**
 * Loads all the users from firebase and stores them locally.
 * @returns {undefined} void
 */
function loadUsers() {
    console.log("Initializing Loading users...");
    var userRef = database.ref('User');
    userRef.once("value", function(snapshot) {
        snapshot.forEach(function(u) {
            var uid = u.key.toString();
            var course = u.val().Course;
            var groupid = u.val().GroupID;
            var lname = u.val().Lastname;
            var fname = u.val().Name;
            var mail = u.val().Mail;
            var role = u.val().Role;
            var semester = u.val().Semester;
            var status = u.val().Status;
            var user = new User(uid, fname, lname, mail, role, semester, status, course, groupid);
            users.push(user);
            console.log("User: " + user.toString());
        }); 
    })
}

// Perform the following methods on load:
loadUsers();
loadGroups();
