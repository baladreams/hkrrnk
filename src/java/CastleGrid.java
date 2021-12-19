import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// solution for https://www.hackerrank.com/challenges/castle-on-the-grid/problem

class CastleGrid {

    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {
        int[][] moves = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        Queue<int[]> positions = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        positions.add(new int[] { startX, startY, 0, -1 });
        visited.put(startX + ":" + startY, 0);
        int min = Integer.MAX_VALUE;
        while (!positions.isEmpty()) {
            int[] curPos = positions.poll();
            for (int[] move : moves) {
                int[] newPos = curPos;
                do {
                    newPos = new int[] { newPos[0] + move[0], newPos[1] + move[1],
                            curPos[2] + 1,
                            move[0] == 0 ? 1 : 0 };
                    if (newPos[0] >= 0 && newPos[1] >= 0 && newPos[0] < grid.size()
                            && newPos[1] < grid.get(0).length()) {
                        if (visited.getOrDefault(newPos[0] + ":" + newPos[1], Integer.MAX_VALUE) > newPos[2]
                                && 'X' != grid.get(newPos[0]).charAt(newPos[1])) {
                            if (newPos[0] == goalX && newPos[1] == goalY) {
                                min = Math.min(min, newPos[2]);
                                // printSolution(grid, parent, curPos, startX, startY, goalX, goalY);
                            } else {
                                visited.put(newPos[0] + ":" + newPos[1], newPos[2]);
                                parent.put(newPos[0] + ":" + newPos[1], curPos[0] + ":" + curPos[1]);
                                positions.add(newPos);
                            }
                        } else if ('X' == grid.get(newPos[0]).charAt(newPos[1])) {
                            break;
                        }
                    } else {
                        break;
                    }
                } while (true);
            }
        }
        return min;
    }

