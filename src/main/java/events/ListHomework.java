package events;

import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;
import org.bson.Document;
import static com.mongodb.client.model.Filters.lt;
import java.util.Iterator;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;
import static java.time.temporal.TemporalAdjusters.next;

public class ListHomework extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String author = event.getAuthor().getId();

        if (event.getAuthor().isBot()) {
            return;

        } else if (message.contains("List")) {
            event.getChannel().sendMessage("นี่เลยครับการบ้านที่เหลืออยู่ อย่าลืมไปทำนะครับ!").queue();
            try (MongoClient mongoClient = MongoClients.create("uri")) {
                MongoDatabase database = mongoClient.getDatabase("Homework");
                MongoCollection<Document> collection = database.getCollection("General Homework");
                // Creates instructions to project two document fields
                Bson projectionFields = Projections.fields(
                        Projections.include("Homework"),
                        Projections.excludeId());
                MongoCursor<Document> cursor = collection.find()
                        .projection(projectionFields).iterator();
                try {
                    while (cursor.hasNext()) {
                        event.getChannel().sendMessage(cursor.next().toString().replace("Document", "")
                                .replace("{{Homework=Add", "").replace("}}", "")).queue();
                    }
                } finally {
                    cursor.close();
                    event.getChannel().sendMessage("ได้เลยครับผมมมมม จัดให้ๆ");
                }
            }
        }
    }
}
