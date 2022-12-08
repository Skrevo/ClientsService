//PURE JS
document.addEventListener('DOMContentLoaded',function () {
    document.getElementById('submitAddClientForm').onclick = function (ev){
        ev.preventDefault()
        ajaxSubmitForm()
    }
})

function ajaxSubmitForm () {
    let form = document.getElementById('addClientForm')
    let formData = new FormData(form)
    let json = JSON.stringify(Object.fromEntries(formData))
    //
    let request = new XMLHttpRequest()
    request.open('post','/rest/addClientForm')
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8')
    request.send(json)
    request.addEventListener('readystatechange', function () {
        if (request.readyState !== 4)
            return
        if (request.status === 200){
            let clients = JSON.parse(request.responseText)
            clients = {'clients': clients}
        }
    })
}