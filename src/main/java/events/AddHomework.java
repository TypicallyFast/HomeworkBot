package events;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

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

public class AddHomework extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String author = event.getAuthor().getId();

        if (event.getAuthor().isBot()) {
            return;
        } else if (message.contains("Add")) {
            event.getChannel().sendMessage(message + " , เดี๋ยวผมเพิ่มให้นะคร้าบบบ").queue();
            try (MongoClient mongoClient = MongoClients.create(token2)) {
                MongoDatabase database = mongoClient.getDatabase("Homework");
                MongoCollection<Document> collection = database.getCollection("General Homework");
                try {
                    // Inserts a sample document describing a movie into the collection (ใส่ sample doc เข้า collection)
                    InsertOneResult result = collection.insertOne(new Document()
                            .append("_id", new ObjectId())
                            .append("Homework", message));
                    }
                catch (MongoException me) {
                    System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }
}
}






