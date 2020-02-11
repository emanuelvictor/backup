'use strict';

describe('Service: position', function () {

  // load the service's module
  beforeEach(module('funcionario2App'));

  // instantiate service
  var position;
  beforeEach(inject(function (_position_) {
    position = _position_;
  }));

  it('should do something', function () {
    expect(!!position).toBe(true);
  });

});
