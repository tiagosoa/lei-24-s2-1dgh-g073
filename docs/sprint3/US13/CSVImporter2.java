package US13;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVImporter2 {
    private final List<Object> lista1;
    private final List<Object> lista2;
    private final List<Integer> lista3;


    public CSVImporter2(String direction) {
        lista1 = new ArrayList<>();
        lista2 = new ArrayList<>();
        lista3 = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(direction));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (isInteger(parts[0])) {
                    lista1.add(Integer.parseInt(parts[0]));
                } else {
                    lista1.add(parts[0]);
                }
                if (isInteger(parts[1])) {
                    lista2.add(Integer.parseInt(parts[1]));
                } else {
                    lista2.add(parts[1]);
                }

                lista3.add(Integer.parseInt(parts[2]));

                // lista1.add(Integer.parseInt(parts[0]));
                //                lista2.add(Integer.parseInt(parts[1]));
            }
            reader.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Object> getLista1() {
        return lista1;
    }

    public List<Object> getLista2() {
        return lista2;
    }

    public List<Integer> getLista3() {
        return lista3;
    }

    public int getNumberOfEdges(){
        // Contagem de elementos diferentes
           int diferentes = 0;

        // Verificar se as listas têm o mesmo tamanho
        if (lista1.size() != lista2.size()) {
            diferentes += Math.abs(lista1.size() - lista2.size()); // Adicionar a diferença de tamanho à contagem
        }

        // Verificar elementos diferentes
        for (int i = 0; i < Math.min(lista1.size(), lista2.size()); i++) {
            if (!lista1.get(i).equals(lista2.get(i))) {
                diferentes++;
            }
        }

        return diferentes;
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isStringArrayList(List<Object> lista) {
        for (Object obj : lista) {
            if (obj == null || !(obj.getClass().equals(String.class))) {
                return false;
            }
        }
        return true;
    }


}
