import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame{
	private final static int FRAME_SIZE = 500;
	private final static int CANVAS_SIZE = 400;
	private BufferedImage image =
			new BufferedImage(FRAME_SIZE, CANVAS_SIZE, BufferedImage.TYPE_INT_RGB);


	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		super("ペイント");

		Graphics2D g2d = (Graphics2D) image.createGraphics();

		Canvas canvas = new Canvas(g2d, image);
		JButton colorButton = new JButton(" ");

		MousePaintListener mouseListener = new MousePaintListener(canvas, g2d, colorButton);
		canvas.addMouseListener(mouseListener);
		canvas.addMouseMotionListener(mouseListener);

		MenuListener menuListener = new MenuListener(canvas, mouseListener, image);
		MenuBar menuBar = new MenuBar(menuListener);

		ToolsPanel tools = new ToolsPanel(canvas, colorButton, mouseListener);

		setBounds(0,0,FRAME_SIZE, FRAME_SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(null);
		setResizable(false);
		setVisible(true);

		setJMenuBar(menuBar);
		getContentPane().add(canvas);
		getContentPane().add(tools);
	}
}
