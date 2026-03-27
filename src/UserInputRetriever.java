public interface UserInputRetriever<T>{

    public T produceOutput(int selection) throws IllegalArgumentException;
}