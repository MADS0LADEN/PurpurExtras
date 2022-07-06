package me.youhavetrouble.purpurextras.modules;

import me.youhavetrouble.purpurextras.PurpurExtras;
import org.bukkit.event.HandlerList;

import java.util.HashSet;

public interface PurpurExtrasModule {

    /**
     * Enables the feature, registers the listeners.
     */
    void enable();

    /**
     * @return true if the feature should be enabled
     */
    boolean shouldEnable();

    HashSet<PurpurExtrasModule> modules = new HashSet<>();

    static void reloadModules() {
        PurpurExtras.getInstance().reloadConfig();
        modules.clear();
        HandlerList.unregisterAll(PurpurExtras.getInstance());

        modules.add(new BeeHiveLoreModule());
        modules.add(new AnvilChangesBlocksModule());

        modules.forEach(module -> {
            if (module.shouldEnable()) module.enable();
        });

    }

}
