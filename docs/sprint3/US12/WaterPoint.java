package US12;

public class WaterPoint{
    private int vertice;
    private static final int VERTICE_BY_OMISSION = 0;
    public WaterPoint(int vertice){
        this.vertice = vertice;
    }
    public WaterPoint(){
        vertice = VERTICE_BY_OMISSION;
    }
    public void setVertice(int vertice){
        this.vertice = vertice;
    }
    public int getVertice(){
        return vertice;
    }

    public String toString() {
        return String.format("Water Point: %d", getVertice());
    }
}
