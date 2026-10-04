package proofEngine.objects;

import java.util.ArrayList;
import java.util.List;

public class Proof {

	public final Rule rule;
	public final List<Transform> transforms;
	
	public Proof(Rule rule, List<Transform> transforms)
	{
		this.rule = rule;
		this.transforms = transforms;
	}
	
	public Proof(Node node)
	{
		this.rule = new Rule(node.nodes.get(0));
		this.transforms = new ArrayList<Transform>();
		for(Node t : node.nodes.get(1).nodes)
		{
			transforms.add(new Transform(t));
		}
	}
	
	/*public Node toNode()
	{
		//TODO
	}*/
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("(\n");
		sb.append(rule);
		sb.append("\n(\n");
		for(Transform t : transforms) 
		{
			sb.append(t);
			sb.append("\n");
		}
		sb.append(")\n)");
		return sb.toString();
	}
}
