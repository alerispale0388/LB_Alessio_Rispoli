const URL = 'http://localhost:8080';
let entries = [];
let showOrHide = 0;

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const registration = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {};
    user['email'] = formData.get('email');
    user['password'] = formData.get('password');

    fetch(`${URL}/auth/registration`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then((result) => {
        result.json().then((user) => {
            entries.push(user);
            renderEntries();
        });
    });
};

const login = (e) => {
    e.preventDefault();
    showMainPage();
    const formData = new FormData(e.target);
    const loginViewModel = {};
    loginViewModel['email'] = formData.get('loginEmail');
    loginViewModel['password'] = formData.get('loginPassword');

    fetch(`${URL}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginViewModel)
    }).then((response)=> {
        response.json().then((loginViewModel) =>{
            entries.push(loginViewModel);
            console.log(response);
            renderEntries();
        });
    });
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));

    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        result.json().then((entry) => {
            entries.push(entry);
            renderEntries();
        });
    });
};

function deleteEntry(id) {

    console.log(id)

    const response = fetch( `${URL}/entries/${id}`, {
        method: 'DELETE',
    }).then((result) => {
        indexEntries();
    })

}

const updateEntry = (e) => {
    const formData = new FormData(e.target);
    const entry = {};
    entry['id'] = document.getElementById("id").value
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDateUpdate'), formData.get('checkInTimeUpdate'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDateUpdate'), formData.get('checkOutTimeUpdate'));

    fetch(`${URL}/entries`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        indexEntries();
    });
}

const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        const button = document.createElement('button');
        button.innerHTML = "Delete";
        button.id = entry.id;
        button.onclick = function () { deleteEntry(this.id) };
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(createCell(entry.project.name));
        row.appendChild(createCell(entry.project.activities.name));
        row.appendChild(createCell(entry.user.email));
        row.appendChild(button)
        display.appendChild(row);
    });
};

function showLogin() {
    document.getElementById("loginPage").style.display = 'block';
    document.getElementById("mainPage").style.display = 'none';
}

function showMainPage() {
    document.getElementById("loginPage").style.display = 'none';
    document.getElementById("mainPage").style.display = 'block';
}


document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    indexEntries();
});

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#updateEntryForm');
    createEntryForm.addEventListener('submit',updateEntry );
    indexEntries();
});

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#registration');
    createEntryForm.addEventListener('submit',registration );
    indexEntries();
});

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#login');
    createEntryForm.addEventListener('submit',login );
    indexEntries();
});