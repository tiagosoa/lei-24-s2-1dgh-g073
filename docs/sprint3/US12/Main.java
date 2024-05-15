
import US12.CSVImporter;
import US12.Grafo;
import US13.CSVImporter2;
import US13.ConvertList;
import US13.Edge;
import US13.KruskalAlgorithm;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        String caminho = "C:\\Users\\diogo\\IdeaProjects\\MDISC\\input\\us14_1.csv";
        processFile(caminho);

        String caminho2 = "C:\\Users\\diogo\\IdeaProjects\\MDISC\\input\\US13_JardimDosSentimentos.csv";
        processCSVFile(caminho2);
        String caminho3 = "C:\\Users\\diogo\\IdeaProjects\\MDISC\\input\\US13_JardimEspeciesNucleoRural.csv";
        processCSVFile(caminho3);
    }

    private static void processFile(String caminho) {
        try {
            CSVImporter csvImporter = new CSVImporter();
            Grafo grafo = new Grafo(csvImporter.importRoutes(caminho));
            System.out.println(grafo);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void processCSVFile(String caminho) {
        CSVImporter2 data = new CSVImporter2(caminho);
        List<Object> lista1 = data.getLista1();
        List<Object> lista2 = data.getLista2();
        List<Integer> lista3 = data.getLista3();
        int edges = data.getNumberOfEdges();

        if (!data.isStringArrayList(lista1) && !data.isStringArrayList(lista2)) {
            processKruskalAlgorithm(edges, lista1, lista2, lista3);
        }

        if (data.isStringArrayList(lista1) && data.isStringArrayList(lista2)) {
            processKruskalAlgorithmWithVertices(edges, lista1, lista2, lista3);
        }
    }

    private static void processKruskalAlgorithm(int edges, List<Object> lista1, List<Object> lista2, List<Integer> lista3) {
        KruskalAlgorithm graph = new KruskalAlgorithm(edges);
        List<Integer> lista1Inteiros = ConvertList.toIntegers(lista1);
        List<Integer> lista2Inteiros = ConvertList.toIntegers(lista2);

        for (int i = 0; i < lista1Inteiros.size(); i++) {
            int vetorI = lista1Inteiros.get(i);
            int vetorF = lista2Inteiros.get(i);
            int cost = lista3.get(i);
            graph.addEdge(vetorI, vetorF, cost);
        }

        printMST(graph.kruskalMST(), edges);
        exportInputedGraph(graph.kruskalMST(), edges);

    }

    private static void processKruskalAlgorithmWithVertices(int edges, List<Object> lista1, List<Object> lista2, List<Integer> lista3) {
        KruskalAlgorithm graph = new KruskalAlgorithm(edges);
        List<String> lista1String = ConvertList.toStrings(lista1);
        List<String> lista2String = ConvertList.toStrings(lista2);

        String[][] vertices = new String[2][edges];

        int y = 0;
        int m = 0;
        while (y < vertices[0].length) {
            vertices[0][y] = lista1String.get(m);
            vertices[1][y] = lista2String.get(m);
            y++;
            m++;
        }

        int z = edges - 1;
        for (int i = 0; i < lista3.size(); i++) {
            int vetorI = i;
            int vetorF = z;
            int cost = lista3.get(i);
            graph.addEdge(vetorI, vetorF, cost);
            z--;
        }

        printMSTWithVertices(graph.kruskalMST(), vertices, edges);
        exportInputedGraph(graph.kruskalMST(), vertices, edges);
    }

    private static void printMST(List<Edge> result, int edges) {
        int totalCost = 0;
        for (Edge edge : result) {
            System.out.println( edge.source + "," + edge.destination + "," + edge.weight);
            totalCost += edge.weight;
        }
        System.out.println("Number of edges: " + edges + "\n" + "Total cost: " + totalCost);
    }

    private static void printMSTWithVertices(List<Edge> result, String[][] vertices, int edges) {
        int totalCost = 0;
        for (Edge edge : result) {
            System.out.println(vertices[0][edge.source] + "," + vertices[1][(edges - 1) - edge.destination] + "," + edge.weight);
            totalCost += edge.weight;
        }
        System.out.println("Number of edges: " + edges + "\n" + "Total cost: " + totalCost);

    }

    static String graphName;

    public static void exportInputedGraph(List<Edge> result, String[][] vertices, int edges){

        StringBuilder graphString = new StringBuilder("graph InputedGraph {\n");

        for (Edge edge : result) {
            graphString.append(String.format("  %s -- %s [label=\"%d\"];\n", vertices[0][edge.source], vertices[1][(edges - 1) - edge.destination], edge.weight));
        }

        graphString.append("}");
        // Criar o Graphviz com a string do grafo
        try {
            Graphviz.fromString(graphString.toString()).render(Format.SVG).toFile(new File("src/main/java/pt/ipp/isep/dei/mdisc/project/export/" + graphName + "/inputedGraphJENR.svg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void exportInputedGraph(List<Edge> result, int edges){
        // Construir a string do grafo diretamente
        StringBuilder graphString = new StringBuilder("graph InputedGraph {\n");

        for (Edge edge : result) {
            graphString.append(String.format("  %d -- %d [label=\"%d\"];\n", edge.source, edge.destination, edge.weight));
        }

        graphString.append("}");

        // Criar o Graphviz com a string do grafo
        try {
            Graphviz.fromString(graphString.toString()).render(Format.SVG).toFile(new File("src/main/java/pt/ipp/isep/dei/mdisc/project/export/" + graphName + "/inputedGraphJDS.svg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}