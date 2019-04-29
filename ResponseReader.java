import java.io.*;
import java.util.*;

/**
 * Read the Commands. 
 * 
 * @author Akash Darji
 * @version Apr 29, 2019
 */
public class ResponseReader
{
    // Default responses
    private static final String FILE_OF_DEFAULT_RESPONSES = "default.txt";
    // Responses with associations.
    private static final String FILE_OF_MAIN_RESPONSES = "responses.txt";

  
    public ResponseReader()
    {
    }// end of public ResponseReader
     
    /**
      * @return The list of responses.
     */
    public List<String> readDefaultResponses()
    {
        String filename = FILE_OF_DEFAULT_RESPONSES;
        List<String> defaultResponses = new ArrayList<>();
        
        try {
            BufferedReader reader = 
                new BufferedReader(new FileReader(filename));
            String response = reader.readLine();
            while(response != null) {
                defaultResponses.add(response);
                response = reader.readLine();
            }// end of while
            reader.close();
        }// end of try
        catch(FileNotFoundException e) {
            System.err.println("Unable to open " + filename);
        }// end of catch
        catch(IOException e) {
            System.err.println("A problem was encountered reading " +
                               filename);
        }// end of catch
        // Make sure we have at least one response.
        if(defaultResponses.size() == 0) {
            defaultResponses.add("Could you elaborate on that?");
        }// end of if
        return defaultResponses;
    }// end of public list

    /**   
     * @return A map of key/response pairs.
     */
    public Map<String, String> readMainResponses()
    {
        String filename = FILE_OF_MAIN_RESPONSES;
        HashMap<String, String> map = new HashMap<>();
        try {
            BufferedReader reader =
                new BufferedReader(new FileReader(filename));
            String word;
            word = reader.readLine();
            while(word != null) {
                String response = reader.readLine();
                if(response != null) {
                    response = response.trim();
                    if(response.length() != 0) {
                        map.put(word, response);
                    }// end of if
                    else {
                        System.out.println("Blank response for " +
                                           word + " in file " +
                                           filename);
                    }// end of else
                }// end of if
                else {
                    System.out.println("Missing response for " +
                                       word + " in file " +
                                       filename);
                }// end of else
                word = reader.readLine();
            }// end of while
            reader.close();
        }// end of try
        catch(IOException e) {
            System.out.println("Problem reading file: " + filename +
                               " in readMap");
        }// end of catch
        return map;
    }// end of public Map

    /**
   
     * @param map The map to be written.
     * @param filename The file to write.
     */
    public void writeMap(HashMap<String, String> map, String filename)
    {
        if(map != null) {
            try {
                FileWriter writer = new FileWriter(filename);
                for(String key : map.keySet()) {
                    String value = map.get(key);
                    if(value != null) {
                        writer.write(key.trim());
                        writer.write('\n');
                        writer.write(value.trim());
                        writer.write('\n');
                    }// end of if
                    else {
                        System.out.println("Null response for " +
                                           key + " in writeFile.");
                    }// end of else
                }// end of for                
                writer.close();
            }// end of try
            catch(IOException e) {
                System.out.println("Problem writing file: " + filename +
                                   " in writeList");
            }// end of catch
        }
        else {
            System.out.println("Null map passed to writeList.");
        }// end of else
    }// end of writeMap

}
