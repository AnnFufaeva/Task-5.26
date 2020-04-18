package com.company;

import java.util.function.Function;


public class MyBinaryTree extends BinaryTree<Integer>{

    public MyBinaryTree(Function<String, Integer> fromStrFunc) {
        super(fromStrFunc);
    }
     public void myPostOrderVisit(Visitor<Integer> visitor) {

        class Inner {
            void myPostOrderVisit(TreeNode<Integer> node, Visitor<Integer> visitor, int level) {
                if (node == null) {
                    return;
                }
                myPostOrderVisit(node.getLeft(), visitor, level + 1);
                myPostOrderVisit(node.getRight(), visitor, level + 1);
                if (node.getLeft() == null){
                    if (node.getRight() == null) node.setValue(0);
                    else node.setValue(node.getRight().getValue() + 1);
                } else {
                    if (node.getRight() == null) node.setValue(node.getLeft().getValue() + 1);
                    else {
                        if (node.getLeft().getValue() > node.getRight().getValue()) node.setValue(node.getLeft().getValue() + 1);
                        else node.setValue(node.getRight().getValue() + 1);
                    }
                }

                visitor.visit(node.getValue(), level);

            }
        }

        new Inner().myPostOrderVisit(getRoot(), visitor, 0);
    }


}
