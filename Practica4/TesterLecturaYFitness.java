import java.io.IOException;
import java.util.List;

import Dominio.DominioAritmetico;
import Dominio.IDominio;
import Excepciones.CruceNuloException;
import Individuo.IIndividuo;
import Individuo.Individuo;
import Nodo.Terminal;
import Nodo.TerminalAritmetico;
import Nodo.Funcion.Funcion;
import Nodo.Funcion.FuncionMultiplicacion;
import Nodo.Funcion.FuncionResta;
import Nodo.Funcion.FuncionSuma;

public class TesterLecturaYFitness {
	public static void main(String[] args) throws IOException {
		IDominio domAritm;
		double fitness;
		
		domAritm = new DominioAritmetico();
		domAritm.definirValoresPrueba("valoresReducido.txt");
		Terminal x = new TerminalAritmetico("x");
		Funcion suma = new FuncionSuma(2);
		Funcion resta = new FuncionResta(2);
		Funcion multi = new FuncionMultiplicacion(2);
		
		multi.incluirDescendiente(x);
		multi.incluirDescendiente(x);
		suma.incluirDescendiente(multi);
		suma.incluirDescendiente(x);
		resta.incluirDescendiente(suma);
		resta.incluirDescendiente(multi);
		
		IIndividuo indiv = new Individuo();
		indiv.setExpresion(resta);
		System.out.println();
		System.out.println("INDIVIDUO");
		indiv.writeIndividuo();
		System.out.println();
		fitness = domAritm.calcularFitness(indiv);
		System.out.println("\nFITNESS= "+fitness);
		
		
		
		PruebaCruce prueba = new PruebaCruce();
		IIndividuo indiv2 = new Individuo();
		indiv2.setExpresion(suma);
		List<IIndividuo> lista;
		try {
			lista = prueba.cruce(indiv, indiv2);
			lista.get(0).writeIndividuo();
			lista.get(1).writeIndividuo();
		} catch (CruceNuloException e) {
			e.printStackTrace();
		}
	}
}