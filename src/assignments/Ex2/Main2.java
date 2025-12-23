package assignments.Ex2;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main2 {
    public static void main(String[] a) {
       //int[][] n = {{1 ,2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}, {0,1,0}};
        Map _m1 = new Map(10,10,0);
        Pixel2D p1 = new Index2D(4,0);
        Pixel2D p2 = new Index2D(8,9);
        Pixel2D start = new Index2D(2,2);
        Pixel2D end = new Index2D(9,5);
        _m1.drawRect(p1,p2,1);
        _m1.printMap();
        Pixel2D [] ans = _m1.shortestPath(start,end,1,false);
        System.out.println(Arrays.toString(ans));
        Pixel2D [] ans2 = _m1.shortestPath(start,end,1,true);
//        for (Pixel2D p : ans2) {
//            System.out.println(p);
//        }
        System.out.println(Arrays.toString(ans2));


    }
    }

  //  {
//       String mapFile = "map.txt";
//        Map map = null;
//        try {
//            map = (Map) loadMap(mapFile);
//            map.printMap();
//            drawMap(map);
//        }
//        catch (IOException e){
//            System.out.println("file isn't founded");
//        }
//
//        Pixel2D p = new Index2D(5 , 5);
//        map.fill(p , 5 , false);
//        try {
//            saveMap(map , "filled map");
//        }
//        catch (IOException e){
//            System.out.println("filed");
//        }
//        StdDraw.pause(5000);
//        drawMap(map);
//        Map map2 = new Map(10 , 10 , 0 );
//        Index2D p1 = new Index2D(2 , 2);
//        Index2D p2 = new Index2D(3 , 5);
//        map2.drawRect(p1 , p2 , 1);
//        map2.printMap();
//        drawMap(map2);
//        p1 = new Index2D(1 , 1);
//        p2 = new Index2D(9 , 5);
//        Pixel2D[] path = map2.shortestPath(p1 , p2 , 1 , true);
//        if (path != null) {
//            for (int i = 0; i < path.length; i++) {
//                map2.setPixel(path[i].getX(), path[i].getY(), 5);
//            }
//            StdDraw.pause(5000);
//            drawMap(map2);
////        }
//Map map3 = new Map(10, 7, 0);
//
//        for (int i = 0; i < 30; i++) {
//int x = (int) (Math.random() * map3.getWidth());
//int y = (int) (Math.random() * map3.getHeight());
//double direction = Math.random();
//int moves = 0;
//            if (direction < 0.5){
//moves = (int) (Math.random() * (map3.getHeight())/3) + 1 ;
//        for (int j = y ; j < (y + moves) && j < map3.getHeight(); j++) {
//        map3.setPixel(x, j, 1);
//                }
//                        }
//                        else {
//moves = (int) (Math.random() * (map3.getWidth()/3)) + 1;
//        for (int j = x ; j < (x + moves) && j < map3.getWidth(); j++) {
//        map3.setPixel(j, y, 1);
//                }
//                        }
//
//                        }
//
//                        map3.printMap();
//drawMap(map3);
//Index2D p1 = new Index2D((int) (Math.random() * map3.getWidth()), (int) (Math.random() * map3.getHeight()));
//Index2D p2 = new Index2D((int) (Math.random() * map3.getWidth()), (int) (Math.random() * map3.getHeight()));
//        p1.toString();
//        p2.toString();
//        map3.setPixel(p1 , 2);
//        map3.setPixel(p2 , 3);
//        StdDraw.pause(2000);
//drawMap(map3);
//Pixel2D[] path = map3.shortestPath(p1, p2, 1, false);
//        if (path != null) {
//        for (int i = 0; i < path.length; i++) {
//        map3.setPixel(path[i].getX(), path[i].getY(), 5);
//        }
//        StdDraw.pause(5000);
//drawMap(map3);
//        }
//                else{
//                System.out.println("no relvant path");
//        }
//                }


