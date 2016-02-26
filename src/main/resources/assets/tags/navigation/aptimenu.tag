<aptimenu>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Brand</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <p class="navbar-text" id="user-logged-in">
                        Signed in as <label id="current-user"></label>
                        <a href="#" id="btn-logout" class="btn btn-info">logout</a>
                    </p>

                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <script>

    this.on('mount', function(){

         if(CORS.session.get('user')){
            $("#user-logged-in").show();
            $("#current-user").text(CORS.session.get('user').username);
         }
         var fnLogout = function(){
                console.log("logout");
                currentUser=null;
                $("#user-logged-in").hide();
                $("#current-user").text("");
                CORS.session.remove('user');
                console.log("STORAGE  " + CORS.session.get('user'));
                //riot.update();
                location.reload();
         };

         console.log("mountin");
         $("#btn-logout").on("click", fnLogout);


     })
    </script>
</aptimenu>