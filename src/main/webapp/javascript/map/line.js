/**************
 * Line class *
 **************/
var line = (function() {
    // constructor
    function line(id, x, y, x2, y2) {
		this.id = id;
        this.x = x;
        this.y = y;
		this.x2 = x2 || (x + 50);
		this.y2 = y2 || y;
		this.width = 5;
		this.type = "line";

		this.strokeStyle = '#000000';
		
		this.startLine = false;
		this.endLine = false;
		
        this.redraw(this.x, this.y);
		
        return (this);
    }
	// Redraw the line - makes use of Draw
    line.prototype.redraw = function(x, y) {
        this.x = x || this.x;
        this.y = y || this.y;
        this.draw();
        return (this);
    }
    // 
    line.prototype.highlight = function(x, y) {
        this.x = x || this.x;
        this.y = y || this.y;
        this.draw();
        return (this);
    }
    // Draw the line
    line.prototype.draw = function() {
        ctx.save();
        ctx.beginPath();
		ctx.moveTo(this.x, this.y);
		ctx.lineTo(this.x2, this.y2);
		ctx.strokeStyle = this.strokeStyle;
		ctx.lineWidth = this.width;
        ctx.stroke();
        ctx.restore();
    }
	// Check if the click is close enough
	line.prototype.checkCloseEnough = function(mouseX, mouseY) {		
		if (mouseX <= this.x + 5 && mouseX >= this.x && 
				mouseY <= this.y + 5 && mouseY >= this.y) {
			this.startLine = true;
			console.log("Startline selected");
		} 
		else if (mouseX >= this.x2 - 5 && mouseX <= this.x2 &&
					mouseY >= this.y2 - 5 && mouseY <= this.y2) {
			this.endLine = true;
			console.log("Endline selected");
		}
		else {
			console.log("No start, no endline");
		}
	}
	// Move the line
	line.prototype.moveTo = function(mouseX, mouseY) {
		var difX = this.x2 - this.x;
		var difY = this.y2 - this.y;

		this.x = mouseX; 
		this.y = mouseY; 
		this.x2 = this.x + difX;
		this.y2 = this.y + difY;
		
		this.draw();
		return (this);
	}
    // Check if a point is inside the line
    line.prototype.isPointInside = function (x, y) {
		var width = Math.abs(this.x2 - this.x);
		var lowX = this.x < this.x2 ? this.x : this.x2; 
		var height = Math.abs(this.y2 - this.y);
		var lowY = this.y < this.y2 ? this.y : this.y2;
		
		if(lowX < x && lowX + width > x)
		{
			var linear = (lowY - lowY + height) / (lowX - lowX + width);
			var constante = (linear * lowX - lowY) * -1;
			var lineSensitive = this.width;			
			return (x * linear + constante > y - lineSensitive && x * linear + constante < y + lineSensitive);
		}
		else return false;
    }
	// Resize the rectangle
	line.prototype.resizeTo = function(mouseX, mouseY) {
		if (this.startLine) {
			this.x = mouseX;
			this.y = mouseY;
		}
		else if (this.endLine) {
			this.x2 = mouseX;
			this.y2 = mouseY;
		}
		this.draw();
		return (this);
	}
	// Set the resizing values on false again
	line.prototype.stopResize = function() {
		this.startLine = false;
		this.endLine = false;
	}
	// Check if the start coordinate or end coordinate is selected
	line.prototype.isResizing = function() {
		if (this.startLine || this.endLine) return true;
		else return false;
	}	
    return line;
})();