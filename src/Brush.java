import java.awt.BasicStroke;

public class Brush extends BasicStroke{
	public Brush() {
		super(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	}

	public Brush(int h) {
		super(h, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	}
}