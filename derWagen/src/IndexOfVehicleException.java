import java.io.IOException;

public class IndexOfVehicleException extends IOException {
    @Override
    public String getMessage() {
        return "Input a valid number of vehicle!";
    }
}
