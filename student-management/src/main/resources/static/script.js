document.addEventListener("DOMContentLoaded", function () {

    const form = document.getElementById("loginForm");

    form.addEventListener("submit", function (e) {

        e.preventDefault();

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        fetch("http://localhost:8080/students/login?email=" + encodeURIComponent(email) + "&password=" + encodeURIComponent(password), {
            method: "POST"
        })
        .then(response => {

            if (response.ok) {
                window.location.href="dashboard.html";
                document.getElementById("message").style.color = "green";
            } else {
                document.getElementById("message").innerHTML = "❌ Invalid Email or Password";
                document.getElementById("message").style.color = "red";
            }

        })
        .catch(error => {

            console.log(error);

            document.getElementById("message").innerHTML = "❌ Unable to connect to server";
            document.getElementById("message").style.color = "red";

        });
        

    });

});