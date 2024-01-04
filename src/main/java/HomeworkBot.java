
import events.*;
import events.MessageEventListener;
import events.ReadyEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import static com.mongodb.client.model.Filters.eq;

import javax.security.auth.login.LoginException;

// Sharding is used for multiple servers. I'll get back to it when it's famous haha

public class HomeworkBot extends ListenerAdapter  {


    public static void main(String[] args) throws LoginException {


        JDA jda = JDABuilder.createDefault
                        (Passwords.token1)

                .setActivity(Activity.listening("เลี้ยงไข้ By Bokylion (เพลงโปรดผมเอง)"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MEMBERS)

                // สำหรับการดึงข้อมูลจากผู้ใช้มา
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL)
                .enableCache(CacheFlag.ONLINE_STATUS)

                // Register listeners, basically triggers when each class is called into usage สำคัญมาก
                .addEventListeners(new ReadyEventListener(), new MessageEventListener(),
                        new InteractionEventListener(),
                        new HelloListener(), new Hello2Listener(), new AddHomework(),
                        new ListHomework(), new RemoveHomework())

                .build();

                jda.upsertCommand("list-homework", "List all homework").setGuildOnly(true).queue();
                jda.upsertCommand("add-homework", "Add in new homework").setGuildOnly(true).queue();
                jda.upsertCommand("remove-homework", "Remove homework").setGuildOnly(true).queue();


    }




    }
