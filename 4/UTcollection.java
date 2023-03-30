import java.util.ArrayList;

public class UTcollection <T> 
implements UTCinterface<T>
 {
    ArrayList<T> body;
    // T[] body;
    // вот и поговорили: https://stackoverflow.com/questions/2927391/whats-the-reason-i-cant-create-generic-array-types-in-java#:~:text=It%20is%20because%20generics%20were,in%20the%20making%20of%20it.

    public UTcollection() {
        this.body = new ArrayList<T>();
    }
    public UTcollection(ArrayList<T> prequel) {
        this.body = prequel;
    }
    @Override
    public void add(T obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
    @Override
    public void unlink(Integer index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unlink'");
    }
    @Override
    public void purge(T unit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'purge'");
    }
    @Override
    public T min() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'min'");
    }
    @Override
    public T max() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'max'");
    }
    @Override
    public T sum() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sum'");
    }
    @Override
    public T mult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mult'");
    }
    @Override
    public boolean exists(T unit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exists'");
    }
    @Override
    public void bubbleSort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bubbleSort'");
    }
    @Override
    public void insertionSort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertionSort'");
    }
    @Override
    public void selectionSort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectionSort'");
    }
    @Override
    public void get(Integer index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
    @Override
    public void edit(Integer index, T unit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'edit'");
    }
    @Override
    public Integer size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }
    @Override
    public void getSet(Integer index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSet'");
    }
    @Override
    public void getSet(Integer index, T obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSet'");
    }
}