const clearButton = document.querySelector<HTMLButtonElement>('#clear-button');
const taskInputs = document.querySelectorAll<HTMLInputElement>('.task-input');

function clearTaskInput(): void {
    taskInputs.forEach(task => {
        task.value = "";
    });
}

clearButton?.addEventListener('click', () => clearTaskInput());
