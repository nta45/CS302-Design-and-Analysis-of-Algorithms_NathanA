import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class LowestFare {
    public static void main(String[] args) throws Exception {
        String line = "";
        ArrayList<String> flight = new ArrayList<String>();
        ArrayList<Double> cost = new ArrayList<Double>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/FlightCostsSmall119.csv"))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                flight.add(values[0]);
                flight.add(values[1]);
                flight.add(values[2]);
                cost.add(Double.parseDouble(values[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringToInt flightSI = new StringToInt(flight);

        EdgeWeightedDigraph G = new EdgeWeightedDigraph(flight.size());
        int costindex = 0;
        for (int i = 0; i < flight.size(); i+=3) {
            G.addEdge(new DirectedEdge(flightSI.getCode(flight.get(i+1)), flightSI.getCode(flight.get(i)),
                    0));
            G.addEdge(new DirectedEdge(flightSI.getCode(flight.get(i)), flightSI.getCode(flight.get(i+2)), cost.get(costindex)));
            costindex++;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Origin: ");
        String origin = scanner.nextLine();
        String[] origins = origin.split(" ");

        while (true) {
            System.out.print("Enter Destination: ");
            String destination = scanner.nextLine();
            
            String bestorigin = null;
            for (String o : origins) {
                DijkstraSP sp = new DijkstraSP(G, flightSI.getCode(o));            
                boolean value = sp.hasPathTo(flightSI.getCode(destination));
                if(value){
                    if (bestorigin == null) bestorigin = o;
                    else{
                        if(sp.distTo(flightSI.getCode(destination)) < sp.distTo(flightSI.getCode(bestorigin))){
                            bestorigin = o;
                        }
                    }
                }
            }
            System.out.println("---------------------------------------------");
            System.out.println("From the given origins, the lowest fare flight to "+ destination +" departs from: " + bestorigin + ".");

            DijkstraSP ans = new DijkstraSP(G, flightSI.getCode(bestorigin));
            boolean value = ans.hasPathTo(flightSI.getCode(destination));
            if(value){
                System.out.println("The lowest fare from " + bestorigin + " to "+ destination +" is: $ " + ans.distTo(flightSI.getCode(destination)));
                
                Stack<DirectedEdge> pathStack = new Stack<>();
                Iterable<DirectedEdge> path = ans.pathTo(flightSI.getCode(destination));
                    for (DirectedEdge edge : path) {
                        pathStack.push(edge);
                    }
                    String lastorigin = bestorigin;
                    while (!pathStack.isEmpty()) {
                        DirectedEdge edge = pathStack.pop();
                        String from = flightSI.getString(edge.from());
                        String to = flightSI.getString(edge.to());
                        Double fare = edge.weight();
                        if(fare!=0){
                            System.out.println("  -> Take Flight " + from + " from "+ lastorigin + " to " + to + " for $ " + fare);
                            lastorigin = to;
                        }
                        
                    }  
            } else {
                System.out.println("There are no flights between "+  origin + " and "+ destination +".");
            }
            System.out.println("---------------------------------------------");
        }
    }
}