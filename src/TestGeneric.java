public class TestGeneric <T>{
    private T obj;
    public TestGeneric(T оbj) {
        this.obj = оbj;
    }
    public T getObj() {
        return obj;
    }
    public void showType() {
        System.out.println("Тип T: " + obj.getClass().getName());
    }

}
