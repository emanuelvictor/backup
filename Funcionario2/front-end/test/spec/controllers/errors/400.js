'use strict';

describe('Controller: Errors400Ctrl', function () {

  // load the controller's module
  beforeEach(module('funcionario2App'));

  var Errors400Ctrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    Errors400Ctrl = $controller('Errors400Ctrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
