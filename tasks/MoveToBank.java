package FieschKebabBuyer.tasks;

import FieschKebabBuyer.Util.Util;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.TaskNode;

public class MoveToBank extends TaskNode {
    @Override
    public boolean accept() {
        return (Inventory.isFull() || !Inventory.contains("Coins")) && !Util.bankArea.contains(Players.localPlayer());
    }

    @Override
    public int execute() {
        log("Moving to bankarea");
        if(!Util.bankArea.contains(Players.localPlayer())) {
            Tile rndTile = Util.bankArea.getRandomTile();
            Walking.walk(rndTile);
            sleepUntil(() -> (rndTile.distance() < 2), 2000);
        }
        return Calculations.random(75, 450);
    }
}
