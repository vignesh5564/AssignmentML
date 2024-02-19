import java.util.Scanner;

class HiddenNetwork {
    private int layers;
    private int[] nodes;
    private double[][][] weights;

    public HiddenNetwork(int l, int[] n) {
        this.layers = l;
        this.nodes = n;
        this.weights = new double[l - 1][][];
        for (int i = 0; i < l - 1; i++) {
            weights[i] = new double[n[i]][n[i + 1]];
        }
    }

    public void assignWeight(int layer, int fromNode, int toNode, double value) {
        if (layer < 1 || layer >= layers || fromNode < 0 || fromNode >= nodes[layer - 1]
                || toNode < 0 || toNode >= nodes[layer]) {
            System.out.println("Invalid layer or node index!");
            return;
        }
        weights[layer - 1][fromNode][toNode] = value;
    }

    public double getWeight(int layer, int fromNode, int toNode) {
        if (layer < 1 || layer >= layers || fromNode < 0 || fromNode >= nodes[layer - 1]
                || toNode < 0 || toNode >= nodes[layer]) {
            System.out.println("Invalid layer or node index!");
            return 0.0;
        }
        return weights[layer - 1][fromNode][toNode];
    }
}

public class SecretiveNetwork {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter total number of layers: ");
        int layers = scan.nextInt();

        int[] nodes = new int[layers];
        for (int i = 0; i < layers; i++) {
            System.out.print("Enter number of nodes in layer " + (i + 1) + ": ");
            nodes[i] = scan.nextInt();
        }

        HiddenNetwork net = new HiddenNetwork(layers, nodes);

        for (int i = 1; i < layers; i++) {
            for (int j = 0; j < nodes[i - 1]; j++) {
                for (int k = 0; k < nodes[i]; k++) {
                    System.out.print("Enter weight for edge from node " + j + " to node " + k + " in layer " + i + ": ");
                    double value = scan.nextDouble();
                    net.assignWeight(i, j, k, value);
                }
            }
        }

        System.out.print("Enter layer index: ");
        int layer = scan.nextInt();
        System.out.print("Enter source node index: ");
        int fromNode = scan.nextInt();
        System.out.print("Enter destination node index: ");
        int toNode = scan.nextInt();

        double weight = net.getWeight(layer, fromNode, toNode);
        System.out.println("Weight of edge from node " + fromNode + " to node " + toNode + " in layer " + layer + " is " + weight);

        scan.close();
    }
}