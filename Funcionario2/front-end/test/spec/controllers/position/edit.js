'use strict';

describe('Controller: PositionEditCtrl', function () {

  // load the controller's module
  beforeEach(module('funcionario2App'));

  var PositionEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PositionEditCtrl = $controller('PositionEditCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
