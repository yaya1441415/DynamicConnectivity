/**
 * this is an improvement if for the
 * unionfind data structure, the idea is to take steps
 * to avoid tall trees. when combining large and small trees,
 * make sure to avoid putting the larger trees lower.
 */
public class Uf_Weighting {
    private int[] id;

    //initialize the union find dataStructure.
    public Uf_Weighting(int n ){
        id = new int[n];
        for (int i = 0; i<n; i++){

            id[i]=i;
        }
    }

    //get the size of the tree.
    public int sz(int p){
        int size = 0;
        while(p!=id[p]){
            p = id[p];
            size++;
        }
        return size;
    }

    //get the root of p
    public int root(int p){
        while(p!=id[p]){
            p = id[p];
        }
        return p;
    }

    // add a connecton between p and q.
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);

        if (i == j){
            return;
        }
        if (sz(p)<sz(q)){
            id[i] = j;
        }
        else {
            id[j] = i;
        }
    }

    //return true if pand q are connected
    public boolean conneted(int p , int q){
        return root(p) == root(q);
    }

    public static void main(String[] args){
        Uf_Weighting data = new Uf_Weighting(8);
        //examples
        data.union(2, 5);
        System.out.println(data.conneted(2, 5));//true
        System.out.println(data.conneted(3, 5));// falss
        data.union(1, 5);
        System.out.println(data.conneted(2,1));// true
        data.union(2,7 );
        System.out.println(data.conneted(7, 1));// true 

    }
}
