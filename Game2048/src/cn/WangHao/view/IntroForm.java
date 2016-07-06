package cn.WangHao.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import res.ResTool;

public class IntroForm extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			IntroForm dialog = new IntroForm();
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setDefaultCloseOperation(2);
			
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public IntroForm() {
		//setBounds(100, 100, 450, 300);

		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().createImage(
				ResTool.getImage("intro.png")));
		setTitle("游戏说明");
		setBounds(100, 100, 397, 304);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, "Center");
		this.contentPanel.setLayout(null);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 212, 381, 33);
		this.contentPanel.add(buttonPane);
		buttonPane.setLayout(new FlowLayout(1));

		JButton okButton = new JButton("确定");
		okButton.setFocusable(false);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntroForm.this.dispose();
			}
		});
		okButton.setFont(new Font("新宋体", 0, 14));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JLabel lblNewLabel = new JLabel("操作指南：");
		lblNewLabel.setFont(new Font("新宋体", 0, 12));
		lblNewLabel.setBounds(39, 30, 65, 15);
		this.contentPanel.add(lblNewLabel);

		JTextArea txtIntro = new JTextArea();
		txtIntro.setFont(new Font("黑体", 0, 14));
		txtIntro.setEditable(false);
		txtIntro.setBackground(new Color(245, 245, 245));
		txtIntro.setWrapStyleWord(true);
		txtIntro.setLineWrap(true);
		txtIntro.setText("在4X4的棋盘上，每次会增加一个数字，你可以选择四个方向，然后数字会按方向移动，遇到相同的数字就会合并，游戏的目的就是合并出2048这个数字，最终看你获得了多少总分。\r\n2048也就是2的11次方，即合并11次。");
		txtIntro.setBounds(39, 55, 296, 133);
		this.contentPanel.add(txtIntro);

	}

}
