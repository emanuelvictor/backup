var Plane = function(TAM){
      
  this.matrix = new Array();
  for (var i = 0; i < TAM; i++) {
    this.matrix[i] = new Array();
  };

  for (var i = 0; i < TAM; i++) {
    for (var j = 0; j < TAM; j++) {
      if (j!=i) {
        this.matrix[i][j] = Math.floor((Math.random() * (TAM+TAM)) + (TAM + 1));
      } else{
        this.matrix[i][j] = 00;
        if (j>0) {
          this.matrix[i][j-1] = Math.floor(Math.random() * (TAM+1));
          this.matrix[j-1][i] = this.matrix[i][j-1];
        };
      };
      this.matrix[j][i] = this.matrix[i][j];
    };
  };

  this.matrix[0][TAM - 1] = Math.floor(Math.random() * (TAM+1));
  this.matrix[TAM - 1][0] = this.matrix[0][TAM - 1];

  this.fitness = 0;
  for (var i = 0; i < TAM; i++) {
    this.fitness = this.fitness + i;
  };
};

Plane.prototype.display = function() {
  for (var i = 0; i < this.matrix.length; i++) {
    console.log(this.matrix[i]);
  };
};