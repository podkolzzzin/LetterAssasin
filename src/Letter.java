import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Letter {
	char _letter;
	double X,Y;
	private double Speed;
	private double Angle,AngleSpeed,Acceleration;
	private boolean _isKilled;
	private int _killAnimationState;
	private final int KILL_ANIMATION_LENGTH = 25;
	private Color color;
	public Letter(char c,int W,int H)
	{
		Random random=new Random();
		Angle = random.nextDouble() % (Math.PI*2);
		Speed=random.nextDouble()%10+5;
		AngleSpeed= 0.01;
		Acceleration = random.nextDouble()%0.001+0.001;
		_letter=c;
		X = Math.abs(random.nextInt()%(W-50))+50;
		Y = Math.abs(random.nextInt()%(H-50))+50;
		color=GetRandColor(random);
		_isKilled=false;
	}
	private Color GetRandColor(Random random) {
		switch (Math.abs(random.nextInt()%5)) {
		case 0:
			return Color.RED;
		case 1:
			return Color.BLUE;
		case 2:
			return Color.YELLOW;
		case 3:
			return Color.orange;
		case 4:
			return Color.GREEN;
		default:
			return null;
		}
	}
	public void Update(InputHandler input,GameEngine parent) {
		
		if(input.KeyChar==Character.toLowerCase(_letter))
		{
			if(parent.isGameStarted)
				Kill(parent);
		}
		if(!parent.IsInsideX(this))
		{
			Angle=Math.atan2(-Speed*Math.sin(Angle), Speed*Math.cos(Angle));
			AngleSpeed*=-1;
		}
		if(!parent.IsInsideY(this))
		{
			Angle=Math.atan2(Speed*Math.sin(Angle), -Speed*Math.cos(Angle));
			AngleSpeed*=-1;
		}
		if(Speed>15 ||Speed<3) Acceleration*=-1;
		Angle+=AngleSpeed;
		Speed+=Acceleration;
		X += Speed * Math.sin(Angle);
		Y += Speed * Math.cos(Angle);

	}
	private void Kill(GameEngine parent) {
		if(!_isKilled)
			parent.AddElement(new Score((int)Speed*10, X, Y));
		_isKilled=true;
	}
	public void Render(Graphics g) {		
		char[] c=new char[1];
		c[0]=_letter;
		String string=new String(c);
		Font font;
		if(!_isKilled)
			font = new Font("Arial", Font.BOLD, 30);
		else {
			font=new Font("Arial",Font.BOLD,30-_killAnimationState);
			_killAnimationState++;
		}
		if(_isKilled && _killAnimationState>=KILL_ANIMATION_LENGTH)
			return;
		g.setFont(font);
		g.setColor(color);
		g.drawString(string, (int)X, (int)Y);
	}
	public boolean Intersects(Letter item) {
		Rectangle rectangle=new Rectangle((int)X,(int)Y,20,30);
		Rectangle itemRectangle=new Rectangle((int)X,(int)Y,20,30);
		return rectangle.intersects(itemRectangle);
	}
}
