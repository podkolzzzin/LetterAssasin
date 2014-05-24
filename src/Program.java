import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Program extends Canvas implements Runnable
{
	public GameEngine Game;
	public boolean Running=true;
	public InputHandler Input;

	public Program() {
		Dimension dimension=new Dimension();
		dimension.width=740;
		dimension.height=510;
		Game = new GameEngine(dimension.width,dimension.height);
		this.setMinimumSize(dimension);
		this.setMaximumSize(dimension);
		Input=new InputHandler(this);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		Program gc=new Program();
        JFrame frame = new JFrame("Letter assassin");
        frame.add(gc, BorderLayout.CENTER);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);

    
        gc.Start();
	}

	public void Start() {
		new Thread(this).start();
	}
	
	public void Stop() {
		Running=false;
	}
	@Override public void run() 
	{
		BufferStrategy bs;
		Graphics graphics;
		while(Running)
		{
			bs = getBufferStrategy();
	        if (bs == null) {
	            createBufferStrategy(2);
	            continue;
	        }
	        graphics = bs.getDrawGraphics();
	        Input.tick();
	        ClearScreen(graphics);
			Game.Update(Input);
			Game.Render(graphics);
			
			graphics.dispose();
			bs.show();
			try 
			{
				Thread.sleep(20);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	private void ClearScreen(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);		
	}
}
