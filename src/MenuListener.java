import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuListener implements ActionListener{
	private MousePaintListener painter;
	private BufferedImage image;
	private JPanel canvas;

	public MenuListener(Canvas canvas, MousePaintListener painter, BufferedImage image) {
		this.canvas = canvas;
		this.painter = painter;
		this.image = image;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem)e.getSource();
		String command = item.getActionCommand();

		if(command.compareTo("ファイルを開く") == 0) {
			FileChooser chooser = new FileChooser();
			int result = chooser.showOpenDialog(null);
			if(result == JFileChooser.APPROVE_OPTION) {
				try {
					File f = chooser.getSelectedFile();
					BufferedImage bi = ImageIO.read(f);
					bi = painter.resizeImage(bi,500,400);
					image.setData(bi.getData());
				    if(image == null) {
				    	JOptionPane.showMessageDialog(null, "エラーが発生した");
				    }
				    canvas.repaint();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "エラーが発生した");
				}
			}
		}else if(command.compareTo("名前を付けて保存") == 0) {
			File saveFile = new File("untitled.png");
            FileChooser chooser = new FileChooser();
            chooser.setSelectedFile(saveFile);
            int result = chooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
            	saveFile = chooser.getSelectedFile();

                try {
                	if(!ImageIO.write(image, chooser.getSelectedFileExtension(), saveFile)) {
                		JOptionPane.showMessageDialog(null, "保存が失敗した！");
                	}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "エラーが発生した");
				}
            }
		}


		if(command.compareTo("黒")==0) {
			painter.setColor(Color.BLACK);
		}else if(command.compareTo("白")==0) {
			System.out.println("Haha");
			painter.setColor(Color.WHITE);
		}else if(command.compareTo("赤")==0) {
			painter.setColor(Color.RED);
		}else if(command.compareTo("青")==0) {
			painter.setColor(Color.BLUE);
		}else if(command.compareTo("緑")==0) {
			painter.setColor(Color.GREEN);
		}else if(command.compareTo("カスタムカラー")==0) {
			Color c = JColorChooser.showDialog(new JFrame(), "カスタムカラー", painter.getColor());

			if(c!=null)
				painter.setColor(c);
		}

		if(command.compareTo("カスタム太さ")==0) {
			try {
				int h = (int) Double.parseDouble(JOptionPane.showInputDialog(null, "太さを数字で入力してください"));
				painter.setBrush(h);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "数字で入力してください");
			}
		}else {
			try {
				int h = Integer.parseInt(command);
				painter.setBrush(h);
			} catch ( NumberFormatException error) {}
		}

		if(command.contains("スタンプ")) {
			String sticker = command.substring(0, command.indexOf(" スタンプ"));
			System.out.println(sticker);
			painter.setShape("sticker+"+sticker);
		}

		if(command.compareTo("シャープン")==0) {
			float[] sharpen = new float[] {
	    	     0.0f, -1.0f, 0.0f,
	    	    -1.0f, 5.0f, -1.0f,
	    	     0.0f, -1.0f, 0.0f
	    	};
	    	Kernel kernel = new Kernel(3, 3, sharpen);
	    	ConvolveOp op = new ConvolveOp(kernel,ConvolveOp.EDGE_NO_OP,null);

	    	image.setData(op.filter(image, null).getData());
	    	canvas.repaint();
		}

	}

}
