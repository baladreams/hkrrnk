import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Scanner;

// solution for https://www.hackerrank.com/challenges/java-vistor-pattern/problem

public class Visitors {

    enum Color {
        RED, GREEN
    }

    abstract static class Tree {

        private int value;
        private Color color;
        private int depth;

        public Tree(int value, Color color, int depth) {
            this.value = value;
            this.color = color;
            this.depth = depth;
        }

        public int getValue() {
            return value;
        }

        public Color getColor() {
            return color;
        }

        public int getDepth() {
            return depth;
        }

        public abstract void accept(TreeVis visitor);
    }

    static class TreeNode extends Tree {

        private ArrayList<Tree> children = new ArrayList<>();

        public TreeNode(int value, Color color, int depth) {
            super(value, color, depth);
        }

        public void accept(TreeVis visitor) {
            visitor.visitNode(this);

            for (Tree child : children) {
                child.accept(visitor);
            }
        }

        public void addChild(Tree child) {
            children.add(child);
        }
    }

    static class TreeLeaf extends Tree {

        public TreeLeaf(int value, Color color, int depth) {
            super(value, color, depth);
        }

        public void accept(TreeVis visitor) {
            visitor.visitLeaf(this);
        }
    }

    abstract static class TreeVis {
        public abstract int getResult();

        public abstract void visitNode(TreeNode node);

        public abstract void visitLeaf(TreeLeaf leaf);

    }

    static class SumInLeavesVisitor extends TreeVis {
        int result = 0;

        public int getResult() {
            return result;
        }

        public void visitNode(TreeNode node) {
            result = result;
        }

        public void visitLeaf(TreeLeaf leaf) {
            this.result += leaf.getValue();
        }
    }

    static class ProductOfRedNodesVisitor extends TreeVis {
        BigInteger result = BigInteger.ONE;

        public int getResult() {
            // implement this
            return result.mod(BigInteger.valueOf(1000000007)).intValue();
        }

        public void visitNode(TreeNode node) {
            if (node.getColor() == Color.RED && node.getValue() > 1)
                this.result = this.result.multiply(BigInteger.valueOf(node.getValue()));
        }

        public void visitLeaf(TreeLeaf leaf) {
            if (leaf.getColor() == Color.RED && leaf.getValue() > 1)
                this.result = this.result.multiply(BigInteger.valueOf(leaf.getValue()));
        }
    }

    static class FancyVisitor extends TreeVis {
        int evenSum = 0;
        int greenLeafSum = 0;

        public int getResult() {
            // implement this
            return Math.abs(evenSum - greenLeafSum);
        }

        public void visitNode(TreeNode node) {
            // implement this
            if (node.getDepth() % 2 == 0)
                this.evenSum += node.getValue();
        }

        public void visitLeaf(TreeLeaf leaf) {
            if (leaf.getColor() == Color.GREEN)
                this.greenLeafSum += leaf.getValue();
        }
    }

