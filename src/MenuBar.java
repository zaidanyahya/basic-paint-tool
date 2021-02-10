import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;

public class MenuBar extends JMenuBar{
	private MenuListener menuListener;
	private Border padding = BorderFactory.createEmptyBorder(4,6,4,6);

	public MenuBar(MenuListener listener) {
		setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder());

        this.menuListener = listener;

        JMenu file = new JMenu("ファイル");
        file.setBorder(padding);
        createMenuItem(file, "ファイルを開く");
        createMenuItem(file, "名前を付けて保存");
        add(file);

        JMenu color = new JMenu("色の変更");
        color.setBorder(padding);
        createMenuItem(color, "黒");
        createMenuItem(color, "白");
        createMenuItem(color, "赤");
        createMenuItem(color, "青");
        createMenuItem(color, "緑");
        createMenuItem(color, "カスタムカラー");
        add(color);

        JMenu height = new JMenu("線の太さ変更");
        height.setBorder(padding);
        createMenuItem(height, "1");
        createMenuItem(height, "3");
        createMenuItem(height, "5");
        createMenuItem(height, "8");
        createMenuItem(height, "10");
        createMenuItem(height, "カスタム太さ");
        add(height);

        JMenu sticker = new JMenu("スタンプ");
        sticker.setBorder(padding);
        String[] stickerList = {
        		"\uD83D\uDE00", "\uD83D\uDE05", "\uD83D\uDE2D",
        		"\uD83D\uDE21", "\uD83D\uDE0E", "\uD83D\uDE43",
        		"\uD83E\uDD23", "\uD83D\uDC4D"
        };
        for(int i=0;i<stickerList.length;i++) {
        	createMenuItem(sticker, stickerList[i]+" スタンプ");
        }
        add(sticker);

        JMenu filter = new JMenu("フィルター");
        filter.setBorder(padding);
        createMenuItem(filter, "シャープン");
        add(filter);
	}

	private void createMenuItem(JMenu menu, String command) {
		JMenuItem item = new JMenuItem(command);
		item.addActionListener(menuListener);
		item.setBackground(new Color(240,240,240));
		item.setBorder(padding);
		menu.add(item);
	}
}