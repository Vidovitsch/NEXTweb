<%@page import="Controllers.welcomeController"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : main
    Created on : 6-mrt-2017, 11:02:11
    Author     : Michiel van Eijkeren
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="Controllers.Dingetje"%>
<%@page import="Controllers.welcomeController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        
        <title>Using GET and POST Method to Read Form Data</title>
    </head>
    
    <body>
        <% String myObjectId = request.getParameter("myObjectId");
        welcomeController myObject = new welcomeController((Dingetje)request.getSession().getAttribute(myObjectId));
        request.getSession().removeAttribute(myObjectId);
        out.println(myObject.getD().getDing()); %>
        <h2>HTTP Header Request Example</h2>
        <table width="100%" border="1" align="center">
            <tr bgcolor="#949494">
                <th>Param Name</th><th>Param Value(s)</th>
            </tr>
            <%
                Enumeration paramNames = request.getParameterNames();
                while (paramNames.hasMoreElements())
                {
                    String paramName = (String) paramNames.nextElement();
                    out.print("<tr><td>" + paramName + "</td>\n");
                    String paramValue = request.getParameter(paramName);
                    out.println("<td> " + paramValue + "</td></tr>\n");
                }
            %>
        </table>
</body>
</html>

