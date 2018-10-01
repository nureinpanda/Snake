
public class Tail extends Points{
	
	public int xposbefore;
	public int yposbefore;
	
	public Tail(int xpos, int ypos) {
		super(xpos, ypos);
	}

	public int getXpos() {
		return xpos;
	}
	public int getYpos() {
		return ypos;
	}
	
	
	public void setXpos(int xpos) {
		this.xpos = xpos;
		xposbefore = xpos;
	}
	public void setYpos(int ypos) {
		this.ypos = ypos;
		yposbefore = ypos;
	}
	
	
	public int getXposbefore() {
		return xposbefore;
	}
	public int getYposbefore() {
		return yposbefore;
	}

}
