package Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Philipp Klein (phi.klein17@gmail.com)
 * @version 1.0
 * @since 01.27.2024
 */

public class Structure {

    // List of all the available structures
    public static ArrayList<Structure> structures = new ArrayList<>();


    // Private object attributes
    private final ArrayList<Node> nodes;
    private final ArrayList<Node[]> relations;

    /**
     * Constructor of the class.
     *
     * @param addToList Should Structure be added to the list of all available structures?
     */

    public Structure( boolean addToList ) {

        relations = new ArrayList<>();
        nodes = new ArrayList<>();

        if ( addToList ) {
            structures.add(this);
        }

    }

    /**
     * Rotates the Structure around the x-axis by calling the rotateXAxis()
     * method for each node.
     *
     * @param alpha angle
     */

    public void rotateStructureXAxis(int alpha) { for ( Node node : nodes ) node.rotateXAxis(alpha); }

    /**
     * Rotates the Structure around the y-axis by calling the rotateYAxis()
     * method for each node.
     *
     * @param alpha angle
     */

    public void rotateStructureYAxis(int alpha) { for ( Node node : nodes ) node.rotateYAxis(alpha); }

    /**
     * Rotates the Structure around the z-axis by calling the rotateZAxis()
     * method for each node.
     *
     * @param alpha angle
     */

    public void rotateStructureZAxis(int alpha) { for ( Node node : nodes ) node.rotateZAxis(alpha); }

    /**
     * Adding a new Node by passing in the coordinates. Before it adds the
     * new Node, it checks if it already exists in our structure.
     *
     * @param xValue x-value for the new Node.
     * @param yValue y-value for the new Node.
     * @param zValue z-value for the new Node.
     * @return boolean : was the saving successful?
     */

    public boolean addNode(double xValue, double yValue, double zValue) {

        Node tmp = new Node(xValue, yValue, zValue);

        if ( !containsNode(tmp) )  {
            this.nodes.add( tmp );
            return true;
        }

        return false;
    }

    /**
     * Quickly adds new Nodes to our structure. Here, it WON'T be
     * checked if a Node already exists in our Structure.
     *
     * @param nodes List of Nodes, that are going to be added.
     */

    public void addNodes( Node[] nodes ) { this.nodes.addAll(Arrays.asList(nodes)); }

    public boolean addRelation(Node node1, Node node2) {

        if ( !containRelation( node1, node2 ) ) {
            this.relations.add( new Node[] { node1, node2 } );
            return true;
        }

        return false;
    }

    /**
     * Quickly adds new Relations to our structure. Here, it WON'T be
     * checked if a Relation already exists in our Structure.
     *
     * @param relations List of Relations, that are going to be added.
     */

    public void addRelations( Node[][] relations ) { Collections.addAll(this.relations, relations); }

    /**
     * Returns the stored Relations of our Structure.
     *
     * @return relations ArrayList
     */

    public ArrayList<Node[]> getRelations() { return relations; }

    /**
     * Returns the stored Relations of our Structure.
     *
     * @return nodes ArrayList
     */

    public ArrayList<Node> getNodes() { return nodes; }

    /**
     * Clones our Structure and returns a new dummy-Structure containing
     * the same values without referring to our main Structure.
     *
     * @param addToList Should the cloned, new structure be added to our list of total Structures?
     * @return dummy-Structure
     */

    public Structure clone( boolean addToList ) {

        Structure dummy = new Structure( false );

        for ( Node node : this.nodes ) dummy.nodes.add( node.clone() );

        for ( Node[] relation : this.relations ) {

            Node[] relationCopy = new Node[2];

            for ( Node node1 : dummy.nodes ) if ( node1.compareTo( relation[0] ) == 0 ) relationCopy[0] = node1;
            for ( Node node2 : dummy.nodes ) if ( node2.compareTo( relation[1] ) == 0 ) relationCopy[1] = node2;

            dummy.relations.add( relationCopy );

        }

        if ( addToList ) structures.add(dummy);

        return dummy;

    }

    /**
     * Deletes and returns and ArrayList containing the index of the
     * Relation in the JList to be deleted.
     *
     * @param list List that is being updated.
     * @param node Node that is being deleted.
     * @return ArrayList of indexes.
     */

    public void deleteRelationContaining( Node node, DefaultListModel list ) {

        for ( int i = 0 ; i < this.relations.size() ; ++i ) {

            Node[] relation = this.relations.get( i );

            if ( relation[0].compareTo( node ) == 0 | relation[1].compareTo( node ) == 0 ) {

                this.relations.remove( i );
                list.remove( i );
                --i;

            }
        }
    }

    /**
     * Checks if the given Node already exists in our Structure.
     *
     * @param node Node that is looked for.
     * @return boolean : already contains the Node?
     */

    private boolean containsNode( Node node ) {
        for ( Node n : this.nodes ) if ( n.compareTo(node) == 0 ) return true;
        return false;
    }

    /**
     * Checks if a Relation is already in our Structure. It compares
     * the elements of the Relation-tupels and returns the result.
     *
     * @param node1 First Node of our Relation.
     * @param node2 Second Node of our Relation.
     * @return boolean : already contains the Relation?
     */

    private boolean containRelation( Node node1, Node node2 ) {
        for ( Node[] relation : this.relations ) if ( relation[0].compareTo( node1 ) == 0 && relation[1].compareTo( node2 ) == 0 ) return true;
        return false;
    }

}
