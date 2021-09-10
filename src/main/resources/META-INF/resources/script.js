const URL = 'http://localhost:8080';
let users = [];
let activities = [];
let specialUser = [];

const registration = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {};
    user['email'] = formData.get('registrationEmail');
    user['password'] = formData.get('registrationPassword');

    console.log(user.email);
    fetch(`${URL}/auth/registration`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then((result) => {
        result.json().then((user) => {
            users.push(user);
            renderUser();
        });
    });
};

const login = async (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const loginViewModel = {};
    loginViewModel['email'] = formData.get('loginEmail');
    loginViewModel['password'] = formData.get('loginPassword');

    const response = await fetch(`${URL}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginViewModel)
    })
    let jwt = await response.json();
    if (jwt != null) {
       await localStorage.setItem('token', jwt.token);
        hideOrShowLogin();
    } else {

    }
};

function logout() {
    localStorage.clear();
}


const createUser = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {};
    user['email'] = formData.get('email');
    user['password'] = formData.get('password');

    fetch(`${URL}/user`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        body: JSON.stringify(user)
    }).then((result) => {
        result.json().then((user) => {
            users.push(user);
            renderUser();
        });
    });
};


const updateUser = (e) => {
    const formData = new FormData(e.target);
    const user = {};
    user['id'] = document.getElementById("id").value
    user['email'] = formData.get('emailUpdate');
    user['password'] = formData.get('passwordUpdate');

    fetch(`${URL}/user`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        body: JSON.stringify(user)
    }).then(() => {
        indexUser();
    });
}

function deleteUser(id) {
    fetch( `${URL}/user/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }).then(() => {
        indexUser();
        indexSpecialUser();
    })
}

const indexUser = async () => {
    await fetch(`${URL}/user`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }).then((result) => {
        result.json().then((result) => {
            users = result;
            renderUser();
        });
    });
    renderUser();
};

const createActivity = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const activity = {};
    activity['name'] = formData.get('name');
    activity['project_id'] = formData.get('projectId');

    fetch(`${URL}/activities`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        body: JSON.stringify(activity)
    }).then((result) => {
        result.json().then((activity) => {
            activities.push(activity);
            indexActivity();
        });
    });
};


const updateActivity = (e) => {
    const formData = new FormData(e.target);
    const activity = {};
    activity['id'] = formData.get("activityId")
    activity['name'] = formData.get('nameUpdate');
    activity['project_id'] = formData.get('projectIdUpdate');

    console.log(activity)

    fetch(`${URL}/activities`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        body: JSON.stringify(activity)
    }).then(() => {
        indexActivity();
    });
}

function deleteActivity(id) {
    fetch( `${URL}/activities/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }).then(() => {
        indexActivity();
    })
}

const indexActivity = () => {
    fetch(`${URL}/activities`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }).then((result) => {
        result.json().then((result) => {
            activities = result;
            renderActivity();
        });
    });
    renderActivity();
};

const indexSpecialUser = () => {
    fetch(`${URL}/user/special`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }).then((result) => {
        result.json().then((result) => {
            specialUser = result;
            renderSpecialUser();
        });
    });
    renderSpecialUser();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const renderUser = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    users.forEach((user) => {
        const row = document.createElement('tr');
        const button = document.createElement('button');
        button.innerHTML = "Delete";
        button.id = user.id;
        button.onclick = function () { deleteUser(this.id) };
        row.appendChild(createCell(user.id));
        row.appendChild(createCell(user.email));
        row.appendChild(button)
        display.appendChild(row);
    });
};

const renderActivity = () => {
    const displayActivity = document.querySelector('#activityDisplay');
    displayActivity.innerHTML = '';
    activities.forEach((activity) => {
        const row = document.createElement('tr');
        const button = document.createElement('button');
        button.innerHTML = "Delete";
        button.id = activity.id;
        button.onclick = function () { deleteActivity(this.id) };
        row.appendChild(createCell(activity.id))
        row.appendChild(createCell(activity.name));
        row.appendChild(createCell(activity.project_id));
        row.appendChild(button)
        displayActivity.appendChild(row);
    });
};

const renderSpecialUser = () => {
    const displaySpecialUser = document.querySelector('#specialUser');
    displaySpecialUser.innerHTML = '';
    specialUser.forEach((specialUser) => {
        const row = document.createElement('tr');
        const button = document.createElement('button');
        button.innerHTML = "Delete";
        button.id = specialUser.id;
        button.onclick = function () { deleteUser(this.id) };
        row.appendChild(createCell(specialUser.id))
        row.appendChild(createCell(specialUser.email));
        row.appendChild(button)
        displaySpecialUser.appendChild(row);
    });
};

function hideOrShowLogin() {
    if (localStorage.getItem('token') != null) {
        document.getElementById("loginPage").style.display = 'none';
        document.getElementById("mainPage").style.display = 'block';
        indexUser();
        indexActivity();
        indexSpecialUser();

    } else {
        document.getElementById("loginPage").style.display = 'block';
        document.getElementById("mainPage").style.display = 'none';
    }
}

document.addEventListener('DOMContentLoaded', function(){
    const createUserForm = document.querySelector('#createUserForm');
    createUserForm.addEventListener('submit', createUser);

    const updateUserForm = document.querySelector('#updateUserForm');
    updateUserForm.addEventListener('submit',updateUser );

    const createActivityForm = document.querySelector('#createActivityForm');
    createActivityForm.addEventListener('submit', createActivity);

    const updateActivityForm = document.querySelector('#updateActivityForm');
    updateActivityForm.addEventListener('submit',updateActivity );

    const createRegistration = document.querySelector('#registration');
    createRegistration.addEventListener('submit',registration );

    const createEntryForm = document.querySelector('#login');
    createEntryForm.addEventListener('submit',login );
    hideOrShowLogin();
    indexUser();
    indexActivity();

    const createLogout = document.querySelector('#buttonForLogout');
    createLogout.addEventListener('submit',logout );
    indexUser();
    indexActivity();
    indexSpecialUser();
    hideOrShowLogin();
});