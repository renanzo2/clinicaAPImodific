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