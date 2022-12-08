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
            console.log(clients)
            const html = `
                <table>
        <tr>
            <th>ID</th>
            <th>Surname</th>
            <th>Name</th>
            <th>Patronymic</th>
            <th>Gender</th>
            <th>Email</th>
        </tr>
        {{#clients}}
        <tr>
            <td>{{id}}</td>
            <td>{{surname}}</td>
            <td>{{name}}</td>
            <td>{{patronymic}}</td>
            <td>{{gender}}</td>
            <td>{{email}}</td>
            <td>
                <form action="@{/openClientForm}" method="post">
                    <input type="hidden" name="id" value="{{id}}">
                    <input type="submit" value="&#8942;">
                </form>
            </td>
        </tr>
        {{/clients}}
    </table>
            `
            let result = mustache.render(html,clients)
            let output = document.getElementById('tableOutput')
            output.innerHTML = result
        }
    })
}