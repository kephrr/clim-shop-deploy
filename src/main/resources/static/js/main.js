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
    const selectRadios = document.querySelectorAll('.select');
    console.log(selectRadios);
    selectRadios.forEach(function(radio) {
        radio.checked = !radio.checked;
    });
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
    let prix = parseInt(montant.value);
    if (check.checked){
        montant.value = prix -35000
    }else{
        montant.value = prix + 35000
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