public class Cell {

    private int livingNeighbours = 0;
    private boolean alive = true;

    public int getLivingNeighbours() {
        return livingNeighbours;
    }

    public void setLivingNeighbours(int livingNeighbours) {
        this.livingNeighbours = livingNeighbours;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void Update(){
        if (livingNeighbours >= 2 || livingNeighbours <= 3){
            alive = true;
        }
        if (livingNeighbours == 3){
            alive = true;
        }
        else alive = false;
    }

}
