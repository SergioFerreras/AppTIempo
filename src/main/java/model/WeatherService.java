package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Esta clase servira para manejar las solicitudes de nuestra API

public class WeatherService {

    //para que funcione la conexion a la API, tengo que registrarme y conseguir una URL y una KEY
    private static final String API_KEY = "d2826f9f1babbdc768f5a1567eac2214";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    /*Este metodo lo que hace es coger por parametro un String que corresponde con el nombre de la ciudad
    y lo que hace es una consulta a la API, la consulta en este caso es simple, esta API ofrece mas funcionalidades*/

    public String getWeatherData(String ciudad) {
        try {
            // Con esta url nos conectamos a un enlace que corresponde con nuestra URL y nuestra clave, esto es lo que hace que podamos recoger la info.
            URL urlAPI = new URL(BASE_URL + "?q=" + ciudad + "&appid=" + API_KEY);

            //Esto sirve para abrir la conexion con  nuestra API
            HttpURLConnection connection = (HttpURLConnection) urlAPI.openConnection();
            connection.setRequestMethod("GET");

            //Esto sirve para poder leer la respuesta de la API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            //leemos la informacion que nos pasa la API
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            
            reader.close();
            connection.disconnect();
            
            //a la hora de implementar la logica, si esto nos devuelve un null, hay que manejarlo
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
