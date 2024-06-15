import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;                       
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.io.*;
// import org.json.JSONArray;
// import org.json.JSONObject;
import org.json.*;


public class cineview {
    public static void main(String[] args) throws Exception {

                System.out.println("DISCOVER THE WORLD OF CINEMA!!");
                Scanner inp=new Scanner(System.in);
                System.out.println("Enter the movie you want the details!!");
                String name;
                name=inp.nextLine();

                System.out.println("Enter your specificity: ");
                System.out.println("fulldetails "+" genre "+" adult? "+" story "+" language "+" popularity "+" trailer "+" rating "+" cast ");
                System.out.println("-----------------------------------------------------------------------------");
                String specificity;
                specificity=inp.next();

                if(specificity.equals("fulldetails")){
                    fetch_full_detatils(name);
                }
                else{
                    if(specificity.equals("genre")){
                        System.out.println("The Genres of the Movie: "+fetch_genre(name));
                    }
                    else if(specificity.equals("adult?")){
                        System.out.println("Is it adult rated?: "+fetch_adult(name));
                    }
                    else if(specificity.equals("story")){
                        System.out.println("The STORY: "+fetch_overview(name));
                    }
                    else if(specificity.equals("language")){
                        System.out.println("Original Language of the Movie: "+fetch_original_language(name));
                    }
                    else if(specificity.equals("popularity")){
                        System.out.println("The Popularity of the movie: "+fetch_popularity(name));
                    }
                    else if(specificity.equals("trailer")){
                        System.out.println("Got You the Trailer watch it here (Click it): "+fetch_trailer_link(name));
                    }
                    else if(specificity.equals("rating")){
                        System.out.println("Rating of the Movie by the audience: "+fetch_vote_average(name));
                    }
                    else if(specificity.equals("cast")){
                        fetch_casting(name);
                    }
                }


            }
                
   
        public static void fetch_full_detatils(String name) throws Exception {

            System.out.println("Here You Go, Explore the World of Cinema");
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Is it adult rated?: "+fetch_adult(name));
            System.out.println("---------------------");
            System.out.println("---------------------");
            System.out.println("The STORY: "+fetch_overview(name));
            System.out.println("---------------------");
            System.out.println("---------------------");
            System.out.println("Release date of "+name+" is: "+fetch_release_date(name));
            System.out.println("---------------------");
            System.out.println("---------------------");
            System.out.println(fetch_genre(name));
            System.out.println("---------------------");
            System.out.println("---------------------");
            System.out.println("Original Language of the Movie: "+fetch_original_language(name));
            System.out.println("---------------------");
            System.out.println("---------------------");
            System.out.println("Rating of the Movie by the audience: "+fetch_vote_average(name));
            System.out.println("---------------------");
            System.out.println("---------------------");
            System.out.println("The Popularity of the movie: "+fetch_popularity(name));
            System.out.println("---------------------");
            System.out.println("---------------------");
            System.out.println("The CASTING YOU WILL BE FAN FOR!!!");
            System.out.println("---------------------");
            fetch_casting(name);
            System.out.println("---------------------");
            System.out.println("Got You the Trailer watch it here (Click it): "+fetch_trailer_link(name));
            System.out.println("---------------------");
            System.out.println("---------------------");
            System.out.println("Binge Watch it YAA");
            System.out.println("Thats it! Enjoy the Movie now and Binge Watch");
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------");
        }
        public static void fetch_casting(String name)throws Exception  {
            int id = fetch_id(name);
            String apiKey = "b06f5ab31c81a66c57e1ab13303f0cc8";
            if(id==404){
                System.out.println("Not found cast");
            }
            else{
                String apiUrl = "https://api.themoviedb.org/3/movie/" + id + "/credits?api_key=" + apiKey;
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                JSONObject json_obj = new JSONObject(response.toString());
                JSONArray dataArray = json_obj.getJSONArray("cast");
                
                for(int i=0;i<dataArray.length();i++){
                    JSONObject cast = dataArray.getJSONObject(i);
                    String cast_character_name=cast.getString("character");
                
                    String cast_original_name=cast.getString("original_name");
                    System.out.println(cast_original_name+" playing as "+"("+cast_character_name+")");
                    System.out.println("------");

                    
                    
                }

            }
            
        }

    public static String fetch_genre(String name) throws Exception {
        int id = fetch_id(name);
        String apiKey = "b06f5ab31c81a66c57e1ab13303f0cc8";
        String apiUrl = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        JSONObject json_obj = new JSONObject(response.toString());
        JSONArray dataArray = json_obj.getJSONArray("genres");
        try{
            JSONObject json_genre1 = dataArray.getJSONObject(0);
            JSONObject json_genre2 = dataArray.getJSONObject(1);
            String genre1 = json_genre1.getString("name");
            String genre2 = json_genre2.getString("name");
            return "Genres are :" + genre1 + " , " + genre2;

        }
        catch(Exception e){
            return "No genres for this movie!";
        }
        

        

    }

