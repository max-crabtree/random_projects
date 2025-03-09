var inputFirstNum = document.getElementById("inputFirstNum");
var inputSecondNum = document.getElementById("inputSecondNum");
var addButton = document.getElementById("addButton");
var subtractButton = document.getElementById("subtractButton");
var multiplyButton = document.getElementById("multiplyButton");
var divideButton = document.getElementById("divideButton");
var resultNum = document.getElementById("resultNum");
if (!inputFirstNum || !inputSecondNum || !addButton || !subtractButton || !multiplyButton || !divideButton || !resultNum) {
    throw new Error("Required elements are missing from the DOM");
}
function getInputs() {
    var first = parseFloat(inputFirstNum.value);
    var second = parseFloat(inputSecondNum.value);
    if (isNaN(first) || isNaN(second)) {
        resultNum.textContent = "Please enter valid numbers";
        return null;
    }
    return [first, second];
}
function add() {
    var inputs = getInputs();
    if (!inputs)
        return;
    var first = inputs[0], second = inputs[1];
    resultNum.textContent = (first + second).toString();
}
function sub() {
    var inputs = getInputs();
    if (!inputs)
        return;
    var first = inputs[0], second = inputs[1];
    resultNum.textContent = (first - second).toString();
}
function mult() {
    var inputs = getInputs();
    if (!inputs)
        return;
    var first = inputs[0], second = inputs[1];
    resultNum.textContent = (first * second).toString();
}
function div() {
    var inputs = getInputs();
    if (!inputs)
        return;
    var first = inputs[0], second = inputs[1];
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
