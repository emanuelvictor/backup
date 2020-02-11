'use strict';

describe('Service: requestErrorInterceptor', function () {

  // load the service's module
  beforeEach(module('funcionario2App'));

  // instantiate service
  var requestErrorInterceptor;
  beforeEach(inject(function (_requestErrorInterceptor_) {
    requestErrorInterceptor = _requestErrorInterceptor_;
  }));

  it('should do something', function () {
    expect(!!requestErrorInterceptor).toBe(true);
  });

});
