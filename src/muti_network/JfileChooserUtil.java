package muti_network;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class JfileChooserUtil {
	private static String what;
	private static String path;
	public static String getPath() {
		return path;
	}

	public void setPath(String path) {
		JfileChooserUtil.path = path;
	}

	public static String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		JfileChooserUtil.what = what;
	}

	public static String jFileChooserUtil() {

		String folderPath = "";

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
		chooser.setCurrentDirectory(new File(getPath())); // 현재 사용 디렉토리를 지정
		chooser.setAcceptAllFileFilterUsed(true); // Fileter 모든 파일 적용
		chooser.setDialogTitle(getWhat()); // 창의 제목
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // 파일 선택 모드

		// FileNameExtensionFilter filter = new FileNameExtensionFilter("폴더",""); //
		// filter 확장자 추가
		// chooser.setFileFilter(filter); // 파일 필터를 추가

		int returnVal = chooser.showOpenDialog(null); // 열기용 창 오픈

		if (returnVal == JFileChooser.APPROVE_OPTION) { // 열기를 클릭
			folderPath = chooser.getSelectedFile().toString();
		} else if (returnVal == JFileChooser.CANCEL_OPTION) { // 취소를 클릭
			System.out.println("cancel");
			folderPath = "";
		}

		return folderPath;

	}
	public static String jFileChooserUtilfile() {

		String folderPath = "";
		System.out.println(getPath());
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
		chooser.setCurrentDirectory(new File(getPath())); // 현재 사용 디렉토리를 지정
		chooser.setAcceptAllFileFilterUsed(true); // Fileter 모든 파일 적용
		chooser.setDialogTitle(getWhat()); // 창의 제목
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // 파일 선택 모드

		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt 파일","txt"); //
		// filter 확장자 추가
		chooser.setFileFilter(filter); // 파일 필터를 추가

		int returnVal = chooser.showOpenDialog(null); // 열기용 창 오픈

		if (returnVal == JFileChooser.APPROVE_OPTION) { // 열기를 클릭
			folderPath = chooser.getSelectedFile().toString();
		} else if (returnVal == JFileChooser.CANCEL_OPTION) { // 취소를 클릭
			System.out.println("cancel");
			folderPath = "";
		}

		return folderPath;

	}
	/*
	 * public static void main(String[] args) { JfileChooserUtil fcu = new
	 * JfileChooserUtil(); String str = fcu.jFileChooserUtil();
	 * System.out.println(str); }
	 */
}
