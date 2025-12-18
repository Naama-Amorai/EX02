package assignments.Ex2;

public class Index2D implements Pixel2D {
    private int X;
    private int Y;

    public Index2D(int w, int h) {
        this.X = w;
        this.Y = h;
    }
    public Index2D(Pixel2D other) {
        this.X = other.getX();
        this.Y = other.getY();
    }
    @Override
    public int getX() {
        return this.X;
    }

    @Override
    public int getY() {
        return this.Y;
    }

    @Override
    public double distance2D(Pixel2D p2) {
        if (p2 == null){
            throw new RuntimeException("illegal pixel");
        }
        if (p2.getX() == this.X && p2.getY() == this.Y){
            return 0.0;
        }
        double distance = Math.sqrt(Math.pow(p2.getX() - this.X,2) + Math.pow(p2.getY() - this.Y,2));
        return distance;
    }

    @Override
    public String toString() {
        String ans = null;
        System.out.println("Index X: " + this.X + " ,Index Y: " + this.Y);
        return ans;
    }

    @Override
    public boolean equals(Object p) {
        if (p.getClass() != this.getClass()){
            return false;
        }
        if ( ((Index2D) p).X != this.X){
            return false;
        }
        if ( ((Index2D) p).Y != this.Y){
            return false;
        }
        return true;
    }
}
