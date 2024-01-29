package Model;

/**
 * @author Philipp Klein (phi.klein17@gmail.com)
 * @version 1.0
 * @since 01.27.2024
 */

public class Node implements Comparable<Node> {

    private double xValue, yValue, zValue;

    /**
     * Constructor of the class.
     *
     * @param xValue x-value
     * @param yValue y-value
     * @param zValue z-value
     */

    public Node(double xValue, double yValue, double zValue) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.zValue = zValue;
    }

    /**
     * Gives access to x-value.
     *
     * @return x-value
     */

    public double getxValue() { return xValue; }

    /**
     * Gives access to y-value.
     *
     * @return y-value
     */

    public double getyValue() { return yValue; }

    /**
     * Gives access to z-value.
     *
     * @return z-value
     */

    public double getzValue() { return zValue; }

    /**
     * Applying the rotation matrix Rx for the x-axis on our Node.
     *
     * @param alpha angle
     */

    public void rotateXAxis(int alpha) {

        double cos = Math.cos(Math.toRadians(alpha));
        double sin = Math.sin(Math.toRadians(alpha));

        double yOld = this.yValue;
        double zOld = this.zValue;

        this.yValue = cos * yOld - sin * zOld;
        this.zValue = sin * yOld + cos * zOld;

    }

    /**
     * Applying the rotation matrix Ry for the y-axis on our Node.
     *
     * @param alpha angle
     */

    public void rotateYAxis(int alpha) {

        double cos = Math.cos(Math.toRadians(alpha));
        double sin = Math.sin(Math.toRadians(alpha));

        double xOld = this.xValue;
        double zOld = this.zValue;

        this.xValue = cos * xOld + sin * zOld;
        this.zValue = cos * zOld - sin * xOld;

    }

    /**
     * Applying the rotation matrix Rz for the z-axis on our Node.
     *
     * @param alpha angle
     */

    public void rotateZAxis(int alpha) {

        double cos = Math.cos(Math.toRadians(alpha));
        double sin = Math.sin(Math.toRadians(alpha));

        double xOld = this.xValue;
        double yOld = this.yValue;

        this.xValue = cos * xOld - sin * yOld;
        this.yValue = sin * xOld + cos * yOld;

    }

    /**
     * Returns the Node in the following form: (x, y, z).
     *
     * @return node
     */

    public String toString() { return "(" + xValue + ", " + ( yValue == 0 ? 0 : yValue * (-1) ) + ", " + zValue + ")"; }

    /**
     * Returns a new Node with the same values of our current Node.
     * @return new Node
     */

    public Node clone() { return new Node( this.xValue, this.yValue, this.zValue ); }

    /**
     * Compares two Nodes by their coordinates. Class
     * Node implements Comparable<T> interface.
     *
     * @param o the object to be compared.
     * @return int in (0,1)
     */

    @Override
    public int compareTo( Node o ) {

        if ( this.xValue == o.xValue && this.yValue == o.yValue && this.zValue == o.zValue ) return 0;

        return 1;

    }

}
