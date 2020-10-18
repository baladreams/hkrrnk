import java.io.IOException;

public class RoadsAndLibraries {

    // Complete the roadsAndLibraries function below.

    static int root(int n, int[] root) {
        while (root[n] != n)
            n = root[n];
        return n;
    }

    static long roadsAndLibraries(int n_city, int c_lib, int c_road, int[][] roads) {
        long result = 0;
        if (c_road >= c_lib)
            result = (long)n_city * c_lib;
        else { // execute Kruskal's to identify connected components
            int[] root = new int[n_city + 1];
            for (int i = 0; i <= n_city; i++) {
                root[i] = i;
            }
            for (int[] road : roads) {
                if(road[0]>road[1])
                {
                    int temp = road[0];
                    road[0] = road[1];
                    road[1] = temp;
                }
                int root0 = root(road[0], root);
                int root1 = root(road[1], root);
                if (root1 != root0) {
                    root[root1] = root[root0];
                }
            }
            int components = 0;
            for (int i = 1; i <= n_city; i++) {
                if (root(i, root) == i) {
                    components++;
                }
            }
            System.out.println(components);
            result = (long)components * (long)c_lib + (long)c_road * ((long)n_city - 1 - ((long)components - 1));// sum of edges in spanning tree of
                                                                                   // connected components
        }
        return result;
    }
    // use end

    public static void main(String[] args) throws IOException {

        String input = "96295 12709 81523 98351";

        String[] nmC_libC_road = input.split(" ");

        int n = Integer.parseInt(nmC_libC_road[0]);
        int m = Integer.parseInt(nmC_libC_road[1]);
        int c_lib = Integer.parseInt(nmC_libC_road[2]);
        int c_road = Integer.parseInt(nmC_libC_road[3]);
        int[][] cities = { { 1, 2 }, { 1, 3 }, { 1, 4 } };

        long result = roadsAndLibraries(n, c_lib, c_road, cities);

        System.out.println(result);

    }
}
