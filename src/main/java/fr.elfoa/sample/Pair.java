package fr.elfoa.sample;

/**
 * @author Pierre Colomb
 */
public class Pair {

    private final int x;
    private final int y;


    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }



    public int getX() {
        return x;
    }



    public int getY() {
        return y;
    }





    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Pair))
            return false;
        Pair point = (Pair) object;
        return x == point.x &&
               y == point.y;
    }



    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }


}
