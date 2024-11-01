document.addEventListener('DOMContentLoaded', () => {
    const loginContainer = document.querySelector('.login-container');
    const loginForm = document.getElementById('loginForm');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const passwordToggle = document.getElementById('passwordToggle');
    const errorMessage = document.getElementById('errorMessage');

    // Animation de chutes de glace
    function createSnowflake() {
        const snowflake = document.createElement('div');
        snowflake.classList.add('snowflake');

        // Position aléatoire en haut de l'écran
        snowflake.style.left = ` ${Math.random() * 100}%`;

        // Taille et opacité aléatoires
        const size = Math.random() * 6 + 2;
        snowflake.style.width = `${size}px`;
        snowflake.style.height = `${size}px`;
        snowflake.style.opacity = Math.random() * 0.7;

        // Durée et délai d'animation aléatoires
        const duration = Math.random() * 5 + 3;
        const delay = Math.random() * 2;

        snowflake.style.animation = `fall ${duration}s linear ${delay}s infinite`;

        document.body.appendChild(snowflake);

        // Supprimer le flocon après son animation
        setTimeout(() => {
            snowflake.remove();
        }, (duration + delay) * 1000);
    }

    // Créer des flocons toutes les 200ms
    setInterval(createSnowflake, 200);

    passwordToggle.addEventListener('click', () => {
        const type = passwordInput.type === 'password' ? 'text' : 'password';
        passwordInput.type = type;

        passwordToggle.classList.toggle('fa-eye');
        passwordToggle.classList.toggle('fa-eye-slash');
    });

    loginForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const username = usernameInput.value;
        const password = passwordInput.value;

        if (username === 'admin' && password === 'passer') {
            loginContainer.style.transform = 'scale(1.1)';
            loginContainer.style.opacity = '0';

            setTimeout(() => {
                window.location.href = 'dashboard.html';
            }, 300);
        } else {
            errorMessage.textContent = 'Identifiants incorrects';
            errorMessage.style.opacity = '1';

            loginContainer.classList.add('shake-animation');
            usernameInput.style.borderColor = 'var(--error-color)';
            passwordInput.style.borderColor = 'var(--error-color)';

            setTimeout(() => {
                loginContainer.classList.remove('shake-animation');
                errorMessage.style.opacity = '0';
                usernameInput.style.borderColor = '#E0E0E0';
                passwordInput.style.borderColor = '#E0E0E0';
            }, 1500);
        }
    });
});