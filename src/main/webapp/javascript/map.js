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
}

// This function selects an element based on mouseX and mouseY coordinates
function selectElement(x, y) {
    if (!selectedLoc || !selectedLoc.selectedFloor) {
        alert("No location/floor selected.");
        return;
    }
    
    createGroupElement(null);
    var elements = selectedLoc.selectedFloor.elements;
    for (var i = 0; i < elements.length; i++) {
        if (elements[i].type === "table" && elements[i].isPointInside(x, y)) {
            selectedLoc.selectedFloor.selectElement(elements[i]);
            findGroupByTable(elements[i].id);
            console.log("Elements[i].id: " + elements[i].id);
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
    mouseX = parseInt(e.pageX - offsetX);
    mouseY = parseInt(e.pageY - offsetY);

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
function changeLocation(location) {
    var options = document.getElementById("option-location").options;
    for (var i = 0; i < options.length; i++) {
        if (options[i].value === location.id) {
            options.selectedIndex = i;
            loadFloorList();
            break;
        }
    }
}

function onFloorChange() {
    var result = document.getElementById("option-floor"); 
    
    //console.log("Floor change: " + result.value);
    //  Set the selected floor
    selectedLoc.selectFloor(result.value);
    //console.log("Selected floor... " + selectedLoc.selectedFloor);
    
    clearElementSelection();

    // Redraw the current floor
    redrawAll();
}
function changeFloor(floor) {
    var options = document.getElementById("option-floor").options;
    for (var i = 0; i < options.length; i++) {
        if (options[i].value === floor.id) {
            options.selectedIndex = i;
            redrawAll();
            break;
        }
    }
}