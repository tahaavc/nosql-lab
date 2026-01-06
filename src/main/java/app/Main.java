package app;

import static spark.Spark.*;
import com.google.gson.Gson;
import app.store.*;

public class Main {
    public static void main(String[] args) {
        ipAddress("0.0.0.0");
        port(8080);
        Gson gson = new Gson();

        // Veritabanlarını ilklendir
        RedisStore.init();
        HazelcastStore.init();
        MongoStore.init();

        // REDIS
        get("/nosql-lab-rd/:full_id", (req, res) -> {
            String param = req.params(":full_id"); // Örn: "student_no=2025000001"
            String id = param.contains("=") ? param.split("=")[1] : param;
            res.type("application/json");
            return gson.toJson(RedisStore.get(id));
        });

        // HAZELCAST
        get("/nosql-lab-hz/:full_id", (req, res) -> {
            String param = req.params(":full_id");
            String id = param.contains("=") ? param.split("=")[1] : param;
            res.type("application/json");
            return gson.toJson(HazelcastStore.get(id));
        });

        // MONGODB
        get("/nosql-lab-mon/:full_id", (req, res) -> {
            String param = req.params(":full_id");
            String id = param.contains("=") ? param.split("=")[1] : param;
            res.type("application/json");
            return gson.toJson(MongoStore.get(id));
        });

        System.out.println("Sunucu hazır: http://localhost:8080");
    }
}