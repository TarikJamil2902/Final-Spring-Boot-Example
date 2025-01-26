<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Item Cards</title>
    <!-- Link to Bootstrap for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card {
            margin-bottom: 20px;
        }

        .card img {
            max-height: 200px;
            object-fit: cover;
            width: 100%;
        }

        .card-body {
            text-align: center;
        }

        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
        }

        .card-text {
            font-size: 1rem;
            color: gray;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="my-4 text-center">Item Cards</h1>

        <div class="row">
            <!-- Iterate over items and display each card -->
            <c:forEach var="item" items="${images}">
                <div class="col-md-4">
                    <div class="card">
                        <!-- Display image if it exists -->
                        <c:if test="${not empty item.imageData}">
                       
                           <img src="data:image/jpeg;base64,${item.imageString}" alt="Item Image"/>

                         </c:if>

                        <div class="card-body">
                            <h5 class="card-title">${item.name}</h5>
                            <p class="card-text">${item.type}</p>
                            <a href="#" class="btn btn-primary" href="${pageContext.request.contextPath}/item/${item.id}">View Details</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>