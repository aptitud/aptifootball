(function() {
  'use strict';

  angular
    .module('footyApp')
    .config(['$stateProvider', '$urlRouterProvider', routeConfig]);

  function routeConfig($stateProvider, $urlRouterProvider) {

    $stateProvider
      .state('user', {
        url: '/user',
        templateUrl: 'app/partials/user/user.html',
        controller: 'UserController as vm'
      })

      .state('league', {
        url: '/league',
        templateUrl: 'app/partials/league/league.html',
        controller: 'LeagueController as vm'
      })

      .state('selected', {
              url: '/league',
              templateUrl: 'app/partials/league/selected.html',
              controller: 'LeagueController as vm'
       })

    $urlRouterProvider.otherwise('/user');
  };

  angular
    .module('footyApp')
    .run(function($state, $rootScope) {
      $rootScope.$on("$stateChangeError", console.log.bind(console));
    });

})();
