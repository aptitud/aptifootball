(function() {
  'use strict';

  angular
    .module('footyApp')
    .service('LeagueService', function($http) {
      this.createLeague = createLeague;

      function getLeagues(userId) {
        return $http.get('/api/aptifootball/league/' + userId)
          .then(onSuccess);
      }

      function getLeague(leagueId) {
          return $http.get('/api/aptifootball/league/' + leagueId)
            .then(onSuccess);
      }

      function createLeague(league) {
        return $http.post('/api/aptifootball/league', league)
          .then(onSuccess);
      }

      function onSuccess(response) {
        return response.data;
      }

    });

})();
