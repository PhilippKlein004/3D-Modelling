package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Philipp Klein (phi.klein17@gmail.com)
 * @version 1.0
 * @since 01.26.2024
 */

public class creatorGUI extends JFrame {

    // renderedStructure = displayed Structure on the left.
    private Structure renderedStructure = Cube.getCube();

    // Structure that's developed in the creator window.
    private Structure creatorStructure = new Structure( false );

    // Drawing Panel for the rendering of our Structures.
    private class DrawingPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.setStroke(new BasicStroke(2));

            // Separation line
            g2D.drawLine(500,0,500,500);

            g2D.setColor(Color.blue);
            g2D.translate(250,250);

            if ( renderedStructure == null ) return;

            for ( Node[] relation : renderedStructure.getRelations() ) {

                Node node1 = relation[0];
                Node node2 = relation[1];

                g2D.drawLine((int) node1.getxValue(), (int) node1.getyValue(), (int) node2.getxValue(), (int) node2.getyValue());

            }

        }
    }


    // ---------------------------------------- Section for the Components of the Default Window ----------------------------------------


    // DrawingPanel
    private final DrawingPanel drawingPanel = new DrawingPanel();

    // JButtons
    private final JButton rotateXButton = new JButton("Rotate x-axis (10°)");
    private final JButton rotateYButton = new JButton("Rotate y-axis (10°)");
    private final JButton rotateZButton = new JButton("Rotate z-axis (10°)");
    private final JButton renderButton = new JButton("Render");
    private final JButton addStructureButton = new JButton("+ Create structure");

    // JLabels
    private final JLabel copyright = new JLabel("Copyright (c) Philipp Klein");
    private final JLabel availableStructuresText = new JLabel("Structures available:");

    // JLists
    DefaultListModel<String> structureList = new DefaultListModel<>();
    JList<String> structureJList = new JList<>(structureList);


    // ---------------------------------------- Section for the Components of the Creator Window ----------------------------------------


    // JTextFields
    JTextField xValueInput = new JTextField("X");
    JTextField yValueInput = new JTextField("Y");
    JTextField zValueInput = new JTextField("Z");
    JTextField structureNameInput = new JTextField();

    // JLabels
    JLabel leftBrace = new JLabel("(");
    JLabel rightBrace = new JLabel(")");
    JLabel nodeListText = new JLabel("Nodes:");
    JLabel relationsListText = new JLabel("Relations: ");
    JLabel structureNameText = new JLabel("Name of the new structure:");
    JLabel statusMessages = new JLabel("");
    JLabel nodeInputText = new JLabel("Node:");

    // JButtons
    JButton cancelButton = new JButton("Cancel");
    JButton saveButton = new JButton("Save");
    JButton testRenderButton = new JButton("Test-Render");
    JButton deleteNodeButton = new JButton("Delete Node");
    JButton deleteRelationButton = new JButton("Delete Relation");
    JButton addNodeButton = new JButton("<< Add Node");
    JButton addRelationButton = new JButton("Add Relation >>");

    // JLists
    DefaultListModel<String> nodeList = new DefaultListModel<>();
    JList<String> nodeJList = new JList<>(nodeList);

    DefaultListModel<String> relationsList = new DefaultListModel<>();
    JList<String> relationsJList = new JList<>(relationsList);

    /**
     * Opens the GUI and sets the Components and ActionListeners.
     */

    public creatorGUI() {

        // Adding our demo Structure, to be available
        structureList.addElement("Cube (Demo)");

        // DrawingPanel customization
        drawingPanel.setBounds(0, 0, 500, 500);


        // ------------------------ Component Customization for the Default-Window ------------------------


        // JButtons
        rotateXButton.setBounds(125, 440, 250, 30);
        rotateYButton.setBounds(125, 480, 250, 30);
        rotateZButton.setBounds(125, 520, 250, 30);
        renderButton.setBounds(750, 480, 200, 30);
        addStructureButton.setBounds(1050, 590, 150, 30);

        // JLabels
        copyright.setBounds(515, 600, 170, 25);
        availableStructuresText.setBounds(750, 125, 200, 25);

        // JLists
        structureJList.setBounds(750, 150, 200,300);

        // ActionListeners

        rotateXButton.addActionListener(action -> {
            renderedStructure.rotateStructureXAxis(10);
            repaint();
        });

        rotateYButton.addActionListener(action -> {
            renderedStructure.rotateStructureYAxis(10);
            repaint();
        });

        rotateZButton.addActionListener(action -> {
            renderedStructure.rotateStructureZAxis(10);
            repaint();
        });

        renderButton.addActionListener(action -> {
            renderedStructure = Structure.structures.get( structureJList.getSelectedIndex() == -1 ? 0 : structureJList.getSelectedIndex() );
            repaint();
        });

        addStructureButton.addActionListener(action -> {
            hideDefaultWindowComponents();
            showCreatorWindowComponents();
        });


        // ------------------------ Component Customization for the Creator Window ------------------------


        // JTextFields
        xValueInput.setBounds(820, 232, 50, 25);
        yValueInput.setBounds(820, 262, 50, 25);
        zValueInput.setBounds(820, 292, 50, 25);
        structureNameInput.setBounds(750, 150, 200, 25);

        // JLabels
        leftBrace.setFont(new Font("Arial Narrow", Font.PLAIN, 100));
        leftBrace.setBounds(800, 217, 35, 100);

        rightBrace.setFont(new Font("Arial Narrow", Font.PLAIN, 100));
        rightBrace.setBounds(865, 217, 35, 100);

        nodeListText.setBounds(525, 125, 200, 25);
        relationsListText.setBounds(975, 125, 200, 25);
        structureNameText.setBounds(750, 125, 200, 25);
        statusMessages.setBounds(525, 90, 500, 25);
        nodeInputText.setBounds(750, 215,200, 25);

        // JButtons
        cancelButton.setBounds(750, 520, 100, 30);
        saveButton.setBounds(850, 520, 100, 30);
        testRenderButton.setBounds(750, 480, 200, 30);
        deleteNodeButton.setBounds(525, 460, 200, 30);
        deleteRelationButton.setBounds(975, 460, 200, 30);
        addNodeButton.setBounds(750, 420, 200, 30);
        addRelationButton.setBounds(750, 380, 200, 30);

        // JLists
        nodeJList.setBounds(525, 150, 200,300);
        relationsJList.setBounds(975, 150, 200,300);

        // ActionListeners

        cancelButton.addActionListener(action -> {

            hideCreatorWindowComponents();
            showDefaultWindowComponents();
            this.creatorStructure = new Structure( false );
            nodeList.clear();
            relationsList.clear();

        });

        saveButton.addActionListener(action -> {

            creatorStructure.clone( true );

            structureList.addElement( structureNameInput.getText() );

            this.creatorStructure = new Structure( false );
            hideCreatorWindowComponents();
            showDefaultWindowComponents();

        });

        testRenderButton.addActionListener(action -> {

            this.renderedStructure = creatorStructure.clone( false );
            repaint();

        });

        deleteRelationButton.addActionListener(action -> {

            int index = relationsJList.getSelectedIndex();

            if ( index != -1 ) {

                this.creatorStructure.getRelations().remove( index );
                relationsList.removeElementAt( index );

                statusMessages.setForeground(Color.GREEN);
                statusMessages.setText("Relation was deleted!");

            } else {

                statusMessages.setForeground(Color.RED);
                statusMessages.setText("No Relation was selected!");

            }

        });

        deleteNodeButton.addActionListener(action -> {

            int index = nodeJList.getSelectedIndex();

            if ( index != -1 ) {

                this.creatorStructure.deleteRelationContaining( this.creatorStructure.getNodes().get( index ), relationsList );

                this.creatorStructure.getNodes().remove( index );
                nodeList.removeElementAt( index );

                statusMessages.setForeground(Color.GREEN);
                statusMessages.setText("Node was deleted!");

            } else {

                statusMessages.setForeground(Color.RED);
                statusMessages.setText("No Node was selected or is available!");

            }

        });

        addNodeButton.addActionListener(action -> {

            try {

                int x = Integer.parseInt(xValueInput.getText());
                int y = Integer.parseInt(yValueInput.getText()) * -1;
                int z = Integer.parseInt(zValueInput.getText());

                if ( creatorStructure.addNode(x, y, z) ) {

                    nodeList.addElement("(" + x + ", " + y * (-1) + ", " + z + ")");

                    statusMessages.setForeground(Color.GREEN);
                    statusMessages.setText("Node was added!");

                } else {

                    statusMessages.setForeground(Color.RED);
                    statusMessages.setText("Node already exists!");

                }


            } catch ( Exception error ) {

                statusMessages.setForeground(Color.RED);
                statusMessages.setText("Node couldn't be saved! Make sure you only input numbers.");

            }

        });

        addRelationButton.addActionListener(action -> {

            int[] indices = nodeJList.getSelectedIndices();

            if ( indices.length == 2 ) {

                if ( this.creatorStructure.addRelation( this.creatorStructure.getNodes().get( indices[0] ), this.creatorStructure.getNodes().get( indices[1] ) ) ) {

                    relationsList.addElement( this.creatorStructure.getNodes().get( indices[0] ) + " -> " + this.creatorStructure.getNodes().get( indices[1] ) );

                    statusMessages.setForeground(Color.GREEN);
                    statusMessages.setText("Relation was added!");

                } else {

                    statusMessages.setForeground(Color.RED);
                    statusMessages.setText("Relation already exists!");

                }

            } else {

                statusMessages.setForeground(Color.RED);
                statusMessages.setText("Relation couldn't be saved! Make sure to select Tupels of Nodes.");

            }

        });


        // ------------------------ JFrame customization ------------------------


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("3D Modelling");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(1200, 650);
        this.setVisible(true);
        this.setLayout(null);

        hideCreatorWindowComponents();

        this.add(availableStructuresText);
        this.add(structureJList);
        this.add(rotateXButton);
        this.add(rotateYButton);
        this.add(rotateZButton);
        this.add(renderButton);
        this.add(drawingPanel);
        this.add(addStructureButton);
        this.add(copyright);

        this.add(leftBrace);
        this.add(rightBrace);
        this.add(xValueInput);
        this.add(yValueInput);
        this.add(zValueInput);
        this.add(cancelButton);
        this.add(saveButton);
        this.add(testRenderButton);
        this.add(nodeJList);
        this.add(relationsJList);
        this.add(nodeListText);
        this.add(relationsListText);
        this.add(structureNameText);
        this.add(structureNameInput);
        this.add(deleteNodeButton);
        this.add(deleteRelationButton);
        this.add(addNodeButton);
        this.add(addRelationButton);
        this.add(statusMessages);
        this.add(nodeInputText);

    }

    /**
     * Hides the default window components.
     */

    public void hideDefaultWindowComponents() {

        this.renderedStructure = creatorStructure;
        repaint();

        availableStructuresText.setVisible(false);
        structureJList.setVisible(false);
        renderButton.setVisible(false);
        addStructureButton.setVisible(false);

    }

    /**
     * Shows the default window components.
     */

    public void showDefaultWindowComponents() {

        this.renderedStructure = Structure.structures.getFirst();
        repaint();

        availableStructuresText.setVisible(true);
        structureJList.setVisible(true);
        renderButton.setVisible(true);
        addStructureButton.setVisible(true);

    }

    /**
     * Hides the creator window components, resets the
     * input fields and clears the lists.
     */

    public void hideCreatorWindowComponents() {

        leftBrace.setVisible(false);
        rightBrace.setVisible(false);
        xValueInput.setVisible(false);
        yValueInput.setVisible(false);
        zValueInput.setVisible(false);
        cancelButton.setVisible(false);
        saveButton.setVisible(false);
        testRenderButton.setVisible(false);
        nodeJList.setVisible(false);
        relationsJList.setVisible(false);
        nodeListText.setVisible(false);
        relationsListText.setVisible(false);
        structureNameText.setVisible(false);
        structureNameInput.setVisible(false);
        deleteNodeButton.setVisible(false);
        deleteRelationButton.setVisible(false);
        addNodeButton.setVisible(false);
        addRelationButton.setVisible(false);
        statusMessages.setVisible(false);
        nodeInputText.setVisible(false);

        statusMessages.setText("");
        structureNameInput.setText("");
        xValueInput.setText("X");
        yValueInput.setText("Y");
        zValueInput.setText("Z");

        nodeList.clear();
        relationsList.clear();

    }

    /**
     * Shows the creator window components.
     */

    public void showCreatorWindowComponents() {

        leftBrace.setVisible(true);
        rightBrace.setVisible(true);
        xValueInput.setVisible(true);
        yValueInput.setVisible(true);
        zValueInput.setVisible(true);
        cancelButton.setVisible(true);
        saveButton.setVisible(true);
        testRenderButton.setVisible(true);
        nodeJList.setVisible(true);
        relationsJList.setVisible(true);
        nodeListText.setVisible(true);
        relationsListText.setVisible(true);
        structureNameText.setVisible(true);
        structureNameInput.setVisible(true);
        deleteNodeButton.setVisible(true);
        deleteRelationButton.setVisible(true);
        addNodeButton.setVisible(true);
        addRelationButton.setVisible(true);
        statusMessages.setVisible(true);
        nodeInputText.setVisible(true);

    }

}
