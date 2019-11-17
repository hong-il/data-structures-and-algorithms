package UnionFind.QuickUnion;

import java.util.Scanner;

/**Quick-union is also too slow
 * Cost model -> Number of array accesses (for read or write).
 * 1. initialize : N
 * 2. union : N (includes cost of finding roots)
 * 3. find : N (worst case)
 * Trees can get tall.
 * Find too expensive (could be N array accesses).
 * */
public class QuickUnion {

    private int[] id;

    /**
     * set id of each object to itself
     * (N array accesses)
     * */
    public QuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
    }

    /**
     * chase parent pointers until reach root
     * (depth of i array accesses)
     * */
    private int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    /**
     * check if p and q have same root
     * (depth of p and q array accesses)
     * */
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    /**
     * change root of p to point to root of q
     * (depth of p and q array accesses)
     * */
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        QuickUnion qu = new QuickUnion(N);

        int n = sc.nextInt();
        int cnt = N;
        for(int i = 0; i < n; i++) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if(!qu.connected(p, q)) {
                qu.union(p, q);
                cnt--;
            }
        }

        System.out.println("Count : " + cnt);
        sc.close();
    }
}
