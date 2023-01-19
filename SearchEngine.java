import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

// import javax.sql.rowset.spi.SyncResolver;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

    String currentList = "";
    public String handleRequest(URI url) {

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

        // FIX LATER
        ArrayList <String> tempList = new ArrayList<String>();
        if (url.getPath().contains("/search")) {
            String[] temp = url.getQuery().split("=");
            if (temp[0].equals("s")) {
                for (int i = 0; i < fullList.size(); i++) {
                    if (fullList.get(i).contains(temp[1])) {
                        tempList.add(fullList.get(i));
                    }
                }
            }
            String finalList = String.join(", ", tempList);
            return "Final list: " + finalList;
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
