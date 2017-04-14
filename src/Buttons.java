package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Buttons {
	File file;

	private int label = 0;
	private File[] songList = new File[9];

	public void GUI() {
		JButton button = new JButton("Choose a File");
		button.setBounds(20, 20, 200, 30);
		JButton play = new JButton("Play");
		play.setBounds(270, 20, 100, 30);
		JButton pause = new JButton("Pause");
		pause.setBounds(370, 20, 100, 30);
		JButton stop = new JButton("Stop");
		stop.setBounds(470, 20, 100, 30);
		JPanel panel = new JPanel();

		JButton playFile1 = new JButton("Play: ");
		playFile1.setBounds(20, 50, 100, 30);
		JButton playFile2 = new JButton("Play: ");
		playFile2.setBounds(20, 80, 100, 30);
		JButton playFile3 = new JButton("Play: ");
		playFile3.setBounds(20, 110, 100, 30);
		JButton playFile4 = new JButton("Play: ");
		playFile4.setBounds(20, 140, 100, 30);
		JButton playFile5 = new JButton("Play: ");
		playFile5.setBounds(20, 170, 100, 30);
		JButton playFile6 = new JButton("Play: ");
		playFile6.setBounds(20, 200, 100, 30);
		JButton playFile7 = new JButton("Play: ");
		playFile7.setBounds(20, 230, 100, 30);
		JButton playFile8 = new JButton("Play: ");
		playFile8.setBounds(20, 260, 100, 30);

		JFrame frame = new JFrame();

		panel.setLayout(null);

		panel.add(button);
		panel.add(play);
		panel.add(pause);
		panel.add(stop);
		panel.add(playFile1);
		panel.add(playFile2);
		panel.add(playFile3);
		panel.add(playFile4);
		panel.add(playFile5);
		panel.add(playFile6);
		panel.add(playFile7);
		panel.add(playFile8);

		frame.add(panel);
		frame.setSize(650, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JLabel label1 = new JLabel();
		label1.setBounds(125, 50, 1000, 30);
		panel.add(label1);

		JLabel label2 = new JLabel();
		label2.setBounds(125, 80, 1000, 30);
		panel.add(label2);

		JLabel label3 = new JLabel();
		label3.setBounds(125, 110, 1000, 30);
		panel.add(label3);

		JLabel label4 = new JLabel();
		label4.setBounds(125, 140, 1000, 30);
		panel.add(label4);

		JLabel label5 = new JLabel();
		label5.setBounds(125, 170, 1000, 30);
		panel.add(label5);

		JLabel label6 = new JLabel();
		label6.setBounds(125, 200, 1000, 30);
		panel.add(label6);

		JLabel label7 = new JLabel();
		label7.setBounds(125, 230, 1000, 30);
		panel.add(label7);

		JLabel label8 = new JLabel();
		label8.setBounds(125, 260, 1000, 30);
		panel.add(label8);

		class AddListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File chosen = chooser.getSelectedFile();
					file = chosen;
					String selectedFile = chosen.getName();
					try {
						InetAddress[] addresses = new InetAddress[1];
						addresses[0] = InetAddress.getByName("localhost");
						//addresses[1] = InetAddress.getByName("");
						UserUtil.uploadFile(addresses, chosen);
					} catch (Exception e) {
						e.printStackTrace();
					}
					/*
					 * 'file' stores the selected file. Call the method that
					 * sends the selected file to the Pi and use 'file'.
					 */

					if (label == 8) {
						label = 0;
					}

					if (label == 0) {
						label1.setText(selectedFile);
						panel.add(label1);

						label++;
						songList[label] = file;
					} else if (label == 1) {
						label2.setText(selectedFile);
						panel.add(label2);

						label++;
						songList[label] = file;
					} else if (label == 2) {
						label3.setText(selectedFile);
						panel.add(label3);

						label++;
						songList[label] = file;
					} else if (label == 3) {
						label4.setText(selectedFile);
						panel.add(label4);

						label++;
						songList[label] = file;
					} else if (label == 4) {
						label5.setText(selectedFile);
						panel.add(label5);

						label++;
						songList[label] = file;
					} else if (label == 5) {
						label6.setText(selectedFile);
						panel.add(label6);

						label++;
						songList[label] = file;
					} else if (label == 6) {
						label7.setText(selectedFile);
						panel.add(label7);

						label++;
						songList[label] = file;
					} else if (label == 7) {
						label8.setText(selectedFile);
						panel.add(label8);

						label++;
						songList[label] = file;
					}
				}
			}
		}
		ActionListener listener = new AddListener();
		button.addActionListener(listener);

		class AddListenerPlay implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Play");
			}
		}
		ActionListener listenerPlay = new AddListenerPlay();
		play.addActionListener(listenerPlay);

		class AddListenerPause implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Pause");
			}
		}
		ActionListener listenerPause = new AddListenerPause();
		pause.addActionListener(listenerPause);

		class AddListenerStop implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Stop");
			}
		}
		ActionListener listenerStop = new AddListenerStop();
		stop.addActionListener(listenerStop);

		class AddListenerPlay1 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println(songList[1]);
				try {
					UserUtil.playFile(songList[1].getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				/*
				 * The array songList stores the files that correspond to each
				 * button. This is where you would call the method to play the
				 * uploaded file already in the Pi. for this listener, you would
				 * use songList[1] for the file that you want to play.
				 */
			}
		}
		ActionListener listenerPlay1 = new AddListenerPlay1();
		playFile1.addActionListener(listenerPlay1);

		class AddListenerPlay2 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println(songList[2]);
				try {
					UserUtil.playFile(songList[2].getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ActionListener listenerPlay2 = new AddListenerPlay2();
		playFile2.addActionListener(listenerPlay2);

		class AddListenerPlay3 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println(songList[3]);
				try {
					UserUtil.playFile(songList[3].getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ActionListener listenerPlay3 = new AddListenerPlay3();
		playFile3.addActionListener(listenerPlay3);

		class AddListenerPlay4 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println(songList[4]);
				try {
					UserUtil.playFile(songList[4].getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ActionListener listenerPlay4 = new AddListenerPlay4();
		playFile4.addActionListener(listenerPlay4);

		class AddListenerPlay5 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println(songList[5]);
				try {
					UserUtil.playFile(songList[5].getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ActionListener listenerPlay5 = new AddListenerPlay5();
		playFile5.addActionListener(listenerPlay5);

		class AddListenerPlay6 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println(songList[6]);
				try {
					UserUtil.playFile(songList[6].getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ActionListener listenerPlay6 = new AddListenerPlay6();
		playFile6.addActionListener(listenerPlay6);

		class AddListenerPlay7 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println(songList[7]);
				try {
					UserUtil.playFile(songList[7].getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ActionListener listenerPlay7 = new AddListenerPlay7();
		playFile7.addActionListener(listenerPlay7);

		class AddListenerPlay8 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println(songList[8]);
				try {
					UserUtil.playFile(songList[8].getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ActionListener listenerPlay8 = new AddListenerPlay8();
		playFile8.addActionListener(listenerPlay8);
	}

	public File getFile() {
		return file;
	}
}