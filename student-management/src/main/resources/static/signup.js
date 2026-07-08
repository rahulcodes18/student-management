document.addEventListener("DOMContentLoaded", function () {

    document.getElementById("signupForm").addEventListener("submit", function (e) {

        e.preventDefault();

        const student = {
            name: document.getElementById("name").value,
            email: document.getElementById("email").value,
            password: document.getElementById("password").value,
            course: document.getElementById("course").value,
            department: document.getElementById("department").value,
            city: document.getElementById("city").value
        };

        fetch("http://localhost:8080/students/signup", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(student)

        })

        .then(response => {

            if (response.ok) {

                document.getElementById("message").innerHTML = "✅ Registration Successful";
                document.getElementById("message").style.color = "green";

                document.getElementById("signupForm").reset();

            } else {

                document.getElementById("message").innerHTML = "❌ Email Already Exists";
                document.getElementById("message").style.color = "red";

            }

        })

        .catch(error => {

            console.log(error);

            document.getElementById("message").innerHTML = "❌ Server Error";
            document.getElementById("message").style.color = "red";

        });
        setTimeout(() => {
    window.location.href = "login.html";
}, 1500);

    });

});