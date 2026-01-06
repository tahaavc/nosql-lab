package app.store;

import app.model.Student;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

public class RedisStore {
    private static Jedis jedis;
    private static Gson gson = new Gson();

    public static void init() {
        jedis = new Jedis("localhost", 6379);
        for (int i = 1; i <= 10000; i++) {
            String id = String.format("2025%06d", i); // 2025000001 formatı
            Student s = new Student(id, "Student " + i, "Computer Engineering");
            jedis.set(s.getStudent_no(), gson.toJson(s));
        }
        System.out.println("Redis: 10.000 kayıt hazır.");
    }

    public static Student get(String studentNo) {
        String json = jedis.get(studentNo);
        return (json == null) ? null : gson.fromJson(json, Student.class);
    }
}