    public static Tree solve() {
        // read the tree from STDIN and return its root as a return value of this
        // function
        Tree root = null;
        try {
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
            // String nString = br.readLine();
            int n = 100;// Integer.parseInt(nString);
            String valueString = "237 945 785 695 165 919 740 706 67 342 6 282 965 39 441 922 87 555 936 550 860 972 448 577 931 542 733 374 977 335 709 440 850 493 729 478 871 538 189 557 356 737 341 946 875 969 198 315 376 540 84 216 3 833 51 310 992 898 27 52 670 141 629 770 903 355 834 43 156 882 48 317 959 842 776 671 312 931 640 368 525 900 824 485 292 90 665 646 748 73 636 619 992 603 248 946 472 778 601 935";// br.readLine();
            String[] valueStrings = valueString.split("\\s");
            String colourString = "0 0 0 0 1 0 0 1 1 0 1 1 0 1 0 1 0 1 1 0 0 1 1 1 0 0 1 0 0 0 1 1 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1 1 0 0 1 0 1 0 1 1 0 1 1 0 0 0 0 1 0 0 0 0 0 0 1 1 1 0 0 1 1 1 0 0 0 1 0 0 1 0 0 1 0 0 0 0 1 0 0 0 1 1 0";// br.readLine();
            String[] colourStrings = colourString.split("\\s");
            List<Set<Integer>> childrenMap = new ArrayList<Set<Integer>>(n + 1);
            for (int i = 0; i <= n; i++) {
                childrenMap.add(new HashSet<Integer>());
            }
            String[] edgeInput = new String[] { "1 97", "1 4", "1 5", "1 55", "1 72", "1 73", "1 42", "1 75", "1 12",
                    "1 78", "1 48", "1 82", "1 51", "1 83", "1 87", "1 89", "1 90", "1 32", "1 94", "1 70", "2 4",
                    "2 9", "2 10", "2 11", "2 13", "2 15", "2 17", "2 19", "2 20", "2 31", "2 41", "2 44", "2 45",
                    "2 53", "2 61", "2 62", "2 64", "2 66", "2 79", "2 88", "2 92", "2 93", "2 100", "3 33", "3 99",
                    "3 68", "3 5", "3 47", "3 80", "3 57", "3 26", "3 59", "4 67", "4 36", "4 34", "4 43", "4 56",
                    "4 14", "4 81", "4 24", "4 58", "4 91", "5 65", "5 69", "5 7", "5 8", "5 35", "5 84", "5 85",
                    "5 54", "5 23", "5 27", "6 33", "6 98", "6 76", "6 16", "6 22", "6 29", "6 95", "7 49", "7 52",
                    "7 28", "7 74", "8 77", "8 30", "8 71", "8 40", "9 60", "9 37", "9 63", "9 39", "10 96", "11 38",
                    "12 50", "16 46", "18 97", "19 25", "21 98", "23 86" };
            for (int i = 0; i < n - 1; i++) {
                String edgeString = edgeInput[i];// br.readLine();
                String[] edge = edgeString.split("\\s");
                int n1 = Integer.parseInt(edge[0]);
                int n2 = Integer.parseInt(edge[1]);
                childrenMap.get(n1).add(n2);
                childrenMap.get(n2).add(n1);
            }
            Tree[] nodes = new Tree[n];
            Stack<Integer> pendingNodes = new Stack<Integer>();
            Set<Integer> exploredNodes = new HashSet<Integer>();
            root = (childrenMap.get(0 + 1).isEmpty())
                    ? new TreeLeaf(Integer.parseInt(valueStrings[0]),
                            "0".equals(colourStrings[0]) ? Color.RED : Color.GREEN, 0)
                    : new TreeNode(Integer.parseInt(valueStrings[0]),
                            "0".equals(colourStrings[0]) ? Color.RED : Color.GREEN, 0);
            pendingNodes.push(1);
            nodes[0] = root;
            while (!pendingNodes.empty()) {
                int i = pendingNodes.pop();
                exploredNodes.add(i);
                for (Integer c : childrenMap.get(i)) {
                    if(exploredNodes.contains(c))
                        continue;
                    TreeNode current = (TreeNode) nodes[i - 1];
                    childrenMap.get(c).removeAll(exploredNodes);
                    Tree node = (childrenMap.get(c).isEmpty())
                            ? new TreeLeaf(Integer.parseInt(valueStrings[c - 1]),
                                    "0".equals(colourStrings[c - 1]) ? Color.RED : Color.GREEN, current.getDepth() + 1)
                            : new TreeNode(Integer.parseInt(valueStrings[c - 1]),
                                    "0".equals(colourStrings[c - 1]) ? Color.RED : Color.GREEN, current.getDepth() + 1);
                    nodes[c - 1] = node;
                    current.addChild(node);
                    pendingNodes.push(c);
                }
            }
            root = nodes[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }

    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
