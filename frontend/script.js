function deleteIncomeFunction(id, source){
    deleteIncome(id, source);
    alert("Income deleted successfully!");
}
function deleteExpenseFunction(id, category){
    deleteExpense(id, category);
    alert("Expense deleted successfully!");
}
function deleteBudgetFunction(id, category){
    deleteBudget(id, category);
    alert("Budget deleted successfully!");
}
function deleteGoalFunction(id, goalName){
    deleteGoal(id, goalName);
    alert("Goal deleted successfully!");
}



async function updateIncomeFunction(){
    event.preventDefault();
    const id = document.getElementById('id').value;
    const amount = document.getElementById('amount').value;
    const source = document.getElementById('source').value;
    const date = document.getElementById('date').value;

    const data = {
        amount : amount,
        source : source,
        date : date
    }

    const updatedIncomeData = await updateIncome(id, source, data);
    console.log(updatedIncomeData);
    alert("Income updated successfully!");
    location.reload();
}
async function updateExpenseFunction(){
    event.preventDefault();
    const id = document.getElementById('id').value;
    const amount = document.getElementById('expenseAmount').value;
    const category = document.getElementById('category').value;
    const date = document.getElementById('expenseDate').value;

    const data = {
        amount : amount,
        category : category,
        date : date
    }

    const updatedExpenseData = await updateExpense(id, category, data);
    console.log(updatedExpenseData);
    alert("Expense updated successfully!");
    location.reload();
}
async function updateBudgetFunction(){
    event.preventDefault();
    const id = document.getElementById('id').value;
    const category = document.getElementById('budgetCategory').value;
    const currentSpending = document.getElementById('currentSpending').value;
    const moneyLimit = document.getElementById('moneyLimit').value;

    const data = {
        category : category,
        currentSpending : currentSpending,
        moneyLimit : moneyLimit
    }

    const updatedBudgetData = await updateBudget(id, category, data);
    console.log(updatedBudgetData);
    alert("Budget updated successfully!");
    //location.reload();
}
async function updateGoalFunction(){
    event.preventDefault();
    const id = document.getElementById('id').value;
    const goalName = document.getElementById('goalName').value;
    const currentAmount = document.getElementById('currentAmount').value;
    const targetAmount = document.getElementById('targetAmount').value;
    const deadLine = document.getElementById('deadLine').value;

    const data = {
        goalName : goalName,
        currentAmount : currentAmount,
        targetAmount : targetAmount,
        deadLine : deadLine
    }

    const updatedGoalData = await updateGoal(id, goalName, data);
    console.log(updatedGoalData);
    alert("Goal updated successfully!");
    //location.reload();
}






async function addIncomeFunction(){
    event.preventDefault();
    const id = document.getElementById('id').value;
    const amount = document.getElementById('amount').value;
    const source = document.getElementById('source').value;
    const date = document.getElementById('date').value;

    const data = {
        amount : amount,
        source : source,
        date : date
    }

    const addedIncomeData = await addIncome(id, data);
    alert("Income added successfully!");
    location.reload();
}
async function addExpenseFunction(){
    event.preventDefault();
    const id = document.getElementById('id').value;
    const amount = document.getElementById('expenseAmount').value;
    const category = document.getElementById('category').value;
    const date = document.getElementById('expenseDate').value;

    const data = {
        category : category,
        amount : amount,
        date : date
    }
    console.log(data);

    const addedExpneseData = await addExpense(id, data);
    alert("Expense added successfully!");
    location.reload();
}
async function addBudgetFunction(){
    event.preventDefault();
    const id = document.getElementById('id').value;
    const budgetCategory = document.getElementById('budgetCategory').value;
    const currentSpending = document.getElementById('currentSpending').value;
    const moneyLimit = document.getElementById('moneyLimit').value;

    const data = {
        category : budgetCategory,
        currentSpending : currentSpending,
        moneyLimit : moneyLimit
    }
    console.log(data);

    const addedBudgetData = await addBudget(id, data);
    console.log(addedBudgetData);
    alert("Budget added successfully!");
    //location.reload();
}
async function addGoalFunction(){
    event.preventDefault();
    const id = document.getElementById('id').value;
    const goalName = document.getElementById('goalName').value;
    const currentAmount = document.getElementById('currentAmount').value;
    const targetAmount = document.getElementById('targetAmount').value;
    const deadLine = document.getElementById('deadLine').value;

    const data = {
        goalName : goalName,
        currentAmount : currentAmount,
        targetAmount : targetAmount,
        deadLine : deadLine
    }
    console.log(data);

    const addedGoalData = await addGoal(id, data);
    console.log(addedGoalData);
    alert("Goal added successfully!");
    //location.reload();
}










