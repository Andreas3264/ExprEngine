package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import proofEngine.Utility;
import proofEngine.Verifier;
import proofEngine.objects.Proof;
import proofEngine.objects.Rule;

public class VerifierMain {
	
	public static void main(String[] args) throws IOException
	{
		String input = null;
		
		for(int i = 0; i < args.length; i++)
		{
			String arg = args[i];
			
			if(arg.equals("-in"))
			{
				input = args[i+1];
				i++;
			}
			else if(arg.equals("ExprEngine"))
			{
			}
			else 
			{
				throw new RuntimeException("Invalid argument: " + arg);
			}
		}
		
		Proof p = new Proof(Utility.nodeFromFile(input));
		List<Rule> rules = new ArrayList<Rule>();
		boolean validProof = Verifier.isValidProof(p, rules);
		
		if(validProof)
		{
			System.out.println("Valid proof");
			for(Rule r : rules)
			{
				System.out.println(r.toNode());
			}
		}
		else
		{
			System.out.println("Invalid proof");
		}
	}
}