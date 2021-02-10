import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class DrawingToolsListener implements ActionListener {
	private JComboBox<String> drawingTools;
	private MousePaintListener painter;

	private Color color;

	public DrawingToolsListener(JComboBox<String> drawingTools, MousePaintListener painter) {
		this.drawingTools = drawingTools;
		this.painter = painter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = (String) drawingTools.getSelectedItem();
		String shape = painter.getShape();
		if(shape.compareTo("eraser")==0) {
			painter.setColor(color);
		}

		if(s.compareTo("ペン")==0) {
			painter.setShape("pen");
		}else if(s.compareTo("三角")==0){
			painter.setShape("triangle");
		}else if(s.compareTo("直線")==0) {
			painter.setShape("line");
		}else if(s.compareTo("虹色ペン")==0) {
			color = painter.getColor();
			painter.setShape("rainbow-pen");
		}else {
			//消しゴム
			painter.setShape("eraser");
			color = painter.getColor();
			painter.setColor(Color.WHITE);
		}
	}
}
