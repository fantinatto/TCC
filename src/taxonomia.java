import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.Tree;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.layout.PersistentLayout;

public class taxonomia {

	public void abreTaxonomia() throws IOException {
		int nivelTree = 0, idEdge = 0;
		
		Node aux = null;
		Tree<Node, String> tree = new DelegateTree<Node, String>();
		FileReader txtFile = new FileReader(
				"C:/Users/Vinicius/Desktop/fantinatto/academico/TCC/alg/dataminingSE/taxonomia_materias.txt");
		BufferedReader txtBuffer = new BufferedReader(txtFile);
		String curLine = txtBuffer.readLine();

		Node root = new Node();
		root.setVal("Root", 0);
		tree.addVertex(root);
		aux = root;

		while (curLine != null) {
			idEdge++;
			String minusculo = curLine.toLowerCase(); // normaliza em minusculo
														// e sem acento
			minusculo = Normalizer.normalize(minusculo, Normalizer.Form.NFD);
			minusculo = minusculo.replaceAll("[^\\p{ASCII}]", "");
			String[] linhaSplit = minusculo.split("\t");

			// tamanho do indice
			nivelTree = linhaSplit[0].length();
			// encontra pai para o novo nó
			while (nivelTree <= aux.getNivel()) {
				// pega nó pai, se for root retorna null
				if (tree.getParent(aux) != null) {
					aux = tree.getParent(aux);
				}
			}
			if (nivelTree == aux.getNivel()) {
				Node newNode = new Node();
				newNode.setVal(linhaSplit[1], nivelTree);
				tree.addEdge("", tree.getParent(aux), newNode);
				aux = tree.getParent(newNode);
			} else {
				Node newNode = new Node();
				newNode.setVal(linhaSplit[1], nivelTree);
				tree.addEdge(Integer.toString(idEdge), aux, newNode);
				aux = newNode;

			}

			curLine = txtBuffer.readLine();

		}
		
		VisualizationImageServer vs = new VisualizationImageServer(
				new TreeLayout(tree), new Dimension(1000, 400));
		 
		
		JFrame frame = new JFrame();
		frame.getContentPane().add(vs);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		txtBuffer.close();
	}
}
