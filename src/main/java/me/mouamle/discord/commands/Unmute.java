package me.mouamle.discord.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;

public class Unmute extends Command {

    @Override
    protected void execute(CommandEvent event) {
        Common.preCommand(event).ifPresent(channel -> {
            event.getChannel().sendMessage("Unmuting all users...").queue();
            final List<Member> members = channel.getMembers();
            for (Member aMember : members) {
                aMember.mute(false).queue();
            }
        });
    }

    @Override
    public String getRequiredRole() {
        return "Operator";
    }

    @Override
    public String getName() {
        return "unmute";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"um"};
    }

    @Override
    public String getHelp() {
        return "Unmutes all users in a current voice channel";
    }

}
