package events;


import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;



public class InteractionEventListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);

        switch (event.getName()) {
            case "add-homework":
            event.reply("What would you like to add?").queue();
            break;
            case "remove-homework":
            event.reply("What would you like to remove?").queue();
            break;
            case "list-homework":
            event.reply("Here are all your homework").queue();
            break;








}
}
}