<login>
    <div class="row centered-form login" id="div-register-login">
        <div class="col-xs-12 col-sm-8 col-sm-offset-2">
            <div class="panel panel-aptifooty">
                <div class="panel-heading">
                    <h1 class="panel-title">Aptifooty wants you!</h1>
                </div>
                <div class="panel-body" id="div-user-register">
                    <form role="form" id="frm-new-user">
                        <div class="form-group">
                            <input type="text" name="username" id="username" class="form-control input-lg" placeholder="Username" required>
                        </div>
                        <div class="form-group">
                            <input type="email" name="email" id="email" class="form-control input-lg" placeholder="Email address" required>
                        </div>
                        <input type="submit" value="Register" id="btn-new-user" class="btn btn-lg btn-default btn-block">
                    </form>
                    <a href="#" id="lnk-user-login">Login </a>
                </div>
                <div class="panel-body" id="div-user-login">
                    <form role="form" id="frm-login">
                        <div class="form-group">
                            <input type="text" name="username" id="login-username" class="form-control input-lg" placeholder="Username" required>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" id="login-password" class="form-control input-lg" placeholder="Password" required>
                        </div>
                        <input type="submit" value="Login" id="btn-login-user" class="btn btn-lg btn-default btn-block">
                    </form>
                    <a href="#" id="lnk-user-register">Register</a>
                </div>
            </div>
        </div>
    </div>


    <script>
        this.on('mount', function(){

            console.log(CORS.session);
            var fnLogin = function(){
                console.log("login");
                currentUser=JSON.parse('{"id":"56cc93487ef13f80d254f899","username":"johan.elmstrom@apti.tud","email":"jelm","teams":null}');
                CORS.session.set('user', currentUser);
                console.log("STORAGE  " + CORS.session.get('user').username);
            };

            var fnNewUser = function(event) {
                var values = {};
                $.each($('#frm-new-user').serializeArray(), function(i, field) {
                    values[field.name] = field.value;
                });
                console.log("new user function");
                currentUser=JSON.parse('{"id":"56cc93487ef13f80d254f899","username":"johan.elmstrom@apti.tud","email":"jelm","teams":null}');
                CORS.newUser(values)
                        .done(function(response){
                            console.log("new user done!");
                            CORS.session.set('user', currentUser);
                        })
                        .fail(function(response){
                            console.log(response);
                        });
            };

            var currentUser=CORS.session.get('user');

            if(currentUser) {
                $("#div-register-login").hide();
            } else {
                $("#div-user-login").show();
                $("#div-user-register").hide();
            }

            $("#lnk-user-register").on("click", function(){
                $("#div-user-login").hide();
                $("#div-user-register").show();

            });

            $("#lnk-user-login").on("click", function(){
                $("#div-user-login").show();
                $("#div-user-register").hide();

            });

            $("#btn-new-user").on("click", fnNewUser); // create mission on click
            $("#btn-login-user").on("click", fnLogin);

        });


    </script>
</login>