<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Jobs</title>
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
                <c:forEach items="${jobs}" var="job" begin="${begin}" end="${end}">
                    <div class="job">
                        <h1>
                            <a href="<c:url value="/jobs"><c:param name="id" value="${job.id}" /></c:url>"><c:out value="${job.title}" /></a>
                        </h1>
                        <h2 class="city"><c:out value="${job.city}" />,&nbsp;<c:out value="${job.state}" /></h2>
                        <h2 class="department"><c:out value="${job.department}" /></h2>
                    </div>
                </c:forEach>
            </div>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${maxPages}">
                    <a <c:if test="${currentPage == i}">class="active"</c:if> href="<c:url value="/jobs"><c:param name="page" value="${i}" /></c:url>">${i}</a>
                </c:forEach>
            </div>
        </main>
    </body>
</html>
