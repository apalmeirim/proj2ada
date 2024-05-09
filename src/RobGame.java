
public class RobGame {

    //Diameter of the robot
    private final int diameter;

    //Array with all the beams
    private Beam[] beams;

    /**
     * Constructor of the game class
     * @param diameter the diameter of the robot
     * @param beams the array with all the beams/lasers on the map
     */
    public RobGame(int diameter, Beam[] beams){
        this.diameter = diameter;
        this.beams = beams;
        createPartitions();
        //test();
    }

    /**
     * Creates the partitions with all the nodes (beams/lasers)
     */
    public void createPartitions() {
        for(int i = 0; i < this.beams.length; i ++) {
            for(int j = i + 1; j < this.beams.length; j ++) {
                union(this.beams[i], this.beams[j]);
            }
        }
    }

    /**
     * Creates a union between two nodes (beams/lasers)
     * A union occurs in the case where the distance between two lasers is not sufficiently large enough for
     * the robot to pass through, given the distance calculated and the diameter of the robot.
     * @param beam1 the first node (laser/beam) to unite
     * @param beam2 the second node (laser/beam) to unite
     */
    private void union(Beam beam1, Beam beam2){
        //Calculates the distance between the two beams
        double distance = Math.sqrt(Math.pow(beam2.getX() - beam1.getX(), 2) + Math.pow(beam2.getY() - beam1.getY(), 2));
        //If the distance between beams is inferior to the diameter of the robot,
        //i.e. the robot cannot pass between the two beams, adding them to the partition
        if(distance < this.diameter){
            //If the higher representative of the first beam is lower than higher representative of the second beam
            if(beam1.getRepHigher().getY() < beam2.getRepHigher().getY()) {
                beam2.addRepLower(beam1.getRepLower());
                beam1.addRepHigher(beam2.getRepHigher());
                beam1.getRepLower().addRepHigher(beam2.getRepHigher());
            }
            //If the lower representative of the first beam is higher than lower representative of the second beam
            else if (beam1.getRepLower().getY() > beam2.getRepLower().getY()){
                beam2.addRepHigher(beam1.getRepHigher());
                beam1.addRepLower(beam2.getRepLower());
                beam1.getRepHigher().addRepLower(beam2.getRepLower());
            }
        }
    }

    /**
     * Checks if the robot can escape, this occurs when in all the partition of lasers
     * the highest and lowest representative don't touch the superior and inferior walls.
     * If there exists a partition where the highest and lowest representative are a distance inferior to the
     * wall, the robot cannot escape.
     * @return false, if the robot cannot escape; true, if the robot can escape
     */
    public boolean canEscape(){
        //Checks all the lasers, and confirms if exists a partition that goes from the superior wall to the inferior wall.
        for (Beam beam : this.beams) {
            if (beam.getRepHigher().isFrontierSup() && beam.getRepLower().isFrontierInf())
                return false;
        }
        return true;
    }

    /**
     * Tester.
     */
    private void test() {
        for(int i = 1; i < this.beams.length + 1; i++){
            System.out.printf("Info de %d\n", i);
            System.out.println(this.beams[i].isFrontierSup() + " " + this.beams[i].isFrontierInf());
            System.out.println(this.beams[i].getX() + " " + this.beams[i].getY());
            System.out.println(this.beams[i].getRepHigher().getX() + " " + this.beams[i].getRepHigher().getY());
            System.out.println(this.beams[i].getRepLower().getX() + " " + this.beams[i].getRepLower().getY());
            System.out.println();
        }
    }
}
