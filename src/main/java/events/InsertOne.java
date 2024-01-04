package usage.examples;
import java.util.Arrays;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import static events.Passwords2.token2;


public class InsertOne {
    public static void main(String[] args) {
        // ใส่ URI แต่ประกาศใน class อื่นแล้วค่อยดึงมาด้วย public static เพื่อให้ token ไม่หลุด แล้วประกาศใน .gitignore ให้ไม่ commit

        try (MongoClient mongoClient = MongoClients.create(token2)) {
            MongoDatabase database = mongoClient.getDatabase("Homework");
            MongoCollection<Document> collection = database.getCollection("General Homework");
            try {
                //  ทดลอง ใส่ข้อมูล
                InsertOneResult result = collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("Homework", "Ms. Somying Science Homework"));

                // Prints the ID of the inserted document (self-explained)
                System.out.println("Success! Inserted document id: " + result.getInsertedId());

                // Prints a message if any exceptions occur during the operation
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }
}