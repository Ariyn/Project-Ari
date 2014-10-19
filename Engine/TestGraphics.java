package Engine;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class TestGraphics extends Frame implements MouseListener, MouseMotionListener, MouseWheelListener{
	ImagePanel imagePanel;
	private int originX, originY;
	
	public TestGraphics(){
		addMouseListener(this);
	    addMouseMotionListener(this);
		this.imagePanel = new ImagePanel("src/Engine/resource/worldmap.jpg");
		this.add(this.imagePanel);
		setSize(500, 500);
		setVisible(true);         // "super" Frame shows
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.imagePanel.moveTo(e.getX()-this.originX, e.getY()-this.originY);
		System.out.println(e.getX()+" "+e.getY() +",\t\t"+ (this.originX - e.getX()) +" "+ (this.originY-e.getY()));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.originX = e.getX();
		this.originY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println(e.getScrollType() +", "+ e.getScrollAmount());
		
	}


}

class ImagePanel extends JPanel{

    private BufferedImage image;
    private int x, y, screenWidth, screenHeight;
    private int P2LX=1850, P2LY=1300;
    private int decPixWid, decPixHei;
    private int scale = 2;

    public ImagePanel(String path) {
       
        this.x=0;
        this.y=0;
        this.screenHeight = 500;
        this.screenWidth = 500;
        
        try {                
        	image = ImageIO.read(new File(path));
        	decPixWid = image.getWidth()/36;
        	decPixHei = (int)(image.getHeight()/18*1.3);
        	
        	System.out.println(image);
        } catch (IOException ex) {
            // handle exception...
        	ex.printStackTrace();
        }
    }
    
    public void setScreen(int width, int height){
    	this.screenWidth = width;
    	this.screenHeight = height;
    }
    
    public void moveTo(int x, int y) {
    	this.x += x;
    	this.y += y;
    	
    	if(this.x < 0 || this.image.getWidth()-this.screenWidth < this.x) {
    		System.out.println("x:\t"+this.x);
    		this.x -= x;
    	}
    	if(this.y < 0 || this.image.getHeight()-this.screenHeight < this.y) {
    		System.out.println("y:\t"+this.y);
    		this.y -= y;
    	}
    	
    	System.out.println(this.x + " "+this.y);
    	repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(image, -this.x*this.scale, -this.y*this.scale, image.getWidth()*this.scale, image.getHeight()*this.scale, null); // see javadoc for more info on the parameters
        g.setColor(new Color(0, 255, 0));
        
        g.drawLine(300-this.x*this.scale, 300-this.y*this.scale, 302-this.x*this.scale, 302-this.y*this.scale);
        g.drawString("KAL123", 300-this.x*this.scale, 300-this.y*this.scale);
        
        this.drawLines(g);
    }
    
    public void drawLines(Graphics g) {
    	for(int i=-14;i<22;i++){
	    	g.drawLine((this.P2LX-this.x+this.decPixWid*i)*this.scale, -this.y*this.scale, (this.P2LX-this.x+this.decPixWid*i)*this.scale, (this.image.getHeight()-this.y)*this.scale);
    	}
    	for(int i=-9;i<9;i++)
    		g.drawLine(-this.x*this.scale, (this.P2LY-this.y+this.decPixHei*i)*this.scale, (this.image.getWidth()-this.x)*this.scale, (this.P2LY-this.y+this.decPixHei*i)*this.scale);
    }
    
    public void scaleTo(int scale) {
    	this.scale = scale;
    }
    
}