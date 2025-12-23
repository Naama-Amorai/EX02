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
        StdDraw.setCanvasSize(setCanvasWidth(map), setCanvasHeight(map));
        StdDraw.setXscale(0, map.getWidth());
        StdDraw.setYscale(0, map.getHeight());
        StdDraw.setYscale(map.getHeight(), 0);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Color c = setcolor(map.getPixel(x, y));
                StdDraw.setPenColor(c);
                StdDraw.filledSquare(x + 0.5, y + 0.5, 0.48);
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
                    ans.setPixel(j, i, 00);
                }
                ans.setPixel(j, i, Integer.parseInt(parts[j]));
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
                myFile.write(map.getPixel(j, i) + "\t");
            }
            myFile.write("\n");
        }
        myFile.close();
    }

    public static void main(String[] a) throws IOException {
        Map mymap = new Map(5,5,0);
        Pixel2D p1 = new Index2D(1,0);
        Pixel2D p2 = new Index2D(2,4);
        Pixel2D start = new Index2D(1,1);
        mymap.drawRect(p1 , p2 , -1);
        String mapFile = "map.txt";
        saveMap(mymap , mapFile);
        mymap.printMap();
        drawMap(mymap);
        StdDraw.pause(2000);
        Map2D ans = new Map();
        ans = mymap.allDistance(start , -1 , false);
        drawMap(ans);
        String allDistanceFile = "map_allDistance.txt";
        saveMap(ans , allDistanceFile);


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
            final int factor = 100;
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
            final int factor = 100;
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



