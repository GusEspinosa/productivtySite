// get data from server
    // prepare HTTP request to send to servlet (GET - localhost:8082/todo-demo/tasks)
let xhr = new XMLHttpRequest();
xhr.open("GET","http://localhost:8082/test/tasks");
xhr.onreadystatechange = function(){
    if(xhr.readyState === 4){
        
        // obtain HTTP response that is returned from our server with JSON in body
        let taskJson = xhr.responseText;

        // convert JSON into JavaScript objects, so that we can iterate over them and access their props (JSON.parse())
        let tasks = JSON.parse(taskJson);
        console.log(tasks); // array of task objects 

        let tableBody = document.getElementById("task-table-body");
        // iterate over the array of task objects and create a table row with each one of them
        for(let task of tasks){
            let tableRow = document.createElement("tr");

            // this data would need to be sanitized or this implementation would be vulnerable to xss (cross site scripting)
            tableRow.innerHTML = `<td>${task.taskName}</td><td>${task.description}</td><td>${task.dueDate}</td><td>${task.completed}</td>`
                   
            // append table rows to table body of our task table
            tableBody.appendChild(tableRow);
        }

    }
}
xhr.send();



// registering an event listener with the submit button
// we want to tie the "create task" functionality to the submission of our "add task" form
document.getElementById("submit-btn").addEventListener("click", createTask);


function createTask(e){

    // stops the form from the default submission behavior
    e.preventDefault();

    // obtain the value of the input box
    let nameInput = document.getElementById("tname").value;
    let descInput = document.getElementById("desc").value;
       let dateInput = document.getElementById("ddate").value;
    // create an object to represent our new task
    //let task = {taskName : nameInput}
        let task = { taskName : nameInput,description : descInput, dueDate : dateInput}
        //System.out.println(task);
    // configure HTTP request using xhr
    let xhr = new XMLHttpRequest();
    xhr.open("POST","http://localhost:8082/test/tasks");
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            // process response
            if(xhr.status === 201){
                console.log("added in new task");
                // render notification to user that task was created -- can also render new task on the page

                // create a new table row to include in our dom for the new task
                let tableRow = document.createElement("tr");

                // this data would need to be sanitized or this implementation would be vulnerable to xss (cross site scripting)
                tableRow.innerHTML = `<td>${task.taskName}</td><td>${null}</td><td>${null}</td><td>${false}</td>`
                   
                // append table rows to table body of our task table
                document.getElementById("task-table-body").appendChild(tableRow);

            } else {
                console.log("there was an issue creating task --- status code: "+xhr.status+" "+xhr.statusText)
            }
        }
    }

    // convert my task JS object -> JSON string
    let requestPayload = JSON.stringify(task);
    //System.out.println(requestPayload + ")))");
    xhr.send(requestPayload); // {"newTask":"input"}
}

