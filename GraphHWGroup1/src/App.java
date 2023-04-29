import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        //read a csv file
        String fileName = "src\\FlightCostsSmall119.csv";
        String line = "";
        ArrayList<String> flight = new ArrayList<String>();
        ArrayList<String> origin = new ArrayList<String>();
        ArrayList<String> destination = new ArrayList<String>();
        ArrayList<Double> cost = new ArrayList<Double>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                flight.add(values[0]);
                origin.add(values[1]);
                destination.add(values[2]);
                cost.add(Double.parseDouble(values[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringToInt flightSI = new StringToInt(flight);
        StringToInt originSI = new StringToInt(origin);
        StringToInt destinationSI = new StringToInt(destination);

        EdgeWeightedDigraph G = new EdgeWeightedDigraph(flight.size());
        for (int i = 0; i < flight.size(); i++) {
            G.addEdge(new DirectedEdge(originSI.getCode(origin.get(i)), flightSI.getCode(flight.get(i)),
                    cost.get(i)));
            // G.addEdge(new DirectedEdge(flightSI.getCode(flight.get(i)), destinationSI.getCode(destination.get(i)), cost.get(i)));
        }
        System.out.println(G);

        DijkstraSP ewr = new DijkstraSP(G, originSI.getCode("EWR"));
        System.out.println(ewr.pathTo(destinationSI.getCode("DL1569")));
        // print out the sp result with the string representation
        // for (DirectedEdge e : ewr.pathTo(destinationSI.getCode("PBI"))) {
        //     System.out.println(originSI.getString(e.from()) + " to " + destinationSI.getString(e.to()) + " ($"
        //             + e.weight() + ")");
        // }
    }
}
        
