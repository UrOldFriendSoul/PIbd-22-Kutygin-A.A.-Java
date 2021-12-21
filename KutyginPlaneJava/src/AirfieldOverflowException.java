public class AirfieldOverflowException extends RuntimeException{
    public AirfieldOverflowException(){
        super("На аэродроме нет свободных мест");
    }
}
