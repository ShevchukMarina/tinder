<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                                <tr>
                                    <td width="10">
                                        <div class="avatar-img">
                                            <img class="img-circle" src="https://loremflickr.com/200/200/face?random=${users[0].getId()}" />
                                        </div>
                                    </td>
                                    <td class="align-middle">
                                        <h4>${users[0].getName()}</h4>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <form action="/users" method="post">
                            <button class="btn btn-lg btn-primary btn-block" id="button-yes" type="submit" onclick="document.getElementById('button-name').value='yes'">Yes</button>
                            <button class="btn btn-lg btn-primary btn-block" id="button-no" type="submit" onclick="document.getElementById('button-name').value='no'">No</button>
                            <input type="text" id="button-name" name="button_name" value="" hidden>
                            <input type="text" name="user_name" value="${users[0].getName()}" hidden>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>