'use strict';

describe('Service: successInterceptor', function () {

  // load the service's module
  beforeEach(module('funcionario2App'));

  // instantiate service
  var successInterceptor;
  beforeEach(inject(function (_successInterceptor_) {
    successInterceptor = _successInterceptor_;
  }));

  it('should do something', function () {
    expect(!!successInterceptor).toBe(true);
  });

});
