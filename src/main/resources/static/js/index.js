fetch("http://localhost:9090/api/students/all")
    .then(response => response.json())
    .then(data => {
        let html = "<ul>";
        data.forEach(student => {
            html += `<li>${student.id}: ${student.firstName} ${student.lastName} (${student.age} years old)</li>`;
        });
        html += "</ul>";

        document.getElementById("students").innerHTML = html;
    })
    .catch(error => {
        console.error("Error fetching students:", error);
    });