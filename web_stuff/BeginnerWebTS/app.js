var numberInput = document.getElementById("numberInput");
var printButton = document.getElementById("printButton");
var printedNumber = document.getElementById("printedNumber");
function printEnteredNumber() {
    var number = parseFloat(numberInput.value);
    printedNumber.textContent = number.toString();
}
printButton.addEventListener("click", printEnteredNumber);
