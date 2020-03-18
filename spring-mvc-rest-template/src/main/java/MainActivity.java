import com.java.controller.RestCalling;

// This class will be responsible for creating calling the rest api
public class MainActivity
{
    public static void main(String args[]) {
        RestCalling restCalling = new RestCalling();
        restCalling.callMethods();
    }
}
