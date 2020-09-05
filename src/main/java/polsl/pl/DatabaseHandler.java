package polsl.pl;

import redis.clients.jedis.Jedis;

public class DatabaseHandler {
    public static void openDatabase(){
        Jedis jedis = new Jedis("localhost",6379);
        System.out.println("Server is running: "+jedis.ping());
    }

}
