package cn.WangHao.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import res.ResTool;

public class AboutFrom extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField wanghao;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AboutFrom dialog = new AboutFrom();
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
	public AboutFrom() {
		//setBounds(100, 100, 450, 300);

		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().createImage(
				ResTool.getImage("about.png")));
		setTitle("关于");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, "Center");
		this.contentPanel.setLayout(null);

		Label label = new Label("作者：");
		label.setBounds(182, 56, 42, 23);
		this.contentPanel.add(label);

		this.wanghao = new JTextField();
		this.wanghao.setHorizontalAlignment(0);
		this.wanghao.setEditable(false);
		this.wanghao.setText("wanghao");
		this.wanghao.setBounds(230, 56, 98, 22);
		this.contentPanel.add(this.wanghao);
		this.wanghao.setColumns(10);

		Label label_1 = new Label("学号：");
		label_1.setBounds(182, 103, 42, 23);
		this.contentPanel.add(label_1);

		this.textField = new JTextField();
		this.textField.setEditable(false);
		this.textField.setHorizontalAlignment(0);
		this.textField.setText("1341310728");
		this.textField.setBounds(230, 105, 98, 22);
		this.contentPanel.add(this.textField);
		this.textField.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 190, 434, 36);
		this.contentPanel.add(buttonPane);
		buttonPane.setLayout(new FlowLayout(1));

		JButton okButton = new JButton("确定");
		okButton.setFocusable(false);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutFrom.this.dispose();
			}
		});
		okButton.setFont(new Font("新宋体", 0, 14));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(ResTool.getImage("logo-L.png")));
		label_2.setBounds(99, 56, 73, 69);
		this.contentPanel.add(label_2);
	}

}
