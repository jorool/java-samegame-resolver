package jonathan.resamemaven;

import java.util.ArrayList;
import java.util.List;

public class Resame {
	
	private static List<String> resultado = new ArrayList<String>();
	
	public static void main(String[] args) {
		if (args.length == 0 || args[0] != null) {
			Jogo jogo = Jogo.novoJogo(args[0]);
			boolean resolveu = resolver(jogo);
			if (resolveu) {
				imprimirResultado();
			}
			else {
				System.out.println("sem-solucao");
			}
		} else {
			System.out.println("nenhum argumento");
		}
	}

	private static boolean resolver(Jogo jogo) {
		if (jogo.isResolvido()){
			jogo.imprimir(true);
			return true;
		}

		if (!jogo.isResolvido() && !jogo.getTodosGruposRemoviveis().isEmpty()) {
			List<Grupo> gruposDisponiveis = jogo.getTodosGruposRemoviveis();
			while (!gruposDisponiveis.isEmpty()) {
				Jogo novoJogo = jogo.removerGrupo(gruposDisponiveis.get(0));
				salvarJogada(gruposDisponiveis.get(0).getPosicoes().get(0).toString(), novoJogo.toString());
				gruposDisponiveis.remove(0);
				if (novoJogo.isResolvido()){
					return true;
				} else { // se nao resolveu, tenta fazer outra jogada
					boolean resolveu = resolver(novoJogo);
					if (resolveu){
						return true;
					} else {
						removerUltimaJogada();
					}
					// se ainda nao resolveu, tenta escolher o proximo grupo disponível
				}
			}
		}
		return false;
	}

	private static void imprimirResultado() {
		for (String string : resultado) {
			if (!string.isEmpty()) {
				System.out.print(string);
				if (resultado.indexOf(string) < resultado.size() -1) System.out.println("\n");
			}
		}
	}

	private static void salvarJogada(String posicao, String jogo) {
		resultado.add(posicao);
		resultado.add(jogo);
	}
	
	private static void removerUltimaJogada() {
		resultado.remove(resultado.size()-1); //jogo
		resultado.remove(resultado.size()-1); //posicao
	}
}
