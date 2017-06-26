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
/**
 * This method fills the selection menu with all the existing locations.
 * @returns {undefined}
 */
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
/**
 * This method finds and selects a location based on ID. 
 * If no matching ID can be found in the locations list, false will be returned.
 * Else, the location matching the ID will be selected and true will be returned.
 * @param {type} id
 * @returns {Boolean}
 */
function findLocation(id) { 
    for (var i = 0; i < locations.length; i++) {
        if (String(locations[i].id) == String(id)) {
            selectedLoc = locations[i];
            return true;
        }
    }
    return false;
}

/**
 * This method clears the current floor selection menu by setting the length value to 0.
 * @returns {undefined}
 */
function clearFloorList() { 
    document.getElementById("option-floor").options.length = 0;
}
/**
 * This method will load all the floors of a location in the Floor selection menu.
 * The floors cannot be loaded if no existing location has been selected.
 * @returns {undefined}
 */
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

/**
 * This method selects an element by X and Y coördinate.
 * Selecting an element is only possible if an existing location and existing floor are selected.
 * If the X and Y coördinates aren't within any of the elements boundaries, the selected element will be cleared or no element will be selected.
 * @param {type} x
 * @param {type} y
 * @returns {undefined}
 */
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
/**
 * This method clears the canvas. 
 * This method can be very useful for actions which require you to redraw elements on the map as it will remove the error of drawing over existing objects.
 * @returns {undefined}
 */
function clearCanvas() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}
/**
 * This method redraws all the elements of the currently selected floor on the currently selected location.
 * Before drawing, the canvas will be cleared.
 * @returns {undefined}
 */
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
/**
 * This method acts as an event handler for a mouseclick event.
 * Based on the mouseclick the X and Y coördinates will be received by the event for pageX and pageY to set the mouseX and mouseY coördinates.
 * If successfully selecting an element, the element will be highlighted and the element specific options will be shown in the top menu.
 * Selecting an element only works for tables.
 * @param {type} e
 * @returns {undefined}
 */
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
/**
 * This method changes the current location and also loads all the floors from that location.
 * Also onFloorChange() will be called to load the right item menu (containing a list of elements of that floor) and all these elements will also be drawn on the canvas.
 * @returns {undefined}
 */
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
/**
 * This method changes a Location.
 * This location change will be visually shown in the selection menu.
 * Also the floors for the selected location will be loaded.
 * @param {type} location
 * @returns {undefined}
 */
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

/**
 * This method changes the floor based on selection menu value.
 * The selection menu value will be used to select the floor from the current location. 
 * After selecting a floor on the current location, the item menu (which contains all elements of a floor) will be reloaded.
 * Element selection will be cleared so selection should be "none" which means that no element is selected.
 * Also all the elements of the selected floor will be redrawn on the canvas.
 * @returns {undefined}
 */
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
/**
 * This method changes a Floor.
 * This floor change will be visually shown in the selection menu.
 * Also the elements for the selected floor will be redrawn.
 * @param {type} floor
 * @returns {undefined}
 */
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