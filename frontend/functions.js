const baseUrl = "http://localhost:8000";

async function getUserDetailsById(id){
    const response = await fetch(`${baseUrl}/api/user/${id}`)
    const data = await response.json();
    return data;
}

async function signupUser(data){
    const respnose = await fetch(`${baseUrl}/api/user`, {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(data)
    });
    const obj = await respnose.json();
    return obj;
}


async function addIncome(id, data) {
    const response = await fetch(`${baseUrl}/api/income/${id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    });
    const obj = await response.json();
    return obj;
}
async function addExpense(id, data) {
    const response = await fetch(`${baseUrl}/api/expense/${id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    });
    const obj = await response.json();
    return obj;
}
async function addBudget(id, data) {
    const response = await fetch(`${baseUrl}/api/budget/${id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    });
    const obj = await response.json();
    return obj;
}
async function addGoal(id, data) {
    const response = await fetch(`${baseUrl}/api/goals/${id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    });
    const obj = await response.json();
    return obj;
}




async function updateIncome(id, source, data){
    const respnose = await fetch(`${baseUrl}/api/income/${id}/source?name=${source}`, {
        method : 'PUT',
        headers : {
            'Content-Type' : 'application/json',
        },
        body : JSON.stringify(data)
    });
    const obj =  await respnose.json();
    return obj;
}
async function updateExpense(id, category, data){
    const respnose = await fetch(`${baseUrl}/api/expense/${id}/category?name=${category}`, {
        method : 'PUT',
        headers : {
            'Content-Type' : 'application/json',
        },
        body : JSON.stringify(data)
    });
    const obj =  await respnose.json();
    return obj;
}
async function updateBudget(id, category, data){
    const respnose = await fetch(`${baseUrl}/api/budget/${id}/category?name=${category}`, {
        method : 'PUT',
        headers : {
            'Content-Type' : 'application/json',
        },
        body : JSON.stringify(data)
    });
    const obj =  await respnose.json();
    return obj;
}
async function updateGoal(id, goalName, data){
    const respnose = await fetch(`${baseUrl}/api/goals/${id}/goalName?name=${goalName}`, {
        method : 'PUT',
        headers : {
            'Content-Type' : 'application/json',
        },
        body : JSON.stringify(data)
    });
    const obj =  await respnose.json();
    return obj;
}





async function deleteIncome(id, source){
    const respnose = await fetch(`${baseUrl}/api/income/${id}/source?name=${source}`, {
        method : 'DELETE'
    });
}
async function deleteExpense(id, category){
    const respnose = await fetch(`${baseUrl}/api/expense/${id}/category?name=${category}`, {
        method : 'DELETE'
    });
}
async function deleteBudget(id, category){
    const respnose = await fetch(`${baseUrl}/api/budget/${id}/category?name=${category}`, {
        method : 'DELETE'
    });
}
async function deleteGoal(id, goalName){
    const respnose = await fetch(`${baseUrl}/api/goals/${id}/goalName?name=${goalName}`,{
        method: 'DELETE'
    });
}