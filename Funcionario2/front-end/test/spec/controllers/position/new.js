'use strict';

describe('Controller: PositionNewCtrl', function () {

  // load the controller's module
  beforeEach(module('funcionario2App'));

  var PositionNewCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PositionNewCtrl = $controller('PositionNewCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
