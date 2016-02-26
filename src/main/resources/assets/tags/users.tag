<users>
    <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-1" id="div-manage-users">
        <div class="panel panel-aptifooty">
            <div class="panel-heading">
                <h1 class="panel-title">Users</h1>
            </div>

            <div class="panel-body">
                <table id="tbl-users" class="display" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                    </tr>
                    </tfoot>
                </table>

            </div>
        </div>
    </div>
    <script>
        this.on('mount', function(){
            var currentUser=CORS.session.get('user');
            console.log(currentUser);

            $('#tbl-users').DataTable( {
                "ajax": {
                    "url": "http://localhost:8080/api/aptifootball/user/list",
                    "dataSrc": ""
                },
                "columns": [
                    { "data": "username" },
                    { "data": "email" },
                ]
            });
            if(currentUser){
                $("#div-manage-users").show();
            } else {
                $("#div-manage-users").hide();

            }
        })
    </script>
</users>