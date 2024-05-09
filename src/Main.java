import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {

        //Initializes the reader
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        //Read input
        String[] passage = in.readLine().split(" ");
        int length = Integer.parseInt(passage[0]);
        int width = Integer.parseInt(passage[1]);
        int diameter = Integer.parseInt(in.readLine());
        int numOfLasers = Integer.parseInt(in.readLine());

        //Creates array to store beams/lasers
        Beam[] beams = new Beam[numOfLasers];

        //Storing the information of the beams/lasers in the array
        for (int i = 0; i < numOfLasers; i++) {
            String[] position = in.readLine().split(" ");
            beams[i] = new Beam(Integer.parseInt(position[0]), Integer.parseInt(position[1]), width, diameter);
        }

        //Calls the class and prints the result
        RobGame rob = new RobGame(diameter, beams);
        if (rob.canEscape()) System.out.println("Rob manages to escape!");
        else System.out.println("Impossible to escape");
    }
}