package komponen;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Math.pow;
//import javafx.stage.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.Interpolator;

public class DialogAlert extends JDialog{
    private Background content;
    private Animator animator;
    private Dimension originalSize;
    private Point originalLocation;
    private boolean show;
    
    public DialogAlert(Frame fram, boolean modal){
        super(fram, modal);
        init();
    }
    
    private void init(){
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        content = new Background();
        content.setBackground(new Color(245,250,242));
        setContentPane(content);
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void end(){
//                super.end();
                if(show == false){
                    dispose();
                }
            }
            
            @Override
            public void timingEvent(float fraction){
//                super.timingEvent(fraction);
                float f;
                if(show){
                    f=fraction;
                }
                else{
                    f=1f - fraction;
                }
                int w = (int) (originalSize.width * f);
                int h = (int) (originalSize.height * f);
                int x = originalLocation.x-w/2;
                int y = originalLocation.y-h/2;
                setLocation(x, y);
                setSize(new Dimension(w,h));
            }
        };
        animator = new Animator(300,target);
        animator.setInterpolator(new Interpolator() {
            @Override
            public float interpolate(float f) {
                if(show){
                    return easeInOutBack(f);
                }
                else{
                    return easeOutExpo(f);
                }
            }
        });
        animator.setResolution(0);
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.err.println("halo");
                closeAlert(); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public float easeInOutBack(float x){
        float c1 = (float) 1.70158;
        float c2 = c1 * 1.525f;
        double v;

        if(x< 0.5){
            v = (pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2;
        }
        else{
            v = (pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2;
        }
        
        return (float)v;
    }
    
    public float easeOutExpo (float x){
//        x === 1 ? 1 : 1 - pow(2, -10 * x);
        double v;
        if(x == 1){
            v = 1;
        }
        else{
            v = 1 - pow(2, -10 * x);
        }
        
        return (float)v;
    }
    
    public void showAlert(){
//        setLocation(getLocation(getParent()));
//        untuk disable alt + f4 yang berguna untuk close aplikasi
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        originalSize = getPreferredSize();
        originalLocation = getLocation(getParent());
        setSize(new Dimension(0,0));
        if(animator.isRunning()){
            animator.stop();
        }
        show = true;
        animator.setDuration(500);
        animator.start();
        setVisible(true);
    }
    
    public void closeAlert() {
        if(animator.isRunning()){
            animator.stop();
        }
        show = false;
        animator.setDuration(300);
        animator.start();
    }
    
//    biar di tengah tengah parent
    public Point getLocation(Container parent){
        Point location = parent.getLocationOnScreen();
        Dimension size = parent.getSize();
        int x = location.x+size.width/2;
        int y = location.y+size.height/2;
        Point point = new Point(x,y);
        return  point;
    }
    
    private class Background extends  JComponent{
        @Override
        public void paint(Graphics grphcs){
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.dispose();
            super.paint(grphcs);
        }
    }
}
