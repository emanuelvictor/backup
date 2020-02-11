'use strict';

describe('Controller: Errors403Ctrl', function () {

  // load the controller's module
  beforeEach(module('funcionario2App'));

  var Errors403Ctrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    Errors403Ctrl = $controller('Errors403Ctrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
