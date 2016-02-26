<leagues>
    <div id="div-manage-leagues">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-1">
            <div class="panel panel-aptifooty">
                <div class="panel-heading">
                    <h1 class="panel-title">leagues</h1>
                </div>

                <div class="panel-body">
                    <ul id="ul-leagues" class="list-group">
                        <li class="list-group-item panel-aptifooty"><a href="#">...</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script>

        this.on('mount', function(){

            var currentUser=CORS.session.get('user');
            console.log(currentUser);

            if(currentUser){
                CORS.listLeagues(function(leagues) {
                     console.log(leagues);
                     var lgs = $("#ul-leagues");
                     lgs.empty();
                     $.each(leagues, function(pos) {
                        var data = leagues[pos]
                        console.log("item is :" + data.name);
                        lgs.append('<li class="list-group-item"><a href="#">'+data.name+'</a></li>');
                     });
                });
                $("#div-manage-leagues").show();
            } else {
                $("#div-manage-leagues").hide();
            }
        })
    </script>
</leagues>