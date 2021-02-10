import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlsListener implements ActionListener {
	private MousePaintListener painter;
	private JPanel canvas;

	public ControlsListener(Canvas canvas, MousePaintListener painter) {
		this.painter = painter;
		this.canvas = canvas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if(command.compareTo(" ")==0) {
			Color c = JColorChooser.showDialog(new JFrame(), "カスタムカラー", painter.getColor());
			if(c != null)
				painter.setColor(c);
		}

		if(command.compareTo("初期化")==0) {
			Color c = JColorChooser.showDialog(new JFrame(), command, painter.getBackground());
			if(c!=null) {
				painter.setBgColor(c);
			}
		}

		if(command.compareTo("文字入力")==0) {
			String s = JOptionPane.showInputDialog(null,"文字を入力してください", "文字入力",JOptionPane.PLAIN_MESSAGE);

			JTextField xField = new JTextField(5);
		    JTextField yField = new JTextField(5);

			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("0>= X <=470 と 0>= Y 0 <= 380"));
			myPanel.add(new JLabel(" の範囲で入力してください"));
			myPanel.add(Box.createVerticalStrut(30));
		    myPanel.add(new JLabel("x:"));
		    myPanel.add(xField);
		    myPanel.add(new JLabel("y:"));
		    myPanel.add(yField);
		    myPanel.add(Box.createHorizontalStrut(20));

		    double x,y;
		    boolean finished = false;

		    if(s == null)
		    	finished = true;

		    while(!finished) {
		    	int result = JOptionPane.showConfirmDialog(null, myPanel, "座標を入力してください", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			    if (result == JOptionPane.OK_OPTION) {
			        try{
				    	x = Double.parseDouble(xField.getText());
				    	y = Double.parseDouble(yField.getText());

				    	if(x<0 || x>470 || y<0 || y>380) {
				    		JOptionPane.showMessageDialog(null, "範囲以内で入力してください");
				    	}else {
				    		finished = true;
				    		painter.drawString(s, (int)x, 10+(int)y);
				    	}
				    }catch (NumberFormatException error) {
						JOptionPane.showMessageDialog(null, "数字で入力してください");
					}
			    }else {
			    	finished = true;
			    }
		    }
		}

	}

}
