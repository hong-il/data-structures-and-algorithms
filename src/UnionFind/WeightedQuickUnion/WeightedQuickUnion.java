package UnionFind.WeightedQuickUnion;

import java.util.Scanner;

/**Modify quick-union to avoid tall trees.
 * Keep track of size of each tree (number of objects).
 * Balance by linking root of smaller tree to root of larger tree.
 * 1. initialize : N
 * 2. union : lg N
 * 3. connected : lg N
 * */
public class WeightedQuickUnion {

    private int[] id;
    private int[] size;

    /**
     * Same as quick-union, but maintain extra array size[i]
     * to count number of objects in the tree rooted at i.
     * */
    WeightedQuickUnion(int N) {
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Two-pass implementation: add second loop to root() to set the id[]
     *  of each examined node to the root.
     * Simpler one-pass variant: Make every other node in path point to its
     *  grandparent (thereby halving path length).
     * */
    int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    /**
     * Identical to quick-union.
     * */
    boolean connected(int p, int q) {
        return (root(p) == root(q));
    }

    /**
     * Modify quick-union to:
     * Link root of smaller tree to root of larger tree.
     * Update the size[] array.
     * */
    void union(int p, int q) {
        if (root(p) == root(q)) return;
        if (size[root(p)] < size[root(q)]) {
            id[root(p)] = root(q);
            size[root(q)] += size[root(p)];
        } else {
            id[root(q)] = root(p);
            size[root(p)] += size[root(q)];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        WeightedQuickUnion wqu = new WeightedQuickUnion(N);

        int n = sc.nextInt();
        int cnt = N;
        for (int i = 0; i < n; i++) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if(!wqu.connected(p, q)) {
                wqu.union(p, q);
                cnt--;
            }
        }

        System.out.println("Count : " + cnt);
        sc.close();
    }
}
