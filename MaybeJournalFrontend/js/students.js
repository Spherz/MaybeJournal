"use strict";

document.addEventListener('DOMContentLoaded', function () {
    getAllStudents();
    getAllGroups();
});

function getAllStudents() {
    fetch('http://127.0.0.1:8001/api/students')
        .then(response => response.json())
        .then(students => {
            displayStudents(students);
        })
        .catch(error => console.error('Ошибка: ', error));
}

function getAllGroups() {
    fetch('http://127.0.0.1:8001/api/groups')
        .then(response => response.json()
            .then(groups => {
                displayGroups(groups);
                fillGroupsDropdown(groups);
            })
            .catch(error => console.error('Ошибка: ', error)));
}

async function getStudentGroups(studentId) {
    try {
        const response = await fetch(`http://127.0.0.1:8001/api/students/${studentId}/groups`);
        const studentGroups = await response.json();
        return studentGroups;
    } catch (error) {
        return console.error('Ошибка: ', error);
    }
}

function fillGroupsDropdown(groups) {
    const groupsDropdown = document.getElementById('groups-dropdown');

    groups.forEach(group => {
        const groupButton = document.createElement('option');

        groupButton.className = 'dropdown-item';
        groupButton.id = 'groupButton';
        groupButton.value = group.groupId;

        groupButton.innerText = group.name;

        groupsDropdown.appendChild(groupButton);
    });
}

function displayStudentGroups(studentGroups) {
    const studentGroup = document.createElement('div');
    const rowStriped = document.getElementById('row-striped');

    studentGroup.innerHTML = studentGroups.map(item => {
        return item.name;
    });

    studentGroup.className = 'col-2';

    rowStriped.appendChild(studentGroup);
}

function displayGroups(groups) {
    const groupsList = document.getElementById('groups-list');

    groups.forEach(group => {
        const button = document.createElement('a');

        button.className = 'btn btn-primary col-2 mx-3';
        button.setAttribute('data-value', group.groupId);

        button.innerText = group.name;

        button.addEventListener('click', function () {
            filterStudentByGroup(button.getAttribute('data-value'));
        });

        groupsList.appendChild(button);
    });
}

function filterStudentByGroup(groupId) {
    fetch(`http://127.0.0.1:8001/api/groups/${groupId}/students`)
        .then(response => response.json())
        .then(students => {
            displayStudents(students);
        })
        .catch(error => console.error('Ошибка: ', error));
}

function displayStudents(students) {
    const studentsList = document.getElementById('students__list');

    clearStudentsList(studentsList);

    students.forEach(student => {
        const rowStriped = document.createElement('div');
        const studentId = document.createElement('div');
        const studentFirstName = document.createElement('div');
        const studentSurName = document.createElement('div');
        const studentThirdName = document.createElement('div');
        const studentGroupNames = document.createElement('div');

        rowStriped.className = 'row striped';
        rowStriped.id = 'row-striped';

        studentId.innerHTML = student.studentId;
        studentFirstName.innerHTML = student.firstName;
        studentSurName.innerHTML = student.surName;
        studentThirdName.innerHTML = student.fatherName;
        getStudentGroups(student.studentId)
        .then(data => {
            studentGroupNames.innerHTML = data.map(function(item) {
                return item.name;
            });
        });

        studentId.className = 'col-2';
        studentFirstName.className = 'col-2';
        studentSurName.className = 'col-2';
        studentThirdName.className = 'col-2';
        studentGroupNames.className = 'col-2';

        rowStriped.appendChild(studentId);
        rowStriped.appendChild(studentSurName);
        rowStriped.appendChild(studentFirstName);
        rowStriped.appendChild(studentThirdName);
        rowStriped.appendChild(studentGroupNames);

        studentsList.appendChild(rowStriped);
    });
}

let studentGroups = [];
document.getElementById("groups-dropdown").addEventListener('change', function () {
    console.log("Select value: ", this.value);
    studentGroups.push(this.value);
});
function addStudent() {
    const inputFirstName = document.getElementById("form-firstname").value;
    const inputSurName = document.getElementById("form-surname").value;
    const inputFatherName = document.getElementById("form-fathername").value;

    fetch('http://127.0.0.1:8001/api/students', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            firstName: inputFirstName,
            surName: inputSurName,
            fatherName: inputFatherName,
            groupIds: studentGroups
        })
    })
        .then(response => response.json())
        .then(data => {
            addSingleStudent(data.studentId);
            studentGroups = [];
        });
}

function addSingleStudent(studentId) {
    const studentsList = document.getElementById('students__list');

    fetch(`http://127.0.0.1:8001/api/students/${studentId}`)
        .then(response => response.json())
        .then(students => {
            generateRow(students, studentsList);
        })
        .catch(error => console.error('Ошибка: ', error));
}

function generateRow(students, studentsList) {
    const rowStriped = document.createElement('div');
    const studentId = document.createElement('div');
    const studentFirstName = document.createElement('div');
    const studentSurName = document.createElement('div');
    const studentThirdName = document.createElement('div');
    const studentGroup = document.createElement('div');

    rowStriped.className = 'row striped';

    let studentGropNames = students.groupIds;

    studentId.innerHTML = students.studentId;
    studentFirstName.innerHTML = students.firstName;
    studentSurName.innerHTML = students.surName;
    studentThirdName.innerHTML = students.fatherName;
    studentGroup.innerHTML = studentGropNames.map(function (item) {
        return item.name;
    });

    studentId.className = 'col-2';
    studentFirstName.className = 'col-2';
    studentSurName.className = 'col-2';
    studentThirdName.className = 'col-2';
    studentGroup.className = 'col-2';

    rowStriped.appendChild(studentId);
    rowStriped.appendChild(studentSurName);
    rowStriped.appendChild(studentFirstName);
    rowStriped.appendChild(studentThirdName);
    rowStriped.appendChild(studentGroup);

    studentsList.appendChild(rowStriped);
}

function clearStudentsList(studentsList) {
    while (studentsList.firstChild) {
        studentsList.removeChild(studentsList.firstChild);
    }
}

document.getElementById('add-student').addEventListener('click', function () {
    addStudent();
});
