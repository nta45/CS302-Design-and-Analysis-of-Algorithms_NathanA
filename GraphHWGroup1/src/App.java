import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        //read a csv file
        String fileName = "src\\FlightCostsSmall119.csv";
        String line = "";
        ArrayList<String> flight = new ArrayList<String>();
        ArrayList<Double> cost = new ArrayList<Double>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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
        // System.out.println(G);
        
        // use dikjstra
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Origin: ");
        String origin = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        DijkstraSP sp = new DijkstraSP(G, flightSI.getCode(origin));
        // System.out.println(sp.pathTo(flightSI.getCode(destination)));
        
        boolean value = sp.hasPathTo(flightSI.getCode(destination));
        if(value){
            System.out.println("The lowest fare from " + origin + " to "+ destination +" is: " + sp.distTo(flightSI.getCode(destination)));
            System.out.println("The path is " + sp.pathTo(flightSI.getCode(destination)));
        } else {
            System.out.println("There are no flights between "+  origin + " and "+ destination +".");
        }
        
    }
        
}

/** WORKING ON THE MULTIPLE ORIGINS INPUT ***
 * 
 * Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Origin: ");
        String origin = scanner.nextLine();
        String[] origins = origin.split(" ");

        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        String bestorigin = null;
        for (String o : origins) {
            DijkstraSP sp = new DijkstraSP(G, flightSI.getCode(o));            
            boolean value = sp.hasPathTo(flightSI.getCode(destination));
            if(value){
                if(sp.distTo(flightSI.getCode(destination)) < sp.distTo(flightSI.getCode(bestorigin))){
                    bestorigin = o;
                }
            }
        }
            DijkstraSP ans = new DijkstraSP(G, flightSI.getCode(bestorigin));
            boolean value = ans.hasPathTo(flightSI.getCode(destination));
            if(value){
                System.out.println("The lowest fare from " + bestorigin + " to "+ destination +" is: " + ans.distTo(flightSI.getCode(destination)));
            } else {
                System.out.println("There are no flights between "+  origin + " and "+ destination +".");
            }
 */