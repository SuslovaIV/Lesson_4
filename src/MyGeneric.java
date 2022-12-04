import java.util.Arrays;
import java.util.List;

public class MyGeneric {
    public static void main(String[] args) {
        System.out.println("--------1--------");
        replacer();
        System.out.println();
        System.out.println("--------2--------");
        converter();
        System.out.println("--------3--------");
        fruitStorage();
    }
    public static void replace(Object[]array,int i1,int i2){
        var element0 = array[i1];
        array[i1]=array[i2];
        array[i2]=element0;
    }
    public static void replacer(){

        TestGeneric int1 = new TestGeneric<>(100);
        TestGeneric int2 = new TestGeneric<>(150);
        TestGeneric str1 = new TestGeneric<>("a");
        TestGeneric str2 = new TestGeneric<>("b");
        TestGeneric str3 = new TestGeneric<>("c");

        TestGeneric[] array={int1,int2,str1,str2,str3};
        for (TestGeneric n : array) {
            System.out.print(n.getObj() + " ");
        }
        replace(array,1,2);
        System.out.println();
        for (TestGeneric n : array) {
            System.out.print(n.getObj() + " ");
        }
    }
    public static <T> List<T> convertArrayToArrayList(T array[]){
        return Arrays.asList(array);
    }
    public static void converter() {
        String[] array={"rr","84","eee","23"};
        var list= convertArrayToArrayList(array);
        System.out.println(list);
    }
    public static <F extends Fruits> void change(Box<F> boxFrom, Box<F> boxTo) {
        while (boxFrom.getSize() > 0) {
            boxTo.addFruit(boxFrom.getFruit());
        }
    }
    public static void fruitStorage(){
        Box<Apple> boxApp = new Box<>();
        boxApp.addFruit(new Apple());
        boxApp.addFruit(new Apple());
        System.out.println("Вес коробки яблок 1: "+boxApp.getWeight());

        Box<Apple> boxApp2 = new Box<>();
        boxApp2.addFruit(new Apple());
        boxApp2.addFruit(new Apple());
        boxApp2.addFruit(new Apple());
        System.out.println("Вес коробки яблок 2: "+boxApp2.getWeight());

        Box<Orange> boxOrange = new Box<>();
        boxOrange.addFruit(new Orange());
        boxOrange.addFruit(new Orange());
        System.out.println("Вес коробки апельсинов: "+boxOrange.getWeight());
        System.out.println("Вес корбоки яблок 1 идентичен весу коробки апельсинов:"+boxApp.compare(boxOrange));
        System.out.println("Вес корбоки яблок 2 идентичен весу коробки апельсинов:"+boxApp2.compare(boxOrange));
        System.out.println("Пересыпаем яблоки во коробку 2");
        change(boxApp, boxApp2);

        System.out.println("Вес коробки яблок 1: "+ boxApp.getWeight());
        System.out.println("Вес коробки яблок 2: "+boxApp2.getWeight());
    }
}
