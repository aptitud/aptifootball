
var CORS = {
    BASE_URL : "http://localhost:8080/api/aptifootball",
    LIST_LEAGUES : "http://localhost:8080/api/aptifootball/league/list",


    HTTP_METHOD_GET : "GET",

    aFunction : null,
    
    listUsers : function (callback){
        var deferredObject = $.Deferred();
        CORS.makeAjaxCall(CORS.BASE_URL + "/user/list","GET", null)
            .done(function(response){callback(response)})
            .always(function(response){
                deferredObject.resolve(response);
            });
        return deferredObject.promise();
    },

    listLeagues : function (callback){
        console.log("listing leagues");
        var deferredObject = $.Deferred();
        CORS.makeAjaxCall(CORS.LIST_LEAGUES,"GET", null)
            .done(function(response){callback(response)})
            .always(function(response){
                deferredObject.resolve(response);
            });
        return deferredObject.promise();
    },

    newUser : function (data){
            console.log(data);
            CORS.makeAjaxCall(CORS.BASE_URL+"/user","POST", data)
            var deferredObject = $.Deferred();
            return deferredObject.resolve(data).resolve();

     },



    makeAjaxCall : function (url, type, data) {
        var deferredObject = $.Deferred();
        var msgBody;
        if(data){
            msgBody=JSON.stringify(data);
        }

        $.ajax({
        headers: {
                'Content-Type': 'application/json'
            },
            type: type,
            cache: "false",
            url: url,
            data: msgBody,
            async: true,
            success: function (response) {
                console.log("success");
                console.log(response);
                deferredObject.resolve(response);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                deferredObject.reject(jqXHR);
            }
        });
        return deferredObject.promise();
    },

    ns : null,
    session : null


};