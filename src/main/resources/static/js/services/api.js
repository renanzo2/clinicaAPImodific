export const API_URL = 'http://localhost:8080/api';

export function getAuthHeaders() {
    const token = localStorage.getItem('jwt_toen');
    if (token) {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        };
    } else {
        return {
            'Content-Type': 'application/json'
        };
    }
}

export async function handleResponse(response) {
    if (!response.ok) {
        if (response.status === 401 || response.status === 403) {
            alert("Sessão expirada ou sem permissão. Faça login novamente.");
            localStorage.removeItem('jwt_token');
            localStorage.removeItem('user_name');
            window.location.reload();
            return null;
        }

        const data = await response.json().catch(() => ({ message: 'Erro desconhecido' }));
        throw new Error(data.message || `Erro ${response.status}`);
    }
    if(response.status === 204) return null;
    return response.json();
}