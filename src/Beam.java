public class Beam {

    //The x,y coordinates of the laser/beam
    private final int x,y;

    //The higher and lower representative of the node (laser/beam)
    private Beam repLower, repHigher;

    //Frontier variables
    private boolean frontierSup;
    private boolean frontierInf;

    /**
     * Constructor of the node that represents the beam/laser
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width (height) of the map
     * @param diameter the diameter of the robot
     */
    public Beam(int x, int y, int width, int diameter) {
        this.x = x;
        this.y = y;
        this.repLower = this;
        this.repHigher = this;
        this.frontierSup = false;
        this.frontierInf = false;
        //Checks if the beam is a frontier beam
        checkFrontier(width, diameter);
    }

    /**
     * Adds the lower representative
     * @param rep Node, to be added
     */
    public void addRepLower(Beam rep) {
        this.repLower = rep;
    }

    /**
     * Adds the higher representative
     *  @param rep Node, to be added
     */
    public void addRepHigher(Beam rep) {
        this.repHigher = rep;
    }

    /**
     * @return Node, the highest representative of the partition
     */
    public Beam getRepHigher() {
        return this.repHigher;
    }

    /**
     * @return Node, the lowest representative of the partition
     */
    public Beam getRepLower() {
        return this.repLower;
    }

    /**
     * Checks if the node is a frontier node (distance from laser to wall is inferior
     * to the robots diameter).
     * @param width the width of the map
     * @param diameter the diameter of the robot
     */
    public void checkFrontier(int width, int diameter) {
        if(width - this.y < diameter) {
            this.frontierSup = true;
        }
        if(this.y < diameter) {
            this.frontierInf = true;
        }
    }

    /**
     * @return true if the node is a superior frontier, false otherwise
     */
    public boolean isFrontierSup() {
        return this.frontierSup;
    }

    /**
     * @return true if the node is an inferior frontier, false otherwise
     */
    public boolean isFrontierInf() {
        return this.frontierInf;
    }

    /**
     * @return x coordinate of beam
     */
    public int getX(){
        return this.x;
    }

    /**
     * @return y coordinate of beam
     */
    public int getY() {
        return this.y;
    }
}
