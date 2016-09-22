package walaTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.ibm.wala.classLoader.CallSiteReference;
import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.callgraph.AnalysisCache;
import com.ibm.wala.ipa.callgraph.AnalysisOptions;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.Entrypoint;
import com.ibm.wala.ipa.callgraph.impl.DefaultEntrypoint;
import com.ibm.wala.ipa.callgraph.impl.Util;
import com.ibm.wala.ipa.callgraph.propagation.InstanceKey;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.callgraph.propagation.PointerKey;
import com.ibm.wala.ipa.callgraph.propagation.SSAPropagationCallGraphBuilder;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.util.config.AnalysisScopeReader;
import com.ibm.wala.util.io.FileProvider;

public class JavaTest {

	static String[] mains = { "Lnet/pradeo/bench/Bench", // bench.jar
			"Lorg/antlr/v4/Tool", // antlr.jar
			"Lbrut/apktool/Main", // apktool.jar
			"Lcom/android/dx/command/Main", // dx.jar
			"Lnet/minecraft/bootstrap/Bootstrap", // minecraft.jar
			"LwalaTest/JavaTest", // walaTest.jar
	};

	static String app;
	static int k;
	static long begin;

	public static AnalysisScope makeScope(String app) throws IOException {
		File exFile = new FileProvider()
				.getFile("Java60RegressionExclusions.txt");
		AnalysisScope scope = AnalysisScopeReader
				.makeJavaBinaryAnalysisScope(app, exFile);
		return scope;
	}

	public static List<Entrypoint> getRoot(IClassHierarchy cha) {
		List<String> classesMains = Arrays.asList(mains);

		IMethod root = null;
		for (IClass cl : cha) {
			if (classesMains.contains(cl.getName().toString()))
				for (IMethod m : cl.getDeclaredMethods()) {
					if (m.getName().toString().equals("main"))
						root = m;
				}
		}
		System.out.println("ROOT;" + root);

		List<Entrypoint> p = new ArrayList<Entrypoint>();
		p.add(new DefaultEntrypoint(root, cha));

		return p;
	}

	public static void makeStates(SSAPropagationCallGraphBuilder cfa) {
		CallGraph callgraph = cfa.getCallGraph();

		// states
		int nbrnode = 0;
		int nbrcallsite = 0;
		int nbrtarget = 0;
		for (CGNode node : callgraph) {
			if (!node.toString().contains("< Application,"))
				continue;
			nbrnode++;

			Iterator<CallSiteReference> it = node.iterateCallSites();
			while (it.hasNext()) {
				CallSiteReference ref = it.next();
				nbrcallsite++;
				nbrtarget = nbrtarget
						+ callgraph.getPossibleTargets(node, ref).size();
			}
		}

		PointerAnalysis<InstanceKey> factory = cfa.getPointerAnalysis();
		int nbrclass=0;
		for(InstanceKey key : factory.getInstanceKeys()){
			if(!key.toString().contains("< Application,")) continue;
			nbrclass++;
			System.out.println(key);
		}
		
		for(PointerKey it : factory.getPointerKeys()){
			if(!it.toString().contains("< Application,")) continue;
			System.out.println(it);
		}
			

		double time = (double) Calendar.getInstance().getTimeInMillis() - begin;
		System.out.println("time;" + time);
		System.out.println("version;" + nbrnode);
		System.out.println("types;" + nbrclass);
		System.out.println("precsion;" + ((float) nbrtarget / nbrcallsite));

	}

	static void output(SSAPropagationCallGraphBuilder cfa) throws IOException {

		CallGraph callgraph = cfa.getCallGraph();

		File file = new File(app + "-" + k + ".log");

		FileOutputStream output = new FileOutputStream(file);

		String line = "digraph call { \n";
		output.write(line.getBytes());
		for (CGNode node : callgraph) {
			if (!node.toString().contains("< Application,"))
				continue;

			String nodeId = "CG" + node.getGraphNodeId();
			line = "\t" + nodeId + " [label=\"" + node.toString() + "\"];\n";
			output.write(line.getBytes());

			Iterator<CallSiteReference> it = node.iterateCallSites();
			while (it.hasNext()) {
				CallSiteReference site = it.next();
				line = "\t" + site.getProgramCounter() + " [label=\""
						+ site.toString() + "\"];\n";
				output.write(line.getBytes());

				line = nodeId + "->" + site.getProgramCounter() + ";\n";
				output.write(line.getBytes());

				for (CGNode target : callgraph.getPossibleTargets(node, site)) {

					if (!target.toString().contains("< Application,")) {
						String targetId = "CG" + node.getGraphNodeId();
						line = "\t" + targetId + " [label=\""
								+ target.toString() + "\"];\n";
						output.write(line.getBytes());
					}

					line = site.getProgramCounter() + "-> CG"
							+ target.getGraphNodeId() + " ;\n";
					output.write(line.getBytes());

				}

			}
		}

		output.write('}');
		output.close();
	}

	public static void main(String args[]) throws Exception {
		begin = Calendar.getInstance().getTimeInMillis();

		// check number of argument
		if (args.length < 2) {
			throw new Exception("java -jar walaTest.jar  app.jar k ");
		}

		// read parameters
		app = args[0];
		k = Integer.parseInt(args[1]);
		System.out.println("App;" + app);
		System.out.println("k;" + k);

		// make scope
		AnalysisScope scope = makeScope(app);

		// calculate cha
		IClassHierarchy cha = ClassHierarchy.make(scope);

		// calculate entry point;
		List<Entrypoint> p = getRoot(cha);

		// build CFA
		AnalysisOptions options = new AnalysisOptions(scope, p);
		SSAPropagationCallGraphBuilder cfa = Util.makeNCFABuilder(k, options,
				new AnalysisCache(), cha, scope);
		cfa.makeCallGraph(options);

		makeStates(cfa);

		output(cfa);
	}
}
