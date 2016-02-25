$(document).ready(function () {
    $(".data").mask("00/00/0000");
    $(".hora").mask("00:00:00");
    $(".dataEhora").mask("00/00/0000 00:00:00");
    $(".cep").mask("00000-000");
    $(".telefone").mask("0000-0000");
    $(".ddTelefone").mask("(00) 0000-0000");
    $(".ddiTelefone").mask("(000) 000-0000");
    $(".mix").mask("AAA 000-S0S");
    $(".cpf").mask("000.000.000-00", {reverse: true});
    $(".moeda").mask("000.000.000.000.000,00", {reverse: true});
    $(".moeda2").mask("#.##0,00", {reverse: true, maxlength: false});
    $(".porcentagem").mask("##0,00%", {reverse: true});
    $(".soHora").mask("00:00");

});


