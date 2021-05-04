/* global PF */

/**
 * Created by chuandong on 15/11/27.
 */

function load() {

    var btns = document.querySelectorAll('#calculator span');
    var operators = ['+', '-', 'x'];
    var inputScreen = document.querySelector('#screen');
    var btnValue;
    var input;

    for (var i = 0; i < btns.length; i++) {

        var decimalAdded = false; // Flag used to avoid two decimal

        btns[i].addEventListener('click', function () {

            btnValue = this.innerHTML;
            input = inputScreen.innerHTML;

            switch (btnValue) {
                case 'Borrar':
                    inputScreen.innerHTML = '';
                    decimalAdded = false;
                    break;
                case '.':
                    if (!decimalAdded) {
                        inputScreen.innerHTML += btnValue;
                        decimalAdded = true;
                    }
                    break;
                case '+':
                case '-':
                case 'x':
                    // Last char of string
                    var lastChar = input[input.length - 1];

                    // Only add operator if input is not empty and there is no operator at the last
                    if (input != '' && operators.indexOf(lastChar) == -1)
                        inputScreen.innerHTML += btnValue;

                    // Allows minus if the string is empty. The first number could be under zero
                    else if (input == '' && btnValue == '-')
                        inputScreen.innerHTML += btnValue;

                    // Allows to represent the last operation
                    if (operators.indexOf(lastChar) > -1 && input.length > 1) {
                        inputScreen.innerHTML = input.replace(/.$/, btnValue);
                    }
                    decimalAdded = false;
                    break;
                default:
                    inputScreen.innerHTML += btnValue;
                    decimalAdded = false;
                    break;
            }
        });
    }
}

function changeConcepto(value) {
    var inputScreen = document.querySelector('#screen');
    inputScreen.innerHTML += value;

}

function saveFormula() {
    var inputScreen = document.querySelector('#screen');
    jQuery('#formConceptosNomina\\:calculator').val(inputScreen.innerHTML);
    jQuery('#formConceptosNomina\\:resultCalculator').val(inputScreen.innerHTML);
    PF('calculatorDialog').hide();

}

function preEditar(formula) {
    var inputScreen = document.querySelector('#screen');
    inputScreen.innerHTML = formula;
}

function openCalculator(){
    PF('calculatorDialog').show();
    var inputFormula = jQuery('#formConceptosNomina\\:calculator').val();
    preEditar(inputFormula);
    
}