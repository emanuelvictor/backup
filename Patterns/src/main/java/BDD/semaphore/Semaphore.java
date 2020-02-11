package BDD.semaphore;

/**
 * Created by emanuelvictor on 01/09/14.
 */
public class Semaphore {
    private String color;

    public Semaphore(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String next(){
        if (color/*.toLowerCase().trim()*/.equals("red")){
            this.color = "green";
            return this.color;
        }else if (color/*.toLowerCase().trim()*/.equals("yellow")){
            this.color = "red";
            return this.color;
        }else{
            this.color = "yellow";
            return this.color;
        }
    }
}
