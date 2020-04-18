package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreeFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField textFieldBracketNotationTree;
    private JButton buttonMakeTree;
    private JButton buttonPostOrderTraverse;
    private JPanel panelPaintArea;


    private JPanel paintPanel = null;

    MyBinaryTree tree = new MyBinaryTree(s -> Integer.parseInt(s));

    public TreeFrame(){
        this.setTitle("Task 2.5");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        textFieldBracketNotationTree.setText("8 (6 (4 (5), 6), 5 (, 5 (2, 8)))");


        paintPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics gr) {
                super.paintComponent(gr);
                Point size = BinaryTreePainter.paint(tree, gr);
                setFixedSize(paintPanel, size.x, size.y);
            }
        };
        JScrollPane paintJScrollPane = new JScrollPane(paintPanel);
        panelPaintArea.add(paintJScrollPane);

        buttonMakeTree.addActionListener(actionEvent -> {
            try {
                MyBinaryTree tree = new MyBinaryTree(s -> Integer.parseInt(s));
                tree.fromBracketNotation(textFieldBracketNotationTree.getText());
                this.tree = tree;
                repaintTree();
            } catch (Exception ex) {
                showErrorMessageBox(null, "Error", ex);
            }
        });

        buttonPostOrderTraverse.addActionListener(actionEvent -> {
            tree.myPostOrderVisit((value, level) -> {
                repaintTree();
            });
        });

    }

    public void repaintTree(){
        paintPanel.repaint();
    }


    public  <T extends Component> T setFixedSize(T comp, int width, int height) {
        Dimension d = new Dimension(width, height);
        comp.setMaximumSize(d);
        comp.setMinimumSize(d);
        comp.setPreferredSize(d);
        comp.setSize(d);
        return comp;
    }
    public static void showErrorMessageBox(String message, String title, Throwable ex) {
        StringBuilder sb = new StringBuilder(ex.toString());
        if (message != null) {
            sb.append(message);
        }
        if (ex != null) {
            sb.append("\n");
            for (StackTraceElement ste : ex.getStackTrace()) {
                sb.append("\n\tat ");
                sb.append(ste);
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString(), title, JOptionPane.ERROR_MESSAGE);
    }

}
