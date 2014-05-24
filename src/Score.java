import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Score {
	public double X,Y,Angle,Speed=10;
	public final double Acceleration=0.5;
	public int Score;
	public boolean Delete;
	public Score(int score,double x,double y)
	{
		Score=score;
		X=x;
		Y=y;
		Angle=Math.atan2(-Y, -X);
	}
	
	public void Update()
	{
		Speed+=Acceleration;
		X+=Speed*Math.cos(Angle);
		Y+=Speed*Math.sin(Angle);
		if(X<=0 && Y<=0)
			Delete=true;
		
	}
	
	public void Render(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.RED);
		g.drawString(Integer.toString(Score), (int)X, (int)Y);
	}
}
