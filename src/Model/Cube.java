package Model;

/**
 * @author Philipp Klein (phi.klein17@gmail.com)
 * @version 1.0
 * @since 01.25.2024
 */

// Cube could/should extend the class 'Structure'!
public abstract class Cube {

    /**
     * This class is demo structure.
     */

    public static Structure getCube() {

        Structure cube = new Structure( true );

        Node bottomLeftBack = new Node(-100, -100, -100);
        Node bottomLeftFront = new Node(-100, -100, 100);

        Node topLeftBack = new Node(-100, 100, -100);
        Node topLeftFront = new Node(-100, 100, 100);

        Node bottomRightBack = new Node(100, -100, -100);
        Node bottomRightFront = new Node(100, -100, 100);

        Node topRightBack = new Node(100, 100, -100);
        Node topRightFront = new Node(100, 100, 100);

        Node[] nodes = {bottomLeftBack, bottomLeftFront, topLeftBack, topLeftFront, bottomRightBack, bottomRightFront, topRightBack, topRightFront};

        Node[][] relations = {  {bottomLeftBack, bottomLeftFront},
                                {topLeftBack, topLeftFront},
                                {bottomLeftBack, topLeftBack},
                                {bottomLeftFront, topLeftFront},
                                {bottomRightFront, topRightFront},
                                {bottomRightBack, topRightBack},
                                {bottomRightBack, bottomRightFront},
                                {topRightBack, topRightFront},
                                {bottomLeftBack, bottomRightBack},
                                {topLeftBack, topRightBack},
                                {topLeftFront, topRightFront},
                                {bottomLeftFront, bottomRightFront} };

        cube.addNodes(nodes);
        cube.addRelations(relations);

        return cube;

    }

}
