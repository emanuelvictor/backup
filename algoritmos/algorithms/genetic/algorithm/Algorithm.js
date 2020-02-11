gerarPopulacaoAleatoria = function(pop_size, plane) {

  var routes = [];

  for (var i = 0; i < pop_size; i++) {
    routes[i] = [];
  }

  //Inicializando população inicial
  for (var k = 0; k < pop_size; k++) {

    var otherRoute = [];
    for (i = 0; i < plane.matrix.length; i++) {
      otherRoute[i] = i+1;
    }

    var route = [];

    routes[k] = shuffle(otherRoute.length, route, otherRoute);
  }

  var fitness = calculeFitnessPopulation(routes, plane.matrix);
  return sort(routes,fitness);
};



//Ordenação
sort = function(population, fitness) {
  // otimizar
  var i, i2;
  for (i = 0; i < population.length; i++) {
    for (i2 = i; i2 < population.length; i2++) {
      if (fitness[i] > fitness[i2]) {
        var vTmp = fitness[i];
        fitness[i] = fitness[i2];
        fitness[i2] = vTmp;

        var vvTmp = population[i];
        population[i] = population[i2];
        population[i2] = vvTmp;
      }
    }
  }
  return population;
};

shuffle = function(size, route, otherRoute) {
  // shuffle
  for (var i = 0; i < size; i++) {
    var r;
    do {
      r = Math.floor((Math.random() * (size - i)));
    } while (r == i);

    route[i] = otherRoute[r];
    otherRoute = preencheVetorSemOR(otherRoute, r);
  }
  route[route.length] = route[0];
  return route;
};

preencheVetorSemOR = function(indexes, r) {
  var vetAux = [], cont = 0;
  for (var j = 0; j < indexes.length; j++) {
    if (j == r) {
        continue;
    }
    vetAux[cont] = indexes[j];

    cont++;
  }
  return vetAux;
};

calculeFitnessPopulation = function(population, matrix) {
  var fitness = [];
  for (var i = 0; i < population.length; i++) {
    fitness[i] = calculeFitnessIndividue(population[i], matrix);
  }
  return fitness;
};


calculeFitnessIndividue = function(individue, matrix) {
  var fitness = 0;
  for (var j = 0; j < individue.length-1; j++) {
    fitness = fitness + matrix[(individue[j]-1)][(individue[j + 1]-1)];
  }
  return fitness;
};
