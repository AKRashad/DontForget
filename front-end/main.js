document.getElementById("showAllTask").addEventListener("click", function () {
    const token = localStorage.getItem('jwtToken');
    if (token) {
        const apiUrl = "http://localhost:8080/api/v1/todo";
        fetch(apiUrl, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Unauthorized access or CORS error');
                }
                else {
                    return response.json();
                }
            })
            .then(data => {
                filltable(data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    } else {
        console.log('JWT token not found');
    }
})

function filltable(data) {
    const tableBody = document.getElementById('todo-table');
    tableBody.innerHTML = '';
    data.forEach(item => {
        const row = document.createElement('tr');

        const cellId = document.createElement('td');
        cellId.textContent = item.id;
        row.appendChild(cellId);

        const cellSubject = document.createElement('td');
        cellSubject.textContent = item.todoSubject;
        cellSubject.setAttribute('contenteditable', 'true');
        row.appendChild(cellSubject);

        // const cellDate = document.createElement('td');
        // cellDate.textContent = item.todoDate;
        // cellDate.setAttribute('contenteditable', 'true');
        // row.appendChild(cellDate);

        // const cellIsDone = document.createElement('td');
        // cellIsDone.textContent = item.todoIsDone;
        // cellIsDone.setAttribute('contenteditable', 'true');
        // row.appendChild(cellIsDone);

        const cellAction = document.createElement('td');
        cellAction.innerHTML = `<button class="deletebtn"><i class='fa-solid fa-trash'></i></button>  
        <button class="updatebtn"><i class='fa-regular fa-pen-to-square'></i></button> `;
        row.appendChild(cellAction);
        tableBody.appendChild(row);
    });
}

document.querySelector('table').addEventListener("click", function (e) {
    console.log(e.target);
    const token = localStorage.getItem('jwtToken');
    if (e.target.classList.contains("updatebtn") || e.target.classList.contains("fa-pen-to-square")) {
        const row = e.target.closest('tr');
        const cells = row.getElementsByTagName('td');
        const id_ = cells[0].textContent;
        const subject = cells[1].textContent;
        // const date = cells[2].textContent;
        // const isDone = cells[3].textContent;
        const requestBody = {
            id: id_,
            todoSubject: subject
            // todoDate: date,
            // todoIsDone: isDone
        };
        fetch(`http://localhost:8080/api/v1/todo`, {
            method: 'PUT', // or 'POST', 'PUT', etc. depending on your API request
            headers: {
                'Authorization': `Bearer ${token}`,  // Attach the JWT token here
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody)
        })
            .then(response => response.text())
            .then((responsetxt) => {
                alert(responsetxt);
                document.getElementById("showAllTask").click();
            })
    }
});

document.querySelector('table').addEventListener("click", function (e) {
    console.log(e.target);
    const token = localStorage.getItem('jwtToken');


    if (e.target.classList.contains("deletebtn") || e.target.classList.contains("fa-trash")) {
        if (window.confirm("هل تريد ان تمسح")) {
            const row = e.target.closest('tr');
            const cells = row.getElementsByTagName('td');
            const id = cells[0].textContent;  // ID cell (first column)
            console.log(`ID: ${id}`);
            fetch(`http://localhost:8080/api/v1/todo/${id}`, {
                method: 'DELETE', // or 'POST', 'PUT', etc. depending on your API request
                headers: {
                    'Authorization': `Bearer ${token}`,  // Attach the JWT token here
                    'Content-Type': 'application/json',
                }
            })
                .then(response => response.text())
                .then((responsetxt) => {
                    alert(responsetxt);
                    document.getElementById("showAllTask").click();
                })
        }
    }
});

document.getElementById("addtaskbtn").addEventListener("click", function () {
    const token = localStorage.getItem('jwtToken');
    const subject = document.getElementById("task-input").value;
    // const date = document.getElementById("task-date-input").value;
    // const status = document.getElementById("task-status-input").value;
    // Create the login request body
    const requestBody = {
        todoSubject: subject,
        // todoDate: date,
    };
    if (token) {
        const apiUrl = "http://localhost:8080/api/v1/todo";
        fetch(apiUrl, {
            method: 'post', // or 'POST', 'PUT', etc. depending on your API request
            headers: {
                'Authorization': `Bearer ${token}`,  // Attach the JWT token here
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody)
        })
            .then(response => response.text())
            .then((responsetxt) => {

                alert(responsetxt);
                document.getElementById("showAllTask").click();

            })
    } else {
        console.log('JWT token not found');
    }
})