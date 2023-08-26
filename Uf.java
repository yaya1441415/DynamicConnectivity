public class Uf {
    private int[] id;
    //initialize a union-Find data structure.
    //with n objects.
    public Uf(int n){
        id=new int[n];
        for(int i =0; i<n; i++){
            id [i] = i;
        }
    }
    //add connection between p and q.
    public void union(int p, int q){
        //change all entries whose id equals id[p]
        //id[q]
        id[q] = id[p];
    }

    //check if q and p have the same id.
    public boolean quickfind(int p, int q){
      // p and q are connected if they have the same id.
        return id[p] == id[q];

    }

    public static void main(String[] args){
        Uf tru = new Uf(9);

        tru.union(3, 6);
        System.out.println(tru.quickfind(3,6));
        tru.union(6, 7);
        System.out.println(tru.quickfind(7, 3));
        tru.union(7, 1);
        System.out.println(tru.quickfind(1, 6));
    }


}
