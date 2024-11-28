
public class Pet {
	
	public int sleep = 0;

	public int getSleep() {
		// TODO Auto-generated method stub
		if(sleep<0)
			return 100;
		return sleep;
	}

	public void updateStats(int i, int j, int sleep, int k) {
		// TODO Auto-generated method stub
		this.sleep+=sleep;
		
	}

	public boolean isTired() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getMoney() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setMoney(int i) {
		// TODO Auto-generated method stub
		
	}

	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

}
