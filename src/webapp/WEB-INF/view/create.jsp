<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Employee</title>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 20px;
        }
        
        h2 {
            text-align: center;
            color: #333;
        }

        form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        label {
            font-size: 1.1rem;
            margin-bottom: 5px;
            display: block;
            color: #333;
        }

        input[type="text"], 
        input[type="datetime-local"], 
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0 20px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
        }

        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            background-color: #45a049;
        }

        a {
            display: inline-block;
            text-align: center;
            color: #333;
            text-decoration: none;
            margin-top: 20px;
            font-size: 1rem;
        }

        a:hover {
            text-decoration: underline;
        }

    </style>
</head>
<body>
    <h2>Add Employee</h2>
    
    <!-- Form to Edit Employee -->
    <form action="/save" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"  /><br>

        <label for="email">Email:</label>
        <input type="text" id="email" name="email"  /><br>

        <label for="dob">Date of Birth:</label>
        <input type="datetime-local" id="dob" name="dateOfBirth"   /><br>

        <label for="salary">Salary:</label>
        <input type="number" id="salary" name="salary"  step="0.01"  /><br>

        <button type="submit">Save</button>
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/employee/list">Back to Employee List</a>
</body>
</html>
