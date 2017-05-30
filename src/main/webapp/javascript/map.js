/* 
 * Reserved variables:
 * - canvas
 * - ctx
 * - canvasOffset
 * - offsetX
 * - offsetY
 * - locations
 * - selectedLoc
 * - mouseDown
 * - clickDifX
 * - clickDifY
 * - venue (is the location variabele, location itself conflicts)
 * - floor
 * - room
 * - circle
 * - rectangle
 */

// Canvas + Context
var canvas = document.getElementById("map");
//canvas.width = window.innerWidth;
//canvas.height = window.innerHeight;
var ctx = canvas.getContext("2d");
var canvasOffset = $("#map").offset();
var offsetX = canvasOffset.left;
var offsetY = canvasOffset.top;

// Locations
var locations = [];
// dummy
var users = [];

// Selection
var selectedLoc = null;
var mouseDown = false;
var clickDifX, clickDifY;

/*************
 * Functions *
 *************/
function loadLocationList() {    
    document.getElementById("option-location").options.length = 0;
    var menu = document.getElementById("option-location");
    for (var i = 0; i < locations.length; i++) {
        var option = document.createElement("option");
        option.value = locations[i].id;
        option.text = locations[i].name;
        menu.add(option);
    }
    console.log("All locations have been added to the list.");
}
function findLocation(id) { 
    for (var i = 0; i < locations.length; i++) {
        if (String(locations[i].id) == String(id)) {
            selectedLoc = locations[i];
            return true;
        }
    }
    return false;
}

function clearFloorList() { 
    document.getElementById("option-floor").options.length = 0;
}
function loadFloorList() { 
    clearFloorList();
    var menu = document.getElementById("option-floor");
    for (var i = 0; i < selectedLoc.floors.length; i++) {
        var option = document.createElement("option");
        option.value = selectedLoc.floors[i].id;
        option.text = selectedLoc.floors[i].name;
        menu.add(option);
    }
    console.log("All floors have been added to the list related to the selected location.");
}

// This function selects an element based on mouseX and mouseY coordinates
function selectElement(x, y) {
    if (!selectedLoc || !selectedLoc.selectedFloor) {
        alert("No location/floor selected.");
        return;
    }
    
    var elements = selectedLoc.selectedFloor.elements;
    for (var i = 0; i < elements.length; i++) {
        if (elements[i].type == "table" && elements[i].isPointInside(x, y)) {
            selectedLoc.selectedFloor.selectElement(elements[i]);
            break;
        } else {
            clearElementSelection();
        }
    }
}

// This function selects a table element based on table number
function selectElementTableNr(tableNr) {
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
}

/**
 * This method will clear the selected element of the current floor.
 * @returns {undefined}
 */
function clearElementSelection() {
    // check if location and floor are both selected
    if (!selectedLoc || !selectedLoc.selectedFloor) return; 
    
    selectedLoc.selectedFloor.selectElement(null);
}
// This function clears the canvas
function clearCanvas() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}
// This function redraws all the elements
function redrawAll() {
    clearCanvas();
    if (!selectedLoc || !selectedLoc.selectedFloor) { 
        return;
    }  
    console.log("[redrawAll]");
    selectedLoc.selectedFloor.drawElements();
}

/*********************
 * Map-EventHandlers *
 *********************/
function handleMouseClick(e) {
    if (!selectedLoc || !selectedLoc.selectedFloor) {
        alert("No location/floor selected.");
        return;
    }
    
    // get the mouseX and mouseY location from the eventhandler
    mouseX = parseInt(e.clientX - offsetX);
    mouseY = parseInt(e.clientY - offsetY);

    // attempt to select an element by pressing within the element's boundaries
    selectElement(mouseX, mouseY);
    // if an element was selected, the redrawAll function will now automatically show a red border
    redrawAll();
}

// Bind the EventHandler functions to the Map control
$("#map").click(handleMouseClick);

/******************************************
 * Location/Floor selection EventHandlers *
 ******************************************/
function onLocationChange() {
    var result = document.getElementById("option-location");
    redrawAll();
    // Find location by ID
    if (!findLocation(result.value)) {
        return;
    }
    
    // Load the designated floors in the list
    loadFloorList();
    onFloorChange();    
    
    // Reset floor selection 
    selectedLoc.selectFloor(null);    
}
function onFloorChange() {
    var result = document.getElementById("option-floor"); 
    
    console.log("Floor change: " + result.value);
    //  Set the selected floor
    selectedLoc.selectFloor(result.value);
    console.log("Selected floor... " + selectedLoc.selectedFloor);

    
    clearElementSelection();

    // Redraw the current floor
    redrawAll();
}

// DUMMY
function createDummyUsers() {
    var user1 = { 
        fname : "Bert",
        lname : "Berkvens",
        tableNr : "0"
    }
    var user2 = { 
        fname : "Twan",
        lname : "Bertvens",
        tableNr : "1"
    }
    users.push(user1);
    users.push(user2);
}

function searchUser() {
    var searchTxt = document.getElementById("searchText").value.toUpperCase();
    for (var i = 0; i < users.length; i++) {
        var user = users[i];
        if (user.fname.toUpperCase().indexOf(searchTxt) != -1 || 
                user.lname.toUpperCase().indexOf(searchTxt) != -1) {
            selectElementTableNr(user.tableNr);
            console.log("Table found: " + user.fname + " " + user.lname + " at table " + user.tableNr);
            break;
        }
    }
}

createDummyUsers();