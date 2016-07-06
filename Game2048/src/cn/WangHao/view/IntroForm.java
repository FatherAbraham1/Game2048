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
		setTitle("��Ϸ˵��");
		setBounds(100, 100, 397, 304);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, "Center");
		this.contentPanel.setLayout(null);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 212, 381, 33);
		this.contentPanel.add(buttonPane);
		buttonPane.setLayout(new FlowLayout(1));

		JButton okButton = new JButton("ȷ��");
		okButton.setFocusable(false);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntroForm.this.dispose();
			}
		});
		okButton.setFont(new Font("������", 0, 14));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JLabel lblNewLabel = new JLabel("����ָ�ϣ�");
		lblNewLabel.setFont(new Font("������", 0, 12));
		lblNewLabel.setBounds(39, 30, 65, 15);
		this.contentPanel.add(lblNewLabel);

		JTextArea txtIntro = new JTextArea();
		txtIntro.setFont(new Font("����", 0, 14));
		txtIntro.setEditable(false);
		txtIntro.setBackground(new Color(245, 245, 245));
		txtIntro.setWrapStyleWord(true);
		txtIntro.setLineWrap(true);
		txtIntro.setText("��4X4�������ϣ�ÿ�λ�����һ�����֣������ѡ���ĸ�����Ȼ�����ֻᰴ�����ƶ���������ͬ�����־ͻ�ϲ�����Ϸ��Ŀ�ľ��Ǻϲ���2048������֣����տ������˶����ܷ֡�\r\n2048Ҳ����2��11�η������ϲ�11�Ρ�");
		txtIntro.setBounds(39, 55, 296, 133);
		this.contentPanel.add(txtIntro);

	}

}
