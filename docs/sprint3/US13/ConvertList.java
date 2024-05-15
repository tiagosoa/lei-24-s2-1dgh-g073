package US13;

import java.util.ArrayList;
import java.util.List;

public class ConvertList {


    public static List<String> toStrings(List<Object> listaDeObjetos) {
        List<String> listaDeStrings = new ArrayList<>();
        for (Object obj : listaDeObjetos) {
            if (obj instanceof String) {
                listaDeStrings.add((String) obj);
            }
        }
        return listaDeStrings;
    }

    public static List<Integer> toIntegers(List<Object> listaDeObjetos) {
        List<Integer> listaDeInteiros = new ArrayList<>();
        for (Object obj : listaDeObjetos) {
            if (obj instanceof Integer) {
                listaDeInteiros.add((Integer) obj);
            }
        }
        return listaDeInteiros;
    }

}
