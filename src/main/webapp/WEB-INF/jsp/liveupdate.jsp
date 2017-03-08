<%-- 
    Document   : liveupdate
    Created on : Mar 7, 2017, 4:02:32 PM
    Author     : mmjan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<TITLE>Crunchify - Sparkline.js Example which accepts JSONArray
    value every 3 seconds</TITLE>
 
<style type="text/css">
body {
    background-image:
        url('http://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script
    src="http://cdn.crunchify.com/wp-content/uploads/code/jquery.sparkline.js"></script>
 
<script type="text/javascript">
    function crunchifySparkline() {
        $.ajax({
            url : 'sparklinetest.html',
            dataType : "json",
            cache : false,
            contentType : 'application/json; charset=utf-8',
            type : 'GET',
            success : function(result) {
                var one = result.sparkData;
                //alert(one);
                consumeJSONData(one);
            }
        });
    }
 
    function consumeJSONData(sparkData) {
        console.log(sparkData);
        for ( var i = 0; i < sparkData.length; i++) {
            var obj = sparkData[i];
            var crunchifyName;
            var crunchifyValue;
            for ( var key in obj) {
                crunchifyName = key;
                crunchifyValue = obj[key].toString();
                crunchifyValue = crunchifyValue.substr(1);
                crunchifyValue = crunchifyValue.substring(0,
                        crunchifyValue.length - 1);
                var n = crunchifyValue.split(",");
                console.log(n);
                $("#" + crunchifyName).sparkline(n, {
                    type : 'line',
                    width : '80',
                    fillColor : '#eeeeee'
                });
            }
        }
    }
</script>
 
<script type="text/javascript">
    var intervalId = 0;
    intervalId = setInterval(crunchifySparkline, 3000);
</script>
</head>
 
<body>
    <div align="center">
        <br> <br> ${message} <br> <br>
        <div id="result"></div>
 
        <br>
        <br> Sparkline One: &nbsp;&nbsp;&nbsp;&nbsp;<span id="one">.</span>
        <br>
        <br> Sparkline Two: &nbsp;&nbsp;&nbsp;&nbsp;<span id="two">.</span>
        <br>
        <br> Sparkline Three: &nbsp;&nbsp;&nbsp;&nbsp;<span id="three">.</span>
        <br>
        <br> <br> <br>
 
        <div
            style="font-family: verdana; line-height: 25px; padding: 5px 10px; border-radius: 10px; border: 3px solid #EE872A; width: 50%; font-size: 12px;">
 
            Sparkline.js Example which accepts JSONArray value every 3 seconds by
            <a href='http://crunchify.com'>Crunchify</a>. Click <a
                href='http://crunchify.com/category/java-web-development-tutorial/'>here</a>
            for all Java, Spring MVC, Web Development examples.<br>
        </div>
    </div>
</body>
</html>


