package model;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PruebaATextoAPI {
    /*public static void main(String[] args) {
        System.out.println(infoAPI("Galapagar"));

        String lista = infoAPI("Galapagar");

        System.out.println("\nLista de parametros:");
        //El parametro parametro, quiero que este escrito en ingeles para evitar problemas
        /*
        Las opciones son:
        cityName
        temperature
        feelsLike
        tempMin
        tempMax
        humidity
        description

        si no se pone ninguna de estas dara error*//*
        System.out.println(devolverParametro(lista,"cityname"));
        System.out.println(devolverParametro(lista,"temperature"));
        System.out.println(devolverParametro(lista,"feels_like"));
        System.out.println(devolverParametro(lista,"tempmin"));
        System.out.println(devolverParametro(lista,"tempmax"));
        System.out.println(devolverParametro(lista,"humidity"));
        System.out.println(devolverParametro(lista,"description"));

        System.out.println(sacarlogo("Galapagar"));
    }*/

    public PruebaATextoAPI() {
    }

    public  PruebaATextoAPI(String ciudad) {
            String lista = infoAPI(ciudad);
    }

        public String infoAPI (String ciudad){
            model.WeatherService weatherService = new model.WeatherService();
            String cityName = ciudad;
            String weatherData = weatherService.getWeatherData(cityName);

            if (weatherData != null) {
                // Imprimir la información de manera estructurada
                return formatWeatherData(weatherData);
            } else {
                return "Error al obtener los datos meteorológicos.";
            }
        }

        public String formatWeatherData (String weatherData){
            JSONObject json = new JSONObject(weatherData);

            // Extraer información relevante del JSON
            String cityName = json.getString("name");
            JSONObject main = json.getJSONObject("main");
            double temperature = main.getDouble("temp");
            double feelsLike = main.getDouble("feels_like");
            double tempMin = main.getDouble("temp_min");
            double tempMax = main.getDouble("temp_max");
            double humidity = main.getDouble("humidity");

            JSONArray weatherArray = json.getJSONArray("weather");
            String description = weatherArray.getJSONObject(0).getString("description");

            // Formatear la información
            return String.format("Ciudad: %s\nTemperatura: %.2f°C\nSensación térmica: %.2f°C\nTemperatura Mínima: %.2f°C\nTemperatura Máxima: %.2f°C\nHumedad: %.2f%%\nDescripción: %s",
                    cityName, temperature, feelsLike, tempMin, tempMax, humidity, description);
        }


        public String devolverParametro (String mensaje, String parametro){
    /* Lo primero que hacemos es usar patrones para cada tipo de info que se quiere extraer,
    en este caso siempre sigue el mismo patrón que es un parámetro, seguido de info. */

            Pattern ciudadPattern = Pattern.compile("Ciudad: (.+)");
            Pattern temperaturaPattern = Pattern.compile("Temperatura: (.+)");
            Pattern sensacionTermicaPattern = Pattern.compile("Sensación térmica: (.+)");
            Pattern tempMinimaPattern = Pattern.compile("Temperatura Mínima: (.+)");
            Pattern tempMaximaPattern = Pattern.compile("Temperatura Máxima: (.+)");
            Pattern humedadPattern = Pattern.compile("Humedad: (.+)");
            Pattern descripcionPattern = Pattern.compile("Descripción: (.+)");

            // Recogemos el texto de cada uno de los patrones con matcher.
            Matcher ciudadMatcher = ciudadPattern.matcher(mensaje);
            Matcher temperaturaMatcher = temperaturaPattern.matcher(mensaje);
            Matcher sensacionTermicaMatcher = sensacionTermicaPattern.matcher(mensaje);
            Matcher tempMinimaMatcher = tempMinimaPattern.matcher(mensaje);
            Matcher tempMaximaMatcher = tempMaximaPattern.matcher(mensaje);
            Matcher humedadMatcher = humedadPattern.matcher(mensaje);
            Matcher descripcionMatcher = descripcionPattern.matcher(mensaje);

    /*
    Digamos que el pattern lo que hace es siempre coger un tipo de patrón que se repite en el mensaje,
    y el matcher recoge de este mensaje que se ha pasado por el patrón el texto restante que no corresponde con el patrón.
    */

            parametro = parametro.trim().toLowerCase();

            if ("cityname".equals(parametro)) {
                if (ciudadMatcher.find()) {
                    return ciudadMatcher.group(1);
                } else {
                    return "ERROR";
                }
            } else if ("temperature".equals(parametro)) {
                if (temperaturaMatcher.find()) {
                    return temperaturaMatcher.group(1);
                } else {
                    return "ERROR";
                }
            } else if ("feels_like".equals(parametro)) {
                if (sensacionTermicaMatcher.find()) {
                    return sensacionTermicaMatcher.group(1);
                } else {
                    return "ERROR";
                }
            } else if ("tempmin".equals(parametro)) {
                if (tempMinimaMatcher.find()) {
                    return tempMinimaMatcher.group(1);
                } else {
                    return "ERROR";
                }
            } else if ("tempmax".equals(parametro)) {
                if (tempMaximaMatcher.find()) {
                    return tempMaximaMatcher.group(1);
                } else {
                    return "ERROR";
                }
            } else if ("humidity".equals(parametro)) {
                if (humedadMatcher.find()) {
                    return humedadMatcher.group(1);
                } else {
                    return "ERROR";
                }
            } else if ("description".equals(parametro)) {
                if (descripcionMatcher.find()) {
                    return descripcionMatcher.group(1);
                } else {
                    return "ERROR";
                }
            } else {
                return "ERROR Final";
            }
        }


        public String sacarlogo (String ciudad){
            String inf = infoAPI(ciudad);
            String estado = devolverParametro(inf, "description");
            estado = estado.trim().toLowerCase();

            if (estado.equals("thunderstorm with light rain") || estado.equals("thunderstorm with rain") || estado.equals("thunderstorm with heavy rain") || estado.equals("light thunderstorm") || estado.equals("thunderstorm") || estado.equals("heavy thunderstorm") || estado.equals("ragged thunderstorm") || estado.equals("thunderstorm with light drizzle") || estado.equals("thunderstorm with drizzle") || estado.equals("thunderstorm with heavy drizzle")) {
                return "TormentaDia.png";
            } else if (estado.equals("light intensity drizzle") || estado.equals("drizzle") || estado.equals("heavy intensity drizzle") || estado.equals("light intensity drizzle rain") || estado.equals("drizzle rain") || estado.equals("heavy intensity drizzle rain") || estado.equals("shower rain and drizzle") || estado.equals("heavy shower rain and drizzle") || estado.equals("shower drizzle")) {
                return "LluviaDia.png";
            } else if (estado.equals("light rain") || estado.equals("moderate rain") || estado.equals("heavy intensity rain") || estado.equals("very heavy rain") || estado.equals("extreme rain") || estado.equals("light intensity shower rain") || estado.equals("shower rain") || estado.equals("heavy intensity shower rain") || estado.equals("ragged shower rain")) {
                return "LluviaSolDia.png";
            } else if (estado.equals("freezing rain") || estado.equals("light snow") || estado.equals("snow") || estado.equals("heavy snow") || estado.equals("sleet") || estado.equals("light shower sleet") || estado.equals("shower sleet") || estado.equals("light rain and snow") || estado.equals("rain and snow") || estado.equals("light shower snow") || estado.equals("shower snow") || estado.equals("heavy shower snow")) {
                return "NieveDia.png";
            } else if (estado.equals("mist") || estado.equals("smoke") || estado.equals("haze") || estado.equals("sand/dust whirls") || estado.equals("sand") || estado.equals("dust whirls") || estado.equals("fog") || estado.equals("sand") || estado.equals("dust") || estado.equals("volcanic ash") || estado.equals("squalls") || estado.equals("tornado")) {
                return "NeblinaDia.png";
            } else if (estado.equals("clear sky")) {
                return "DespejadoDia.png";
            } else if (estado.equals("few clouds")) {
                return "AlgunasNubesDia.png";
            } else if (estado.equals("scattered clouds")) {
                return "NubesDispersasDia.png";
            } else if (estado.equals("broken clouds") || estado.equals("overcast clouds")) {
                return "NubesRotasDia.png";
            } else {
                return "Error imagen";
            }
        }


    }

