public class PracticePet {
<<<<<<< HEAD
    private int health;
    private String name;
    private String type;

    public PracticePet(int health, String name, String type) {
        this.health = health;
        this.name = name;
        this.type = type;
=======
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
>>>>>>> petClass
    }
}
