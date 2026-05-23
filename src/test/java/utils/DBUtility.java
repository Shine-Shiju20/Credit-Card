package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {

    private static Connection connection;

    public static Connection getConnection(){

        try{

            ConfigReader config =
                    new ConfigReader();

            if(connection == null ||
                    connection.isClosed()){

                Class.forName(
                        "org.postgresql.Driver"
                );

                connection =
                        DriverManager.getConnection(

                                config.getProp(
                                        "dbUrl"
                                ),

                                config.getProp(
                                        "dbUser"
                                ),

                                config.getProp(
                                        "dbPassword"
                                )
                        );
                LoggerUtility.info(
                        "DB Connected Successfully");
            }

        }
        catch(Exception e){

            e.printStackTrace();
        }

        return connection;
    }
}