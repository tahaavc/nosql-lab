package app.store;

import app.model.Student;
import com.mongodb.client.*;
import org.bson.Document;

public class MongoStore {
    private static MongoCollection<Document> collection;

    public static void init() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = client.getDatabase("nosql_lab");
        collection = database.getCollection("students");
        collection.drop();

        for (int i = 1; i <= 10000; i++) {
            String id = String.format("2025%06d", i);
            Document doc = new Document("student_no", id)
                    .append("name", "Student " + i)
                    .append("department", "Computer Engineering");
            collection.insertOne(doc);
        }
        System.out.println("MongoDB: 10.000 kayıt hazır.");
    }

    public static Document get(String studentNo) {
        return collection.find(new Document("student_no", studentNo)).first();
    }
}