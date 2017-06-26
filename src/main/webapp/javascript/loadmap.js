// Requires:
// - Firebase
// - mapcreation.jsp
// - mapoptions.js for dynamic button elements
// - mapcreation.js for elements list which are displayed on the mapcreation canvas

// Get a reference to the database service
var database = firebase.database();
var v;
var f;

/* Loading */
/**
 * This method loads the map from firebase.
 * All locations will be loaded. The locations will be shown in a selection menu.
 * Within a location all assigned floors will be loaded. The floors will be shown in a selection menu (dynamically loaded by location selection)
 * Within a floor all assigned elements will be loaded. The elements will be shown on the map.
 * If several locations have been loaded from firebase, the very first location will be selected along with the first floor.
 * The elements for the selected location and floor combination will be shown on the map.
 * @returns {undefined} void
 */
function loadMap() {
    //Search foor locations
    var locRef = database.ref('Map');
    locRef.once("value", function(snapshot) {
        snapshot.forEach(function(loc) {
            var id = loc.key.toString();
            var address = loc.val().Address;
            var name = loc.val().Name;
            var postal = loc.val().Postal;
            v = new Venue(id, name, postal, address);
            //console.log("New venue? " + v);
            
            //Search for floors
            var floorRef = snapshot.child(v.id + "/Floors");
            floorRef.forEach(function(floor) {
                var id = floor.key.toString();
                var level = floor.val().Level;
                var name = floor.val().Name;
                f = new Floor(id, name, level);
                //console.log("New floor? " + f);

                //Search for elements
                var elemRef = snapshot.child(v.id + "/Floors/" + f.id + "/Elements");
                elemRef.forEach(function(elem) {
                    var e = loadElement(elem);
                    //console.log("New element? " + e);
                    f.addElement(e);
                });
                v.addFloor(f);
            });
            locations.push(v);
        });
        if (locations.length > 0) {
            selectedLoc = locations[0];
            loadLocationList();
            loadFloorList();
            if (selectedLoc.floors.length > 0) {
                selectedLoc.selectedFloor = selectedLoc.floors[0];
            }
            redrawAll();
        }
    });
}
/**
 * This method acts as a help function for receiving the right type of element based on it's type.
 * @param {type} elem
 * @returns {Rectangle|Room|Wall|Table|Circle}
 */
function loadElement(elem) {
    var id = elem.key.toString();
    var type = elem.val().Type;
    if (type === 'rectangle') {
        var x = elem.val().X;
        var y = elem.val().Y;
        var width = elem.val().Width;
        var height = elem.val().Height;
        return new Rectangle(id, x, y, width, height);
    } else if (type === 'room') {
        var x = elem.val().X;
        var y = elem.val().Y;
        var width = elem.val().Width;
        var height = elem.val().Height;
        var capacity = elem.val().Capacity;
        var name = elem.val().Name;
        return new Room(id, x, y, width, height, capacity, name);
    } else if (type === 'circle') {
        var x = elem.val().X;
        var y = elem.val().Y;
        var r = elem.val().Radius;
        return new Circle(id, x, y, r);
    } else if (type === 'table') {
        var x = elem.val().X;
        var y = elem.val().Y;
        var width = elem.val().Width;
        var height = elem.val().Height;
        var number = elem.val().Number;
        return new Table(id, x, y, width, height, number);
    } else  {
        var x = elem.val().X;
        var y = elem.val().Y;
        var x2 = elem.val().X2;
        var y2 = elem.val().Y2;
        return new Wall(id, x, y, x2, y2);
    }
}

loadMap();
