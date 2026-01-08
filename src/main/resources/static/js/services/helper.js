function toggleForm(id) { document.getElementById(id).classList.add('active'); }

function cancelForm(id) {
    const form = document.getElementById(id);
    form.classList.remove('active');
    form.querySelector('form').reset();
    const hide = form.querySelector('input[type="hidden"]');
    if(hide) hide.value = '';

    if(id === 'donoForm') document.getElementById('donoCpf').readOnly = false;
    if(id === 'vetForm') document.getElementById('vetCrmv').readOnly = false;
    if(id === 'certForm') document.getElementById('certNumeroRegistro').readOnly = false;
    if(id === 'animalForm') document.getElementById('animalRfid').readOnly = true;
}