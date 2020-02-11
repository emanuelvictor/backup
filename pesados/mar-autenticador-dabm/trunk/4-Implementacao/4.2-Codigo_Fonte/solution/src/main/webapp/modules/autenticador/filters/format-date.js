/**
 * Created by Boz on 31/07/2015.
 */
/**
 * Filtro para formatar data
 *
 */
angular.module('autenticador').
    filter("date", function () {
        moment.locale("pt-br");
        return function (date) {
            if (date) {
                return moment(new Date(date)).format('DD/MM/YYYY hh:mm');
            }
            return "";
        };
    });