package jonathan.resamemaven;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Grupo {

	private List<Posicao> posicoes = new ArrayList<Posicao>();

	private Grupo() {
	}

	public static final Grupo novoGrupoVazio() {
		return new Grupo();
	}

	public List<Posicao> getPosicoes() {
		return this.posicoes;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Grupo) {
			Grupo other = (Grupo) obj;
			return this.getPosicoes().equals(other.getPosicoes());
		}
		return false;
	}

	public Grupo addPosicao(Posicao posicao) {
		if (!this.getPosicoes().contains(posicao)) {
			this.getPosicoes().add(posicao);
		}
		return this;
	}

	public Grupo addPosicoes(List<Posicao> posicoes) {
		for (Posicao posicao : posicoes) {
			addPosicao(posicao);
		}
		return this;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Grupo removerRepetidos() {
		HashSet hs = new HashSet();
		hs.addAll(this.getPosicoes());
		this.getPosicoes().clear();
		this.getPosicoes().addAll(hs);
		return this;
	}

}
