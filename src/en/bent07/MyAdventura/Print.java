package en.bent07.MyAdventura;

/**
 * Generalizes text printing system to be hopefully easily reworked if needed.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Print {
    private static int sleep = Global.getDelay();
    private String string;
    
    public Print(String string){
        try {
            Thread.sleep(sleep);
        }catch(Exception e){
            System.out.println("There was a problem with waiting for next print.");
        }
        this.string = string;
        System.out.println(string);//only sout that should be in the project after implementing logs


    }
    
    /*
    public void print(String string){
        new Print(string);
    }
     */
    /*
    //ready for future usage in other courses hopefully
    public String print(String string){
        return string;
    }
     */

}
