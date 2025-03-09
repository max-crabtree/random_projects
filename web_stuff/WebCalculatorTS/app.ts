const inputFirstNum = document.getElementById("inputFirstNum") as HTMLInputElement;
const inputSecondNum = document.getElementById("inputSecondNum") as HTMLInputElement;
const addButton = document.getElementById("addButton") as HTMLButtonElement;
const subtractButton = document.getElementById("subtractButton") as HTMLButtonElement;
const multiplyButton = document.getElementById("multiplyButton") as HTMLButtonElement;
const divideButton = document.getElementById("divideButton") as HTMLButtonElement;
const resultNum = document.getElementById("resultNum") as HTMLOutputElement;

if (!inputFirstNum || !inputSecondNum || !addButton || !subtractButton || !multiplyButton || !divideButton || !resultNum) {
    throw new Error("Required elements are missing from the DOM");
}

function getInputs(): [number, number] | null {
    const first = parseFloat(inputFirstNum.value);
    const second = parseFloat(inputSecondNum.value);
    if (isNaN(first) || isNaN(second)) {
        resultNum.textContent = "Please enter valid numbers";
        return null;
    }
    return [first, second];
}

function add(): void {
    const inputs = getInputs();
    if (!inputs) return;
    const [first, second] = inputs;
    resultNum.textContent = (first + second).toString();
}

function sub(): void {
    const inputs = getInputs();
    if (!inputs) return;
    const [first, second] = inputs;
    resultNum.textContent = (first - second).toString();
}

function mult(): void {
    const inputs = getInputs();
    if (!inputs) return;
    const [first, second] = inputs;
    resultNum.textContent = (first * second).toString();
}

function div(): void {
    const inputs = getInputs();
    if (!inputs) return;
    const [first, second] = inputs;
    if (second === 0) {
        resultNum.textContent = "Cannot divide by 0";
        return;
    }
    resultNum.textContent = (first / second).toString();
}

addButton.addEventListener("click", add);
subtractButton.addEventListener("click", sub);
multiplyButton.addEventListener("click", mult);
divideButton.addEventListener("click", div);
