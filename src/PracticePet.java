public class PracticePet {
    private int health = 100;
    private String name;
    private String type;
    private boolean alive;

    public PracticePet(String name, String type, boolean alive) {
        this.name = name;
        this.type = type;
        this.alive = alive;
    }

    public boolean getAlive() {
        return alive;
    }
}
