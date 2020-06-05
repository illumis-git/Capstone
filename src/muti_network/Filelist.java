package muti_network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class Filelist {
	String[] filename;
	String[] fileread;
	String configtotxtpath;
	String configtoaudiopath;

	public String getConfigtotxtpath() {
		return configtotxtpath;
	}

	public void setConfigtotxtpath(String configtotxtpah) {
		this.configtotxtpath = configtotxtpah;
	}

	public String getConfigtoaudiopath() {
		return configtoaudiopath;
	}

	public void setConfigtoaudiopath(String configtoaudiopath) {
		this.configtoaudiopath = configtoaudiopath;
	}

	int k = 0;

	public String getFilename(int i) {
		return filename[i];
	}

	public void setFilename(int i, String filename) {
		this.filename[i] = filename;
	}

	public String getFileread(int i) {
		return fileread[i];
	}

	public void setFileread(int i, String fileread) {
		this.fileread[i] = fileread;
	}

	public void subDirList(String source) {

		File dir = new File(source);

		File[] fileList = dir.listFiles();

		try {

			for (int i = 0; i < fileList.length; i++) {

				File file = fileList[i];
				filename = new String[fileList.length];
				fileread = new String[fileList.length];

				if (file.isFile()) {
					if (file.getName().contains(".txt")) {
						// 파일이 있다면 파일 이름 출력
						setFilename(k, file.getName() + "아마 하나만 나오겠지?");
						setFileread(k, file.getAbsolutePath());
						System.out.println(getFilename(k));
						System.out.println(getFileread(k));
						k++;
					}
					// System.out.println("\t 파일 이름 = " + file.getName());
					// System.out.println("\t 파일 경로= " + file.getAbsolutePath());

				} /*
					 * else if (file.isDirectory()) {
					 * 
					 * System.out.println("디렉토리 이름 = " + file.getName());
					 * 
					 * // 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					 * 
					 * subDirList(file.getCanonicalPath().toString());
					 * 
					 * }
					 */

			}

		} catch (Exception e) {
			System.out.println("파일이없습니다.");
		}

	}

//출처: https://ra2kstar.tistory.com/133 [초보개발자 이야기.]
	public void getConfig() {
		File defaultpath = new File(".");
		String line = "";
		OutputStream output = null;
		try {

			File configpath = new File("." + "memconfig");
			FileReader filereader = new FileReader(configpath);
			BufferedReader bufReader = new BufferedReader(filereader);
			while ((line = bufReader.readLine()) != null) {
				if (line.contains("메모파일 경로 : ")) {
					setConfigtotxtpath(line);
				}
				if (line.contains("음성파일 경로 : ")) {
					setConfigtoaudiopath(line);
				}
			}
			// .readLine()은 끝에 개행문자를 읽지 않는다.
			bufReader.close();

			// 출처: https://jeong-pro.tistory.com/69 [기본기를 쌓는 정아마추어 코딩블로그]

		} catch (IOException e) {
			try {
				output = new FileOutputStream("." + "memconfig");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String str = "메모파일 경로 : " + defaultpath.getAbsolutePath() + "\n" + "음성파일 경로 : "
					+ defaultpath.getAbsolutePath();
			byte[] configby = str.getBytes();
			try {
				output.write(configby);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	/*
	 * public static void main(String[] args) { // TODO Auto-generated method stub
	 * Filelist fl = new Filelist(); String source = "."; fl.getConfig();
	 * fl.getConfig(); }
	 */

}
