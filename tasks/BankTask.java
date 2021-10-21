package FieschKebabBuyer.tasks;

import FieschKebabBuyer.Util.Util;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;

public class BankTask extends TaskNode {
    @Override
    public boolean accept() {
        return (Inventory.isFull() || !Inventory.contains("Coins")) && Util.bankArea.contains(Players.localPlayer());
    }

    @Override
    public int execute() {
        log("Banking");

        if(!Bank.isOpen()){
            GameObject bankBooth = GameObjects.closest(object -> object.getName().equalsIgnoreCase("Bank booth") && object.hasAction("Bank"));
            if(bankBooth.distance(Players.localPlayer()) >= 2) {
                Walking.walk(bankBooth);
                sleepUntil(() -> bankBooth.distance(Players.localPlayer()) < 2, 2000);
            }
            if (bankBooth.distance(Players.localPlayer()) < 2 && bankBooth.interact("Bank") && bankBooth.isOnScreen()) {
                sleepUntil(() -> Bank.isOpen(), 2000);
            }
        }

        if(Inventory.isFull()){
            if(Bank.depositAllExcept("Coins")){
                sleepUntil(() -> Inventory.fullSlotCount() <= 1, 2000);
            }
        }

        if(!Inventory.contains("Coins")){
            if(Bank.withdrawAll("Coins")){
                sleepUntil(() -> Inventory.contains("Coins"), 2000);
            }
        }

        return Calculations.random(75, 450);
    }
}
