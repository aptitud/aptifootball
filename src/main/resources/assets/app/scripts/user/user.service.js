(function() {
  'use strict';

  angular
    .module('footyApp')
    .service('UserService', function($http) {
      this.createUser = createUser;

      function createUser(user) {
        return $http.post('/api/aptifootball/user', user)
          .then(onSuccess);
      }

      function onSuccess(response) {
        return response.data;
      }

    });

})();
