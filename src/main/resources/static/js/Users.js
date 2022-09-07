// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUsers();
  $('#Users').DataTable();
  updateUserEmail();
});

function updateUserEmail(){
    document.getElementById('email-user').outerHTML = localStorage.email;
}

 async function loadUsers(){

     const request = await fetch('api/users',{
     method:  'GET',
     headers:  {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
            }
    });
    const users = await request.json();

    console.log(users);

    let ListHTML= '';
    for (let user of users)
    {
    let delButton = '<a href="#" onclick="deleteUser('+user.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
    let userHTML = '<tr><td>'+user.name+'</td><td>'+user.id+'</td><td>'+user.email+ '</td><td>'+user.phone+'</td><td>'+user.password+'</td><td>'+delButton+'</td></tr>';
    ListHTML += userHTML;
    }
    document.querySelector('#Users tbody').outerHTML = ListHTML;
  }

async function deleteUser(id){
    if(!confirm('Would you like to delete this user?')){
     return;
    }
    const request = await fetch('api/users/' + id,{
         method:  'DELETE',
         headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
              }
        });

    location.reload()

  }
