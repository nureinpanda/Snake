import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class SnakeGUI extends JFrame {

	private static final long serialVersionUID = 1;

	protected final static int GAMEFIELD = 500;
	private final static int FIELDSIZE = 25;

	protected int length = FIELDSIZE;
	protected LinkedList<Points> list = new LinkedList<Points>();

	private int xfield = 0;
	private int yfield = FIELDSIZE;

	Head head = new Head(xfield, yfield);
	Tail tail;

	public boolean eaten;

	public boolean dead = false;

	private int xdirection = 0;
	private int ydirection = 0;
	private int speed = 100;

	private int foodxfield = (int) (Math.random() * GAMEFIELD / FIELDSIZE - 1) * FIELDSIZE + FIELDSIZE;
	private int foodyfield = (int) (Math.random() * GAMEFIELD / FIELDSIZE - 1) * FIELDSIZE + FIELDSIZE;

	JLabel label = new JLabel();
	Container contentPane;

	public SnakeGUI() {

		setLayout(null);
		setTitle("Snake");
		setBounds(10, 10, GAMEFIELD, GAMEFIELD);
		setResizable(false);
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case 37: {
					// left
					xdirection = -1;
					ydirection = 0;
					break;
				}
				case 39: {
					// right
					xdirection = 1;
					ydirection = 0;
					break;
				}
				case 38: {
					// up
					ydirection = -1;
					xdirection = 0;
					break;
				}
				case 40: {
					// down
					ydirection = 1;
					xdirection = 0;
					break;
				}
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

		list.add(head);

	}

	public void paint(Graphics g) {
		
		if (!dead) {
			eaten = false;
			
			if (tail != null) {
				for (int i = list.size() - 1; i > 0; i--) {
					list.get(i).setXpos(list.get(i - 1).getXpos());
					list.get(i).setYpos(list.get(i - 1).getYpos());
				}
			}
	
			head.setXpos(head.getXpos() + FIELDSIZE * xdirection);
			head.setYpos(head.getYpos() + FIELDSIZE * ydirection);
			
			if (tail != null) {
				for (int i = 1; i < list.size(); i++) {	
					if (head.getXpos()==list.get(i).getXpos() && head.getYpos()==list.get(i).getYpos()) {
						dead=true;
					}
				}
			}

	
			if (head.getXpos() >= GAMEFIELD) {
				head.setXpos(0);
			}
			if (head.getXpos() < 0) {
				head.setXpos(GAMEFIELD);
			}
			if (head.getYpos() >= GAMEFIELD) {
				head.setYpos(FIELDSIZE);
			}
			if (head.getYpos() < FIELDSIZE) {
				head.setYpos(GAMEFIELD);
			}
	
			if (!eaten) {
				drawFood(g);
	
				eaten = true;
			}
	
			drawSnake(g);
	
			if (head.getXpos() == foodxfield && head.getYpos() == foodyfield) {
				foodxfield = (int) (Math.random() * GAMEFIELD / FIELDSIZE - 1) * FIELDSIZE + FIELDSIZE;
				foodyfield = (int) (Math.random() * GAMEFIELD / FIELDSIZE - 1) * FIELDSIZE + FIELDSIZE;
				drawFood(g);
	
				tail = new Tail(head.getXpos(), head.getYpos());
				list.add(tail);
				System.out.println(list.size());
			}
	
			bremse(speed);
	
			super.paint(g);
	
			repaint();
			
		}else{
		}

	}


	public void drawSnake(Graphics g) {
		for (int i = 0; i < list.size(); i++) {
			g.fillRect(list.get(i).getXpos(), list.get(i).getYpos(), FIELDSIZE, FIELDSIZE);
		}
	}

	public void drawFood(Graphics g) {
		g.fillOval(foodxfield, foodyfield, FIELDSIZE, FIELDSIZE);
	}

	private void bremse(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

}
