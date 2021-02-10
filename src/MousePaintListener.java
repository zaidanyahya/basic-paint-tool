import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;

public class MousePaintListener implements MouseListener, MouseMotionListener {
	private Graphics2D g2d;
	private Canvas canvas;

	private JButton colorButton;
	private String shape = "pen";

	private ArrayList<Point> points = new ArrayList<Point>();

	private Point lastPoint = new Point();
	private int changeCount;

	private Font defaultFont;
	private Font stickerFont;


	public MousePaintListener(Canvas canvas, Graphics2D g2d, JButton color) {
		this.canvas = canvas;
		this.g2d = g2d;
		this.colorButton = color;

		setColor(Color.WHITE);
		g2d.fill(new Rectangle(canvas.getSize()));

		setBrush();
		setColor(Color.BLACK);

		defaultFont = g2d.getFont();
		stickerFont = new Font(defaultFont.getName(), Font.PLAIN, 28);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = new Point(e.getPoint());
		BasicStroke s = (BasicStroke) g2d.getStroke();
		int w = (int) s.getLineWidth();

		if(this.shape.contains("sticker")) {
			String sticker = this.shape;
			sticker = sticker.substring(sticker.lastIndexOf("+")+1);
			g2d.setFont(stickerFont);
			g2d.drawString(sticker, p.x-14, p.y+14);
			g2d.setFont(defaultFont);
			canvas.repaint();
		}else {
			g2d.fillOval(p.x - w/2, p.y - w/2, w, w);
		}


		canvas.repaint();

		if(this.shape.compareTo("line") == 0) {
			points.add(p);
			if(points.size() == 2) {
				g2d.drawLine(points.get(0).x, points.get(0).y, points.get(1).x, points.get(1).y);
				points.clear();
			}
		}else if(this.shape.compareTo("triangle") == 0) {
			points.add(p);

			if(points.size() == 3) {
				int[] x = new int[3];
				int[] y = new int[3];
				int i=0;
				for (Point point : points) {
					x[i] = point.x;
					y[i] = point.y;
					i++;
				}
				g2d.drawPolygon(x, y, 3);
				points.clear();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastPoint.setLocation(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point newPoint = new Point(e.getPoint());
		if(this.shape.contains("pen") || this.shape.compareTo("eraser")==0) {
			if(this.shape.compareTo("rainbow-pen")==0) {
				int changeTime = 1000;
				double f1 = 0.3;
				double f2 = 0.3;
				double f3 = 0.3;
				double p1 = 0;
				double p2 = 2;
				double p3 = 4;
				double w = 95;
				double c = 160;

				int red = (int) (Math.sin(f1*changeCount + p1) * w + c);
			    int green = (int) (Math.sin(f2*changeCount + p2) * w + c);
			    int blue = (int) (Math.sin(f3*changeCount + p3) * w + c);

			    setColor(new Color(red,green,blue));
				changeCount +=1;
				changeCount %=changeTime;
			}else if(this.shape.compareTo("eraser") == 0) {
				//消しゴムを選択したとき,
				//メニューから色の変更できないように
				g2d.setColor(Color.WHITE);
			}
			g2d.drawLine(lastPoint.x, lastPoint.y, newPoint.x, newPoint.y);
			lastPoint = newPoint;
			canvas.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getShape() {
		return this.shape;
	}
	public void setColor(Color color) {
		colorButton.setBackground(color);
		g2d.setColor(color);
	}
	public Color getColor() {
		return g2d.getColor();
	}

	public void setBrush() {
		g2d.setStroke(new Brush());
	}

	public void setBrush(int h) {
		if(h <= 0) {
			g2d.setStroke(new Brush());
		}else {
			g2d.setStroke(new Brush(h));
		}
	}

	public void setBgColor(Color c) {
		Color tmp = g2d.getColor();
		g2d.setColor(c);
		g2d.fill(new Rectangle(0,0,500,400));
		g2d.setColor(tmp);
		canvas.repaint();
	}

	public Color getBackground() {
		return g2d.getBackground();
	}

	public void drawString(String s, int x, int i) {
		g2d.drawString(s, x, i);
		canvas.repaint();
	}

	public BufferedImage resizeImage(BufferedImage im, int w, int h){
		int imw = im.getWidth();
		int imh = im.getHeight();
		int scale;
		w = (int) Math.ceil(((double)imw/w));
		h = (int) Math.ceil(((double)imh/h));
		scale = (w > h) ? w : h;
		w = imw/scale;
		h = imh/scale;
	    Image scaledIm = im.getScaledInstance(w,  h, Image.SCALE_FAST);
	    BufferedImage result = new BufferedImage(500, 400, BufferedImage.TYPE_INT_RGB);
	    result.getGraphics().setColor(Color.WHITE);
	    result.getGraphics().fillRect(0, 0, 500, 400);
	    result.getGraphics().drawImage(scaledIm, (500-w)/2, (400-h)/2, null);
	    return result;
	}
}
