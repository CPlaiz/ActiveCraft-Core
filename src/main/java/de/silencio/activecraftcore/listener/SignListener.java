package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.MessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        String line1 = ColorUtils.replaceColor(event.getLine(0));
        line1 = ColorUtils.replaceColor(line1);
        String line2 = ColorUtils.replaceColor(event.getLine(1));
        line2 = ColorUtils.replaceColor(line2);
        String line3 = ColorUtils.replaceColor(event.getLine(2));
        line3 = ColorUtils.replaceColor(line3);
        String line4 = ColorUtils.replaceColor(event.getLine(3));
        line4 = ColorUtils.replaceColor(line4);

        event.setLine(0, line1);
        event.setLine(1, line2);
        event.setLine(2, line3);
        event.setLine(3, line4);
    }

}
