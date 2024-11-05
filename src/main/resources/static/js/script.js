document.addEventListener('DOMContentLoaded', () => {
    // Animation des carousels
    const carousels = document.querySelectorAll('.carousel');

    carousels.forEach(carousel => {
        const items = carousel.querySelectorAll('.carousel-item');
        if (!items.length) return;

        // Variables pour le défilement tactile
        let touchStartX = 0;
        let touchEndX = 0;

        // Configuration du carousel
        let currentIndex = 0;
        let isAnimating = false;
        const animationDuration = 500; // Durée de l'animation en ms

        // Calcule la largeur totale du carousel
        const itemWidth = items[0].offsetWidth;
        const totalWidth = itemWidth * items.length;

        // Fonction pour animer le défilement
        function animateSlide(from, to) {
            isAnimating = true;
            const start = performance.now();

            requestAnimationFrame(function animate(currentTime) {
                const elapsed = currentTime - start;
                const progress = Math.min(elapsed / animationDuration, 1);

                // Fonction d'easing pour une animation plus fluide
                const easeProgress = 1 - Math.pow(1 - progress, 4);

                const currentPos = from + (to - from) * easeProgress;
                carousel.style.transform = `translateX(${-currentPos}px)`;

                if (progress < 1) {
                    requestAnimationFrame(animate);
                } else {
                    isAnimating = false;
                }
            });
        }

        // Gestionnaire pour le défilement automatique
        function autoScroll() {
            if (!isAnimating) {
                const nextIndex = (currentIndex + 1) % items.length;
                animateSlide(currentIndex * itemWidth, nextIndex * itemWidth);
                currentIndex = nextIndex;
            }
        }

        // Événements tactiles
        carousel.addEventListener('touchstart', (e) => {
            touchStartX = e.touches[0].clientX;
        }, { passive: true });

        carousel.addEventListener('touchmove', (e) => {
            if (isAnimating) return;

            touchEndX = e.touches[0].clientX;
            const diff = touchStartX - touchEndX;
            const offset = -currentIndex * itemWidth - diff;

            // Applique la transformation avec un effet de résistance aux bords
            if ((currentIndex === 0 && diff < 0) ||
                (currentIndex === items.length - 1 && diff > 0)) {
                carousel.style.transform = `translateX(${offset / 3}px)`;
            } else {
                carousel.style.transform = `translateX(${offset}px)`;
            }
        }, { passive: true });

        carousel.addEventListener('touchend', () => {
            if (isAnimating) return;

            const diff = touchStartX - touchEndX;
            const threshold = itemWidth / 3; // Seuil de défilement

            if (Math.abs(diff) > threshold) {
                // Défilement vers la direction appropriée
                if (diff > 0 && currentIndex < items.length - 1) {
                    animateSlide(currentIndex * itemWidth, (currentIndex + 1) * itemWidth);
                    currentIndex++;
                } else if (diff < 0 && currentIndex > 0) {
                    animateSlide(currentIndex * itemWidth, (currentIndex - 1) * itemWidth);
                    currentIndex--;
                } else {
                    // Retour à la position actuelle si aux limites
                    animateSlide(currentIndex * itemWidth + diff, currentIndex * itemWidth);
                }
            } else {
                // Retour à la position actuelle si le déplacement est trop petit
                animateSlide(currentIndex * itemWidth + diff, currentIndex * itemWidth);
            }
        });

        // Démarrage du défilement automatique avec un intervalle aléatoire
        const autoScrollInterval = setInterval(autoScroll, 3000 + Math.random() * 2000);

        // Nettoyage de l'intervalle lorsque l'utilisateur quitte la page
        window.addEventListener('beforeunload', () => {
            clearInterval(autoScrollInterval);
        });
    });
});

// GESTION DE LA FAQ
const faqItems = document.querySelectorAll('.faq-item');
faqItems.forEach(item => {
    const question = item.querySelector('h3');
    const answer = item.querySelector('p');

    question.addEventListener('click', () => {
        answer.classList.toggle('show');
    });
});

// Initialisation de Lucid
document.addEventListener("DOMContentLoaded", function() {
    Lucide.init();
});
lucide.createIcons();