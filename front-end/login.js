// login.js
document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way

    // Get the username and password values from the form
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Create the login request body
    const requestBody = {
        userName: username,
        passWord: password
    };

    // Send a POST request to the Spring Boot backend (make sure to replace with your actual API URL)
    fetch("http://localhost:8080/api/v1/user/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(requestBody) // Send the form data as a JSON object
    })
    .then(response => {
       
        if (response.ok) {
            return response.json(); // If login is successful, return JSON response (e.g., token or user info)
        } else {
            throw new Error('Login failed');
        }
    })
    .then(data => {
        console.log("Login successful:", data);
        // Handle successful login here (e.g., redirect user, store token)
        localStorage.setItem("jwtToken", data.token);
        location.replace("main.html")
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Login failed: " + error.message);
    });
});
