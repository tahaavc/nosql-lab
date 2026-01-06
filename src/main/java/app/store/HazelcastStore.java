package app.store;

import app.model.Student;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class HazelcastStore {
    private static HazelcastInstance client;
    private static IMap<String, Student> map;

    public static void init() {
        ClientConfig config = new ClientConfig();
        config.getGroupConfig().setName("dev");
        config.getNetworkConfig().addAddress("127.0.0.1:5701");

        client = HazelcastClient.newHazelcastClient(config);
        map = client.getMap("students");
        map.clear();

        for (int i = 1; i <= 10000; i++) {
            String id = String.format("2025%06d", i);
            Student s = new Student(id, "Student " + i, "Computer Engineering");
            map.put(s.getStudent_no(), s);
        }
        System.out.println("Hazelcast: 10.000 kayıt hazır.");
    }

    public static Student get(String studentNo) {
        return map.get(studentNo);
    }
}