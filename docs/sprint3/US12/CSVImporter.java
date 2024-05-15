package US12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVImporter {
    public List<Route> importRoutes(String filename) throws IOException {
        List<Route> routes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            int start = Integer.parseInt(parts[0].trim());
            int end = Integer.parseInt(parts[1].trim());
            int distance = Integer.parseInt(parts[2].trim());

            WaterPoint startPoint = new WaterPoint(start);
            WaterPoint endPoint = new WaterPoint(end);
            Route route = new Route(startPoint, endPoint, distance);
            routes.add(route);
        }

        reader.close();
        return routes;
    }
}

