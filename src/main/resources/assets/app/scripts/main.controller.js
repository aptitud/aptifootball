(function() {
  'use strict';

  angular
    .module('footyApp')
    .controller('MainController', function($timeout, PingService) {
      var vm = this;
      vm.backendStatus = null;

      pingBackend();
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

    });

})();
