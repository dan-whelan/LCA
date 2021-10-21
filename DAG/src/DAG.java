public class DAG {
    private int V; //number of vertices in Graph
    private int E; //number of Edges in Graph
    private int[][] adj; //2D adjacancy matrix for vertex v
    private int[] in; //in degree of vertex v
    private int[] out; //out degree of vertex v
    private int[] visited; //vertices that have been visited

    public DAG(int V) {
        if(V < 0) {
            throw new IllegalArgumentException("A Graph cannot have less than 0 vertices");
        }
        else {
            this.V = V;
            this.E = 0;
            in = new int[V];
            out = new int[V];
            visited = new int[V];
            adj = new int[V][V];
            for(int i = 0; i < V; i++) {
                for(int j = 0; j < V; j++) {
                    adj[i][j] = 0; //setting up empty graph
                }
            }

        }
    }

    public int v() {
        return this.V;
    }

    public int e() {
        return this.E;
    }

    public boolean validate(int v) {
        if(v < 0 || v >= V) {
            return false;
        }
        return true;
    }

    public void addEdge(int v, int w) {
        if(validate(v) && validate(w)) {
            adj[v][w] = 1;
            in[w]++;
            out[v]++;
            E++;
        }
    }

    public void removeEdge(int v, int w) {
        if(validate(v) && validate(w)) {
            adj[v][w] = 0;
            in[w]--;
            out[v]--;
            E--;
        }
    }

    public int outDegreeVertex(int v) {
        if(validate(v)) {
            return out[v];
        }
        return -1;
    }

    public int inDegreeVertex(int v) {
        if(validate(v)) {
            return in[v];
        }
        return -1;
    }

    public int[] adj(int v) {
        if(validate(v)) {
            int[] temp = new int[out[v]];
            int counter = 0;
            for(int i = 0; i < V; i++) {
                if(adj[v][i]==1) {
                    temp[counter] = i;
                    counter++;
                }
            }
            return temp;
        }
        return new int[0];
    } 

    public boolean hasCycle() {
        int counter = 0;
        for(int i = 0; i < V; i++) {
            visited[counter] = i;
            for(int j = 0; j < V; j++) {
                for(int k = 0; k < V; k++) {
                    if(visited[k]==j && adj[i][j]==1) {
                        return true;
                    }
                }
            }
            counter++;
        }
        return false;
    }

    public int findLCA(int v, int w) {
        if(validate(v) && validate(w) && E > 0 && !hasCycle()) {
            return LCAUtility(v,w);
        }
        else {
            return -1;
        }
    }

    public int LCAUtility(int v, int w) {
        int[] vArray = new int[E];
        int[] wArray  = new int[E];
        boolean[] vMark = new boolean[V];
        boolean[] wMark = new boolean[V];
        int vCounter = 0;
        int wCounter = 0;
        vArray[vCounter] = v;
        wArray[wCounter] = w;
        for(int i = 0; i < V; i++) {
            vMark[i] = false;
            wMark[i] = false;
        }
        for(int i = 0; i < V; i++) {
            vMark[i] = true;
            wMark[i] = true;
            for(int j = 0; j < V; j++) {
                if(adj[i][j] == 1 && vMark[i]) {
                    vCounter++;
                    vArray[vCounter] = 1;
                    vMark[j] = true;
                }
                if(adj[i][j] == 1 && wMark[i]) {
                    wCounter++;
                    wArray[wCounter] = 1;
                    wMark[j] = true;
                }
                if(wArray[wCounter] == vArray[vCounter]) {
                    return wArray[wCounter];
                }
            }
        }
        return -1; //if no ancestor found
    }
}