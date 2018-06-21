public class Pair<T> {

    private T first;
    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    //возвращаем первый елемен
    public T first() {
        return first;
    }


    public T second() {
        return second;
    }
}