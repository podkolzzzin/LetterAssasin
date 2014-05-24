import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.security.auth.x500.X500Principal;


public class GameEngine {
	Canvas _graphics;
	Letter[] letters=new Letter[26];
	ArrayList<Score> elements;
	GameMenu menu;
	int Width,Height;
	boolean isGameStarted;
	public GameEngine(int W,int H) {
		Width=W;
		Height=H;
		menu=new GameMenu();
		elements=new ArrayList<Score>();
		for(int i=0;i<26;i++)
		{
			letters[i]=new Letter((char)(i+(int)'A'),W,H);
		}
	}
	public void Update(InputHandler input) {
		for (Letter item : letters) {
			item.Update(input,this);
		}
		for(int i=0;i<elements.size();i++)
		{
			elements.get(i).Update();
			if(elements.get(i).Delete)
			{
				menu.Score+=elements.get(i).Score;
				elements.remove(i);	
				i--;
			}
		}
		
		if(isGameStarted)
			menu.Update();
		if(!isGameStarted)
		{
			if(input.attack.clicked)
			{
				isGameStarted=true;
			}
		}
	}
	public void Render(Graphics g) {
		for (Letter item : letters) {
				item.Render(g);
		}
		for (Score item : elements) {
			item.Render(g);
		}
		menu.Render(g);
		if(!isGameStarted)
		{
			Color c = new Color(0, 0, 0, 128);
			g.setColor(c);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.white);
			g.setFont(new Font("Arial",Font.BOLD,50));
			g.drawString("Press space to start", 180, 250);
		}
	}
	public boolean Inside(Letter letter) {
		Rectangle rectangle=new Rectangle((int)letter.X,(int)letter.Y,20,30);
		Rectangle fieldRectangle=new Rectangle(25,55,Width,Height);
		return rectangle.intersects(fieldRectangle);
	}
	
	public Letter CheckCollision(Letter l) {
		for (Letter item : letters) {
			if(item==l) 
				continue;
			if(l.Intersects(item))
			{
				return item;
			}
		}
		return null;
	}
	public double InsideX(double x) {
		if(x>Width) x=Width-1;
		if(x<25) x=1;
		return x;
	}
	public double InsideY(double y) {
		if(y>Height) y=Height-1;
		if(y<55) y=1;
		return y;
	}
	public boolean IsInsideX(Letter letter) {
		if(letter.X>Width || letter.X<25)
			return false;
		return true;
	}
	public boolean IsInsideY(Letter letter) {
		if(letter.Y>Height || letter.Y<55) 
			return false;
		return true;
	}
	
	public void AddElement(Score child) {
		elements.add(child);
	}
}
