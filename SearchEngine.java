import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

// import javax.sql.rowset.spi.SyncResolver;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

    String currentList = "";
    public String handleRequest(URI url) {

        // if(url.getPath().contains("/add")) {
        //     String[] parameters = url.getQuery().split("=");
        //     if (parameters[0].equals("s")) {
        //         currentList += parameters[1];
        //         return String.format("Added string! Current list is: ") + currentList;
        //     }
        // }

        String current = "";
        ArrayList <String> fullList = new ArrayList<String>();

        if(url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                fullList.add(parameters[1]);
                current += parameters[1];
                return "Added "+ current + " to the list of strings.\n";
            }
        } 
        // need to compare the inputted string to the arraylist of strings, 
        // and if there is a match add that string to new list and print out list at end
        if (url.getPath().contains("/search")) {
            String[] temp = url.getQuery().split("=");
            return "no";
            
        }
         return currentList;
        }
    }
// }

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
