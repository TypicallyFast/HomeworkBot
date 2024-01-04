package events;

import events.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class ReadyEventListener implements EventListener {

    @Override
    public void onEvent(GenericEvent event) {

        if (event instanceof ReadyEvent)
            System.out.println("The bot is ready and online");

    }


}