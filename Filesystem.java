package lab3;
import java.io.*;
import java.util.*;


public class Filesystem 
{
	File dir;
	public Filesystem(File f)
	{
		this.dir = f;
	}
	
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public void runcmd() throws IOException
	{
		String line = in.readLine();
		String cmd[] = line.split(" ");
		
		switch (cmd[0])
		{
		case "exit":
			exit(cmd);
			break;
		
		case "pwd":
			pwd(cmd);
			break;
			
		case "ls":
			ls(cmd);
			break;
			
		case "cd":
			cd(cmd);
			break;
			
		
		}
		
	}
	
	private void exit(String[] cmd)
	{
		System.exit(0);
	}
	
	private void pwd(String[] cmd)
	{
		try 
		{
			System.out.println(dir.getCanonicalPath()/* + " | " + dir.listFiles().length*/);
			
		} 
		catch (IOException ex) 
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	private void ls(String[] cmd)
	{
		boolean l = false;
		if(cmd.length > 1 && cmd[1].equals("-l"))
			l = true;
		
		for(File file: dir.listFiles())
			System.out.println(file.getName());
	}
	
	private void cd(String[] cmd)
	{
		if(cmd[1].equals(".."))
			dir = dir.getParentFile();
		else
			dir = new File(dir, cmd[1]);
	}
	
}
