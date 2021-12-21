public class AirfieldNotFoundException extends RuntimeException {
    public AirfieldNotFoundException(int i){
        super("По данному месту самолет не найден: " + i);
    }
}