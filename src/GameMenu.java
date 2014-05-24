import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class GameMenu {
	public GameMenu()
	{
		
	}
	public int Score=100;
	public void Render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, 800, 30);
		g.setColor(Color.black);
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("Score : "+Score, 5, 23);
	}
	int w=0;
	public void Update() {
		w++;
		if(w%5==0)
			Score--;
	}
}
