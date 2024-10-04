function calcMontant(){
    const inputQte =document.querySelector("#quantite");
    const inputPrix =document.querySelector("#prix");
    const inputMontant =document.querySelector("#montant");
    //Mettre à jour le montant total
    inputMontant.value = inputQte.value*inputPrix.value;
}

function selectAll(){
    const inputAll =document.querySelector("#all");
    if(inputAll.value==="X"){
        inputAll.value = "O"
    }else{
        inputAll.value = "X"
    }
    var selectRadios = document.querySelectorAll('.select');
    console.log(selectRadios);
    selectRadios.forEach(function(radio) {
        radio.checked = !radio.checked;
    });
}

