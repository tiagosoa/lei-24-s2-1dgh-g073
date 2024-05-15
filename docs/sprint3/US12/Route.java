package US12;

public class Route {

    private WaterPoint startPoint;
    private WaterPoint endPoint;

    private int distance;

    private final WaterPoint START_POINT_BY_OMISSION = new WaterPoint();
    private final WaterPoint END_POINT_BY_OMISSION = new WaterPoint();

    private final int DISTANCE_BY_OMISSION = 0;

    public Route(WaterPoint startPoint, WaterPoint endPoint, int distance) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
    }

    public Route() {
        this.startPoint = START_POINT_BY_OMISSION;
        this.endPoint = END_POINT_BY_OMISSION;
        this.distance = DISTANCE_BY_OMISSION;
    }

    public WaterPoint getStartPoint() { return startPoint; }

    public WaterPoint getEndPoint() { return endPoint; }

    public int getDistance() { return distance; }

    public void setStartPoint(WaterPoint startPoint) { this.startPoint = startPoint; }

    public void setEndPoint(WaterPoint endPoint) { this.endPoint = endPoint; }

    public void setDistance(int distance) { this.distance = distance; }

    public String toString() {
        return String.format("Water Point X: %d, Water Point Y: %d, Distance: %d", getStartPoint().getVertice(), getEndPoint().getVertice(), getDistance());
    }






}
