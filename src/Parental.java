public class Parental extends State {
    public Parental(StateManager sg) {
        super(sg);
    }

    public void render() {
        System.out.println("\n\n\n\n----Parental Control Settings----");
        System.out.println("1) View Stats");
        System.out.println("2) Time Controls");
        System.out.println("3) Revive Pets");
        System.out.println("4) Exit");
    }

    public void update() {

    }


}
