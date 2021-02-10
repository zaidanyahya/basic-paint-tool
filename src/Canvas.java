import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Canvas extends JPanel{
	private final static int CANVAS_SIZE = 400;
	private BufferedImage image;

	public Canvas(Graphics2D g2d, BufferedImage image) {
		setBounds(0,40,500, CANVAS_SIZE);

		this.image = image;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		if (image != null)
		{
			g.drawImage(image, 0, 0, null);

		}
	}
}