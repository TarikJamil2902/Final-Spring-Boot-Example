<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Upload Successful</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Image Upload Successful</h2>
        <div class="alert alert-success" role="alert">
            <strong>Success!</strong> Your image has been uploaded successfully.
        </div>
		<div>
			
		<img src= "data:image/jpeg;base64,${image}" alt="Image" />
			
			<!--<img src="data:image/png;base64,{{ imageBase64 }}" alt="Image">-->

			
		</div>

        <div class="mb-3">
            <a href="/imgform" class="btn btn-primary">Upload Another Image</a>
            <a href="/" class="btn btn-secondary">Go to Home</a>
        </div>
    </div>

    <!-- Optional JavaScript and Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
