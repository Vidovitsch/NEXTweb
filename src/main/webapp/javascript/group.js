/***************
 * Group Class *
 ***************/
var Group = (function () {
    // constructor
    function Group(id, name, tableId) {
       this.id = id;
       this.name = name;
       this.location = tableId;
       this.members = [];
       return (this);
    }
    Group.prototype.addMember = function(member) {
        this.members.push(member);
    }
    Group.prototype.toString = function() {
        return String(this.id + ". " + this.name + " - " + this.location + " and " + this.members.length + " members.");
    }
    return Group;
})();