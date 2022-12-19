package en.bent07.MyAdventura;

import java.io.*;
import java.awt.GraphicsEnvironment;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * This opens a command line and runs some other class in the jar
 * Bit of not really successful debugging of problematic characters from Tomáš Beneda, solved very poorly and will almost certainly break in case of different special characters.
 * EDIT ZS2022.2: I hope I actually got universal solution for the encoding problem
 * Also added support for parameters when running from cmd
 * @author Tomáš Beneda on basis from Brandon Barajas
 * @version ZS2022.2
 */
public class Main{
    public static void main (String [] args) throws IOException, InterruptedException, URISyntaxException{
        String arguments = "";
        if(args.length == 1){
            arguments += args[0];
            arguments += " ";
        }else if(args.length == 2){
            arguments += args[0];
            arguments += " ";
            arguments += args[1];
            arguments += " ";
        }else if(args.length >= 3){
            arguments += args[0];
            arguments += " ";
            arguments += args[1];
            arguments += " ";
            arguments += args[2];
        }
        Console console = System.console();
        if(console == null && !GraphicsEnvironment.isHeadless()){
            String result = java.net.URLDecoder.decode(Main.class.getProtectionDomain().getCodeSource().getLocation().toString(), StandardCharsets.UTF_8);
            String filename = result.substring(6);//.replace("%c5%a0","Š").replace("%20", " ");
            String[] tmp = filename.split("/");
            tmp[tmp.length-1] = "";
            String filepath = "";
            for(int i = 0; i < tmp.length; i++){
                filepath += tmp[i]; //Can use StringBuilder.append() apparently. But this works fine
                filepath += "\\";
            }
            //Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\" " + arguments }); //Original command

            //Edited command with right encoding and moving to the file directory to hopefully avoid problems with file creating rights
            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","chcp 1250 && cd \"" + filepath + "\" && java -jar \"" + filename + "\" " + arguments});
        }else{
            Start.main(args);

            //Closes the command line after use when opened by double-click
            //copied from stackoverflow
            try {
                Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
            } catch (Exception e) {
                e.printStackTrace();
            }

            //This should not appear anywhere
            System.out.println("Program has ended, please type 'exit' to close the console");
        }
    }
}