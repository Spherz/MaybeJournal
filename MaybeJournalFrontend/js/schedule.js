document.addEventListener('DOMContentLoaded', function() {
    getAllGroups();
});

const groupName = document.getElementById('group-name');

function getAllGroups() {
    fetch('http://127.0.0.1:8001/api/groups')
        .then(response => response.json()
            .then(groups => {
                fillGroupsDropdown(groups);
            })
            .catch(error => console.error('Ошибка: ', error)));
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

document.getElementById('groups-dropdown').addEventListener('change', function() {
    getGroupDaysOfWeek(this.value);
    groupName.innerText = 'Группа: ' + this.options[this.selectedIndex].getAttribute('name');
});

function getGroupDaysOfWeek(groupId) {
    fetch(`http://127.0.0.1:8001/api/schedule/${groupId}`)
    .then(response => response.json())
    .then(data => {
        displayGroupDayOfWeek(data);
    })
    .catch(error => console.error('Ошибка', error));
}

function displayGroupDayOfWeek(schedules) {
    
    const daysOfWeek = document.getElementById('days-of-week');

    clearScheduleList(daysOfWeek);

    schedules.forEach(schedule => {
        const dayOfWeek = document.createElement('div');
        dayOfWeek.innerHTML = schedule.dayOfWeek;
    
        dayOfWeek.className = 'fs-3 text-center bg-light rounded w-100';
        daysOfWeek.appendChild(dayOfWeek);
    });
}

function clearScheduleList(scheduleList) {
    while (scheduleList.firstChild) {
        scheduleList.removeChild(scheduleList.firstChild);
    }
    const scheduleListTitle = document.createElement('h3');
    scheduleListTitle.className = 'text-white';
    scheduleListTitle.innerText = 'День недели';
    scheduleList.appendChild(scheduleListTitle);
}