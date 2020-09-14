package me.mouamle.discord.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;

import java.util.Optional;

public class Reset extends Command {

    @Override
    protected void execute(CommandEvent event) {
        final Member member = event.getMember();

        Optional.ofNullable(member)
                .map(Member::getVoiceState)
                .map(GuildVoiceState::getChannel)
                .ifPresent(channel -> member.mute(false).queue());

        event.getChannel().sendMessage("Mute reset").queue();
    }

    @Override
    public String getName() {
        return "reset";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"r"};
    }

    @Override
    public String getHelp() {
        return "Resets mute status for a single user";
    }


}
