package FieschKebabBuyer;

import FieschKebabBuyer.Util.Util;
import FieschKebabBuyer.tasks.BankTask;
import FieschKebabBuyer.tasks.BuyTask;
import FieschKebabBuyer.tasks.MoveToBank;
import FieschKebabBuyer.tasks.MoveToVendor;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;
import org.dreambot.api.utilities.Timer;

import java.awt.*;

@ScriptManifest(category = Category.MONEYMAKING, name = "Fiesch Kebab Buyer", author = "fiesch", version = 1.0)
public class KebabBuyer extends TaskScript {

    @Override
    public void onStart(){
        Util.kebabCount = 0;
        Util.timer = new Timer();
        addNodes(new BankTask(), new BuyTask(), new MoveToBank(), new MoveToVendor());
    }

    @Override
    public void onPaint(Graphics g) {

        Font normal = new Font("default", Font.PLAIN, 12);
        Font bold = new Font("default", Font.BOLD, 12);

        Color full = new Color(0, 0, 0, 255);
        Color transparent = new Color(255, 255, 255, 50);
        int x = 20;
        int y = 30;
        int with = 120;
        int height = 60;

        g.setColor(full);
        g.drawRect(x, y, with, height);
        g.drawLine(x,y + 17,x + with,y + 17);

        g.setFont(bold);
        g.drawString("Fiesch Kebab Buyer", x + 3, y + 13);
        g.setFont(normal);
        g.drawString("Kebabs bought: " + Util.kebabCount, x + 2, y + 29);
        if(Util.timer != null){
            g.drawString("Kebabs per h: " +  Util.timer.getHourlyRate(Util.kebabCount), x + 2, y + 41);
            g.drawString("Runtime: " +  Util.timer.formatTime(), x + 2, y + 53);
        }
        g.setColor(transparent);
        g.fillRect(x, y, with, height);

    }
}
