package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser {

    private static List<List<String>> shapeList = new ArrayList<>();
    private final static String SHAPES_PATH = "src/shapes.csv";

    public static void initShapesFromFile()  {
        try(BufferedReader reader = new BufferedReader(new FileReader(SHAPES_PATH))) {
            String line;

            while((line = reader.readLine()) != null) {
                String[] shapes = line.split(",");
//            System.out.println("Shape [ShapeType=" + shapes[0]);
                shapeList.add(Arrays.asList(shapes));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static List<List<String>> getShapeList() {
        initShapesFromFile();
        return shapeList;
    }
}
