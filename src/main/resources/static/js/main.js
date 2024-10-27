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
        montant.value = price - 35000
    }else{
        montant.value = price + 35000
    }
}

function handleLikeDislike(){
    const likeBtnAll = document.querySelectorAll(".likebtn");
    const dislikeBtnAll = document.querySelectorAll(".dislikebtn");

    likeBtnAll.forEach((likeBtn) => {
        likeBtn.addEventListener("mouseover", () =>{
            likeBtn.src = '/img/likew.png';
        })
        likeBtn.addEventListener("mouseleave", () =>{
            likeBtn.src = '/img/like.png';
        })
    })

    dislikeBtnAll.forEach((dislikeBtn) => {
        dislikeBtn.addEventListener("mouseover", () =>{
            dislikeBtn.src = '../../static/img/dislikew.png';
        })
        dislikeBtn.addEventListener("mouseleave", () =>{
            dislikeBtn.src = '../../static/img/dislike.png';
        })
    })
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

