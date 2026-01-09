export function toggleForm(id) {
    const element = document.getElementById(id)
    if (element) {
        element.classList.add('active');
    }
 }

export function cancelForm(id) {
    const modal = document.getElementById(id);
    if (!modal) return;

    modal.classList.remove('active');
    modal.querySelector('form')?.reset();
    const form = modal.querySelector('input[type="hidden"]');
    if(hide) hide.value = '';

    switch (id) {
        case 'donoForm':
            const cpfInput = document.getElementById('donoCpf');
            if (cpfInput) cpfInput.readOnly = false;
            break;

        case 'vetForm':
            const crmvInput = document.getElementById('vetCrmv');
            if (crmvInput) crmvInput.readOnly = false;
            break;

        case 'certForm':
            const certInput = document.getElementById('certNumeroRegistro');
            if (certNumeroRegistro) certNumeroRegistro.readOnly = false;
            break;

        case 'animalForm':
            const rfidInput = document.getElementById('animalRfid');
            if (animalRfid) animalRfid.readOnly = true;
            break;
    }
}