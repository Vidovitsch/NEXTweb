<%-- 
    Document   : map
    Created on : 20-mrt-2017, 14:04:16
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/map.css" />
        <title>Map</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="searchbar">
                <input type="text" id="searchText" name="searchText" oninput="search()" placeholder="Enter name or GroupID">
                <div id="searchresults">
                    <table id="tbsearchresults"></table>
                </div>
            </div>
            <span class="soflow-title">Locations: </span>
            <select class="soflow" id="option-location" onchange="onLocationChange()">
                <!-- Data depends on load -->
            </select>
            <span class="soflow-title">Floors: </span>
            <select class="soflow" id="option-floor" onchange="onFloorChange()">
                <!-- Data depends on location selection -->
            </select>
            
            <div>
                <div id="group" style="float: left; width: 300px; background-color: #E82C0C;">
                    <div id="groupname" class="soflow-title">{ None }</div>
                    <div id="groupinfo" class="soflow-regular-txt-wrapped">{ No Info }</div>
                    <div id="groupmembers" style="background-color: #ED4E0C">
                        <!--<div id="user.id" class="soflow-regular-txt-limited">TWAN@STUDENT.FONTYS.NL</div>
                        <div id="user.id" class="soflow-regular-txt-limited">MICHIEL@STUDENT.FONTYS.NL</div>
                        <div id="user.id" class="soflow-regular-txt-limited">DAVID@STUDENT.FONTYS.NL</div>-->
                    </div>
                </div>
                <div id="canvaspro" style="overflow: auto">
                    <canvas height="800px" width="1600px" id="map" style="background-color: #F0F0F0; "></canvas>
                </div>
            </div>
        </div>
            
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
        <script src="/javascript/map.js"></script>
        <script src="/javascript/circle.js"></script>
        <script src="/javascript/line.js"></script>
        <script src="/javascript/rectangle.js"></script>
        <script src="/javascript/table.js"></script>
        <script src="/javascript/room.js"></script>
        <script src="/javascript/location.js"></script>
        <script src="/javascript/floor.js"></script>
        <script src="/javascript/loadmap.js"></script>
        <script src="/javascript/group.js"></script>
        <script src="/javascript/user.js"></script>
        <script src="/javascript/search.js"></script>
    </body>
</html>
