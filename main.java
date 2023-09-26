package lab3;
import java.io.*;


public class main 
{
	public static void main (String args[])
	{
		Filesystem fs = new Filesystem(new File(System.getProperty("user.dir")));
		try
		{
			while(true)
				fs.runcmd();
		}
		catch(IOException ex)
		{
			
		}
	}
}
