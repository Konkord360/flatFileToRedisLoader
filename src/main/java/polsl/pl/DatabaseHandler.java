package polsl.pl;

import redis.clients.jedis.Jedis;

public class DatabaseHandler {
    final Jedis jedis;

    public DatabaseHandler() {
        this.jedis = new Jedis("localhost", 6379);
    }

    public void insertIntoRedis(String key, String value) {
        jedis.set(key, value);
    }

    public void closeDatabaseConnection() {
        jedis.close();
    }

    public String getKeyValue(String key){
        return jedis.get(key);
    }

    public void deleteKeyValues(String key){
        jedis.del(key);
    }

    public void clearDatabase(){
        jedis.flushAll();
    }

}
