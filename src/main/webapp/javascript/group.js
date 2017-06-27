/***************
 * Group Class *
 ***************/
var Group = (function () {
    /**
     * This is the constructor of the Group class.
     * @param {type} id
     * @param {type} name
     * @param {type} tableId
     * @returns {group_L4.Group}
     */
    function Group(id, name, tableId) {
       this.id = id;
       this.name = name;
       this.location = tableId;
       this.members = [];
       return (this);
    }
    /**
     * This method adds a member to the members list.
     * @param {type} member
     * @returns {undefined}
     */
    Group.prototype.addMember = function(member) {
        this.members.push(member);
    }
    /**
     * This method returns a toString value for better readability.
     * This can be very useful for debugging or quickly showing values of the raw Group objects.
     * @returns {unresolved}
     */
    Group.prototype.toString = function() {
        return String(this.id + ". " + this.name + " - " + this.location + " and " + this.members.length + " members.");
    }
    return Group;
})();