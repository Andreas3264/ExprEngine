package main;

import java.io.IOException;
import java.util.List;

import proofEngine.ProofGenerator;
import proofEngine.Utility;
import proofEngine.objects.*;

public class ProofGeneratorMain {
	
	public static Proof tryGenerateProof(List<Node> exprs, List<Rule> axioms)
	{
		Rule rule = new Rule(exprs.get(0), exprs.get(exprs.size()-1));
		List<Transform> transforms = ProofGenerator.tryGetTransforms(exprs, axioms);
		return new Proof(rule, transforms);
	}
	
	public static void buildProof(String axiomFile, String machineProofsPath, String input, String output) throws IOException
	{
		List<Rule> axioms = Utility.rulesFromNode(Utility.nodeFromFile(axiomFile));
		List<Node> machineProofs = Utility.nodesFromFolder(machineProofsPath);
		for(Node n : machineProofs)
		{
			Proof p = new Proof(n);
			axioms.add(p.rule);
		}
		
		Node proofToProve = Utility.nodeFromFile(input);
		Proof proof = tryGenerateProof(proofToProve.nodes, axioms);
		Utility.proofToFile(proof, output);
	}
	
	public static void main(String[] args) throws IOException
	{
		String input = null;
		String output = null;
		String axiomFile = null;
		String machineProofFolder = null;
		
		for(int i = 0; i < args.length; i++)
		{
			String arg = args[i];
			
			if(arg.equals("-in"))
			{
				input = args[i+1];
				i++;
			}
			else if(arg.equals("-out"))
			{
				output = args[i+1];
				i++;
			}
			else if(arg.equals("-axi"))
			{
				axiomFile = args[i+1];
				i++;
			}
			else if(arg.equals("-macdir"))
			{
				machineProofFolder = args[i+1];
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
		
		buildProof(axiomFile, machineProofFolder, input, output);
	}
}