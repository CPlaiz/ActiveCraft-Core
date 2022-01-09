package de.silencio.activecraftcore.guis;

import de.silencio.activecraftcore.guicreator.GuiCloseItem;
import de.silencio.activecraftcore.guicreator.GuiCreator;
import de.silencio.activecraftcore.guicreator.GuiItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class EffectGui extends GuiCreator {

    private Player player;
    private Player target;
    private GuiCreator guiCreator;

    private GuiItem statusEffects, potionEffects,

            absorption, bad_luck, bad_omen, blindness, conduit_power, dolphins_grace, glowing, haste, health_boost,
            village_hero, hunger, levitation, mining_fatigue, nausea, resistance, saturation, wither,

            night_vision, invisibility, jump_boost, fire_resistance, speed, slowness, turtle_master, water_breathing,
                    poison, regeneration, strength, weakness, luck, slow_falling;

    public EffectGui(GuiCreator guiCreator) {
        super("effect_gui", 6, "Effect Gui");
        refresh();
    }

    @Override
    public void refresh() {
        guiCreator.fillBackground(true);
        guiCreator.setCloseItem(new GuiCloseItem(49));

        statusEffects = new GuiItem(Material.OAK_SIGN);
        statusEffects.setDisplayName("bsp");

    }
}
