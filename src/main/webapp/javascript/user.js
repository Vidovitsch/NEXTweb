/**************
 * User Class *
 **************/
var User = (function () {
    // constructor
    function User(id, name, lname, mail, role, semester, status, course, groupId) {
        this.id = id;
        this.name = name;
        this.lastName = lname;
        this.mail = mail;
        this.role = role;
        this.semester = semester;
        this.status = status;
        this.course = course;
        this.groupId = groupId;
        return (this);
    }
    // toString() value for writing to the firebase
    User.prototype.toString = function() {
        return String(this.id + " is: " + this.name + " " + this.lastName + " (" + this.mail + ")");
    }
    return User;
})();