package polsl.pl;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class DatabaseHandler {
    Jedis jedis;

    public DatabaseHandler() {
        this.jedis = new Jedis("localhost", 6379);
    }

    public void insertIntoRedis(String key, String value) {
        jedis.set(key, value);
    }

    public void closeDatabaseConnection() {
        jedis.close();
    }

    public void deleteKeyValues(String key){
        jedis.del(key);
    }
}
