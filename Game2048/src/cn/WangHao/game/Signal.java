package cn.WangHao.game;

public class Signal {

	private int keyDown;
	private int aniThread;

	public Signal() {
		this.keyDown = 0;
		this.aniThread = 0;

	}

	public int getKeyDown() {
		return keyDown;
	}

	public int getAniThread() {
		return aniThread;
	}

	// methods
	public synchronized void pKeyDown() {
		this.keyDown -= 1;
	}

	public synchronized void pAniThread() {
		this.aniThread -= 1;
	}

	public synchronized void vKeyDown() {
		if (this.keyDown != 0)
			this.keyDown += 1;
	}

	public synchronized void vAniThread() {
		if (this.aniThread != 0)
			this.aniThread += 1;
	}

	public synchronized boolean isLast() {
		return (this.keyDown == 0) && 
				(this.aniThread + 1 == 0);
	}

	public synchronized boolean isOver() {
		return (this.keyDown == 0) && (this.aniThread == 0);
	}

}
