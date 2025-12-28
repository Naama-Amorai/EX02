package assignments.Ex2;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * The class has save and load functions, and a GUI draw function.
 * You should implement this class, it is recommender to use the StdDraw class, as in:
 * https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 *
 *
 */
public class Ex2_GUI {
    public static void drawMap(Map2D map) {
        if (map == null) {
            throw new RuntimeException();
        }
        StdDraw.setXscale(0, map.getWidth() );
        StdDraw.setYscale(map.getHeight() , 0);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Color c = setcolor(map.getPixel(x, y));
                StdDraw.setPenColor(c);
                if (map.getPixel(x, y) == 0 || map.getPixel(x, y) == -1) {
                    StdDraw.filledSquare(x + 0.5, y + 0.5, 0.48);
                }
                else {
                    StdDraw.setPenColor(Color.WHITE);
                    StdDraw.filledSquare(x + 0.5, y + 0.5, 0.48);
                    StdDraw.setPenColor(c);
                    StdDraw.filledCircle(x + 0.5, y + 0.5, 0.3);
                }
            }
        }
        StdDraw.show();
    }

    /**
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) throws FileNotFoundException {
        Map2D ans = null;
        File map = new File(mapFileName);
        Scanner mymap = new Scanner(map);
        int col = 0, row = 0;
        while (mymap.hasNextLine()) {
            mymap.nextLine();
            row++;
        }
        mymap = new Scanner(new File(mapFileName));
        if (mymap.hasNextLine()) {
            String firstLine = mymap.nextLine();
            String[] parts = firstLine.trim().split("\\s+");
            col = parts.length;
        }
        ans = new Map(col, row, 0);
        mymap = new Scanner(new File(mapFileName));
        for (int i = 0; i < row; i++) {
            String nextLine = mymap.nextLine();
            String[] parts = nextLine.trim().split("\\s+");
            for (int j = 0; j < col; j++) {
                try {
                    int pixelValue = Integer.parseInt(parts[j]);
                    ans.setPixel(j, i, pixelValue);
                } catch (NumberFormatException e) {
                    System.out.println(parts[j] + "isn't an Integer value");
                    ans.setPixel(j, i, 0);
                }
            }
        }
        return ans;
    }

    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) throws IOException {
        File file = new File(mapFileName);
        FileWriter myFile = new FileWriter(file);
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
            myFile.write(String.format("%4d", map.getPixel(j, i)));
            }
            myFile.write("\n");
        }
        myFile.close();
    }

    public static void main(String[] a) throws IOException {
        Map mymap = new Map(10,10,0);
        StdDraw.setCanvasSize(800,800);
        Pixel2D p1 = new Index2D(1,1);
        Pixel2D p2 = new Index2D(2, mymap.getHeight()-2);
        mymap.drawRect(p1 , p2 , -1);
        saveMap(mymap , "map.txt");
        Pixel2D start = new Index2D(0,0);
        mymap.drawRect(p1 , p2 , -1);
        drawMap(mymap);
        StdDraw.pause(1000);
        Map2D myloadmap =loadMap("map.txt");
        myloadmap = myloadmap.allDistance(start , -1 , true);
        saveMap(myloadmap , "map_allDistance.txt");
        drawMap(myloadmap);

//        System.out.println("clik on start point");
//        while (!StdDraw.isMousePressed()) {
//            StdDraw.pause(10);
//        }
//        int x1 = (int) (StdDraw.mouseX());
//        int y1 = (int) (StdDraw.mouseY());
//        start = new Index2D(x1, y1);
//        mymap.setPixel(start, 2);
//        drawMap(mymap);
//        while (StdDraw.isMousePressed()) {
//            StdDraw.pause(10);
//        }
//        System.out.println("Click on finish point");
//       while (!StdDraw.isMousePressed()) {
//            StdDraw.pause(10);
//        }
//        int x2 = (int) (StdDraw.mouseX());
//        int y2 = (int) (StdDraw.mouseY());
//        end = new Index2D(x2, y2);
//        mymap.setPixel(end, 3);
//        drawMap(mymap);
//        StdDraw.pause(500);
//        Pixel2D[] ans_short = mymap.shortestPath(start, end , -1 , true);
//        if (ans_short == null){
//            mymap.setPixel(start, 0);
//            mymap.setPixel(end, 0);
//            System.out.println("no Path found...");
//
//        }
//        else {
//            for (int i = 1; i < ans_short.length-1 ; i++) {
//                if (mymap.isInside(ans_short[i])) {
//                    mymap.setPixel(ans_short[i], 1);
//                }
//            }
//        }
//        drawMap(mymap);
//       while (true){
//            if (StdDraw.isMousePressed()){
//                int x = (int) (StdDraw.mouseX());
//                int y = (int) (StdDraw.mouseY());
//                start = new Index2D(x , y);
//                if (mymap.isInside(start)){
//                    Map2D dismap = mymap.allDistance(start, -1 , true);
//                    drawMap(dismap);
//                }
//                StdDraw.pause(100);
//                if (!StdDraw.isMousePressed()){
//                    drawMap(mymap);
//                }
//            }
//        }

    }

        /// ///////////// Private functions ///////////////
        public static Color setcolor ( int value){
            if (value <= -1) {
                return Color.black;
            }
            if (value == 0) {
                return Color.white;
            }
            if (value == 1) {
                return Color.red;
            }
            if (value == 2) {
                return Color.orange;
            }
            if (value == 3) {
                return Color.green;
            }
            if (value == 4) {
                return Color.cyan;
            }
            if (value == 5) {
                return Color.blue;
            }
            if (value == 6) {
                return Color.yellow;
            }
            if (value == 7) {
                return Color.darkGray;
            }
            if (value == 8) {
                return Color.GRAY;
            }
            else {
                return Color.lightGray;
            }

        }
        public static int setCanvasWidth (Map2D map){
            final int factor = 50;
            if (map == null) {
                return 0;
            }
            if (map.getHeight() <= map.getWidth()) {
                return Math.min((map.getWidth() * factor), 1000);
            } else {
                if (map.getHeight() * factor > 1000) {
                    double ratio = (double) (1000) / map.getHeight();
                    return (int) (map.getWidth() * ratio);
                }
                return (map.getWidth() * factor);
            }
        }

        public static int setCanvasHeight (Map2D map){
            final int factor = 50;
            if (map == null) {
                return 0;
            }
            if (map.getWidth() <= map.getHeight()) {
                return Math.min(map.getHeight() * factor, 1000);
            } else {
                if (map.getWidth() * factor > 1000) {
                    double ratio = (double) (1000) / map.getWidth();
                    return (int) (map.getHeight() * ratio);
                }
                return (map.getHeight() * factor);
            }
        }

    }



