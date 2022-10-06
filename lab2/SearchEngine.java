import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    List<String> searcher = new ArrayList<String>();
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
		String listString3 = String.join(", ", searchedList);
            return String.format("List:"+ listString3);
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
             
                    searcher.add(parameters[1]);
			String listString = String.join(", ", searcher);
                    return String.format("List: "+ listString);
          
            }
		if (url.getPath().contains("/search")) {
		List<String> searchedList = new ArrayList<String>();
                String[] parameters = url.getQuery().split("=");
		Iterator<String> itr = searcher.iterator();
		while (itr.hasNext()){
			String s = itr.next();
                	if (s.contains(parameters[1])) {
                   	 searchedList.add(s);
                   	 
                	} //if
		}//for

		String listString2 = String.join(", ", searchedList);
		return String.format("Returned Values:"+ listString2);
            
	}//if
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}