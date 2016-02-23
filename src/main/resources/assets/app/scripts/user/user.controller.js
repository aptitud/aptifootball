(function() {
  'use strict';

  angular
    .module('footyApp')
    .controller('UserController', function(UserService, toastr, $state) {
      var vm = this;
      vm.user = null;

      vm.registerUser = function() {
//        UserService.createUser(vm.user)
//          .then(onRegisterUserSuccess, onRegisterUserFailure);
        console.log("Dummy user created: " + vm.user)
        $state.go('league');
      }

      function onRegisterUserSuccess(user) {
        toastr.success('Created user ' + user.username + ' (' + user.email + ')');
        $state.go('league');
      }

      function onRegisterUserFailure(error) {
        console.log('Failed to create user ' + JSON.stringify(error))
        toastr.error('Failed to create user! Check browser log for more info...');
      }

    });

})();
