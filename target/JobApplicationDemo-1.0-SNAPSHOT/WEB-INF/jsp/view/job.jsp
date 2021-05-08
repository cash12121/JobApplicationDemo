<%-- 
    Document   : job
    Created on : May 5, 2021, 11:18:22 PM
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
                    <h1><c:out value="${job.title}"/></h1>
                    <h2>Location:&nbsp;<c:out value="${job.city}"/>, <c:out value="${job.state}"/></h2>
                    <h2>Type:&nbsp;
                        <c:if test="${job.fullTime}">Full-Time</c:if>
                        <c:if test="${not job.fullTime}">Part-Time</c:if>
                    </h2>
                    <p>Department:&nbsp;<span><c:out value="${job.department}"/></span></p>
                    <p>Experience:&nbsp;<span><c:out value="${job.experience}"/></span></p>
                    <p>Salary:&nbsp;<span><fmt:formatNumber type="currency" value="${job.salary}" />
                            <c:choose>
                                <c:when test="${job.wageCategory == 'Salaried'}">
                                    per year
                                </c:when>
                                <c:when test="${job.wageCategory == 'Hourly'}">
                                    per hour
                                </c:when>
                                <c:otherwise>
                                    per hour
                                </c:otherwise>
                            </c:choose>
                        </span></p>
                </div>
                <div id="form">
                    <h1>Apply to this Job:</h1>
                    <form action="applications" method="POST" enctype="multipart/form-data">
                        <input type="hidden" name="jobId" value="<c:out value="${job.id}"/>">
                        <input type="hidden" name="jobTitle" value="<c:out value="${job.title}"/>">
                        <label for="first-name">First Name:</label>
                        <input type="text" id="first-name" name="firstName">
                        <label for="last-name">Last Name:</label>
                        <input type="text" id="last-name" name="lastName">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email">
                        <label for="phone">Phone Number: <span>Format: XXX-XXX-XXXX</span></label>
                        <input type="tel" id="phone" name="phone" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}">
                        <label for="resume">Upload Resume:</label>
                        <input type="file" name="resume" id="resume">
                        <label for="salary">Desired Salary:</label>
                        <input type="number" id="salary" step="any" name="desiredSalary">
                        <label for="start-date">Earliest Start Date:</label>
                        <input type="date" id="start-date" name="earliestStartDate">
                        <input type="submit" id="submit" value="Submit">
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>
