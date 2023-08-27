/**
 * in the union find data structure
 * after computing the root of p,
 * set the id of each examined node
 * to point to that root.
 */
public class Uf_PathCompression {


    //integer id[] of size N has trees in index
    // id[i] is parent of i.
    private int[] id;
    public Uf_PathCompression(int n){
        id = new int[n];
        for (int i = 0; i<n; i++){
            id[i]=i;
        }
    }

    public int root(int p){
        while (p != id[p]){
            id[p] = id[id[p]] = p;
            p = id[p];
        }
        return p;
    }
    // return true if the points are connected
    //false if not
    public boolean find(int p, int q ){
        //check if pand q have the same root.
        return root(p) == root(q);
    }
    //unite p and q.
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
    public static void main(String[] args){
        UfQuickUnion k = new UfQuickUnion(7);
        k.union(3, 6);
        k.union(1, 5);
        k.union(1,2);
        k.union(3,1);
        System.out.println(k.find(1, 3));
        System.out.println(k.find(1,6));
        System.out.println(k.find(2,5));
        System.out.println(k.find(0,1));
    }



}
