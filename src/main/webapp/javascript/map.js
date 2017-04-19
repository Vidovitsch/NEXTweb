// Requires:
// - Firebase
// - mapcreation.jsp
// - mapoptions.js for dynamic button elements
// - mapcreation.js for elements list which are displayed on the mapcreation canvas

// Get a reference to the database service
var database = firebase.database();
// Create a variable components & tables
var components = [];
var tables = [];

// Canvas and mapdrawing
var canvas = document.getElementById("map");
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;
var ctx = canvas.getContext("2d");
// offsets
var canvasOffset = $("#map").offset();
var offsetX = canvasOffset.left;
var offsetY = canvasOffset.top;

// FireBase
function saveMapToDB() {
    console.log("Saving to db...");
    clearMapFromDB();
    for (var i = 0; i < components.length; i++) {
        database.ref('Map/' + components[i].id).set({
            Object: components[i].toString()
        });
    }
}

// FireBase
function loadMapFromDB() {
    var mapRef = database.ref('Map');
    components = [];
    tables = [];
    
    console.log("Loading from db...");
    
    mapRef.on("child_added", function(snapshot) {
        var reference = snapshot.val();
        var object = reference.Object; // type;x;y;dependantOnType
        var values = object.replace("\"", "");
        var values = values.split(";");

        var key = snapshot.key; // id
        console.log("Key value: " + key + " & Object: " + object);
        console.log("Values: " + values);
        console.log("Values[0]: " + values[0]);
        if (values[0] == "circle") {
            console.log("is circle");
            // values[0] = type, values[1] = x, values[2] = y, values[3] = radius
            components.push(new circle(String(key), parseInt(values[1]), parseInt(values[2]), parseInt(values[3])));
        }
        else if (values[0] == "rectangle") {
            console.log("is rectangle");
            // values[0] = type, values[1] = x, values[2] = y, values[3] = width, values[4] = height
            components.push(new rectangle(String(key), parseInt(values[1]), parseInt(values[2]), parseInt(values[3]), parseInt(values[4])));
        }
        else if (values[0] == "table") {
            console.log("is table");
            // values[0] = type, values[1] = x, values[2] = y, values[3] = width, values[4] = height, values[5] = number
            tables.push(new table(String(key), parseInt(values[1]), parseInt(values[2]), parseInt(values[3]), parseInt(values[4]), parseInt(values[5])));
        }
        else if (values[0] == "line") {
            console.log("is line");
            // values[0] = type, values[1] = x, values[2] = y, values[3] = x2, values[4] = y2
            components.push(new line(String(key), parseInt(values[1]), parseInt(values[2]), parseInt(values[3]), parseInt(values[4])));
        }
        else if (values[0] == "text") {
            // Not implemented yet/
        }
        console.log("Components size: " + components.length);
    });
    drawMap();
}

// FireBase
function clearMapFromDB() {
    // Remove the Map reference in firebase
    var mapRef = database.ref('Map');
    mapRef.remove();
}

function drawMap() {
    console.log("Drawing");
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    for (var i = 0; i < components.length; i++) {
        components[i].draw();
    }
    for (var i = 0; i < tables.length; i++) {
        tables[i].draw();
    }
}

function checkIfTable(mouseX, mouseY) {
    for (var i = 0; i < tables.length; i++) {
        if (tables[i].isPointInside(mouseX, mouseY)) {
            alert("Table was selected");
            // put code to show group here - just show it in the alert to prove a point
            // the number can be requested by using: tables[i].number (might have to parseInt)
            break;
        }
    }
}

function handleMouseClick(e) {
    mouseX = parseInt(e.clientX - offsetX);
    mouseY = parseInt(e.clientY - offsetY);
    
    checkIfTable(mouseX, mouseY);
}

// Add eventhandler function to the map
$("#map").click(handleMouseClick);

loadMapFromDB();