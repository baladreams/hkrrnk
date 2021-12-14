import java.util.*;
import static java.util.stream.Collectors.joining;

// solution for https://www.hackerrank.com/challenges/swap-nodes-algo/problem

class SwapNodes {

    static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static void inOrder(TreeNode node, List<Integer> inOrder) {
        if (node == null)
            return;
        if (node.val == -1)
            return;
        inOrder(node.left, inOrder);
        inOrder.add(node.val);
        inOrder(node.right, inOrder);
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        List<List<Integer>> result = new ArrayList<>();
        TreeNode root = new TreeNode(1);
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        for (List<Integer> nodeList : indexes) {
            TreeNode curNode = treeNodes.poll();
            while (curNode.val == -1)
                curNode = treeNodes.poll();
            curNode.left = new TreeNode(nodeList.get(0));
            curNode.right = new TreeNode(nodeList.get(1));
            treeNodes.add(curNode.left);
            treeNodes.add(curNode.right);
        }
        for (int query : queries) {
            int depth = 0;
            List<TreeNode> nodes = new LinkedList<>();
            nodes.add(root);
            while (!nodes.isEmpty()) {
                depth++;
                List<TreeNode> nextNodes = new LinkedList<>();
                for (TreeNode n : nodes) {
                    if (depth % query == 0) {
                        TreeNode temp = n.left;
                        n.left = n.right;
                        n.right = temp;
                    }
                    if (n.left != null)
                        nextNodes.add(n.left);
                    if (n.right != null)
                        nextNodes.add(n.right);
                }
                nodes = nextNodes;
            }
            List<Integer> inOrderList = new LinkedList<>();
            inOrder(root, inOrderList);
            result.add(inOrderList);
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> indexes = new ArrayList<>();
        Integer[][] indexesArray = { { 2, 3 }, { -1, -1 }, { -1, -1 } };
        for (Integer[] index : indexesArray) {
            indexes.add(Arrays.asList(index));
        }
        Integer[] queriesArray = new Integer[] { 1, 1 };
        List<Integer> queries = Arrays.asList(queriesArray);

        List<List<Integer>> result1 = swapNodes(indexes, queries);
        // 3 1 2
        // 2 1 3
        result1.stream().map(l -> l.stream().map(Object::toString).collect(joining(" "))).forEach(System.out::println);

        indexes = new ArrayList<>();
        indexesArray = new Integer[][] { { 2, 3 }, { -1, 4 }, { -1, 5 }, { -1, -1 },
                { -1, -1 } };
        for (Integer[] index : indexesArray) {
            indexes.add(Arrays.asList(index));
        }
        queriesArray = new Integer[] { 2 };
        queries = Arrays.asList(queriesArray);
        List<List<Integer>> result2 = swapNodes(indexes, queries);
        // 4 2 1 5 3
        result2.stream().map(l -> l.stream().map(Object::toString).collect(joining(" "))).forEach(System.out::println);

        indexes = new ArrayList<>();
        indexesArray = new Integer[][] { { 2, 3 }, { 4, -1 }, { 5, -1 }, { 6, -1 }, { 7, 8 }, { -1, 9 }, { -1, -1 },
                { 10, 11 }, { -1, -1 }, { -1, -1 }, { -1, -1 } };
        for (Integer[] index : indexesArray) {
            indexes.add(Arrays.asList(index));
        }
        queriesArray = new Integer[] { 2, 4 };
        queries = Arrays.asList(queriesArray);
        List<List<Integer>> result3 = swapNodes(indexes, queries);
        // 2 9 6 4 1 3 7 5 11 8 10
        // 2 6 9 4 1 3 7 5 10 8 11
        result3.stream().map(l -> l.stream().map(Object::toString).collect(joining(" "))).forEach(System.out::println);
    }
}
