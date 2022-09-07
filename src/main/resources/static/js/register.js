// Call the dataTables jQuery plugin
$(document).ready(function() {
 //on ready
});

 async function registerUsers(){

     let data = {};
     data.name = document.getElementById('exampleFullName').value;
     data.email = document.getElementById('exampleEmail').value;
     data.phone = document.getElementById('examplePhone').value;
     data.password = document.getElementById('exampleInputPassword').value;

     let repeatPassword = document.getElementById('exampleRepeatPassword').value;

     if (repeatPassword != data.password){
     alert('Passwords do not match!');
     return;
     }

     const request = await fetch('api/users',{
     method:  'POST',
     headers:  {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
     },
     body: JSON.stringify(data)
    });
    alert("Account created successfully!")
    window.location.href = 'login.html'
  }
