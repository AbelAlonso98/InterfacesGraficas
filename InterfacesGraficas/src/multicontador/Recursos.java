package multicontador;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Recursos {
	public static ImageIcon addIcon;
	public static ImageIcon resetIcon;
	public static Font font;

	static {
		try {
			Image img = ImageIO.read(Recursos.class.getResourceAsStream("/add.png")).getScaledInstance(32, 32,
					Image.SCALE_SMOOTH);
			addIcon = new ImageIcon(img);
			img = ImageIO.read(Recursos.class.getResourceAsStream("/reset.png")).getScaledInstance(32, 32,
					Image.SCALE_SMOOTH);
			resetIcon = new ImageIcon(img);

			font = Font.createFont(Font.PLAIN, Recursos.class.getResourceAsStream("/Digital Play Italic St.ttf"))
					.deriveFont(30f).deriveFont(Collections.singletonMap(TextAttribute.FOREGROUND, Color.WHITE));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
