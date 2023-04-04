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
        for (int i = 0; i < this.body.size()-1; ++i){
            for (int j = 0; j< this.body.size()-i-1;++j){
                if (this.body.get(j+1).doubleValue() < this.body.get(j).doubleValue()){
                    T swap = this.body.get(j);
                    this.body.set(j, this.body.get(j+1));
                    this.body.set(j+1,swap);
            }

            }
        }
    }
    @Override
    public void insertionSort() {
        for (int i = 1; i <this.body.size();++i){
            T key = this.body.get(i);
            int j = i-1;
            while (j >= 0 && this.body.get(j).doubleValue() > key.doubleValue()){
                T swap = this.body.get(j+1);
                this.body.set(j+1,this.body.get(j));
                this.body.set(j,swap);
                j = j-1;
            }
            this.body.set(j+1,key);
        }
    }
    @Override
    public void selectionSort() {
        int sz = this.body.size();
        for (int i =0; i < sz-1; i++){
            int min_i = i;
            for (int j = i+1; j < sz; j++){
                if (this.body.get((j)).doubleValue() < this.body.get(min_i).doubleValue()){
                    min_i = j;
                }
            }
            T swap = this.body.get(min_i);
            this.body.set(min_i,this.body.get(i));
            this.body.set(i,swap);

        }
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
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("Collection: {");
        for (int i = 0; i < this.body.size(); i++) {
            out.append(String.format("%s ",this.body.get(i).toString()));
        }
        out.append("}");
        return out.toString();
    }
}