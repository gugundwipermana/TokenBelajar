<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <ul id="data-ul">

        </ul>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript">
	        
	        if(sessionStorage.auth_token == null) {
	        	window.location = "/TokenBelajar-1.0-SNAPSHOT/login.jsp";
	        } 

            getAll();

            function getAll() {
                $.ajaxSetup({
                  headers : {
                    'Content-Type':'application/json',
                    'Authorization': sessionStorage.auth_token
                  }
                });
                $.getJSON("/TokenBelajar-1.0-SNAPSHOT/api/users/all", function(data) {
                    var items = [];
                    $.each(data, function(i) {
                        items.push('<li>'+data[i].name+' ('+data[i].email+')</li>');
                    });

                    $("#data-ul").append(items);
                });
            }



        </script>
    </body>
</html>
