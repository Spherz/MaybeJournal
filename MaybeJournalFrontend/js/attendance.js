
document.addEventListener('DOMContentLoaded', function() {
    getAllGroups();
});

function getAllGroups() {
    fetch('http://127.0.0.1:8001/api/groups')
        .then(response => response.json()
            .then(groups => {
                fillGroupsDropdown(groups);
            })
            .catch(error => console.error('Ошибка: ', error)));
}

document.getElementById('groups-dropdown').addEventListener('change', function() {
    filterStudentByGroup(this.value, getGroupLessonDate(this.value));
});

function fillGroupsDropdown(groups) {
    const groupsDropdown = document.getElementById('groups-dropdown');

    groups.forEach(group => {
        const groupButton = document.createElement('option');

        groupButton.className = 'dropdown-item';
        groupButton.id = 'groupButton';
        groupButton.value = group.groupId;
        groupButton.setAttribute('name', group.name);

        groupButton.innerText = group.name;

        groupsDropdown.appendChild(groupButton);
    });
}

function getGroupLessonDate(groupId) {
    fetch(`http://127.0.0.1:8001/api/${groupId}/generate-dates?numLessons=${2}`)
        .then(response => response.json())
        .then(dates => {
            displayGroupDates(dates);
        })
        .catch(error => console.error('Ошибка: ', error));
}

function filterStudentByGroup(groupId) {
    fetch(`http://127.0.0.1:8001/api/groups/${groupId}/students`)
        .then(response => response.json())
        .then(students => {
            displayStudents(students, getGroupLessonDate(groupId));
        })
        .catch(error => console.error('Ошибка: ', error));
}

function displayGroupDates(dates) {
    const groupLessonDates = document.getElementById('dates');
    const tempCol = document.createElement('div');
    const rowStriped = document.getElementById('row-striped');
    const maxLength = 1;

    tempCol.className = 'col-2';
    groupLessonDates.appendChild(tempCol);
    
    dates.forEach(date => {
        const lessonDate = document.createElement('div');
        const presenceCol = document.createElement('div');
        const student = document.getElementById('student');

        lessonDate.innerHTML = date;
        lessonDate.className = 'col-2';
        presenceCol.className = 'col-2';
        presenceCol.setAttribute('data-value', date);
        presenceCol.contentEditable = true;

        presenceCol.addEventListener('input', function() {
            if(presenceCol.innerText.length > maxLength) {
                presenceCol.innerText = presenceCol.innerText.slice(0, maxLength);
            }
            console.log(student.getAttribute('data-value'));
        });

        rowStriped.appendChild(presenceCol);
        groupLessonDates.appendChild(lessonDate);
    });
}

function displayStudents(students, getGroupLessonDate) {
    const studentAttendanceList = document.getElementById('students-attendance');
    const rowTableHead = document.createElement('div');

    rowTableHead.className = 'row table-head';
    rowTableHead.id = 'dates';

    clearStudentsList(studentAttendanceList);

    studentAttendanceList.appendChild(rowTableHead);

    students.forEach(student => {
        const rowStriped = document.createElement('div');
        const studentFullName = document.createElement('div');
        const presenseCol = document.createElement('div');

        rowStriped.className = 'row striped';
        rowStriped.id = 'row-striped';

        studentFullName.innerHTML = student.firstName + " " + student.surName + " " + student.fatherName;
        studentFullName.id = 'student';
        studentFullName.setAttribute('data-value', student.studentId);

        studentFullName.className = 'col-2';
        presenseCol.className = 'col-2';

        rowStriped.appendChild(studentFullName);

        studentAttendanceList.appendChild(rowStriped);
    });
}

function clearStudentsList(studentsList) {
    while (studentsList.firstChild) {
        studentsList.removeChild(studentsList.firstChild);
    }
}