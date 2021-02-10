import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFileChooser{
	private String defaultExtension = "png";

	private FileNameExtensionFilter images = new FileNameExtensionFilter("Image Files (*.jpg, *jpeg, *.png, .*gif)", "jpg", "jpeg", "png", "gif");
	private FileNameExtensionFilter jpg = new FileNameExtensionFilter("JPG (*.jpg)", "jpg");
	private FileNameExtensionFilter jpeg =  new FileNameExtensionFilter("JPEG (*jpeg)", "jpeg");
	private FileNameExtensionFilter png = new FileNameExtensionFilter("PNG (*.png)", "png");
	private FileNameExtensionFilter gif = new FileNameExtensionFilter("GIF (.*gif)", "gif");

	public FileChooser() {
		setPreferredSize(new Dimension(600,600));
		setAcceptAllFileFilterUsed(false);
        setFileFilter(images);

        addChoosableFileFilter(jpg);
        addChoosableFileFilter(jpeg);
        addChoosableFileFilter(png);
        addChoosableFileFilter(gif);
	}

	@Override
    public void approveSelection(){
        File f = getSelectedFile();
        String fileExtension = getSelectedFileExtension();;
        String filterExtension = getFilterExtension();

	    if(fileExtension == null) {
	    	if(filterExtension == "images") {
	    		fileExtension = defaultExtension;
	    	}else {
	    		fileExtension = filterExtension;
	    	}
	    	f = new File(f.getPath() + "." +fileExtension);
	    	setSelectedFile(f);
	    }else if(filterExtension!="images" && fileExtension != filterExtension) {

	    	String path = f.getPath();
	    	path = path.substring(0,path.lastIndexOf("."));
	    	f = new File(path + "." + filterExtension);
	    	setSelectedFile(f);
	    }

        if(getDialogType() == SAVE_DIALOG){
        	if(f.exists() && f.isFile()) {
        		int result = JOptionPane.showConfirmDialog(this,"ファイルが存在しています、上書きしますか？","ファイルが存在しています",JOptionPane.YES_NO_CANCEL_OPTION);
                switch(result){
                    case JOptionPane.YES_OPTION:
                        super.approveSelection();
                        JOptionPane.showMessageDialog(null, f.getName() + "として保存しました！", "",JOptionPane.PLAIN_MESSAGE);
                        return;
                    case JOptionPane.NO_OPTION:
                        return;
                    case JOptionPane.CLOSED_OPTION:
                        return;
                    case JOptionPane.CANCEL_OPTION:
                        cancelSelection();
                        return;
                }
        	}else {
            	JOptionPane.showMessageDialog(null, f.getName() + "として保存しました！", "",JOptionPane.PLAIN_MESSAGE);
            }
        }
        super.approveSelection();
    }

	public String getSelectedFileExtension() {
		String filename = getSelectedFile().getPath();

		try {
			String extension = null;
			if(filename.contains("."))
				extension = filename.substring(filename.lastIndexOf(".")+1);

			return extension;
		} catch (NullPointerException e) {
			return null;
		}

	}

	public String getFilterExtension() {
		String s = getFileFilter().getDescription();
		String result = null;
		switch (s) {
		case "JPG (*.jpg)":
			result = "jpg";
			break;
		case "GIF (.*gif)":
			result = "gif";
			break;
		case "PNG (*.png)":
			result = "png";
			break;
		case "JPEG (*jpeg)":
			result = "jpeg";
			break;
		default:
			result = "images";
			break;
		}
		return result;
	}
}
