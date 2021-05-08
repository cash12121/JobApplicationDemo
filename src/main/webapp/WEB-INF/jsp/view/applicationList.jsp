<%-- 
    Document   : applicationList
    Created on : May 8, 2021, 1:16:29 PM
    Author     : Cash Carlson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Applications</title>
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
            <div id="jobs">
                <c:forEach items="${applications}" var="application" begin="${begin}" end="${end}">
                    <c:if test="${application.active}">
                        <div class="job">
                            <h1>
                                <a href="<c:url value="/application"><c:param name="id" value="${application.id}" /></c:url>"><c:out value="${application.firstName}" />&nbsp;<c:out value="${application.lastName}" /></a>
                            </h1>
                            <h2>Job Title:&nbsp;<c:out value="${application.jobTitle}"></c:out></h2>
                            <h2>Email:&nbsp;<c:out value="${application.email}" /></h2>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${maxPages}">
                    <a <c:if test="${currentPage == i}">class="active"</c:if> href="<c:url value="/applications"><c:param name="page" value="${i}" /></c:url>">${i}</a>
                </c:forEach>
            </div>
        </main>
    </body>
</html>

