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
import java.awt.Component;

public class Memogui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	File txtfile = new File(".");
	File audiofile = new File(".");
	public static Filelist fl;
	
	String txtfilesearch = txtfile.getAbsolutePath();
	public String getTxtfilesearch() {
		return txtfilesearch;
	}

	public void setTxtfilesearch(String txtfilesearch) {
		this.txtfilesearch = txtfilesearch;
	}

	String audiofilesearch = audiofile.getAbsolutePath();

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
		TTS tts = new TTS();
		String headhint = "제목";
		String mainhint = "본문";

		Font gainfont = new Font("나눔고딕", Font.PLAIN, 18);
		Font lostfont = new Font("나눔고딕", Font.ITALIC, 18);
		Font gainfontmain = new Font("나눔고딕", Font.PLAIN, 20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("목록", null, scrollPane, null);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("메모", null, panel_1, null);
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
		panel_1.add(scrollPane_1);

		JTextArea textArea = new JTextArea();
		textArea.setText(mainhint);
		textArea.setFont(lostfont);
		textArea.setForeground(Color.GRAY);
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
		panel_2.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("저장");
		btnNewButton_4.addActionListener(new ActionListener() {
			private OutputStream output;

			public void actionPerformed(ActionEvent e) {
				String savename = textField.getText() + ".txt";

				try {
					output = new FileOutputStream(fl.getConfigtotxtpath()+"\\"+textField.getText()+".txt");

					String savestring = "제목 : " + textField.getText() + "\n" + "본문 : " + textArea.getText();
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
		tabbedPane.addTab("저장경로", null, panel_3, null);
		panel_3.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

		JLabel lblNewLabel = new JLabel("메모파일 경로 : ");
		panel_4.add(lblNewLabel);

		textField_1 = new JTextField(fl.getConfigtotxtpath());
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("설정하기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JfileChooserUtil jcu = new JfileChooserUtil();
				jcu.setWhat("텍스트파일 저장경로를 설정해주세요");
				String str = JfileChooserUtil.jFileChooserUtil();
				textField_1.setText(str);
			}
		});
		panel_4.add(btnNewButton_1);
	

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

		JLabel lblNewLabel_1 = new JLabel("음성파일 경로 : ");
		panel_5.add(lblNewLabel_1);

		textField_2 = new JTextField(fl.getConfigtoaudiopath());
		panel_5.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("설정하기");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JfileChooserUtil jcu = new JfileChooserUtil();
				jcu.setWhat("텍스트파일 저장경로를 설정해주세요");
				String str = JfileChooserUtil.jFileChooserUtil();
				textField_2.setText(str);
			}
		});
		panel_5.add(btnNewButton_5);

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
				JButton btnNewButton = new JButton("설정 저장");
				panel_6.add(btnNewButton);
				btnNewButton.addActionListener(new ActionListener() {
					private OutputStream output;

					public void actionPerformed(ActionEvent e) {
						try {
							output = new FileOutputStream("."+"memconfig");
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

		JButton btnNewButton_2 = new JButton("초기화");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(txtfilesearch);
				textField_2.setText(audiofilesearch);
			}
		});
		panel_6.add(btnNewButton_2);

	}
}
