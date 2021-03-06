package muti_network;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollBar;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;

public class Memogui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	File txtfile = new File(".");
	File audiofile = new File(".");
	public static Filelist fl = new Filelist();
	ImageIcon folder = new ImageIcon("icon/search.png");

	String txtfilesearch = txtfile.getAbsolutePath();

	public String getTxtfilesearch() {
		return txtfilesearch;
	}

	public void setTxtfilesearch(String txtfilesearch) {
		this.txtfilesearch = txtfilesearch;
	}

	String audiofilesearch = audiofile.getAbsolutePath();
	private JTextField textField_3;
	private JTextField textField_4;

	public String getAudiofilesearch() {
		return audiofilesearch;
	}

	public void setAudiofilesearch(String audiofilesearch) {
		this.audiofilesearch = audiofilesearch;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				fl = new Filelist();
				fl.getConfig();
				try {
					Memogui frame = new Memogui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Memogui() {
		setFont(new Font("나눔고딕", Font.PLAIN, 12));
		setTitle("Memo-to-Speech");
		TTS tts = new TTS();
		String headhint = "제목";
		String mainhint = "본문";
		fl.setConfigheadsize(headhint);
		fl.setConfigmainsize(mainhint);
		Font gainfont = new Font("나눔고딕", Font.PLAIN, 22);
		Font lostfont = new Font("나눔고딕", Font.ITALIC, 22);
		Font gainfontmain = new Font("나눔고딕", Font.PLAIN, 22);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(364, 0)); //창 최소크기 상황따라서제거할수도..
		setBounds(100, 100, 364, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 4, 4, 4);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		contentPane.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("메모",
				new ImageIcon(Memogui.class
						.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Paste-Black.png")),
				panel_1, null);
		textField = new JTextField();
		textField.setText(headhint);
		textField.setFont(lostfont);
		textField.setForeground(Color.GRAY);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(headhint)) {
					textField.setText("");
					textField.setFont(gainfont);
					textField.setForeground(Color.BLACK);
				} else if (textField.getText().length() == 0) {
					textField.setText(headhint);
					textField.setFont(lostfont);
					textField.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().equals(headhint) || textField.getText().length() == 0) {
					textField.setText(headhint);
					textField.setFont(lostfont);
					textField.setForeground(Color.GRAY);
				} else {
					textField.setText(textField.getText());
					textField.setFont(gainfont);
					textField.setForeground(Color.BLACK);
				}
			}
		});
		panel_1.setLayout(new BorderLayout(0, 0));
		textField.setToolTipText("");
		panel_1.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(scrollPane_1);

		JTextArea textArea = new JTextArea();
		textArea.setText(mainhint);
		textArea.setFont(lostfont);
		textArea.setForeground(Color.GRAY);
		textArea.setLineWrap(true);
		textArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textArea.getText().equals(mainhint)) {
					textArea.setText("");
					textArea.setFont(gainfontmain);
					;
					textArea.setForeground(Color.BLACK);
				} else if (textArea.getText().length() == 0) {
					textArea.setText(mainhint);
					textArea.setFont(lostfont);
					textArea.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textArea.getText().equals(mainhint) || textArea.getText().length() == 0) {
					textArea.setText(mainhint);
					textArea.setFont(lostfont);
					textArea.setForeground(Color.GRAY);
				} else {
					textArea.setText(textArea.getText());
					textArea.setFont(gainfontmain);
					textArea.setForeground(Color.BLACK);
				}
			}
		});
		scrollPane_1.setViewportView(textArea);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);

		JButton btnNewButton_3 = new JButton("음성저장");
		btnNewButton_3.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btnNewButton_3.setIcon(
				new ImageIcon(Memogui.class.getResource("/jaco/mp3/player/plaf/resources/mp3PlayerSoundOn.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tts.setInput(textField.getText() + textArea.getText());
				TTS.setOutputfilename(textField.getText());
				tts.setAudiosavepath(fl.getConfigtoaudiopath());
				try {
					tts.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnNewButton_6 = new JButton("불러오기");
		btnNewButton_6.setToolTipText("컴퓨터에 저장된 txt파일을 불러옵니다.");
		btnNewButton_6.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btnNewButton_6.setIcon(
				new ImageIcon(Memogui.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JfileChooserUtil jcu = new JfileChooserUtil();
				Filelist fl = new Filelist();
				fl.getConfig();
				jcu.setPath(fl.getConfigtotxtpath());
				jcu.setWhat("불러올 메모파일을 선택해주세요");
				String str = jcu.jFileChooserUtilfile();
				if (str.equals("")) {
					System.out.println("취소하셨습니다");
					if (textField.getText().equals("제목") && textArea.getText().equals("본문")) {
						textField.setFont(lostfont);
						textField.setForeground(Color.GRAY);
						textArea.setFont(lostfont);
						textArea.setForeground(Color.GRAY);
					}
				} else {
					textField.setFont(gainfont);
					textField.setForeground(Color.BLACK);
					textArea.setFont(gainfontmain);
					textArea.setForeground(Color.BLACK);
					fl.gettxt(str);
					textField.setText(fl.getFirstline());
					textArea.setText(fl.secondingline);
				}

			}
		});
		panel_2.add(btnNewButton_6);
		panel_2.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("텍스트저장");
		btnNewButton_4.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btnNewButton_4.setIcon(
				new ImageIcon(Memogui.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		btnNewButton_4.addActionListener(new ActionListener() {
			private OutputStream output;

			public void actionPerformed(ActionEvent e) {
				String savename = textField.getText() + ".txt";

				try {
					output = new FileOutputStream(fl.getConfigtotxtpath() + "\\" + textField.getText() + ".txt");

					String savestring = textField.getText() + "\n" + textArea.getText();
					byte[] savebytearray = savestring.getBytes();
					try {

						output.write(savebytearray);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(btnNewButton_4);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("저장경로",
				new ImageIcon(Memogui.class.getResource("/jaco/mp3/player/plaf/resources/mp3PlayerShuffle.png")),
				panel_3, null);
		panel_3.setLayout(new GridLayout(5, 1, 0, 0));

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("메모파일 경로 : ");
		lblNewLabel.setToolTipText("텍스트파일이 저장될 경로입니다.");
		lblNewLabel.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_4.add(lblNewLabel, BorderLayout.WEST);

		textField_1 = new JTextField(fl.getConfigtotxtpath());
		textField_1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_4.add(textField_1, BorderLayout.CENTER);
		textField_1.setColumns(10);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1
				.setIcon(new ImageIcon(Memogui.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JfileChooserUtil jcu = new JfileChooserUtil();
				Filelist fl = new Filelist();
				fl.getConfig();

				jcu.setWhat("텍스트파일 저장경로를 설정해주세요");
				jcu.setPath(fl.getConfigtotxtpath());
				String str = JfileChooserUtil.jFileChooserUtil();
				if (str.equals("")) {

				} else {
					textField_1.setText(str);
				}
			}
		});
		panel_4.add(btnNewButton_1, BorderLayout.EAST);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("음성파일 경로 : ");
		lblNewLabel_1.setToolTipText("음성파일(.mp3)이 저장될 경로입니다.");
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_1, BorderLayout.WEST);

		textField_2 = new JTextField(fl.getConfigtoaudiopath());
		textField_2.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_5.add(textField_2, BorderLayout.CENTER);
		textField_2.setColumns(10);

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5
				.setIcon(new ImageIcon(Memogui.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Filelist fl = new Filelist();
				fl.getConfig();

				JfileChooserUtil jcu = new JfileChooserUtil();
				jcu.setPath(fl.getConfigtoaudiopath());
				jcu.setWhat("음성파일 저장경로를 설정해주세요");
				String str = JfileChooserUtil.jFileChooserUtil();
				if (str.equals("")) {

				} else {
					textField_2.setText(str);
				}
			}
		});
		panel_5.add(btnNewButton_5, BorderLayout.EAST);
		
		JPanel panel_6_1 = new JPanel();
		panel_3.add(panel_6_1);
		panel_6_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1_1 = new JLabel("제목크기 : ");
		lblNewLabel_1_1.setToolTipText("설정창의 글씨크기는 본문의 글씨크기와 같습니다.");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_6_1.add(lblNewLabel_1_1, BorderLayout.WEST);
		
		textField_3 = new JTextField((String) null);
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_3.setColumns(10);
		panel_6_1.add(textField_3, BorderLayout.CENTER);
		
		JPanel panel_6_1_1 = new JPanel();
		panel_3.add(panel_6_1_1);
		panel_6_1_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("본문크기 : ");
		lblNewLabel_1_1_1.setToolTipText("설정창의 글씨크기는 본문의 글씨크기와 같습니다.");
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_6_1_1.add(lblNewLabel_1_1_1, BorderLayout.WEST);
		
		textField_4 = new JTextField((String) null);
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_4.setColumns(10);
		panel_6_1_1.add(textField_4, BorderLayout.CENTER);

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("경로를 초기화 하시려면 초기화를 누른후 저장을 한번 눌러주세요");
		lblNewLabel_2.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		lblNewLabel_2
				.setIcon(new ImageIcon(Memogui.class.getResource("/com/sun/java/swing/plaf/windows/icons/Warn.gif")));
		panel_6.add(lblNewLabel_2, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		panel_6.add(panel, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("설정 저장");
		btnNewButton.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel.add(btnNewButton);
		btnNewButton.setIcon(
				new ImageIcon(Memogui.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));

		JButton btnNewButton_2 = new JButton("초기화");
		btnNewButton_2.setToolTipText("경로를 초기화 하시려면 초기화를 누른후 저장을 한번 눌러주세요");
		btnNewButton_2.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel.add(btnNewButton_2);
		btnNewButton_2
				.setIcon(new ImageIcon(Memogui.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(txtfilesearch);
				textField_2.setText(audiofilesearch);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			private OutputStream output;

			public void actionPerformed(ActionEvent e) {
				try {
					output = new FileOutputStream("." + "memconfig");
					String str = "메모파일 경로 : " + textField_1.getText() + "\n" + "음성파일 경로 : " + textField_2.getText();
					byte[] configby = str.getBytes();
					try {
						output.write(configby);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fl.getConfig();

			}
		});

	}
}
