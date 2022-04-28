document.getElementById('selector').onchange = function () {
    let seleccion = document.getElementById('selector').value;
    if (seleccion === "SI") {
        let text = document.getElementById("pasoParametroSI/NO");
        text.classList.add('visible');
    } else {
        let text = document.getElementById("pasoParametroSI/NO");
        text.classList.remove('visible');
        /* var inputFotoPerfil = document.getElementById("pasoParametroSI/NO");
        inputFotoPerfil.value = "${alumno.foto}"; */
    }

}



  
