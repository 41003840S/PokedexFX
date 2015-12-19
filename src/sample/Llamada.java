package sample;



import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Llamada {

    private static String jsonPoke;
    public static final int MAXPOKE = 15;

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
    public static void downloadInfoListView(){

        for (int i = 1; i < MAXPOKE; i++) {
            //Pasamos a String la id del pokemon para anadirla a la peticion
            String pokeID = String.valueOf(i);

            //Generamos la peticion con la id del pokemon
            String peticioPoke = "http://pokeapi.co/api/v1/pokemon/" + pokeID;

            try {

                //Metemos en una variable String el JSON que nos da el metodo getHTML
                jsonPoke = getHTML(peticioPoke);

                //Creamos un objeto generico que es lo que devuelve el JSON
                Object obj = JSONValue.parse(jsonPoke);

                //Lo pasamos a un objeto simple con la libreria JSONSimple
                JSONObject objJSimple = (JSONObject) obj;

                long idPoke = (Long) (objJSimple.get("national_id"));
                String nombre = (String) objJSimple.get("name");

                Controller.items.add(new Pokemon((int)idPoke, nombre));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static Pokemon downloadInfoDetails(int id){
             Pokemon poke1 = null;
            //Pasamos a String la id del pokemon para anadirla a la peticion
            String pokeID = String.valueOf(id);

            //Generamos la peticion con la id del pokemon
            String peticioPoke = "http://pokeapi.co/api/v1/pokemon/" + pokeID;

            try {

                //Metemos en una variable String el JSON que nos da el metodo getHTML
                jsonPoke = getHTML(peticioPoke);

                //Creamos un objeto generico que es lo que devuelve el JSON
                Object obj = JSONValue.parse(jsonPoke);

                //Lo pasamos a un objeto simple con la libreria JSONSimple
                JSONObject objJSimple = (JSONObject) obj;

                long hp = (Long) objJSimple.get("hp");
                long idPoke = (Long) objJSimple.get("national_id");
                String nombre = (String) objJSimple.get("name");
                long ataque = (Long) objJSimple.get("sp_atk");
                long defensa = (Long) (objJSimple.get("sp_def"));
                String peso = (String) (objJSimple.get("weight"));


                poke1 = new Pokemon((int)idPoke, nombre, (int)hp, (int)ataque,(int)defensa, peso);


            } catch (Exception e) {
                e.printStackTrace();
            }
        return poke1;
    }

}
