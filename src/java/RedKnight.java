import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// solution for https://www.hackerrank.com/challenges/red-knights-shortest-path/problem

class RedKnight {

    /*
     * Complete the 'printShortestPath' function below.
     *
     * The function accepts following parameters: 1. INTEGER n 2. INTEGER i_start 3.
     * INTEGER j_start 4. INTEGER i_end 5. INTEGER j_end
     */

    public static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
        // Print the distance along with the sequence of moves.
        List<String> moves = new ArrayList<>();
        List<List<Integer>> visitedNodes = new ArrayList<>();
        int[][] minMemory = new int[n + 1][n + 1];
        Map<String, List<String>> moveMemory = new HashMap<>();
        int minMoves = shortestPath(n, i_start, j_start, i_end, j_end, moves, visitedNodes, minMemory, moveMemory);
        if (minMoves == Integer.MAX_VALUE)
            System.out.println("Impossible");
        else {
            System.out.println(minMoves);
            StringBuffer sBuffer = new StringBuffer();
            for (String move : moves) {
                sBuffer.append(move);
                sBuffer.append(" ");
            }
            System.out.println(sBuffer.toString().trim());
        }
    }

    public static int shortestPath(int n, int i_start, int j_start, int i_end, int j_end, List<String> moves,
            List<List<Integer>> visitedNodes, int[][] minMemory, Map<String, List<String>> moveMemory) {
        if (i_start < 0 || i_start > n)
            return Integer.MAX_VALUE;
        if (j_start < 0 || j_start > n)
            return Integer.MAX_VALUE;
        if (i_start == i_end && j_start == j_end)
            return 0;
        if (minMemory[i_start][j_start] != 0) {
            moves.addAll(moveMemory.get(i_start + "," + j_start));
            return minMemory[i_start][j_start];
        }
        List<String> ulMoves = new ArrayList<>();
        List<String> urMoves = new ArrayList<>();
        List<String> lMoves = new ArrayList<>();
        List<String> rMoves = new ArrayList<>();
        List<String> llMoves = new ArrayList<>();
        List<String> lrMoves = new ArrayList<>();
        List<Integer> curLoc = new ArrayList<>();
        curLoc.add(i_start);
        curLoc.add(j_start);
        List<List<Integer>> visitedNodeCopy = new ArrayList<>();
        visitedNodeCopy.addAll(visitedNodes);
        visitedNodeCopy.add(curLoc);
        int ul = !visited(visitedNodeCopy, (i_start - 2), (j_start - 1))
                && isHotMove(i_start, j_start, i_start - 2, j_start - 1, i_end, j_end)
                        ? shortestPath(n, i_start - 2, j_start - 1, i_end, j_end, ulMoves, visitedNodeCopy, minMemory,
                                moveMemory)
                        : Integer.MAX_VALUE;
        int ur = !visited(visitedNodeCopy, (i_start - 2), (j_start + 1))
                && isHotMove(i_start, j_start, i_start - 2, j_start + 1, i_end, j_end)
                        ? shortestPath(n, i_start - 2, j_start + 1, i_end, j_end, urMoves, visitedNodeCopy, minMemory,
                                moveMemory)
                        : Integer.MAX_VALUE;
        int r = !visited(visitedNodeCopy, (i_start), (j_start + 2))
                && isHotMove(i_start, j_start, i_start, j_start + 2, i_end, j_end)
                        ? shortestPath(n, i_start, j_start + 2, i_end, j_end, rMoves, visitedNodeCopy, minMemory,
                                moveMemory)
                        : Integer.MAX_VALUE;
        int lr = !visited(visitedNodeCopy, (i_start + 2), (j_start + 1))
                && isHotMove(i_start, j_start, i_start + 2, j_start + 1, i_end, j_end)
                        ? shortestPath(n, i_start + 2, j_start + 1, i_end, j_end, lrMoves, visitedNodeCopy, minMemory,
                                moveMemory)
                        : Integer.MAX_VALUE;
        int ll = !visited(visitedNodeCopy, (i_start + 2), (j_start - 1))
                && isHotMove(i_start, j_start, i_start + 2, j_start - 1, i_end, j_end)
                        ? shortestPath(n, i_start + 2, j_start - 1, i_end, j_end, llMoves, visitedNodeCopy, minMemory,
                                moveMemory)
                        : Integer.MAX_VALUE;
        int l = !visited(visitedNodeCopy, (i_start), (j_start - 2))
                && isHotMove(i_start, j_start, i_start, j_start - 2, i_end, j_end)
                        ? shortestPath(n, i_start, j_start - 2, i_end, j_end, lMoves, visitedNodeCopy, minMemory,
                                moveMemory)
                        : Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        if (min > ul) {
            min = ul;
        }
        if (min > ur) {
            min = ur;
        }
        if (min > r) {
            min = r;
        }
        if (min > lr) {
            min = lr;
        }
        if (min > ll) {
            min = ll;
        }
        if (min > l) {
            min = l;

        }
        if (min != Integer.MAX_VALUE) {
            if (min == ul) {
                moves.add("UL");
                moves.addAll(ulMoves);
            } else if (min == ur) {
                moves.add("UR");
                moves.addAll(urMoves);
            } else if (min == r) {
                moves.add("R");
                moves.addAll(rMoves);
            } else if (min == lr) {
                moves.add("LR");
                moves.addAll(lrMoves);
            } else if (min == ll) {
                moves.add("LL");
                moves.addAll(llMoves);
            } else if (min == l) {
                moves.add("L");
                moves.addAll(lMoves);
            }
            minMemory[i_start][j_start] = min + 1;
            moveMemory.put(i_start + "," + j_start, moves);
            return min + 1;
        } else {
            minMemory[i_start][j_start] = min;
            moveMemory.put(i_start + "," + j_start, moves);
            return min;
        }
    }

    public static boolean visited(List<List<Integer>> visited, int i, int j) {
        for (List<Integer> loc : visited) {
            if (loc.get(0) == i && loc.get(1) == j)
                return true;
        }
        return false;
    }

    public static boolean isHotMove(int i_cur, int j_cur, int i_new, int j_new, int i_dest, int j_dest) {
        int curDistance = Math.abs(i_cur - i_dest) + Math.abs(j_cur - j_dest);
        int newDistance = Math.abs(i_new - i_dest) + Math.abs(j_new - j_dest);
        if (newDistance < curDistance)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        // UL UL UL L
        printShortestPath(7, 6, 6, 0, 1);
        // Impossible
        printShortestPath(6, 5, 1, 0, 5);
        // LR LL
        printShortestPath(7, 0, 3, 4, 3);
        // Impossible
        printShortestPath(120, 100, 75, 80, 2);
        // UR UR
        printShortestPath(5, 4, 1, 0, 3);
    }

}
