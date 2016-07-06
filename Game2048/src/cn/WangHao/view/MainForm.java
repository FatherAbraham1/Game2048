package cn.WangHao.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import res.ResTool;
import cn.WangHao.ai.AIComplete;
import cn.WangHao.game.Desktop;
import cn.WangHao.listener.GameKeyListener;
import cn.WangHao.listener.MenuItemActionListener;
import cn.WangHao.listener.MenuItemActionListener.MenuType;
import cn.WangHao.listener.MouseHoverAdapter;

public class MainForm {

	private JFrame frame;
	private JDialog aboutDialog;
	private JDialog introDialog;
	private Desktop desktop;
	private JButton btnAi;
	private JPanel gameResPanel;
	private JLabel lblGameOver;

	public Desktop getDesktop() {
		return this.desktop;
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public MainForm() {
		initialize();
	}
	
	
	 private void initialize()
	  {
	    this.frame = new JFrame();
	    this.frame.setResizable(false);
	    this.frame.setIconImage(Toolkit.getDefaultToolkit().createImage(ResTool.getImage("logo-S.gif")));
	    this.frame.setTitle("2048");
	    this.frame.setBounds(100, 100, 560, 605);
	    this.frame.setDefaultCloseOperation(3);
	    this.frame.setLocationRelativeTo(null);

	    JMenuBar menuBar = new JMenuBar();
	    this.frame.setJMenuBar(menuBar);

	    JMenu menuSelect = new JMenu("选项(S)");
	    menuSelect.addMouseListener(new MouseHoverAdapter());
	    menuSelect.setMnemonic('S');
	    menuSelect.setFont(new Font("新宋体", 0, 14));
	    menuBar.add(menuSelect);

	    JMenuItem menuStart = new JMenuItem("重新开始(N)");
	    menuStart.setIcon(new ImageIcon(ResTool.getImage("replay.png")));
	    menuStart.addActionListener(new MenuItemActionListener(MenuItemActionListener.MenuType.START, this));
	    menuStart.setFont(new Font("新宋体", 0, 14));
	    menuStart.setMnemonic('N');
	    menuSelect.add(menuStart);

	    JMenuItem menuExit = new JMenuItem("退出游戏(E)");
	    menuExit.setIcon(new ImageIcon(ResTool.getImage("exit.png")));
	    menuExit.addActionListener(new MenuItemActionListener(MenuItemActionListener.MenuType.EXIT));
	    menuExit.setMnemonic('E');
	    menuExit.setFont(new Font("新宋体", 0, 14));
	    menuSelect.add(menuExit);

	    JMenu menuHelp = new JMenu("帮助(H)");
	    menuHelp.addMouseListener(new MouseHoverAdapter());
	    menuHelp.setMnemonic('H');
	    menuHelp.setFont(new Font("新宋体", 0, 14));
	    menuBar.add(menuHelp);

	    JMenuItem menuIntro = new JMenuItem("游戏说明(I)");
	    menuIntro.setIcon(new ImageIcon(ResTool.getImage("intro.png")));
	    menuIntro.addActionListener(new MenuItemActionListener(MenuItemActionListener.MenuType.INTRO, this));
	    menuIntro.setMnemonic('I');
	    menuIntro.setFont(new Font("新宋体", 0, 14));
	    menuHelp.add(menuIntro);

	    JMenuItem menuAbout = new JMenuItem("关于(A)");
	    menuAbout.setIcon(new ImageIcon(ResTool.getImage("about.png")));
	    menuAbout.setMnemonic('A');
	    menuAbout.addActionListener(new MenuItemActionListener(MenuItemActionListener.MenuType.ABOUT, this));
	    menuAbout.setFont(new Font("新宋体", 0, 14));
	    menuHelp.add(menuAbout);
	    this.frame.getContentPane().setLayout(null);

	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(220, 220, 220));
	    panel.setBounds(0, 0, 556, 38);
	    this.frame.getContentPane().add(panel);
	    panel.setLayout(null);

	    JButton buttonStart = new JButton("重新开始");
	    buttonStart.setBounds(0, 0, 102, 38);
	    panel.add(buttonStart);
	    buttonStart.setToolTipText("");
	    buttonStart.setMargin(new Insets(6, 6, 6, 6));
	    buttonStart.setIcon(new ImageIcon(ResTool.getImage("replay.png")));
	    buttonStart.addActionListener(new MenuItemActionListener(MenuItemActionListener.MenuType.START, this));
	    buttonStart.setFocusable(false);
	    buttonStart.setCursor(Cursor.getPredefinedCursor(0));
	    buttonStart.setBorder(UIManager.getBorder("Button.border"));
	    buttonStart.setFont(new Font("新宋体", 0, 15));

	    JLabel labelScore = new JLabel("00000");
	    labelScore.setOpaque(true);
	    labelScore.setBackground(Color.LIGHT_GRAY);
	    labelScore.setHorizontalAlignment(0);
	    labelScore.setFont(new Font("微软雅黑", 0, 20));
	    labelScore.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
	    labelScore.setBounds(452, 0, 102, 38);
	    panel.add(labelScore);

	    JButton btntrace = new JButton("调试");
	    btntrace.setMargin(new Insets(6, 6, 6, 6));
	    btntrace.setFont(new Font("新宋体", 0, 15));
	    btntrace.setFocusable(false);
	    btntrace.setBounds(211, 0, 69, 38);
	    panel.add(btntrace);
	    //RadiusJPanel;
	    JPanel panelDesk = new RadiusJPanel();
	    panelDesk.setBackground(new Color(187, 173, 160));
	    panelDesk.setBounds(52, 64, 450, 450);
	    this.frame.getContentPane().add(panelDesk);
	    panelDesk.setLayout(null);

	    JLabel label = new BlockBgLabel();
	    label.setBounds(10, 10, 100, 100);
	    panelDesk.add(label);

	    JLabel label_1 = new BlockBgLabel();
	    label_1.setBounds(120, 10, 100, 100);
	    panelDesk.add(label_1);

	    JLabel label_2 = new BlockBgLabel();
	    label_2.setBounds(230, 10, 100, 100);
	    panelDesk.add(label_2);

	    JLabel label_3 = new BlockBgLabel();
	    label_3.setBounds(340, 10, 100, 100);
	    panelDesk.add(label_3);

	    JLabel label_4 = new BlockBgLabel();
	    label_4.setBounds(10, 120, 100, 100);
	    panelDesk.add(label_4);

	    JLabel label_5 = new BlockBgLabel();
	    label_5.setBounds(120, 120, 100, 100);
	    panelDesk.add(label_5);

	    JLabel label_6 = new BlockBgLabel();
	    label_6.setBounds(230, 120, 100, 100);
	    panelDesk.add(label_6);

	    JLabel label_7 = new BlockBgLabel();
	    label_7.setBounds(340, 120, 100, 100);
	    panelDesk.add(label_7);

	    JLabel label_8 = new BlockBgLabel();
	    label_8.setBounds(10, 230, 100, 100);
	    panelDesk.add(label_8);

	    JLabel label_9 = new BlockBgLabel();
	    label_9.setBounds(120, 230, 100, 100);
	    panelDesk.add(label_9);

	    JLabel label_10 = new BlockBgLabel();
	    label_10.setBounds(230, 230, 100, 100);
	    panelDesk.add(label_10);

	    JLabel label_11 = new BlockBgLabel();
	    label_11.setBounds(340, 230, 100, 100);
	    panelDesk.add(label_11);

	    JLabel label_12 = new BlockBgLabel();
	    label_12.setBounds(10, 340, 100, 100);
	    panelDesk.add(label_12);

	    JLabel label_13 = new BlockBgLabel();
	    label_13.setBounds(120, 340, 100, 100);
	    panelDesk.add(label_13);

	    JLabel label_14 = new BlockBgLabel();
	    label_14.setBounds(230, 340, 100, 100);
	    panelDesk.add(label_14);

	    JLabel label_15 = new BlockBgLabel();
	    label_15.setBounds(340, 340, 100, 100);
	    panelDesk.add(label_15);

	    JPanel tablePanel = new JPanel();
	    tablePanel.setOpaque(false);
	    tablePanel.setBounds(0, 0, 450, 450);
	    panelDesk.add(tablePanel, 0);

	    this.btnAi = new JButton("启动AI");
	    this.btnAi.setIcon(new ImageIcon(ResTool.getImage("startAI.png")));
	    this.btnAi.setMargin(new Insets(6, 6, 6, 6));
	    this.btnAi.setFont(new Font("新宋体", 0, 15));
	    this.btnAi.setFocusable(false);
	    this.btnAi.setBounds(102, 0, 109, 38);
	    panel.add(this.btnAi);

	    this.desktop = new Desktop(labelScore, tablePanel, this);
	  
	    
	    tablePanel.setLayout(null);

	    this.gameResPanel = new JPanel();
	    this.gameResPanel.setBorder(new LineBorder(new Color(255, 255, 153), 3));
	    this.gameResPanel.setBounds(113, 194, 224, 56);
	    this.gameResPanel.setBackground(new Color(255, 204, 102));
	    panelDesk.add(this.gameResPanel, 0);
	    this.gameResPanel.setVisible(false);

	    this.lblGameOver = new JLabel("Game Over!");
	    this.lblGameOver.setFont(new Font("微软雅黑", 1, 30));
	    this.lblGameOver.setForeground(new Color(128, 128, 128));
	    this.gameResPanel.add(this.lblGameOver);

	    this.desktop.Start();
	    btntrace.addActionListener(new MenuItemActionListener(MenuType.TRACE, this.desktop));
	    this.btnAi.addActionListener(new MenuItemActionListener(MenuType.AI, this.desktop));
	    this.frame.addKeyListener(new GameKeyListener(this.desktop));
	  }
	 
	 
	 
	 public void changeResPanel(boolean visible, boolean isWin) {
		    if (visible) {
		      this.gameResPanel.setVisible(true);
		      if (isWin)
		        this.lblGameOver.setText("Oh! You Win");
		      else
		        this.lblGameOver.setText("Game Over!");
		    }
		    else {
		      this.gameResPanel.setVisible(false);
		    }
		  }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	  public void initAI()
	  {
	    AIComplete.setStatus(false);
	    this.btnAi.setText("启动AI");
	    this.btnAi.setIcon(new ImageIcon(ResTool.getImage("startAI.png")));
	  }

	  public void showDialog(MenuType type)
	  {
	    JDialog dialog = null;
	    if (type ==MenuType.INTRO) {
	      if (this.introDialog == null)
	        this.introDialog = new IntroForm();
	      dialog = this.introDialog;
	    }
	    else if (type ==MenuType.ABOUT) {
	      if (this.aboutDialog == null)
	        this.aboutDialog = new AboutFrom();
	      dialog = this.aboutDialog;
	    }
	    dialog.setLocationRelativeTo(this.frame);
	    dialog.setVisible(true);
	  }
	 
}
