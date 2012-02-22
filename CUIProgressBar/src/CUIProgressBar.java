import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Swaroop Patra
 */
public class CUIProgressBar {
	public static void startProgressBar(ProgressBarInput pbi) {
		try {
			int d = pbi.getDecimalFraction();
			int x = pbi.getCurrentValue();
			int y = pbi.getTotalValue();
			StringBuffer str = new StringBuffer();
			do {
				float xyper = (float) x / y * 100;
				int floor = (int) Math.floor(xyper);
				// System.out.println(xyper+" "+xyper/2+" "+(50-(xyper/2)));
				str.append("Progress percentage [");
				for (int j = 0; j < floor / 2; j++) {
					str.append("#");
				}
				for (int k = 0; k < 50 - (floor / 2); k++) {
					str.append(" ");
				}
				if (d == 0) {
					str.append("] %d%s completed");
				} else {
					str.append("] %." + d + "f%s completed");
				}
				str.append("\r");
				if (d == 0) {
					System.out.format(str.toString(), (int) xyper, "%");
				} else {
					System.out.format(str.toString(), xyper, "%");
				}
				str.delete(0, str.length() - 1);
				Thread.sleep(50);
				x = pbi.getCurrentValue();
			} while (x <= y);
			System.out.println("");
		} catch (Exception ex) {
			Logger.getLogger(CUIProgressBar.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public static void main(String args[]) {

		ProgressBarInput pbin = new ProgressBarInput() {
			int x = 0;

			public int getCurrentValue() {
				return x++;
			}

			public int getTotalValue() {
				return 800;
			}

			public int getDecimalFraction() {
				return 2;
			}
		};

		startProgressBar(pbin);
	}
}
