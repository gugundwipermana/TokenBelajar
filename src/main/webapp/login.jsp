<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>

        <span id="status"></span>

        <input type="text" id="email" />
        <input type="text" id="password" />
        <button id="login" >Login</button>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript">
	        
	        $("#login").click(function(){

                var email = document.getElementById('email').value;
                var password = document.getElementById('password').value;

                $.ajax({
                    cache: false,
                    crossDomain: true,
                    dataType: "json",
                    url: '/TokenBelajar-1.0-SNAPSHOT/api/auth/login',
                    headers: {
                        'Content-Type':'application/json'
                    },
                    type: "POST",
                    data: JSON.stringify({
                        "email": email,
                        "password": password
                    }),
                    success: function(data) {
                        $("#status").html('<p>'+data.status+'</p>');
                        sessionStorage.auth_token = data.token;

                        if(data.status == 'success') {
                            window.location = "/TokenBelajar-1.0-SNAPSHOT";
                        }
                    }
                });

            })

        </script>
    </body>
</html>
