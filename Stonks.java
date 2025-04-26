import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.*;
import java.net.URL;
public class Stonks {
    public static void main(String[] args) {
        Vector<Stonk> stonks = new Vector<Stonk>();
        stonks.add(new Stonk("S&P", "https://www.google.com/finance/quote/.INX:INDEXSP"));
        while(true){
            for(int i = 0; i < stonks.size(); i++){
                Stonk stonk = stonks.get(i);
                stonk.RunStonk();
            }
        }
    }
}
class Stonk{
    String name;
    String urlhttp;

    Stonk(String name, String urlhttp){
        this.name = name;
        this.urlhttp = urlhttp;
    }

    void RunStonk(){
        try {
            // Specify the URL
            URL urlObject = new URL(urlhttp); // Replace with your desired URL
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            // Set request method
            connection.setRequestMethod("GET");
            // Read response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
    
                // Print the response
                String responseString = response.toString();
                System.out.println(name + ": " + responseString);
        }catch (Exception e){
            // Handle exceptions
            System.out.println("Error: " + e.getMessage());
        }
    }

}