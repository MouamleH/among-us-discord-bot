package me.mouamle.discord.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.Optional;

public class Common {

    static Optional<VoiceChannel> preCommand(CommandEvent event) {
        final Member member = event.getMember();
        if (member == null) {
            return Optional.empty();
        }

        final Optional<VoiceChannel> oChannel = Optional.of(member)
                .map(Member::getVoiceState)
                .map(GuildVoiceState::getChannel);

        boolean roleFound = false;
        for (Role role : member.getRoles()) {
            roleFound |= role.getName().equalsIgnoreCase("Operator");
        }

        if (!roleFound) {
            event.getChannel().sendMessage("Only operators can use me :P").queue();
            return Optional.empty();
        }

        if (!oChannel.isPresent()) {
            event.getChannel().sendMessage("You must be in a voice channel").queue();
            return Optional.empty();
        }

        return oChannel;
    }

}
