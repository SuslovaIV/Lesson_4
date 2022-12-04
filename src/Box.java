import java.util.ArrayList;
import java.util.List;
public class Box <F extends Fruits> {
    private final List<F> fruits;
    public void addFruit(F fruit) {
        fruits.add(fruit);
    }
    public F getFruit() {
        return fruits.remove(fruits.size() - 1);
    }
    public int getSize() {
        return fruits.size();
    }
    public Box() {
        this.fruits = new ArrayList<>();
    }
    public float getWeight() {
        if (fruits.size() > 0) {
            return fruits.get(0).getWeight() * fruits.size();
        } else {
            return 0;
        }
    }
    public boolean compare(Box<? extends Fruits> box) {
        return this.getWeight() == box.getWeight();
    }
}