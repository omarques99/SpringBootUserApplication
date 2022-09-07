// Call the dataTables jQuery plugin
$(document).ready(function() {
 //on ready
});

 async function Login(){

     let data = {};

     data.email = document.getElementById('exampleEmail').value;

     data.password = document.getElementById('exampleInputPassword').value;

     const request = await fetch('api/login',{
     method:  'POST',
     headers:  {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
     },
     body: JSON.stringify(data)
    });
    const response = await request.text();
    if(response != 'FAIL'){
    localStorage.token = response;
    localStorage.email = data.email;

    //Redirect if login was successful
    //            //
    //  IMPORTANT //
    //            //
    window.location.href = 'users.html'
    }else{
    alert("Authentication failed");
    }

  }