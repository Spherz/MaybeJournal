
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
    getGroupLessonDate(this.value).then(() => filterStudentByGroup(this.value));
});

function test(groupId) {
    getGroupLessonDate(groupId)
    .then(dates => {
        return fetch(`http://127.0.0.1:8001/api/groups/${groupId}/students`)
        .then(response => response.json())
        .then(students => {
            displayStudents(students, dates);
        })
        .catch(error => console.error('Ошибка: ', error));
    });
}

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
    return fetch(`http://127.0.0.1:8001/api/${groupId}/generate-dates?numLessons=${2}`)
        .then(response => response.json())
        .then(dates => {
            displayGroupDates(dates);
            return dates;
        })
        .catch(error => console.error('Ошибка: ', error));
}

function filterStudentByGroup(groupId) {
    return fetch(`http://127.0.0.1:8001/api/groups/${groupId}/students`)
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


    tempCol.className = 'col-2';
    groupLessonDates.appendChild(tempCol);
    
    dates.forEach(date => {
        const lessonDate = document.createElement('div');

        lessonDate.innerHTML = date;
        lessonDate.className = 'col-2';

        groupLessonDates.appendChild(lessonDate);
    });
}

function displayStudents(students, dates) {
    const studentAttendanceList = document.getElementById('students-attendance');
    const rowTableHead = document.createElement('div');
    const maxLength = 1;

    rowTableHead.className = 'row table-head';
    rowTableHead.id = 'dates';

    clearStudentsList(studentAttendanceList);

    studentAttendanceList.appendChild(rowTableHead);

    students.forEach(student => {
        const rowStriped = document.createElement('div');
        const studentFullName = document.createElement('div');

        rowStriped.className = 'row striped';
        rowStriped.id = 'row-striped';

        studentFullName.innerHTML = student.firstName + " " + student.surName + " " + student.fatherName;
        studentFullName.id = 'student';
        studentFullName.setAttribute('data-value', student.studentId);

        studentFullName.className = 'col-2';

        dates.then((data) => {
            data.forEach((item) => {
                const presenceCol = document.createElement('div');

                presenceCol.className = 'col-2';
                presenceCol.setAttribute('data-value', item);
                presenceCol.contentEditable = true;

                presenceCol.addEventListener('input', function() {
                    if(presenceCol.innerText.length > maxLength) {
                        presenceCol.innerText = presenceCol.innerText.slice(0, maxLength);
                    }
                    console.log(studentFullName.getAttribute('data-value'));
                });

                rowStriped.appendChild(presenceCol);
            });
        });

        rowStriped.appendChild(studentFullName);

        studentAttendanceList.appendChild(rowStriped);
    });
}

function clearStudentsList(studentsList) {
    while (studentsList.firstChild) {
        studentsList.removeChild(studentsList.firstChild);
    }
}