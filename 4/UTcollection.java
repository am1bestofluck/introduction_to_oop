import java.util.ArrayList;

public class UTcollection <T extends Number & Comparable<? super T>> 
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
        this.body.add(obj);
    }
    @Override
    public void unlink(Integer index) {
            this.body.remove(index);
        }
    @Override
    public void purge(T unit) {
        while (this.body.contains(unit)){
            this.body.remove(unit);
        }
    }
    @Override
    public T min() {
        if (this.body.isEmpty()){
            return null;
        }
        T out = this.body.get(0);
        for (int i = 0; i < this.body.size(); i++) {
            if (this.body.get(i).longValue() < out.longValue()){
                out = this.body.get(i);
            }
        }
        return out;
        
    }
    @Override
    public T max() {
        if (this.body.isEmpty()){
            return null;
        }
        T out = this.body.get(0);
        for (int i = 0; i < this.body.size(); i++) {
            if (this.body.get(i).doubleValue() > out.doubleValue()){
                out = this.body.get(i);
            }
        }
        return out;
    }
    @Override
    public T sum() {
        if (this.body.isEmpty()){
            return null;
        }
        T out;
        Double temp = 0.0;
        for (int i = 0; i < this.body.size(); i++) {
                temp += this.body.get(i).doubleValue();
            }
        out = (T)temp;
        return out;
    }

    @Override
    public T mult() {
        if (this.body.isEmpty()){
            return null;
        }
        T out;
        Double temp = 1.0;
        for (int i = 0; i < this.body.size(); i++) {
                temp *= this.body.get(i).doubleValue();
            }
        out = (T)temp;
        return out;
    }
    @Override
    public boolean exists(T unit) {
        if (this.body.contains(unit)){
            return true;
        }
        return false;
    }
    @Override
    public void bubbleSort() {
        ArrayList<T> temp = new ArrayList<>(this.body.size());
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bubbleSort'");
    }
    @Override
    public void insertionSort() {
        ArrayList<T> temp = new ArrayList<>(this.body.size());
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertionSort'");
    }
    @Override
    public void selectionSort() {
        ArrayList<T> temp = new ArrayList<>(this.body.size());
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectionSort'");
    }
    @Override
    public T get(Integer index) {
        return this.body.get(index);
    }
    @Override
    public void edit(Integer index, T unit) {
        this.body.set(index, unit);
    }
    @Override
    public Integer size() {
        return this.body.size();
    }
    @Override
    public void getSet(Integer index) {
        this.get(index);
    }
    @Override
    public void getSet(Integer index, T unit) {
        this.edit(index, unit);
    }
}