package ThreeDee.shapes;

public interface Shape {
    public String toString();
    public double[][] getCorners();
    public int[][] getFaces();
    public void setFaces(int i, int[] newValue);
}
