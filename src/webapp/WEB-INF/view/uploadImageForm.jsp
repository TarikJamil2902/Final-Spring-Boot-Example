<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Upload Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Upload Image</h2>
        <!-- Form for image upload -->
        <form action="/uploadImages" method="POST" enctype="multipart/form-data">
            <!-- Image file input -->
            <div class="form-group">
                <label for="imageFile" class="form-label">Select Image</label>
                <input type="file" id="imageFile" name="imageFile" class="form-control"  required>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary">Upload</button>
        </form>
    </div>

    <!-- Optional JavaScript and Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
