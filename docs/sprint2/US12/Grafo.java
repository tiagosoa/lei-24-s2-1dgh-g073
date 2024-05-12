package US12;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Route> routes;
    private static final List<Route> ROUTE_BY_OMISSION = new ArrayList<Route>();

    public Grafo(List<Route> routes){
        this.routes = routes;
    }
    public Grafo(){
        routes = ROUTE_BY_OMISSION;
    }
    public void setRoutes(List<Route> routes){
        this.routes = routes;
    }
    public List<Route> getRoutes(){
        return routes;
    }

    public void addRoute(Route route){
        routes.add(route);
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for(Route route : routes){
            str.append(route.toString());
            str.append("\n");
        }
        return str.toString();
    }
}
