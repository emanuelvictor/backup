'use strict';

describe('Controller: PositionFindCtrl', function () {

  // load the controller's module
  beforeEach(module('funcionario2App'));

  var PositionFindCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PositionFindCtrl = $controller('PositionFindCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
