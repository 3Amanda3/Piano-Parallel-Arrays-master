import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A piano that can be played with the computer keyboard.
 * Amanda Schepp
 * Mr.Hardman
 * May 5th
 * Assignment 4
 */
public class Piano extends World
{
    private String[] whiteKeys ={"q","w","e","r","t","y","u","i","o","p","[","]"};
    private String[] whiteNotes ={"3c","3d","3e","3f","3g","3a","3b","4c","4d","4e","4f", "4g"};

    private String[] blackKeys = {"2","3","","5","6","7","","9","0","","="};
    private String[] blackNotes = {"3c#","3d#","","3f#","3g#","3a#","","4c#","4d#","","4f#"};

    private Key[] whiteKeyObjects = new Key [12];
    private Key[] blackKeyObjects = new Key [11];
    private Key[] allKeyObjects = new Key [23];

    /**
     * Make the piano.
     */
    public Piano() 
    {
        super(800, 340, 1);
        makeKeys();
    }

    /**
     * makeKeys will make the keys and put them on the screen
     * @Pram there are no parameters
     * @Return nothing is returned
     */
    private void makeKeys()
    {               
        Key currentKey;

        for(int i =0; i < whiteKeys.length; i++)
        {
            currentKey = new Key ( whiteKeys[i],whiteNotes[i], "white-key", "white-key-down");
            addObject(currentKey, (i*67)+30, 250); 
            whiteKeyObjects[i]=currentKey;
        }    
        
        for(int i =0; i < blackKeys.length; i++)
        {                   
            if(blackKeys[i] != ("") )
            {
                currentKey = new Key ( blackKeys[i],blackNotes[i], "black-key", "black-key-down");
                addObject(currentKey, (i*67)+65, 195);
                blackKeyObjects[i]=currentKey;
            }
            else
            {
                blackKeyObjects[i] = null;
            }
        }
        makeAllKeys();
    }

    /**
     * makeAllKeys will add the black and white keys to the world
     * @Param there are no parameters
     * @return nothing is returned
     */
    private void makeAllKeys()
    {
        for(int i = 0; i < allKeyObjects.length; i++)
        {
            if( i % 2 == 0)
            {
                allKeyObjects[i] = whiteKeyObjects[i/2];
            }
            else
            {
                allKeyObjects[i] = blackKeyObjects[i/2];
            }
        }

        allKeyObjects[22]=whiteKeyObjects[11];
    }

    public void act ()
    {
        int numAllDown = 0;
        int numNulls = 0;

        int[] keyDownLocations = new int [20];

        for(int i = 0; i < allKeyObjects.length; i++)
        {
            if(allKeyObjects[i] == null)
            {
                numNulls++;
            }
            else if(allKeyObjects[i].checkDown()==true)
            {
                keyDownLocations[numAllDown] = i - numNulls;
                numAllDown++;
            }
        }

        if(numAllDown == 2)
        {
            checkForSeconds(keyDownLocations);
        }
        else if(numAllDown == 3)
        {
            checkForTriads(keyDownLocations);
        }
        else if(numAllDown == 4)
        {
            checkForSevenths(keyDownLocations);
        }
        else
        {
            showText("", getWidth()/2,50);
        }
    }

    /**
     * checkForSeonds will check to see if the user has played a second
     * @Param keyDownLocaations tells the user what index the note being pressed is at and uses thst info to display text acordinly
     * @return nothing is returned
     */
    public void checkForSeconds(int[] keyDownLocations)
    {
        if (keyDownLocations[1] == keyDownLocations[0] + 1 || keyDownLocations[1] == keyDownLocations[0] + 2)
        {
            showText("You have made a second!",getWidth()/2,50);
        }
    }

    /**
     * checkForThirds will check to see if the user has played a third
     * @Param keyDownLocaations tells the user what index the note being pressed is at and uses thst info to display text acordinly
     * @return nothing is returned
     */
    public void checkForTriads(int[] keyDownLocations)
    {
        if (keyDownLocations[1] == keyDownLocations[0] + 3 && keyDownLocations[2] == keyDownLocations[1] + 4 || keyDownLocations[1] == keyDownLocations[0] + 4 && keyDownLocations[2] == keyDownLocations[1] + 3 || keyDownLocations[1] == keyDownLocations[0] + 3 &&keyDownLocations[2] == keyDownLocations[1] + 3 )
        {
            showText("You have made a triad!",getWidth()/2,50);
        }
    }

    /**
     * checkForSevenths will check to see if the user has played a seventth
     * @Param keyDownLocaations tells the user what index the note being pressed is at and uses thst info to display text acordinly
     * @return nothing is returned
     */
    public void checkForSevenths(int[] keyDownLocations)
    {
        if (keyDownLocations[1] == keyDownLocations[0] + 3 && keyDownLocations[2] == keyDownLocations[1] + 4 && keyDownLocations[2] == keyDownLocations[1] + 3 || keyDownLocations[1] == keyDownLocations[0] + 4 && keyDownLocations[2] == keyDownLocations[1] + 3 || keyDownLocations[1] == keyDownLocations[0] + 4 || keyDownLocations[2] == keyDownLocations[1] + 3 && keyDownLocations[1] == keyDownLocations[0] + 4 || keyDownLocations[1] == keyDownLocations[0] + 3 && keyDownLocations[2] == keyDownLocations[1] + 3 && keyDownLocations[1] == keyDownLocations[0] + 3 )
        {
            showText("You have made a seventh!",getWidth()/2,50);
        }
    }
}
