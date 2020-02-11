'use strict';

describe('Controller: EmployeeFindCtrl', function () {

  // load the controller's module
  beforeEach(module('funcionario2App'));

  var EmployeeFindCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EmployeeFindCtrl = $controller('EmployeeFindCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
