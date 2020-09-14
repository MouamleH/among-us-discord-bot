package me.mouamle.discord;


import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.mouamle.discord.commands.Mute;
import me.mouamle.discord.commands.Reset;
import me.mouamle.discord.commands.Unmute;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class AmongUsBot {

    private final EventWaiter waiter = new EventWaiter();
    private final CommandClientBuilder clientBuilder = new CommandClientBuilder()
            .setPrefix("!")
            .setOwnerId("498745386547019786")
            .setActivity(Activity.playing("Something"))
            .setStatus(OnlineStatus.ONLINE)
            .useHelpBuilder(false)
            .setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");

    public AmongUsBot() {
        clientBuilder.addCommand(new Mute());
        clientBuilder.addCommand(new Unmute());
        clientBuilder.addCommand(new Reset());
    }

    public void start() throws LoginException {
        // TODO: Put your discord bot token here:
        final String TOKEN = "";

        new JDABuilder(TOKEN)
                .addEventListeners(waiter, clientBuilder.build())
                .build();
    }

    public static void main(String[] args) throws LoginException, IOException {
        InputStream stream = AmongUsBot.class.getClassLoader().getResourceAsStream("logging.properties");
        LogManager.getLogManager().readConfiguration(stream);

        final AmongUsBot amongUsBot = new AmongUsBot();
        amongUsBot.start();
    }

}
