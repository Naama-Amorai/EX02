package assignments.Ex2;
import java.io.Serializable;
/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{
    private int [][] _mymap;

    /**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {init(w, h, v);}
        /**
         * Constructs a square map (size*size).
         * @param size
         */
	public Map(int size) {this(size,size, 0);}
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}
	@Override
	public void init(int w, int h, int v) {
        this._mymap = new int [w][h];
        for (int i = 0 ;  i < w ; i++){
            for (int j = 0 ;  j < h ; j++){
                _mymap[i][j] = v;
            }
        }
	}
	@Override
	public void init(int[][] arr) {
        if (arr == null){
            throw new RuntimeException("error: null arr");
        }
        int w = arr.length;
        int h = arr[0].length;
        for (int i = 1 ; i < w ; i++){
            if (arr[i].length != h){
                throw new RuntimeException("error: ragged 2D array");
            }
        }
        _mymap = new int[w][h];
        for (int i = 0 ;  i < w ; i++){
            for (int j = 0 ;  j < h ; j++){
                _mymap[i][j] = arr[i][j];
            }
        }

	}
    public Map() {
        _mymap = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                _mymap[i][j] = 0;
            }
        }
    }
	@Override
	public int[][] getMap() {
		int[][] ans = new int[_mymap.length][_mymap[0].length] ;
        for (int i = 0 ; i < _mymap.length ; i++){
            for (int j = 0 ; j < _mymap[0].length ; j++){
                ans[i][j] = _mymap[i][j];
            }
        }
		return ans;
	}
	@Override
	public int getWidth() {
        return _mymap.length;
    }
	@Override
	public int getHeight() {
        return _mymap[0].length;
    }
	@Override
	public int getPixel(int x, int y) {
        if (x < 0 || x >= _mymap.length){
            throw new RuntimeException();
        }
        if (y < 0 || y >= _mymap[0].length){
            throw new RuntimeException();
        }
        return _mymap[x][y];
    }
	@Override
	public int getPixel(Pixel2D p) {
        if (p.getX() < 0 || p.getX() >= _mymap.length){
            throw new RuntimeException();
        }
        if (p.getY() < 0 || p.getY() >= _mymap[0].length){
            throw new RuntimeException();
        }
        return _mymap[p.getX()][p.getY()];
    }

	@Override
	public void setPixel(int x, int y, int v) {
    _mymap[x][y] = v;
    }

	@Override
	public void setPixel(Pixel2D p, int v) {
    _mymap[p.getX()][p.getY()] = v;
	}

    @Override
    public boolean isInside(Pixel2D p) {
        if (p == null){
            return false;
        }
        if (this.getWidth() <= p.getX() || p.getX() < 0 || this.getHeight() <= p.getY() || p.getY() < 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        if (p == null){
            return false;
        }
        if (this._mymap.length != p.getWidth() || this._mymap[0].length != p.getHeight()){
            return false;
        }
        return true;
    }

    @Override
    public void addMap2D(Map2D p) {
        if (p == null){
            throw new RuntimeException();
        }
        if (!this.sameDimensions(p)){
            throw new RuntimeException();
        }
        for (int i = 0 ; i < _mymap.length ; i++){
            for (int j = 0 ; j < _mymap[0].length ; j++){
                this._mymap[i][j] += p.getPixel(i , j);
            }
        }
    }

    @Override
    public void mul(double scalar) {
        for (int i = 0; i < _mymap.length; i++) {
            for (int j = 0; j < _mymap[0].length; j++) {
                this._mymap[i][j] = (int) (this._mymap[i][j] * scalar);
            }
        }
    }
    @Override
    public void rescale(double sx, double sy) {
        if (((int) (this.getWidth() * sx)) <= 0 || ((int) (this.getHeight() * sy )) <= 0){
            throw new RuntimeException();
        }
        Map2D ans = new Map((int) (this.getWidth() * sx ), (int) (this.getHeight() * sy ), 0);
        for (int i = 0; i < ans.getWidth(); i++) {
            for (int j = 0; j < ans.getHeight(); j++) {
                int original = this.getPixel((int)(i / sx) , (int)(j / sy));
                ans.setPixel(i , j , original );
            }
        }
        this.init(ans.getMap());
    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {
        if (center == null || rad < 0){
            throw new RuntimeException();
        }
        for (int i = 0; i < _mymap.length; i++) {
            for (int j = 0; j < _mymap[0].length; j++) {
                Index2D current = new Index2D(i , j);
                if (center.distance2D(current) < rad){
                    setPixel(i , j , color);
                }
            }
        }
    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {
        if (p1 == null || p2 == null){
            throw new RuntimeException();
        }
        if (!isInside(p1) || !isInside(p2)){
            throw new RuntimeException();
        }
        int dx = Math.abs(p2.getX()-p1.getX());
        int dy = Math.abs(p2.getY()-p1.getY());
        if (dx >= dy) {
           if (p1.getX() > p2.getX()){
               Pixel2D p = p1;
               p1 = p2;
               p2 = p;

           }
        }



    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public boolean equals(Object ob) {
        boolean ans = false;

        return ans;
    }
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
		int ans = -1;

		return ans;
	}

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
		Pixel2D[] ans = null;  // the result.

		return ans;
	}
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        return ans;
    }
	////////////////////// Private Methods ///////////////////////

}
