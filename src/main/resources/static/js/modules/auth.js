import { API_URL } from = 'static/js/services/api.js';

//-- AUTENTICAÇÃO E LOGIN --
export function initAuth(){
    const loginForm = document.getElementById('login-form');
    const logoutBtn = document.getElementById('logout-btn');

    if (loginForm) {
        loginForm.addEventListener('submit', realizarLogin);
    }

    if (logoutBtn) {
        logoutBtn.addEventListener('click', fazerLogout);
    }

    checkLogin();
}

export function checkLogin(){
    const creds = localStorage.getItem('clinica_creds');
    const loginScreen = document.getElementById('login-screen');
    const appScreen = document.getElementById('app-screen');

    if (creds) {
        if (loginScreen) { loginScreen.style.display = 'none'; }

        if (appScreen) { appScreen.style.display = 'flex'; }
    } else {
        if (loginScreen) loginScreen.style.display = 'flex';
        if (appScreen) appScreen.style.display = 'none';
    }
}

async function realizarLogin(e) {
    e.preventDefault();
    const user = document.getElementById('loginUser').value;
    const pass = document.getElementById('loginPass').value;
    const errorMsg = document.getElementById('loginError');

    try {
        const response = await fetch(`${API_URL}/auth/login`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json' },
            body: JSON.stringify({ login: user, senha: pass })
        });

        if (response.ok) {
            const creds = btoa(`${user}:${pass}`);
            localStorage.setItem('clinica_creds', creds);
            localStorage.setItem('clinica-user', user);

            if (errorMsg) {
                errorMsg.style.display = 'none';
            }
            checkLogin();
        } else {
            if (errorMsg) {
                errorMsg.innerText = 'Usuário ou senha inválidos!'
                errorMsg.style.display = 'block';
            }
        }
    } catch (error) {
        console.error(error);
        if (errorMsg) {
            errorMsg.innerText = "Erro ao conectar com servidor.";
            errorMsg.style.display = 'block';
        }

    }
}

function fazerLogout() {
    localStorage.removeItem('clinica_creds');
    localStorage.removeItem('clinica-user');
    window.location.reload();
}