    public static String fetch_trailer_link(String name) throws Exception {
        int id = fetch_id(name);
        
        String apiKey = "b06f5ab31c81a66c57e1ab13303f0cc8";
        if(id==404){
            return "No trailer link found";
        }
        else{
                String apiUrl = "https://api.themoviedb.org/3/movie/" + id + "/videos" + "?api_key=" + apiKey;
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                
                JSONObject json_obj = new JSONObject(response.toString());
            
                JSONArray dataArray = json_obj.getJSONArray("results");
                String link="";
                for(int i=0;i<dataArray.length();i++){
                    
                    JSONObject result = dataArray.getJSONObject(i);
                    if((result.getString("type")).equals("Trailer")){
                        link += result.getString("key");
                        break;
                    }
                }
                if(link.equals("")){
                    return "Not available in Data base";
                }
                return "https://www.youtube.com/watch?v=" + link; 

        }
        
    }

    public static String fetch_popularity(String name) throws Exception {

        String apiKey = "b06f5ab31c81a66c57e1ab13303f0cc8";
        String res=name.replace(" ","%20");
        String apiUrl = "https://api.themoviedb.org/3/search/movie?query="+res+"&api_key="+apiKey;        
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        JSONObject json_obj = new JSONObject(response.toString());
        JSONArray dataArray = json_obj.getJSONArray("results");

       try{
            JSONObject  result = dataArray.getJSONObject(0);
            double popularity = result.getDouble("popularity");
            return String.valueOf(popularity);


       }
        catch(Exception e){
            System.out.println("Requested specifity not Found!");
            return "Not Rated till now!";
        }
        
    }

    public static String fetch_vote_average(String name) throws Exception {
        String apiKey = "b06f5ab31c81a66c57e1ab13303f0cc8";
        String res=name.replace(" ","%20");
        String apiUrl = "https://api.themoviedb.org/3/search/movie?query="+res+"&api_key="+apiKey;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        JSONObject json_obj = new JSONObject(response.toString());
        JSONArray dataArray = json_obj.getJSONArray("results");
         
     
        try{
            JSONObject result = dataArray.getJSONObject(0);
            double vote = result.getDouble("vote_average");
            return vote * 10 + "%";
        }
        catch(Exception e){
            System.out.println("Requested specifity not Found!");
            return "error%";
        }
        
        

        
        
        

    }

    public static String fetch_original_language(String name) throws Exception {
        String apiKey = "b06f5ab31c81a66c57e1ab13303f0cc8";
        String res=name.replace(" ","%20");
        String apiUrl = "https://api.themoviedb.org/3/search/movie?query="+res+"&api_key="+apiKey;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        JSONObject json_obj = new JSONObject(response.toString());
        JSONArray dataArray = json_obj.getJSONArray("results");
        try{
            JSONObject result = dataArray.getJSONObject(0);
            String language = result.getString("original_language");
            return language;
        }
        catch(Exception e){
            System.out.println("Requested specifity not Found!");
            return "error";
        }
        
        
        
        
        

    }

    public static int fetch_id(String name) throws Exception {
        String apiKey = "b06f5ab31c81a66c57e1ab13303f0cc8";
        String res=name.replace(" ","%20");
        String apiUrl = "https://api.themoviedb.org/3/search/movie?query="+res+"&api_key="+apiKey;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        JSONObject json_obj = new JSONObject(response.toString());
        JSONArray dataArray = json_obj.getJSONArray("results");
        try{
            JSONObject result = dataArray.getJSONObject(0);
            int id = result.getInt("id");
            return (id);
        }
        catch(Exception e){
            System.out.println("Requested id not Found!");
            return 404;
        }
        //JSONObject result = dataArray.getJSONObject(0);
        
        
    }

    public static String fetch_release_date(String name) throws Exception {
        String apiKey = "b06f5ab31c81a66c57e1ab13303f0cc8";
        String res=name.replace(" ","%20");
        String apiUrl = "https://api.themoviedb.org/3/search/movie?query="+res+"&api_key="+apiKey;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        JSONObject json_obj = new JSONObject(response.toString());
        JSONArray dataArray = json_obj.getJSONArray("results");
        try{
            JSONObject result = dataArray.getJSONObject(0);
            String release_date = result.getString("release_date");
            return release_date;
        }
         catch(Exception e){
            System.out.println("Requested specifity not Found!");
            return "error";
        }
        
        

        
        
    }

    public static String fetch_overview(String name) throws Exception {
        String apiKey = "b06f5ab31c81a66c57e1ab13303f0cc8";
        String res=name.replace(" ","%20");
        String apiUrl = "https://api.themoviedb.org/3/search/movie?query="+res+"&api_key="+apiKey;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        JSONObject json_obj = new JSONObject(response.toString());
        JSONArray dataArray = json_obj.getJSONArray("results");
        try{
            JSONObject result = dataArray.getJSONObject(0);
            String overview = result.getString("overview");
            return overview;

        }
        catch(Exception e){
            System.out.println("Requested specifity not Found!");
            return "error";
        }
        
        
        
    }
    public static String fetch_adult(String name) throws Exception {
        String apiKey = "b06f5ab31c81a66c57e1ab13303f0cc8";
       String res=name.replace(" ","%20");
        String apiUrl = "https://api.themoviedb.org/3/search/movie?query="+res+"&api_key="+apiKey;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        JSONObject json_obj = new JSONObject(response.toString());
        JSONArray dataArray = json_obj.getJSONArray("results");
        try{
            JSONObject result = dataArray.getJSONObject(0);
            boolean adult = result.getBoolean("adult");
            return String.valueOf(adult);
        }
        catch(Exception e){
            System.out.println("Requested specifity not Found!");
            return "error";
        }
        
        
        
    }
}
