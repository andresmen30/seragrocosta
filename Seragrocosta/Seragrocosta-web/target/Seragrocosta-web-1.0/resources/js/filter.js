/* global PF */

function filterCalendar(value) {
    var date = value.replace(/[^0-9\.]+/g, '');
    if (date.length === 8) {
        PF('dataEmpleadoWidg').filter();
    }
}   