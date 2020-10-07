package fr.elfoa.sample;

import java.util.Objects;

public class ColoredPair extends Pair {


    private final Color c;

    public ColoredPair(Color c) {
        super(0, 0);
        this.c = c;
    }

    public ColoredPair(int x,int y,Color c){
        super(x,y);
        this.c = c;
    }

    public Color getC() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredPair that = (ColoredPair) o;
        return c == that.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), c);
    }
}
