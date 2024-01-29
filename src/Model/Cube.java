package Model;

/**
 * @author Philipp Klein (phi.klein17@gmail.com)
 * @version 1.0
 * @since 01.25.2024
 */

public abstract class Cube {

    /**
     * This class is demo structure.
     */

    public static Structure getCube() {

        Structure cube = new Structure( true );

        Node node1 = new Node(-100, -100, -100);
        Node node2 = new Node(-100, -100, 100);
        Node node3 = new Node(-100, 100, -100);
        Node node4 = new Node(-100, 100, 100);
        Node node5 = new Node(100, -100, -100);
        Node node6 = new Node(100, -100, 100);
        Node node7 = new Node(100, 100, -100);
        Node node8 = new Node(100, 100, 100);

        Node[] nodes = {node1, node2, node3, node4, node5, node6, node7, node8};

        Node[][] relations = {  {node1, node2}, {node3, node4}, {node1, node3},
                                {node2, node4}, {node6, node8}, {node5, node7},
                                {node5, node6}, {node7, node8}, {node1, node5},
                                {node3, node7}, {node4, node8}, {node2, node6}};

        cube.addNodes(nodes);
        cube.addRelations(relations);

        return cube;

    }

}
