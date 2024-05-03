$(function(){  //Ready-funksjon, vil kjøre når siden er lastet inn
    hentAlleBilletter();
    henteEnBillett();
});

function hentAlleBilletter() {
    $.get( "/hentAlle", function( billetter ) {

    })
        .fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}

function henteEnBillett(){
    const id = window.location.search.substring(1); // kommer fra kallet i index.js
    const url = "/henteEnBillett?id="+id;
    $.get( url, function(enBillett) {

        $("#id").val(enBillett.id);
        $("#film").val(enBillett.film);
        $("#antall").val(enBillett.antall);
        $("#fornavn").val(enBillett.fornavn);
        $("#etternavn").val(enBillett.etternavn);
        $("#telefon").val(enBillett.telefon);
        $("#epost").val(enBillett.epost);
    })
        .fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}

function endreBillett() {

    const billett = {
        id : $("#id").val(),
        film : $("#film").val(),
        antall : $("#antall").val(),
        fornavn : $("#fornavn").val(),
        etternavn : $("#etternavn").val(),
        telefon : $("#telefon").val(),
        epost : $("#epost").val(),
    };

    if(ingenValideringsFeil()){
        $.post("/endre", billett, function(){
            hentAlle();
        })
            .fail(function(jqXHR) {
                const json = $.parseJSON(jqXHR.responseText);
                $("#feil").html(json.message);
            });

        window.location.href="index.html";
    }
}