var clearButton = document.querySelector('clear-button');
var taskInputs = document.querySelectorAll('task-input');
function clearTaskInput() {
    taskInputs.forEach(function (task) {
        task.value = "";
    });
}
clearButton === null || clearButton === void 0 ? void 0 : clearButton.addEventListener('click', function () { return clearTaskInput; });
