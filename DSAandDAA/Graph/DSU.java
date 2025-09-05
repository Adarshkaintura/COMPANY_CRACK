public class DSU{

    public int findParent(int i,int[] parent){
        if(i==parent[i]){
            return i;
        }
        return findParent(parent[i],parent);
    }

    public void union(int x,int y,int[] parent){
      int x_parent=findParent(x, parent);
      int y_parent=findParent(y,parent);

      if(x_parent!=y_parent){
        parent[x_parent]=y_parent;
      }
    }
    //here what we do is we directly put parent in the child this is what we call path compression
    public int findParentCompressin(int i,int[] parent){
        if(i==parent[i]){
            return i;
        }
        return parent[i]=findParentCompressin(parent[i], parent);
    }
    //now for making graph union by Rank
    public void unionByRankPathCompression(int x,int y,int[] parent,int[] rank){
        int x_parent=findParentCompressin(x, parent);
        int y_parent=findParentCompressin(y, parent);
        
        if(x_parent==y_parent){
            return ;
        }
        if(rank[x_parent]>rank[y_parent]){
            parent[y_parent]=x_parent;
        }else if(rank[x_parent]<rank[y_parent]){
            parent[x_parent]=y_parent;
        }else{
            parent[x_parent]=y_parent;
            rank[y_parent]+=1;
        }

    }
    public static void main(String[] args){
       System.out.println("hello");
    }
}