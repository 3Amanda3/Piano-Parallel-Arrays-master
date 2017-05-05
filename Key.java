import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
/**
 * Key class
 * Amanda Schepp
 * Mr.Hardman
 * May 5th
 * Assignment 4
 */
public class Key extends Actor
{
    private boolean isDown = false;
    private String key;
    private String sound;
    private String notPressed;
    private String pressed;   
        
    /**
     * Create a new key.
     */
    public Key()
    {
    }
    
    /**
     * key defines the variables and sets the image to it not being pressed
     * @Param the parameters are String keyName and String soundFile and the String keyNotPressed and keyPressed
     * @Return nothing is returned
     */
    public Key(String keyName, String soundFile, String keyNotPressed, String keyPressed)
    {
        key = keyName;
        sound = soundFile;
        notPressed = keyNotPressed;
        pressed = keyPressed;
        
        setImage(notPressed + ".png");
    }

    /**
     * Do the action for this key.
     */
    public void act()
    {
        if(isDown == false && Greenfoot.isKeyDown( key ) )
        {          
           setImage( pressed + ".png" );
           isDown = true; 
           play();
        }        
         if(isDown == true && !Greenfoot.isKeyDown( key ) )
        {          
           setImage(notPressed + ".png");
           isDown = false; 
        }
    }
    
    /**
     * play will play the sound of the key
     * @Param there are no parameters
     * @Return nothing is returned
     */
    public void play()
    {
        Greenfoot.playSound(sound + ".wav");
    }
    
    /**
     * checkDown will return the boolean vairable isDown
     * @return isDown is returned
     * @param there are no parameters
     */
    public boolean checkDown()
    {
        return isDown;
    }
}

