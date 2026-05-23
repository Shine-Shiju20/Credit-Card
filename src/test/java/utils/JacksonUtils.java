package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

    private static final ObjectMapper mapper =
            new ObjectMapper();

    public static String convertObjectToJson(
            Object object){

        try{

            return mapper.writeValueAsString(
                    object);

        }
        catch(Exception e){

            throw new RuntimeException(
                    "Object conversion failed : "
                            + e.getMessage());

        }

    }


    public static <T> T convertJsonToObject(
            String json,
            Class<T> clazz){

        try{

            return mapper.readValue(
                    json,
                    clazz);

        }
        catch(Exception e){

            throw new RuntimeException(
                    "JSON conversion failed : "
                            + e.getMessage());

        }

    }

}