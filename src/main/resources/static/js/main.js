function calcMontant(){
    const inputQte =document.querySelector("#quantite");
    const inputPrix =document.querySelector("#prix");
    const inputMontant =document.querySelector("#montant");
    //Mettre à jour le montant total
    inputMontant.value = inputQte.value*inputPrix.value;
}

function returnBtnHandler(){
    const btn = document.querySelector("#return");
    const img = document.querySelector("#returnImg");

    btn.addEventListener('mouseover', () => {
        console.log('hop')
        img.src = "../../static/img/fleche-petite-gauche.png";
    });

    btn.addEventListener('mouseout', () => {
        img.src = "../../static/img/fleche-petite-gauche.svg"; // Remet l'image initiale quand la souris quitte le bouton
    });
}

function calcMontantInstall(){
    const montant =document.querySelector("#pMontant");
    const check = document.querySelector("#checkFrais");
    let price = parseInt(montant.value);
    if (check.checked){
        montant.value = price - 30500
    }else{
        montant.value = price + 30500
    }
}

function handleGuideCard() {
    const cards = document.querySelectorAll(".guide");
    console.log(cards)
    cards.forEach((c) => {
        c.addEventListener('click', () => {
            console.log('click')
            c.children[1].classList.toggle('none');
            c.children[1].classList.toggle('show');
        });
    });
}

window.onload = function() {
    console.log("La page est entièrement chargée (via un script externe) !");
    handleGuideCard();
};

