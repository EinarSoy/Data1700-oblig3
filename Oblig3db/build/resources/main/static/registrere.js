$(function(){  // kjøres når dokumentet er ferdig lastet
    hentAlleBilletter();
});

function hentAlleBilletter() {
    $.get( "/hentBilletter", function( billetter ) {

    })
        .fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}


function regBilletter() {
    const billett = {
        film : $("#film").val(),
        antall : $("#antall").val(),
        fornavn : $("#fornavn").val(),
        etternavn : $("#etternavn").val(),
        telefon : $("#telefon").val(),
        epost : $("#epost").val(),
    };

    if(ingenValideringsFeil()){
        $.post("/lagreBillett", billett, function(){
            hentAlle();
        });

        window.location.href="/";
    }
}

