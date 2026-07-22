document.addEventListener("DOMContentLoaded", function () {

     console.log("Dashboard JS Loaded");
    let allStudents = [];


    // ==========================
    // Fetch Students
    // ==========================

    fetch("http://localhost:8080/students")
        .then(response => response.json())
        .then(data => {

            // Latest ID first
            allStudents = data.sort((a, b) => b.id - a.id);


            // Cards

            document.getElementById("totalStudents").innerText =
                allStudents.length;

            document.getElementById("activeStudents").innerText =
                allStudents.length;

            document.getElementById("recentStudents").innerText =
                Math.min(5, allStudents.length);


            // Hide table initially

            document.getElementById("tableContainer").style.display = "none";


        })
        .catch(error => {

            console.log("Error :", error);

        });



    // ==========================
    // Dashboard Button
    // ==========================

    document.getElementById("dashboardBtn")
        .addEventListener("click", function(e){

            e.preventDefault();

            document.getElementById("tableContainer").style.display = "none";

        });



    // ==========================
    // Students Button
    // Show Latest 5
    // ==========================

    document.getElementById("studentsBtn")
        .addEventListener("click", function(e){

            e.preventDefault();


            if(allStudents.length === 0){

                alert("Please wait, students are loading...");
                return;

            }


            document.getElementById("tableTitle").innerText =
                "Recently Added Students";


            createTable(allStudents.slice(0,5));


            document.getElementById("tableContainer").style.display =
                "block";


        });



    // ==========================
    // View Students Button
    // Show All
    // ==========================

    document.getElementById("viewStudents")
        .addEventListener("click", function(e){

            e.preventDefault();


            if(allStudents.length === 0){

                alert("Please wait, students are loading...");
                return;

            }


            document.getElementById("tableTitle").innerText =
                "All Students";


            createTable(allStudents);


            document.getElementById("tableContainer").style.display =
                "block";


        });



    // ==========================
    // Logout
    // ==========================

    document.getElementById("logoutBtn")
        .addEventListener("click", function(e){

            e.preventDefault();


            alert("Logout Successful");


            window.location.href = "login.html";


        });
        document.querySelector(".close").addEventListener("click", function () {

    document.getElementById("editModal").style.display = "none";

});
document.getElementById("updateBtn").addEventListener("click", updateStudent);



    // ==========================
    // Create Table
    // ==========================

    function createTable(list){


        let table = "";


        list.forEach(student => {


            table += `

            <tr>

                <td>${student.id}</td>

                <td>${student.name ?? "-"}</td>

                <td>${student.email ?? "-"}</td>

                <td>${student.course ?? "-"}</td>

                <td>${student.department ?? "-"}</td>
                <td>
    <button class="edit-btn" onclick="editStudent(${student.id})">
        Edit
    </button>

    <button class="delete-btn" onclick="deleteStudent(${student.id})">
        Delete
    </button>
</td>
                

            </tr>


            `;


        });



        document.getElementById("studentTable").innerHTML = table;


    }
    document.getElementById("statusFilter").addEventListener("change", function () {

    let status = this.value;

    if(status === "ALL"){

        createTable(allStudents);

    }

    else{

        fetch("http://localhost:8080/students/filter/status?status=" + status)

        .then(response => response.json())

        .then(data => {

            createTable(data);

        });

    }

});
document.getElementById("searchBox").addEventListener("keyup", function () {

    let name = this.value.trim();

    if(name === ""){

        createTable(allStudents);

        return;

    }

    fetch("http://localhost:8080/students/search?name=" + name)

    .then(response => response.json())

    .then(data => {

        createTable(data);

    });

});


});


function editStudent(id){

    fetch("http://localhost:8080/students/" + id)

    .then(response => response.json())

    .then(student => {

        document.getElementById("editId").value = student.id;
        document.getElementById("editName").value = student.name;
        document.getElementById("editEmail").value = student.email;
        document.getElementById("editCourse").value = student.course;
        document.getElementById("editDepartment").value = student.department;
        document.getElementById("editCity").value = student.city;
        document.getElementById("editStatus").value = student.status;

        document.getElementById("editModal").style.display = "flex";

    });

}

function deleteStudent(id){

    alert(id);

}
function updateStudent() {

    let student = {

        id: document.getElementById("editId").value,
        name: document.getElementById("editName").value,
        email: document.getElementById("editEmail").value,
        course: document.getElementById("editCourse").value,
        department: document.getElementById("editDepartment").value,
        city: document.getElementById("editCity").value,
        status: document.getElementById("editStatus").value

    };

    fetch("http://localhost:8080/students/" + student.id, {

        method: "PUT",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(student)

    })

    .then(response => {

        if(response.ok){

            alert("Student Updated Successfully");

            document.getElementById("editModal").style.display = "none";

            location.reload();

        }

        else{

            alert("Update Failed");

        }

    });

}
function deleteStudent(id){

    let confirmDelete = confirm("Are you sure you want to delete this student?");

    if(!confirmDelete){
        return;
    }

    fetch("http://localhost:8080/students/" + id, {

        method: "DELETE"

    })

    .then(response => {

        if(response.ok){

            alert("Student Deleted Successfully");

            location.reload();

        }

        else{

            alert("Delete Failed");

        }

    })

    .catch(error => {

        console.log(error);

        alert("Something went wrong");

    });

}
