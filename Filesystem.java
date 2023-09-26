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
			
		case "length":
			length(cmd);
			break;
		
		case "tail":
			tail(cmd);
			break;
			
		case "grep":
			grep(cmd);
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
		
		if(l == false)
		{
			for(File file: dir.listFiles())
			{
				System.out.println(file.getName());		
			}
		}
		else
		{
			for(File file: dir.listFiles())
			{
				if(file.isDirectory())
					System.out.println(file.getName() + " d " + file.length() + " byte");
				else if(file.isFile())
					System.out.println(file.getName() + " f " + file.length() + " byte");
			}
		}

	}
	
	private void cd(String[] cmd)
	{
		if(cmd[1].equals(".."))
			dir = dir.getParentFile();
		else
			dir = new File(dir, cmd[1]);
	}
	
	private void length(String[] cmd) throws FileNotFoundException, IOException
	{
		FileNotFoundException ex = new FileNotFoundException("Ilyen nevu file nem letezik!");
		boolean talal = false;
		for(File file: dir.listFiles())
		{
			if(file.getName().equals(cmd[1]))
			{
				System.out.println(file.length() + " byte");
				talal = true;
			}
		}
		if(talal == false)
			throw ex;
	}
	
	private void tail(String[] cmd) throws FileNotFoundException, IOException
	{	
		int n = 10;
		File f;
		if(cmd[1].equals("-n"))
		{
			n = Integer.parseInt(cmd[2]);
			f = new File(dir, cmd[3]);
		}
		else
		{
			f = new File(dir, cmd[1]);
		}
		int sor = 0;
		LineNumberReader lnr = new LineNumberReader(new FileReader(f));
			while(lnr.readLine() != null);
		sor = lnr.getLineNumber();
		lnr.close();
		BufferedReader in = new BufferedReader(new FileReader(f));
		for(int i = 0; i < sor - n; i++)
			in.readLine();
		for(int i = 0; i < n; i++){
			System.out.println(in.readLine());
		}
		in.close();
	}
	
	private void grep(String[] cmd) throws FileNotFoundException, IOException
	{	
		File f = new File(dir, cmd[2]);
		BufferedReader in = new BufferedReader(new FileReader(f));
		String aktualis = in.readLine();
		while(aktualis != null)
		{	
			if(aktualis.matches(cmd[1]))
				System.out.println(in.readLine());
			aktualis = in.readLine();
		}
		in.close();
	}
	
}
