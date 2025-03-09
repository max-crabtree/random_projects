const numberInput = document.getElementById("numberInput") as HTMLInputElement
const printButton = document.getElementById("printButton") as HTMLButtonElement
const printedNumber = document.getElementById("printedNumber") as HTMLOutputElement

function printEnteredNumber(): void {
    const number = parseFloat(numberInput.value);
    printedNumber.textContent = number.toString();
}

printButton.addEventListener("click", printEnteredNumber);
