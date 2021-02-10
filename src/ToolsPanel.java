import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ToolsPanel extends JPanel{
	private ControlsListener controlsListener;
	private DrawingToolsListener drawingToolsListener;

	private Border padding = BorderFactory.createEmptyBorder(3,20,3,20);

	public ToolsPanel(Canvas canvas, JButton colorButton, MousePaintListener mouseListener) {
		//setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        setBounds(0,0,500,50);
        setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        setBorder(BorderFactory.createEmptyBorder(10, 7, 17, 14));
        setBackground(new Color(240,240,240));

        controlsListener = new ControlsListener(canvas, mouseListener);

        createButton("初期化");
        add(Box.createRigidArea(new Dimension(10, 0)));

        createButton("文字入力");
        add(Box.createRigidArea(new Dimension(10, 0)));

        String toolsList[]={"ペン","直線","三角","虹色ペン","消しゴム"};
        JComboBox<String> drawingTools = new JComboBox<String>(toolsList);
		styleObject(drawingTools,false);
		add(drawingTools);
		drawingToolsListener = new DrawingToolsListener(drawingTools, mouseListener);
        drawingTools.addActionListener(this.drawingToolsListener);
		add(Box.createRigidArea(new Dimension(120, 0)));

		styleObject(colorButton, false);
		colorButton.setBackground(Color.BLACK);
		colorButton.addActionListener(this.controlsListener);
		add(colorButton);
        add(Box.createRigidArea(new Dimension(10, 0)));
	}

	private void createButton(String command) {
		JButton button = new JButton(command);
		button.addActionListener(this.controlsListener);
		styleObject(button,false);
		add(button);
	}

	public void styleObject (JComponent obj, boolean border) {
		obj.setBackground(new Color(230,240,250));
		obj.setForeground(new Color(30,30,30));
		if(border)
			obj.setBorder(padding);
		obj.setFocusable(false);
	}
}