async function signupFunction(){
    event.preventDefault();
    const id = document.getElementById('userid').value;
    const email = document.getElementById('email').value;
    const phoneNo = document.getElementById('phoneNo').value;
    const password = document.getElementById('password').value;

    const data = {
        id : id,
        email : email,
        phoneNo : phoneNo,
        password : password
    }

    const response = await signupUser(data);
    alert("User with id " + id + " added successfully! Please login with your id to add other details");
}
async function formSubmitFunction(){
    event.preventDefault();

    const id = document.getElementById('id').value;
    const data = await getUserDetailsById(id);


    const container = document.getElementById('containerElements');

    const userEmail = data.email;
    let str = '';
    for(let i=0; i<userEmail.length; i++){
        if(userEmail[i] === '.') break;
        str += userEmail[i];
    }

    let htmlContent = `<h1> Welcome ${str}! </h1>
    <h2> Your Details: </h2>
    Id: ${data.id}<br>
    Email: ${data.email}<br>
    Phone No: ${data.phoneNo}<br><hr>
    <h3> Your Income Details: </h3>`;
    
    data.incomes.forEach(income => {
        htmlContent += `Income ID: ${income.id}<br>
        Amount: ${income.amount}<br>
        Source: ${income.source}<br>
        Date: ${income.date}<br>
        <button onclick="deleteIncomeFunction(${data.id}, '${income.source}')"> Delete Income </button><br><br>`;
    });
    
    htmlContent += ` Add/Update Income: <br>
    <form onsubmit = "addIncomeFunction()">
        <label name = "id"> Id: </label>
        <input type = "text" name = "id" id = "id" placeholder = "Enter your id here">

        <label name = "amount"> Amount: </label>
        <input type = "text" name = "amount" id = "amount" placeholder = "Enter your amount here">

        <label name = "source"> Source: </label>
        <input type = "text" name = "source" id = "source" placeholder = "Enter your source here">

        <label name = "date"> Date: </label>
        <input type = "date" name = "date" id = "date">

        <button type = "submit"> Add Income </button>
        <button type = "submit" onclick="updateIncomeFunction()"> Update Income </button>
    </form> <br> <hr>`

    htmlContent += `<h3> Your Expense details:</h3>`
    data.expenses.forEach(expense => {
        htmlContent += `Expense Id: ${expense.id}<br>
        Category: ${expense.category}<br>
        Amount: ${expense.amount}<br>
        Date: ${expense.date}<br>
        <button onclick="deleteExpenseFunction(${data.id}, '${expense.category}')"> Delete Expense </button><br><br>`
    })

    htmlContent += ` Add/Update Expense: <br>
    <form onsubmit = "addExpenseFunction()">
        <label name = "id"> Id: </label>
        <input type = "text" name = "id" id = "id" placeholder = "Enter your id here">

        <label name = "category"> Category: </label>
        <input type = "text" name = "category" id = "category" placeholder = "Enter your category here">

        <label name = "expenseAmount"> Amount: </label>
        <input type = "text" name = "expenseAmount" id = "expenseAmount" placeholder = "Enter your amount here">

        <label name = "expenseDate"> Date: </label>
        <input type = "date" name = "expenseDate" id = "expenseDate">

        <button type = "submit"> Add Expnese </button>
        <button type = "submit" onclick="updateExpenseFunction()"> Update Expense </button>
    </form> <br> <hr>`
    
    htmlContent += `<h3> Your Budget details:</h3>`
    data.budgets.forEach(budget => {
        htmlContent += `Budget Id: ${budget.id}<br>
        Category: ${budget.category}<br>
        Current Spending: ${budget.currentSpending}<br>
        Money Limit: ${budget.moneyLimit}<br>
        <button onclick="deleteBudgetFunction(${data.id}, '${budget.category}')"> Delete Budget </button><br><br>`
    })
    htmlContent += ` Add/Update Budget: <br>
    <form onsubmit = "addBudgetFunction()">
        <label name = "id"> Id: </label>
        <input type = "text" name = "id" id = "id" placeholder = "Enter your id here">

        <label name = "budgetCategory"> Category: </label>
        <input type = "text" name = "budgetCategory" id = "budgetCategory" placeholder = "Enter your category here">

        <label name = "currentSpending"> Current Spending: </label>
        <input type = "text" name = "currentSpending" id = "currentSpending" placeholder = "Enter your amount here">

        <label name = "moneyLimit"> Money Limit </label>
        <input type = "text" name = "moneyLimit" id = "moneyLimit" placeholder = "Enter your limit here">

        <button type = "submit"> Add Budget </button>
        <button type = "submit" onclick="updateBudgetFunction()"> Update Budget </button>
    </form> <br> <hr>`

    htmlContent += `<h3> Your Goal details:</h3>`
    data.goals.forEach(goal => {
        htmlContent += `Goal Id: ${goal.id}<br>
        Goal Name: ${goal.goalName}<br>
        Current Amount: ${goal.currentAmount}<br>
        Money Limit: ${goal.targetAmount}<br>
        Date: ${goal.deadLine}<br>
        <button onclick="deleteGoalFunction(${data.id}, '${goal.goalName}')"> Delete Goal </button><br><br>`
    })
    htmlContent += ` Add/Update Goal: <br>
    <form onsubmit = "addGoalFunction()">
        <label name = "id"> Id: </label>
        <input type = "text" name = "id" id = "id" placeholder = "Enter your id here">

        <label name = "goalName"> Gaol Name: </label>
        <input type = "text" name = "goalName" id = "goalName" placeholder = "Enter your goal name here">

        <label name = "currentAmount"> Current Amoount: </label>
        <input type = "text" name = "currentAmount" id = "currentAmount" placeholder = "Enter your current amount here">

        <label name = "targetAmount"> Target Amount </label>
        <input type = "text" name = "targetAmount" id = "targetAmount" placeholder = "Enter your limit here">

        <label name = "deadLine"> Deadline: </label>
        <input type = "date" name = "deadLine" id = "deadLine">

        <button type = "submit"> Add Goal </button>
        <button type = "submit" onclick="updateGoalFunction()"> Update Goal </button>
    </form>`

    container.innerHTML = htmlContent;
    
}