import edu.duke.*;

import java.io.File;

public class PerimeterCalculator {
    public double getPerimeter(Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        // Put code here
        int ptCount = 0;
        for (Point pt : s.getPoints()) {
            ptCount++;
        }
        return ptCount;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double totalPerimeter = getPerimeter(s);
        double average = totalPerimeter / getNumPoints(s);
        return average;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0.0;
        Point prvPoint = s.getLastPoint();
        for (Point currPoint : s.getPoints()) {
            if (largestSide < currPoint.distance(prvPoint))
                largestSide = currPoint.distance(prvPoint);

            prvPoint = currPoint;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0.0;
        Point prvPoint = s.getLastPoint();
        for (Point currPoint : s.getPoints()) {
            if (largestX < currPoint.getX())
                largestX = currPoint.getX();

            prvPoint = currPoint;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double currPerimeter = 0.0;
        double largestPerimeter = 0.0;

        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            FileResource fileResource = new FileResource(file);
            Shape shape = new Shape(fileResource);
            currPerimeter = getPerimeter(shape);
            if (largestPerimeter < currPerimeter)
                largestPerimeter = currPerimeter;

        }

        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File largestFile = null;
        double currPerimeter = 0.0;
        double largestPerimeter = 0.0;
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            FileResource fileResource = new FileResource(file);
            Shape shape = new Shape(fileResource);
            currPerimeter = getPerimeter(shape);
            if (largestPerimeter < currPerimeter) {
                largestPerimeter = currPerimeter;
                largestFile = file;
            }
        }
        return largestFile.getName();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int ptCount = getNumPoints(s);
        double average = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("Perimeter = " + length);
        System.out.println("Number of points in the shape = " + ptCount);
        System.out.println("Average length of the sides = " + average);
        System.out.println("Largest side = " + largestSide);
        System.out.println("Largest x = " + largestX);
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largestFileName = getFileWithLargestPerimeter();
        System.out.println("File with largest perimeter is: " + largestFileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterCalculator perimeterCalculator = new PerimeterCalculator();
        perimeterCalculator.testPerimeter();
        perimeterCalculator.testPerimeterMultipleFiles();
        perimeterCalculator.testFileWithLargestPerimeter();
    }
}
