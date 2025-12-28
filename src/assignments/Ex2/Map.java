package assignments.Ex2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable {
    private int[][] _mymap;

    /**
     * Constructs a w*h 2D raster map with an init value v.
     *
     * @param w
     * @param h
     * @param v
     */
    public Map(int w, int h, int v) {
        init(w, h, v);
    }

    /**
     * Constructs a square map (size*size).
     *
     * @param size
     */
    public Map(int size) {
        this(size, size, 0);
    }

    /**
     * Constructs a map from a given 2D array.
     *
     * @param data
     */
    public Map(int[][] data) {
        init(data);
    }

    @Override
    public void init(int w, int h, int v) {
        this._mymap = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                _mymap[i][j] = v;
            }
        }
    }

    @Override
    public void init(int[][] arr) {
        if (arr == null) {
            throw new RuntimeException("error: null arr");
        }
        int w = arr.length;
        int h = arr[0].length;
        for (int i = 1; i < w; i++) {
            if (arr[i].length != h) {
                throw new RuntimeException("error: ragged 2D array");
            }
        }
        _mymap = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                _mymap[i][j] = arr[i][j];
            }
        }

    }

//    public Map() {
//        _mymap = new int[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                _mymap[i][j] = 0;
//            }
//        }
//    }
    public Map() {
        this(10,10,0);
    }

    @Override
    public int[][] getMap() {
        int[][] ans = new int[_mymap.length][_mymap[0].length];
        for (int i = 0; i < _mymap.length; i++) {
            for (int j = 0; j < _mymap[0].length; j++) {
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
        if (x < 0 || x >= _mymap.length) {
            throw new RuntimeException();
        }
        if (y < 0 || y >= _mymap[0].length) {
            throw new RuntimeException();
        }
        return _mymap[x][y];
    }

    @Override
    public int getPixel(Pixel2D p) {
        if (p.getX() < 0 || p.getX() >= _mymap.length) {
            throw new RuntimeException();
        }
        if (p.getY() < 0 || p.getY() >= _mymap[0].length) {
            throw new RuntimeException();
        }
        return _mymap[p.getX()][p.getY()];
    }

    @Override
    public void setPixel(int x, int y, int v) {
        if (x < 0 || x >= _mymap.length) {
            throw new RuntimeException();
        }
        if (y < 0 || y >= _mymap[0].length) {
            throw new RuntimeException();
        }
        _mymap[x][y] = v;
    }

    @Override
    public void setPixel(Pixel2D p, int v) {
        if (p.getX() < 0 || p.getX() >= _mymap.length) {
            throw new RuntimeException();
        }
        if (p.getY() < 0 || p.getY() >= _mymap[0].length) {
            throw new RuntimeException();
        }
        _mymap[p.getX()][p.getY()] = v;
    }

    @Override
    public boolean isInside(Pixel2D p) {
        if (p == null) {
            return false;
        }
        if (this.getWidth() <= p.getX() || p.getX() < 0 || this.getHeight() <= p.getY() || p.getY() < 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        if (p == null) {
            return false;
        }
        if (this._mymap.length != p.getWidth() || this._mymap[0].length != p.getHeight()) {
            return false;
        }
        return true;
    }

    @Override
    public void addMap2D(Map2D p) {
        if (p == null) {
            throw new RuntimeException();
        }
        if (!this.sameDimensions(p)) {
            throw new RuntimeException();
        }
        for (int i = 0; i < _mymap.length; i++) {
            for (int j = 0; j < _mymap[0].length; j++) {
                this._mymap[i][j] += p.getPixel(i, j);
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
        if (((int) (this.getWidth() * sx)) <= 0 || ((int) (this.getHeight() * sy)) <= 0) {
            throw new RuntimeException();
        }
        Map2D ans = new Map((int) (this.getWidth() * sx), (int) (this.getHeight() * sy), 0);
        for (int i = 0; i < ans.getWidth(); i++) {
            for (int j = 0; j < ans.getHeight(); j++) {
                int original = this.getPixel((int) (i / sx), (int) (j / sy));
                ans.setPixel(i, j, original);
            }
        }
        this.init(ans.getMap());
    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {
        if (center == null || rad < 0) {
            throw new RuntimeException();
        }
        for (int i = (int) (center.getX() - rad); i <= (int) (center.getX() + rad); i++) {
            for (int j = (int) (center.getY() - rad); j <= (int) (center.getY() + rad); j++) {
                Index2D current = new Index2D(i, j);
                if (isInside(current) && center.distance2D(current) <= rad) {
                    setPixel(i, j, color);
                }
            }
        }
    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {
        if (p1 == null || p2 == null) {
            throw new RuntimeException();
        }
        if (!isInside(p1) || !isInside(p2)) {
            throw new RuntimeException();
        }
        int dx = Math.abs(p2.getX() - p1.getX());
        int dy = Math.abs(p2.getY() - p1.getY());
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
            setPixel(p1, color);
        } else if (dx >= dy) {
            if (p1.getX() > p2.getX()) {
                Pixel2D p = p1;
                p1 = p2;
                p2 = p;
            }
            double m = (double) (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
            for (int i = p1.getX(); i <= p2.getX(); i++) {
                int yi = (int) Math.round(m * (i - p1.getX()) + p1.getY());
                setPixel(i, yi, color);
            }
        } else if (dx < dy) {
            if (p1.getY() > p2.getY()) {
                Pixel2D p = p1;
                p1 = p2;
                p2 = p;
            }
            double m_1 = (double) (p2.getX() - p1.getX()) / (p2.getY() - p1.getY());
            for (int i = p1.getY(); i <= p2.getY(); i++) {
                int xi = (int) Math.round(m_1 * (i - p1.getY()) + p1.getX());
                setPixel(xi, i, color);
            }
        }
    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        if (p1 == null || p2 == null) {
            throw new RuntimeException();
        }
        if (!isInside(p1) || !isInside(p2)) {
            throw new RuntimeException();
        }
        int Minx = Math.min(p1.getX(), p2.getX());
        int Miny = Math.min(p1.getY(), p2.getY());
        int Maxx = Math.max(p1.getX(), p2.getX());
        int Maxy = Math.max(p1.getY(), p2.getY());
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
            setPixel(p1, color);
        } else for (int i = Minx; i <= Maxx; i++) {
            for (int j = Miny; j <= Maxy; j++) {
                setPixel(i, j, color);
            }
        }
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null) {
            return false;
        }
        if (ob.getClass() != this.getClass()) {
            return false;
        }
        if (!this.sameDimensions((Map)ob)) {
            return false;
        }
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                if (this.getPixel(i, j) != ((Map) ob).getPixel(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    /**
     * Fills this map with the new color (new_v) starting from p.
     * https://en.wikipedia.org/wiki/Flood_fill
     */
    public int fill(Pixel2D xy, int new_v, boolean cyclic) {
        int counter = 0;
        if (xy == null) {
            throw new RuntimeException();
        }
        if (!isInside(xy)) {
            throw new RuntimeException();
        }
        int old_color = getPixel(xy);
        if (old_color == new_v) {return 0;}
        Queue<Pixel2D> q = new LinkedList<>();
        setPixel(xy, new_v);
        counter ++;
        q.add(xy);
            while (!q.isEmpty()) {
                Pixel2D current = q.poll();
                Pixel2D neighbor1, neighbor2, neighbor3, neighbor4;
                if (cyclic) {
                    neighbor1 = new Index2D((current.getX() + 1) % this.getWidth(), current.getY());
                    neighbor2 = new Index2D((current.getX() - 1 + this.getWidth()) % this.getWidth(), current.getY());
                    neighbor3 = new Index2D(current.getX(), (current.getY() + 1) % this.getHeight());
                    neighbor4 = new Index2D(current.getX(), (current.getY() - 1 + this.getHeight()) % this.getHeight());
                } else {
                    neighbor1 = new Index2D(current.getX() + 1, current.getY());
                    neighbor2 = new Index2D(current.getX() - 1, current.getY());
                    neighbor3 = new Index2D(current.getX(), current.getY() + 1);
                    neighbor4 = new Index2D(current.getX(), current.getY() - 1);
                }
                Pixel2D[] neighbors = {neighbor1, neighbor2, neighbor3, neighbor4};
                for (Pixel2D n : neighbors) {
                    if (isInside(n) && getPixel(n) == old_color) {
                        setPixel(n, new_v);
                        q.add(n);
                        counter++;
                    }
                }
            }
//                if (isInside(neighbor1) && getPixel(neighbor1) == old_color){
//                    setPixel(neighbor1 , new_v);
//                    q.add(neighbor1);
//                    counter ++;
//                }
//                if (isInside(neighbor2) && getPixel(neighbor2) == old_color){
//                    setPixel(neighbor2 , new_v);
//                    q.add(neighbor2);
//                    counter ++;
//                }
//                if (isInside(neighbor3) && getPixel(neighbor3) == old_color){
//                    setPixel(neighbor3 , new_v);
//                    q.add(neighbor3);
//                    counter ++;
//                }
//                if (isInside(neighbor4) && getPixel(neighbor4) == old_color){
//                    setPixel(neighbor4 , new_v);
//                    q.add(neighbor4);
//                    counter ++;
//                }
//            }
    return counter;
    }

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
		Pixel2D[] ans = null;  // the result.
        if (p1 == null || p2 == null || !isInside(p1) || !isInside(p2)){
            throw new RuntimeException();
        }
        if (p1.equals(p2)){
            return new Pixel2D[] {p1};
        }
        Pixel2D [][] savepath = new Pixel2D[this.getWidth()][this.getHeight()];
        savepath [p1.getX()][p1.getY()] = p1;
        Queue<Pixel2D> q = new LinkedList<>();
        q.add(p1);
        while (!q.isEmpty()){
            Pixel2D current = q.poll();
            if (current.equals(p2)) {break;}
            Pixel2D neighbor1, neighbor2, neighbor3, neighbor4;
            if (cyclic){
                neighbor1 = new Index2D((current.getX() + 1) % this.getWidth(), current.getY());
                neighbor2 = new Index2D((current.getX() - 1 + this.getWidth()) % this.getWidth(), current.getY());
                neighbor3 = new Index2D(current.getX(), (current.getY() + 1) % this.getHeight());
                neighbor4 = new Index2D(current.getX(), (current.getY() - 1 + this.getHeight()) % this.getHeight());
            }
            else {
                neighbor1 = new Index2D(current.getX() + 1, current.getY());
                neighbor2 = new Index2D(current.getX() - 1, current.getY());
                neighbor3 = new Index2D(current.getX(), current.getY() + 1);
                neighbor4 = new Index2D(current.getX(), current.getY() - 1);
            }
            Pixel2D [] neighbors = {neighbor1, neighbor2, neighbor3, neighbor4};
            for (Pixel2D n : neighbors){
                if (isInside(n) && getPixel(n) != obsColor){
                    if (savepath [n.getX()][n.getY()] == null) {
                        savepath[n.getX()][n.getY()] = current;
                        q.add(n);
                    }
            }
//            if (isInside(neighbor1) && getPixel(neighbor1) != obsColor){
//                if (savepath [neighbor1.getX()][neighbor1.getY()] == null) {
//                    savepath[neighbor1.getX()][neighbor1.getY()] = current;
//                    q.add(neighbor1);
//                }
//            }
//            if (isInside(neighbor2) && getPixel(neighbor2) != obsColor){
//                if (savepath [neighbor2.getX()][neighbor2.getY()] == null) {
//                    savepath[neighbor2.getX()][neighbor2.getY()] = current;
//                    q.add(neighbor2);
//                }
//            }
//            if (isInside(neighbor3) && getPixel(neighbor3) != obsColor){
//                if (savepath [neighbor3.getX()][neighbor3.getY()] == null) {
//                    savepath[neighbor3.getX()][neighbor3.getY()] = current;
//                    q.add(neighbor3);
//                }
//            }
//            if (isInside(neighbor4) && getPixel(neighbor4) != obsColor){
//                if (savepath [neighbor4.getX()][neighbor4.getY()] == null) {
//                    savepath[neighbor4.getX()][neighbor4.getY()] = current;
//                    q.add(neighbor4);
//                }
            }}
        if (savepath [p2.getX()][p2.getY()] == null){
            return ans;
        }
        else{
            ArrayList <Pixel2D> reverse = new ArrayList<>();
            reverse.add(p2);
            Pixel2D temp = p2;
            while (!temp.equals(p1)){
               temp = savepath[temp.getX()][temp.getY()] ;
               reverse.add(temp);
            }
            ans = new Pixel2D[reverse.size()];
            for (int i = ans.length-1 ; i >= 0; i--) {
                ans [i] = reverse.get(ans.length-1-i);
            }
        }
        return ans;
	}

//@Override
//    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
//        Map2D ans = new Map(this.getWidth() , this.getHeight(),0);
//        for (int i = 0; i < ans.getWidth(); i++) {
//            for (int j = 0; j < ans.getHeight(); j++) {
//                Pixel2D current = new Index2D(i, j );
//                Pixel2D[] short_ans = shortestPath(start, current, obsColor, cyclic);
//                if (short_ans == null){
//                    ans.setPixel(i , j , -1);
//                }
//                else {
//                    ans.setPixel(i , j , short_ans.length-1);
//                }
//            }
//        }
//        return ans;
//    }
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = new Map(this.getWidth(), this.getHeight(), -1);
        ans.setPixel(start, 0);
        Queue<Pixel2D> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()){
            Pixel2D neighbor1, neighbor2, neighbor3, neighbor4;
            Pixel2D current = q.poll();
            if (cyclic){
                neighbor1 = new Index2D((current.getX() + 1) % this.getWidth(), current.getY());
                neighbor2 = new Index2D((current.getX() - 1 + this.getWidth()) % this.getWidth(), current.getY());
                neighbor3 = new Index2D(current.getX(), (current.getY() + 1) % this.getHeight());
                neighbor4 = new Index2D(current.getX(), (current.getY() - 1 + this.getHeight()) % this.getHeight());
            }
            else {
                neighbor1 = new Index2D(current.getX() + 1, current.getY());
                neighbor2 = new Index2D(current.getX() - 1, current.getY());
                neighbor3 = new Index2D(current.getX(), current.getY() + 1);
                neighbor4 = new Index2D(current.getX(), current.getY() - 1);
            }
            Pixel2D [] neighbors = {neighbor1, neighbor2, neighbor3, neighbor4};
            for (Pixel2D n : neighbors){
               if (isInside(n) && getPixel(n) != obsColor && ans.getPixel(n) == -1){
                    ans.setPixel(n , (ans.getPixel(current)+1) );
                    q.add(n);
                }
            }
//
//            if (isInside(neighbor1) && getPixel(neighbor1) != obsColor && ans.getPixel(neighbor1) == -1){
//                    ans.setPixel(neighbor1 , (ans.getPixel(current)+1) );
//                    q.add(neighbor1);
//            }
//            if (isInside(neighbor2) && getPixel(neighbor2) != obsColor && ans.getPixel(neighbor2) == -1){
//                ans.setPixel(neighbor2 , (ans.getPixel(current)+1) );
//                q.add(neighbor2);
//            }
//            if (isInside(neighbor3) && getPixel(neighbor3) != obsColor && ans.getPixel(neighbor3) == -1){
//                ans.setPixel(neighbor3 , (ans.getPixel(current)+1) );
//                q.add(neighbor3);
//            }
//            if (isInside(neighbor4) && getPixel(neighbor4) != obsColor && ans.getPixel(neighbor4) == -1){
//                ans.setPixel(neighbor4 , (ans.getPixel(current)+1) );
//                q.add(neighbor4);
//            }
       }
        return ans;
    }

	////////////////////// Private Methods ///////////////////////

    public void printMap() {
        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                System.out.print(this.getPixel(x, y) + "\t");
            }
            System.out.println();
        }
    }


    }

