package cn.WangHao.listener;

import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.omg.CORBA.PRIVATE_MEMBER;

import res.ResTool;
import cn.WangHao.ai.AIComplete;
import cn.WangHao.game.Desktop;
import cn.WangHao.view.MainForm;
import cn.WangHao.view.NumberBlock;

public class MenuItemActionListener implements ActionListener {
	
	
	
	private MenuType type;
	private MainForm mainForm;
	private Desktop desktop;
	
	//private static int [] MenuType; 
	
	/*public static final class MenuType extends Java.lang.Enum
	{

		public static final MenuType EXIT;
		public static final MenuType INTRO;
		public static final MenuType ABOUT;
		public static final MenuType START;
		public static final MenuType AI;
		public static final MenuType TRACE;
		private static final MenuType VALUES[];
		private static final Class<Enum<Enum<T>>> MenuType = null;

		public static MenuType[] values()
		{
			MenuType amenutype[];
			int i;
			MenuType amenutype1[];
			System.arraycopy(amenutype = VALUES, 0, amenutype1 = new MenuType[i = amenutype.length], 0, i);
			return amenutype1;
		}

		public static MenuType valueOf(String s)
		{
			return (MenuType)java.lang.Enum.valueOf(MenuType, s);
		}

		static 
		{
			EXIT = new MenuType("EXIT", 0);
			INTRO = new MenuType("INTRO", 1);
			ABOUT = new MenuType("ABOUT", 2);
			START = new MenuType("START", 3);
			AI = new MenuType("AI", 4);
			TRACE = new MenuType("TRACE", 5);
			VALUES = (new MenuType[] {
				EXIT, INTRO, ABOUT, START, AI, TRACE
			});
		}

		private MenuType(String s, int i)
		{
			super(s, i);
		}

		public int ordinal() {
			// TODO Auto-generated method stub
			return 0;
		}
	}*/
	
	
	public static enum MenuType {
		EXIT, INTRO, ABOUT, START, AI, TRACE;

		
	}
	public MenuItemActionListener(MenuType type, Desktop desktop) {
		this.type = type;
		this.desktop = desktop;
	}

	public MenuItemActionListener(MenuType type) {
		this.type = type;
	}

	public MenuItemActionListener(MenuType type, MainForm mainForm) {
		this.type = type;
		this.mainForm = mainForm;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//
		switch (MenuType()[this.type.ordinal()]) {
		case 1:
			Runtime.getRuntime().exit(0);
			break;
		case 2:
		case 3:
			this.mainForm.showDialog(this.type);
			break;
		case 4:
			this.mainForm.getDesktop().Start();
			break;
		case 5:
			switchAI(e);
			break;
		case 6:
			outPutTrace();
			break;
		}
		
		
		
	}
	
	
	
	static  int [] MenuType() {
		int[] menuType = null;
		//MenuType.length=null;
		if (menuType==null) {
			int ai[] = new int[MenuType.values().length];
			try
			{
				ai[MenuType.ABOUT.ordinal()] = 3;
			}
			catch (java.lang.NoSuchFieldError _ex) { }
			try
			{
				ai[MenuType.AI.ordinal()] = 5;
			}
			catch (java.lang.NoSuchFieldError _ex) { }
			try
			{
				ai[MenuType.EXIT.ordinal()] = 1;
			}
			catch (java.lang.NoSuchFieldError _ex) { }
			try
			{
				ai[MenuType.INTRO.ordinal()] = 2;
			}
			catch (java.lang.NoSuchFieldError _ex) { }
			try
			{
				ai[MenuType.START.ordinal()] = 4;
			}
			catch (java.lang.NoSuchFieldError _ex) { }
			try
			{
				ai[MenuType.TRACE.ordinal()] = 6;
			}
			catch (java.lang.NoSuchFieldError _ex) { }
			return menuType = ai;
		}
		
		return null;
	}

	private void switchAI(ActionEvent e) {
		if (!desktop.getGameStat())
			return;
		JButton button = (JButton) e.getSource();
		if (AIComplete.isStart()) {
			AIComplete.setStatus(false);
			button.setText("\u542F\u52A8AI");
			button.setIcon(new ImageIcon(ResTool.getImage("startAI.png")));
		} else {
			AIComplete.setStatus(true);
			button.setText("\u6682\u505CAI");
			button.setIcon(new ImageIcon(ResTool.getImage("pauseAI.png")));
			desktop.execute(new AIComplete(desktop));
		}
	}

	private void outPutTrace() {
		StringBuilder builder = new StringBuilder();
		System.out.println("\u8C03\u8BD5\u4FE1\u606F\uFF1A");
		System.out.println((new StringBuilder("\tAniThread:")).append(
				Desktop.keySignal.getAniThread()).toString());
		System.out.println((new StringBuilder("\tKeyDown:")).append(
				Desktop.keySignal.getKeyDown()).toString());
		System.out.println("BlockTable\uFF1A");
		NumberBlock blocks[][] = desktop.getBlockTable();
		for (int i = 0; i < blocks.length; i++) {
			NumberBlock numberBlocks[] = blocks[i];
			for (int j = 0; j < numberBlocks.length; j++) {
				NumberBlock numberBlock = numberBlocks[j];
				String scores = "null";
				if (numberBlock != null)
					scores = Integer.toString(numberBlock.getScore());
				scores = (new StringBuilder(String.valueOf(scores))).append(
						desktop.getTablePanel().isAncestorOf(numberBlock))
						.toString();
				builder.append((new StringBuilder("\t")).append(scores).append(
						"\t").toString());
			}

			builder.append("\n");
		}

		System.out.println(builder);
	}

}
