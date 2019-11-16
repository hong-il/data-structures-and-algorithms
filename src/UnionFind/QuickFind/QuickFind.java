package UnionFind.QuickFind;

import java.util.Scanner;

/**Quick-find is too slow
 * Cost model -> Number of array accesses (for read or write).
 * 1. initialize : N
 * 2. union : N (too expensive)
 * 3. find : 1
 * It takes N² array accesses to process a sequence of N union commands on N objects.
 * */
public class QuickFind {

    private int[] id;

    /**
     * Integer array id[] of length N.
     * */
    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    /**
     * Check if p and q have the same id.
     * */
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    /**
     * To merge components containing p and q, change all entries
     * whose id equals id[p] to id[q].
     * */
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) id[i] = qid;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        QuickFind qf = new QuickFind(N);

        int n = sc.nextInt();
        int cnt = N;
        for (int i = 0; i < n; i++) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (!qf.connected(p, q)) {
                qf.union(p, q);
                cnt--;
            }
        }

        System.out.println("Count : " + cnt);
        sc.close();
    }
}
