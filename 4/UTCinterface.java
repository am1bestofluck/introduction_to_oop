public interface UTCinterface<T>// котиков не перемножаем... 
{
    public void add (T obj);

    public void unlink (Integer index);

    public void purge (T unit);

    public T min();

    public T max();

    public T sum();

    public T mult();

    public boolean exists( T unit);

    public void bubbleSort();

    public void insertionSort();

    public void selectionSort();

    public void get( Integer index);

    public void edit (Integer index, T unit);

    public Integer size();

    public void getSet(Integer index);

    public void getSet( Integer index, T obj);
}