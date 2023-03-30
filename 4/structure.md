<pre>interface UnTypedCollection\<T\>{

    public UnTypedCollection();

    public UnTypedCollection(T[]);

    public add(T obj);

    public unlink (Integer index);

    public purge (T obj);

    public T getMin();

    public T getMax();

    public T getSum();// если не числовое то "уходи"

    public String getMult(); // если не числовое то "уходи"

    public boolean exists( T obj);

    public void bubbleSort();

    public void insertionSort();

    public void PickSort();

    public void get(Integer index);

    public edit( Integer index, T newObj);

    public Integer size();

    public void getSet(Integer index);

    public void getSet(Ineger index, T obj)


}
</pre>