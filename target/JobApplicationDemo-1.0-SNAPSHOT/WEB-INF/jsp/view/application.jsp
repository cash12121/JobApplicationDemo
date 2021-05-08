<%-- 
    Document   : application
    Created on : May 8, 2021, 1:16:40 PM
    Author     : Cash Carlson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><c:out value="${job.title}"/></title>
        <link href="styles/main.css" rel="stylesheet">
    </head>
    <body>
        <header>
            <nav class="nav-menu">
                <ul>
                    <li>
                        <a href="<c:url value="/jobs"/>">View Jobs</a>
                    </li>
                    <li>
                        <a href="<c:url value="/applications"/>">Applications</a>
                    </li>
                    <c:if test="${sessionScope.username != null}">
                        <li>
                            <a href="<c:url value="/login">
                                   <c:param name="logout" value="logout"/>
                               </c:url>">Logout</a>
                        </li>
                    </c:if>
                    
                </ul>
            </nav>
        </header>
        <main>
            <div class="container">
                <div id="job">
                    <h1>Job Title:&nbsp;<c:out value="${application.jobTitle}"/></h1>
                    <h2>Name:&nbsp;<c:out value="${application.firstName}" />&nbsp;<c:out value="${application.lastName}" /></h2>
                    <h2>Email:&nbsp;<c:out value="${application.email}" /></h2>
                    <p>Phone:&nbsp;<span><c:out value="${application.phone}"/></span></p>
                    <p>Resume: <a href="
                                  <c:url value="application">
                                      <c:param name="action" value="download" />
                                      <c:param name="appId" value="${application.id}" />
                                  </c:url>
                                  ">Download</a></p>
                    <p>Desired Salary:&nbsp;<span><fmt:formatNumber type="currency" value="${application.desiredSalary}" /></span></p>
                    <fmt:parseDate value="${application.earliestStartDate}"  type="date" pattern="yyyy-MM-dd" var="parsedDate"/>
                    <p>Earliest Start Date:&nbsp;<span><fmt:formatDate  value="${parsedDate}"/></span></p>
                </div>
                <div id="form">
                    <form action="application" method="GET">
                        <input type="hidden" name="appId" value="<c:out value="${application.id}"/>">
                        <input type="hidden" name="action" value="deactivate">
                        <input type="submit" id="submit" value="Deactivate Application">
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>