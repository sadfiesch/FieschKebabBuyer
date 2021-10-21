package FieschKebabBuyer.tasks;

import FieschKebabBuyer.Util.Util;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.NPC;

import java.awt.*;

public class BuyTask extends TaskNode {

    @Override
    public boolean accept() {
        return !Inventory.isFull() && Inventory.contains("Coins") && Util.vendorArea.contains(Players.localPlayer());
    }

    @Override
    public int execute() {
        log("Buying kebab");
        NPC vendor = NPCs.closest(Util.vendorName);

        if(vendor == null){
            return Calculations.random(75, 450);
        }

        if(Dialogues.inDialogue()){
            if(Dialogues.canContinue()){
                String d = Dialogues.getNPCDialogue();
                if(Dialogues.continueDialogue()){
                    sleepUntil(() -> d != Dialogues.getNPCDialogue(), Calculations.random(500, 1500));
                }

                sleep(500);
            }
            if(Dialogues.areOptionsAvailable()){
                String d = Dialogues.getNPCDialogue();
                if(Dialogues.chooseFirstOptionContaining("Yes")){
                    Util.kebabCount++;
                    sleepUntil(() -> d != Dialogues.getNPCDialogue(), Calculations.random(500, 1500));
                }
            }
        }else{
            if(vendor.interact("Talk-to")){
                sleepUntil(() -> Dialogues.inDialogue(), 2000);
            }
        }

        return Calculations.random(75, 450);
    }
}
