(function() {
  'use strict';

  angular
    .module('footyApp')
    .controller('MainController', function($timeout, PingService) {
      var vm = this;
      vm.backendStatus = null;
      vm.currentUser="none";
      pingBackend();
      getUserFromCookie();

      $timeout(function () {
        pingBackend();
      }, 10000);

      function pingBackend() {
        PingService.ping()
          .then(onPingSuccess, onPingFailure);
      }

      function onPingSuccess() {
        vm.backendStatus = 'up';
      }

      function onPingFailure() {
        vm.backendStatus = 'down';
      }

       function getUserFromCookie() {
           var name = "username" + "=";
           var ca = document.cookie.split(';');
           for(var i=0; i<ca.length; i++) {
               console.log(ca[i]);
               var c = ca[i];
               while (c.charAt(0)==' ') c = c.substring(1);
               if (c.indexOf("username") == 0){
                    vm.currentUser = c.substring(name.length, c.length);
               }
           }
       }
    });


})();
