function validerFilm(){
    const film = $("#film").val();
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,45}$/;
    const ok = regexp.test(film);
    if(!ok){
        $("#feilFilm").html("Filmen må være tilgjengelig");
        return false;
    }
    else{
        $("#feilFilm").html("");
        return true;
    }
}

function validerAntall(){
    const antall = $("#antall").val();
    const regexp = /^\d{1,3}$/;
    const ok = regexp.test(antall);
    if(!ok){
        $("#feilAntall").html("Må være tall, t.om. 3 siffer");
        return false;
    }
    else{
        $("#feilAntall").html("");
        return true;
    }
}


function validerFornavn(){
    const fornavn = $("#fornavn").val();
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    const ok = regexp.test(fornavn);
    if(!ok){
        $("#feilFornavn").html("Fornavnet må bestå av 2 til 20 bokstaver");
        return false;
    }
    else{
        $("#feilFornavn").html("");
        return true;}
}

function validerEtternavn(){
    const etternavn = $("#etternavn").val();
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    const ok = regexp.test(etternavn);
    if(!ok){
        $("#feilEtternavn").html("Etternavnet må bestå av 2 til 20 bokstaver");
        return false;
    }
    else{
        $("#feilEtternavn").html("");
        return true;}
}

function validerTelefon(){
    const telefon = $("#telefon").val();
    const regexp = /^\d{8,}$/;
    const ok = regexp.test(telefon);
    if(!ok){
        $("#feilTelefon").html("Telefonnummer må bestå av 8 siffer");
        return false;
    }
    else{
        $("#feilTelefon").html("");
        return true;}
}

function validerEpost(){
    const epost = $("#epost").val();
    const regexp = /\S+@\S+\.\S+/;
    const ok = regexp.test(epost);
    if(!ok){
        $("#feilEpost").html("Epost må inneholde '@' og et domene (ex. .com)");
        return false;
    }
    else{
        $("#feilEpost").html("");
        return true;}
}

function ingenValideringsFeil(){
    return ( validerFilm() && validerAntall() && validerFornavn() && validerEtternavn() && validerTelefon() && validerEpost() );
}