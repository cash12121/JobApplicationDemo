<%-- 
    Document   : login
    Created on : May 8, 2021, 12:33:58 PM
    Author     : Cash Carlson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login</title>
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
            <div id="form" class="single">
                <h2>Login</h2>
                <c:if test="${loginFailed}">
                    <p style="font-weight: bold;; color: hsl(360, 100%, 67%)">The username or password you entered are not correct. Please try again.</p>
                </c:if>
                <form method="POST" action="<c:url value="/login" />">
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username" />
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" />
                    <input type="submit" id="submit" value="Log In" />
                </form>
            </div>
        </main>
    </body>
</html>
