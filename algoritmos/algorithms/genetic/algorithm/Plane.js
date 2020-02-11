var Plane = function(TAM){
      
  this.matrix = [];
  for (var i = 0; i < TAM; i++) {
    this.matrix[i] = [];
  }

  for (i = 0; i < TAM; i++) {
    for (var j = 0; j < TAM; j++) {
      if (j!=i) {
        this.matrix[i][j] = Math.floor((Math.random() * (TAM+TAM)) + (TAM + 1));
      } else{
        this.matrix[i][j] = 00;
        if (j>0) {
          this.matrix[i][j-1] = Math.floor(Math.random() * (TAM+1));
          this.matrix[j-1][i] = this.matrix[i][j-1];
        }
      }
      this.matrix[j][i] = this.matrix[i][j];
    }
  }

  Plane.prototype.matrix = this.matrix;

  this.matrix[0][TAM - 1] = Math.floor(Math.random() * (TAM+1));
  this.matrix[TAM - 1][0] = this.matrix[0][TAM - 1];

  this.fitness = 0;
  for (i = 0; i < TAM; i++) {
    this.fitness = this.fitness + i;
  }


  Plane.prototype.display();
};

//TODO
Plane.prototype.gBest = function() {
  for (var i = 0; i < this.matrix.length; i++) {
    for (var j = 0; j < this.matrix[i].length; j++) {
      
    }
  }
};

Plane.prototype.setMatrix = function(matrix) {
  this.matrix = matrix;
};

Plane.prototype.getMatrix = function() {
  return this.matrix;
};

Plane.prototype.display = function() {
  for (var i = 0; i < this.matrix.length; i++) {
    console.log(this.matrix[i]);
  }
};