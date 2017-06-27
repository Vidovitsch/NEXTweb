/**************
 * User Class *
 **************/
var User = (function () {
    /**
     * This is the constructor of the User class.
     * @param {type} id
     * @param {type} name
     * @param {type} lname
     * @param {type} mail
     * @param {type} role
     * @param {type} semester
     * @param {type} status
     * @param {type} course
     * @param {type} groupId
     * @returns {user_L4.User}
     */
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
    /**
     * This method returns a toString value for better readability.
     * This can be very useful for debugging or quickly showing values of the raw User objects. 
     * @returns {unresolved}
     */
    User.prototype.toString = function() {
        return String(this.id + " is: " + this.name + " " + this.lastName + " (" + this.mail + ")");
    }
    return User;
})();