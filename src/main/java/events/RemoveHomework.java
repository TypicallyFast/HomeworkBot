package events;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.conversions.Bson;
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

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import static events.Passwords2.token2;

public class RemoveHomework extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String author = event.getAuthor().getId();

        if (event.getAuthor().isBot()) {
            return;
        } else if (message.contains("Remove")) {
            event.getChannel().sendMessage(message + " , เสร็จแล้วนะครับ ยินดีด้วย เดี๋ยวผมลบออกให้ครับ").queue();
            try (MongoClient mongoClient = MongoClients.create(token2)) {
                MongoDatabase database = mongoClient.getDatabase("Homework");
                MongoCollection<Document> collection = database.getCollection("General Homework");


                BasicDBObject document = new BasicDBObject();
                // Delete All documents from collection Using blank BasicDBObject
                collection.deleteOne(document);


            }
        }
    }
}