    private static void printSolution(List<String> grid, Map<String, String> parent, int[] curPos, int startX,
            int startY, int goalX, int goalY) {
        System.out.println();
        System.out.println("Possible Solution");
        String p = curPos[0] + ":" + curPos[1];
        List<String> resultGrid = new ArrayList<>(grid.size());
        resultGrid.addAll(grid);
        resultGrid.set(startX,
                resultGrid.get(startX).substring(0, startY) + "B" + resultGrid.get(startX).substring(startY + 1));
        resultGrid.set(goalX,
                resultGrid.get(goalX).substring(0, goalY) + "E" + resultGrid.get(goalX).substring(goalY + 1));
        while (parent.containsKey(p)) {
            String[] index = p.split(":");
            resultGrid.set(Integer.parseInt(index[0]),
                    resultGrid.get(Integer.parseInt(index[0])).substring(0, Integer.parseInt(index[1])) + "S"
                            + resultGrid.get(Integer.parseInt(index[0]))
                                    .substring(Integer.parseInt(index[1]) + 1));
            p = parent.get(p);
        }
        for (String s : resultGrid) {
            System.out.println(s);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] gridArray = { "...", ".X.", "..." };
        // 2
        System.out.println(CastleGrid.minimumMoves(Arrays.asList(gridArray), 0, 0, 1, 2));
        gridArray = new String[] { ".X.", ".X.", "..." };
        // 3
        System.out.println(CastleGrid.minimumMoves(Arrays.asList(gridArray), 0, 0, 0, 2));
        // 3
        gridArray = new String[] { ".X..XX...X", "X.........", ".X.......X", "..........", "........X.", ".X...XXX..",
                ".....X..XX", ".....X.X..", "..........", ".....X..XX" };
        System.out.println(CastleGrid.minimumMoves(Arrays.asList(gridArray), 9, 1, 9, 6));
        // 13
        gridArray = new String[] { ".X..X.X..X..X.......XX..XX....X.X...X........X.....XX.X.X...X.X...X..X",
                "...X.....XX.........XX......X.X.......X......X..XX.X..X..X.....X.X....",
                "............X.......X........X..X.X......X.......X...X.X.....X.X...X..",
                ".........X....XX.X.X.X......X..X......X.....X.........X..X.......XX...",
                ".....X.......X.X.....XX.....X.XXX.........X.....X.X....XX......XX.....",
                "..X....X..........................X...X.........XX.....X..............",
                "......X.......X...XX.....X.X....X.......X.............X........X.X....",
                "...X.X.XX.XX...X............XX...X.....X..X..X....X.........X.........",
                "X.XX........X..........XX..X.X..X.XX.XXX..X........X..X.....XX......X.",
                "......X..XXX.......X..XX.XX...........X.....XX..X..X.X......X.X...X...",
                "X........X.X....X..X..................XX......X..X.......X.....X..X...",
                "...X......X....XX.......X.....X...........X..X....X.....XXX...X...X.X.",
                "......X..X....X.XXX.X.....X..X....XX.....X....X.....X...X...........X.",
                "....X..X.X...XX..X.X.X..X.....X......X..X......X.X.X.X......X......X..",
                "..X..............X...X.........X........X...........X..X.X......X....X",
                ".X....X..X......X.........X.....XX....XX............XX..X...X...X.....",
                "...........X....X.X...XX...X......X...............X....XX..........X..",
                ".X..X..XX..X...X.....XX...XX...........X.....X..XXX.........XX..X....X",
                ".XX........XX.XX..........XX............X.........X.XXXX.X.X.........X",
                ".....X........X......X.............X.......X............X....X...X..X.",
                "X..X.X..X...........XX..X.....X......X...XXX........XX...........X....",
                "..X....X.XX.X.....X..X..X...........X......X..........X.....X.......X.",
                "..........X.X...X.....X....XX.XX.......XX...X.............XXX..X..X..X",
                "X.....X....XX...X.X...X.X.X..X.X..X....X..X..XXX...X...........X.X.X..",
                "...XX.X....X...X.....XX...X.....X..XXX.......XX......X....XX......X.XX",
                "X..X......X.....X......X.X...X............X.X..........X.X.X..X......X",
                "..X....X..X.X....XX....X.XXX..X.XX.....X........X..X...X...X.X......X.",
                ".......X...............XX..........X...X......XX...X.X........X.......",
                "XXX....X.....X..X.....X.X.....XX..X.......X..X.....XX.......X..X.....X",
                ".......X......X.......X..X.......X.........X...X.........X...X.....XXX",
                "...X..X....X....X.X..XX......X.......X............X...................",
                ".X.....X............X...............X.....X.X...X...X.XXX..X....X..XXX",
                "..........X........X...........XX..........X..............X.....XX.X..",
                ".X...............XX..X.X......................X..X...X......X.....X...",
                "XX..X.X..XXXX..X..........XX..XX..X.............X................XX...",
                "......X.XX..X...............X.X....X....X......X.....X..XX............",
                "..X.X..X..X......X..X................X......X...X......X.XX...X..X....",
                ".........X............X......X......XX.X..................X.....X.....",
                "X..X....X...........X.....XX..X.......XXX.....XXX.......X....X.....X.X",
                "XX..............XXX....X.X......XX..........X....X.....X......X.......",
                ".......X.XX.......X......X..XXX.............X.......XX.....X.XX.......",
                "..X.X.....XXX.X.......X.X.........X..X...X...X..X.....X.....XX.......X",
                "..XX........XX....X..XX..X...XXX.................X..X...........X.....",
                "...X........X..X.....X......X.X...XXX..X..XX.X..X...X........X.XXXX...",
                "...X.X....X.....X.X.......X..............X...X.X.XX...X...XX.X.......X",
                "......X...........X.......X.....XXXX...........X.X.XX......X...X......",
                "....X......X......XXX..XX.X.......X.............X.......X.........X..X",
                "..X..X..X......X.....X............XX....X..........X......X..X..X.X...",
                "X.........X..X..XX........X.X.X......................X.X....X.....X...",
                ".....X.X...X.X..X...X...XX...X...X............X..............X...X....",
                "......................X....X...X....X.X..............XX.....X.........",
                ".................X..X.....X.....XX......X.......X......X.........X.X..",
                "...........X..X...X.....X..X.............X............X..X..XX.X......",
                "X..........X.X..X..X..........X.XX.............X...X.XX........X......",
                "..XX.XX.....X.....X..X.....X.....X...X...........X..X..X....X.........",
                "..X.XX...X.X....X.X....X..X.X...X..X.........X..X.....................",
                "............X...................X....X.X......X.XX..X...X....X..XX.XX.",
                "...X..X.X....X..............X..X.....X.X.........X..........X.......X.",
                "..X...........................X......X.X...X........X.................",
                ".........X..X...............X...........X..X.X......X.................",
                "..XX.............XX.X.........X....XX........X..X...X........X.....X..",
                ".............X.X....X.X...X...X.....X...X.....X.....X..X......X......X",
                ".....X....X.X.X...XXX...X.X.X.X...X...X.X.....X..X...........X.X.X...X",
                "...XX.X...XX......X..........X.......XX..X.......X...X..X.X......X....",
                ".......X.......X....................X..........XX.....XXXX..X.X.......",
                ".....X...XX...X........X..X...X.X...X..........X...X.........X........",
                "XXX..XX.....X...................X.....X.X.......X.X.X..X..............",
                "....X.........X.....X.X...XX.....XX......X..........XX..X.XXX...X.X...",
                "..X...............X.XX.......X....X......X......X.X.X.......X.......X.",
                "..X.X.......XXX..X....X...X....X.....X.X......X..X......X............." };
        System.out.println(CastleGrid.minimumMoves(Arrays.asList(gridArray), 2, 42, 68, 12));
    }
}
