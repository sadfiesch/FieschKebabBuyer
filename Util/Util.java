package FieschKebabBuyer.Util;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.utilities.Timer;

public class Util {
    public static int kebabCount = 0;
    public static Area bankArea = new Area(3270, 3170, 3269, 3166);
    public static Area vendorArea = new Area(3275, 3182, 3271, 3179);
    public static String vendorName = "Karim";
    public static Timer timer;

    public static Boolean antibanActive = false;
}
