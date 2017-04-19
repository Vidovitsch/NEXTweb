/***************
 * Table Class *
 ***************/
var table = (function () {
    // constructor
    function table(id, x, y, width, height, number) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.number = number;
        this.type = "table";

        this.strokeStyle = '#000000';
        this.minWidth = 24;
        this.minHeight = 24;

        this.topLeft = false;
        this.topRight = false;
        this.botLeft = false;
        this.botRight = false;
		
        this.redraw(this.x, this.y);
        return (this);
    }
    // Redraw the table - makes use of Draw
    table.prototype.redraw = function (x, y) {
        this.x = x || this.x;
        this.y = y || this.y;
        this.draw();
        return (this);
    }
    // Draw the table
    table.prototype.draw = function () {
        ctx.save();
        ctx.beginPath();
        ctx.rect(this.x, this.y, this.width, this.height);
        ctx.font = "18px Arial";
        ctx.fillText(this.number, (this.x + this.width / 2) - 6, (this.y + this.height / 2) + 6);
        ctx.strokeStyle = this.strokeStyle;
        ctx.lineWidth = 5;
        ctx.stroke();
        ctx.restore();
    }
    // Check if the click is close enough
    table.prototype.checkCloseEnough = function(mouseX, mouseY) {
        console.log("Check close enough Rect");
        // Top left corner check
        if (Math.abs(mouseX - this.x) < 2 && Math.abs(mouseY - this.y) < 2) {
            this.topLeft = true;
            console.log("Rect: topLeft true");
        }
        // Top right corner check
        else if (Math.abs(mouseX - (this.x + this.width)) < 2 && Math.abs(mouseY - this.y) < 2) {
            this.topRight = true;
            console.log("Rect: topRight true");
        }
        // Bottom left corner check
        else if (Math.abs(mouseX - this.x) < 2 && Math.abs(mouseY - (this.y + this.height)) < 2) {
            this.botLeft = true;
            console.log("Rect: botLeft true");
        }
        // Bottom right corner check
        else if (Math.abs(mouseX - (this.x + this.width)) < 2 && Math.abs(mouseY - (this.y + this.height)) < 2) {
            this.botRight = true;
            console.log("Rect: botRight true");
        }
    }
    // Move the table
    table.prototype.moveTo = function(mouseX, mouseY) {
        this.x = mouseX; 
        this.y = mouseY; 

        this.draw();
        return (this);
    }
    // Check if a point is inside the table
    table.prototype.isPointInside = function (x, y) {
        return (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height);
    }
    // Resize the table
    table.prototype.resizeTo = function (mouseX, mouseY) {
        if (this.topLeft) {
            this.width += this.x - mouseX;
            this.height += this.y - mouseY;
            this.x = mouseX;
            this.y = mouseY;
        }
        else if (this.topRight) {
            this.width = Math.abs(this.x - mouseX);
            this.height += this.y - mouseY;
            this.y = mouseY;
        }
        else if (this.botLeft) {
            this.width += this.x - mouseX;
            this.height = Math.abs(this.y - mouseY);
            this.x = mouseX;
        }
        else if (this.botRight) {
            this.width = Math.abs(this.x - mouseX);
            this.height = Math.abs(this.y - mouseY);
        }

        if (this.width < this.minWidth) {
            this.width = this.minWidth;
        }
        if (this.height < this.minHeight) {
            this.height = this.minHeight;
        }
        
        this.draw();
        return (this);
    }
    // Set the resizing values on false again
    table.prototype.stopResize = function() {
        this.topLeft = false;
        this.topRight = false;
        this.botLeft = false;
        this.botRight = false;
    }
    // 
    table.prototype.isResizing = function() {
        if (this.topLeft || this.topRight || this.botLeft || this.botRight) return true;
        else return false;
    }
    // toString() value for writing to the firebase
    table.prototype.toString = function() {
        return String(this.type + ";" + this.x + ";" + this.y + ";" + this.width + ";" + this.height + ";" + this.number);
    }
    return table;
})();