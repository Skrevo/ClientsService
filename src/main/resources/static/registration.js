function submitRegForm() {
    let form = document.forms.namedItem('registrationForm')
    let pass = form.elements.namedItem('password').value
    let conf = form.elements.namedItem('confirm').value
    if (pass===conf)
        form.submit()
    else
        alert('password mismatch')
}