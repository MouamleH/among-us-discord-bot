package me.mouamle.discord.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;

public class Mute extends Command {

    @Override
    protected void execute(CommandEvent event) {
        Common.preCommand(event).ifPresent(channel -> {
            event.getChannel().sendMessage("Muting all users...").queue();
            final List<Member> members = channel.getMembers();
            for (Member aMember : members) {
                aMember.mute(true).queue();
            }
        });
    }

    @Override
    public String getRequiredRole() {
        return "Operator";
    }

    @Override
    public String getName() {
        return "mute";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"m"};
    }

    @Override
    public String getHelp() {
        return "Mutes all users in a current voice channel";
    }

}
