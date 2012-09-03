package jonathan.resamemaven;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class Jogo implements Cloneable {

    private List<List<Integer>> colunas;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jogo other = (Jogo) obj;
        if (this.colunas != other.colunas && (this.colunas == null || !this.colunas.equals(other.colunas))) {
            return false;
        }
        if (this.tamanhoGrupo != other.tamanhoGrupo && (this.tamanhoGrupo == null || !this.tamanhoGrupo.equals(other.tamanhoGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public Jogo clone() {
        try {
            Jogo novoJogo = (Jogo) super.clone();
            List<List<Integer>> novasColunas = new ArrayList<List<Integer>>();
            for (List<Integer> coluna : this.getColunas()) {
                novasColunas.add(new ArrayList<Integer>(coluna));
            }
            novoJogo.colunas = novasColunas;
            return novoJogo;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Nao deveria chegar aqui.");
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.colunas != null ? this.colunas.hashCode() : 0);
        hash = 31 * hash + (this.tamanhoGrupo != null ? this.tamanhoGrupo.hashCode() : 0);
        return hash;
    }

    public List<List<Integer>> getColunas() {
        return this.colunas;
    }

    public List<Integer> getColuna(Integer index) {
        if (index < 0 || index > this.colunas.size() - 1) {
            return new ArrayList<Integer>();
        }
        return this.colunas.get(index);
    }

    Jogo() {
    }

    Jogo(String argumento) {
        try {
            Integer countColunas;
            List<String> linhas = retornaLinhas(argumento);

            List<List<Integer>> listaColunas = new ArrayList<List<Integer>>();
            for (int i = linhas.size() - 1; i >= 0; i--) {
                countColunas = 0;
                String linha = linhas.get(i);
                for (int j = 0; j < linha.length(); j++) {
                    if (linha.substring(j, j + 1).trim().isEmpty()) {
                        continue;
                    }
                    String elemento = linha.substring(j, j + 1);
                    Integer numero = Integer.parseInt(elemento);
                    //se nao existe a coluna, cria
                    if (listaColunas.size() - 1 < countColunas) {
                        listaColunas.add(new ArrayList<Integer>());
                    }
                    listaColunas.get(countColunas).add(numero);
                    countColunas++;
                }
            }
            this.colunas = listaColunas;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private List<String> retornaLinhas(String argumento) throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream(argumento);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        List<String> linhas = new ArrayList<String>();
        while ((strLine = br.readLine()) != null) {
            linhas.add(strLine);
        }
        in.close();
        return linhas;
    }

    public void imprimir(Boolean quebrarLinha) {
        StringBuilder linha = new StringBuilder();
        for (int i = this.colunas.get(0).size() - 1; i >= 0; i--) {
            for (List<Integer> coluna : this.colunas) {
                if (i <= coluna.size() - 1) {
                    linha.append(coluna.get(i)).append(" ");
                }
            }
            if (i > 0) {
                linha.append("\n");
            }
        }
        System.out.println(linha.toString().trim());
        if (quebrarLinha) {
            System.out.print("\n");
        }
    }
    private Integer tamanhoGrupo = 0;

    public void removerGrupo(Posicao posicaoInicial) {
        removeGrupo(posicaoInicial);
        reorganizar();
    }

    public void removerGrupo(List<Posicao> grupo) {
        removeGrupo(grupo.get(0));
        reorganizar();
    }

    private void removeGrupo(Posicao posicaoInicial) {
        Integer corInicial = getCor(posicaoInicial);
        if (corInicial.equals(0)) {
            return;
        }
        tamanhoGrupo++;
        alterarPosicao(posicaoInicial, 0);
        List<Posicao> vizinhos = getVizinhos(posicaoInicial);
        for (Posicao posicao : vizinhos) {
            if (getCor(posicao).equals(corInicial)) {
                tamanhoGrupo++;
                removeGrupo(posicao);
            }
        }
        if (tamanhoGrupo.equals(1)) {
            alterarPosicao(posicaoInicial, corInicial);
        }
        tamanhoGrupo = 0;
    }

    private Boolean alterarPosicao(Posicao posicao, Integer valor) {
        List<Integer> coluna = this.getColuna(posicao.getColuna());
        coluna.set(posicao.getLinha(), valor);
        return true;
    }

    private void reorganizar() {
        if (this.isResolvido()) {
            return;
        }

        Integer qtdeLinhas = this.getColuna(0).size();
        Integer qtdeColunas = this.getColunas().size();

        //verifica as colunas vazias
        Iterator<List<Integer>> it = this.getColunas().iterator();
        while (it.hasNext()) {
            List<Integer> coluna = it.next();
            Boolean colunaVazia = true;
            for (Integer elemento : coluna) {
                if (!elemento.equals(0)) {
                    colunaVazia = false;
                }
            }
            if (colunaVazia) {
                it.remove();
            }
        }
        Integer diferencaColunas = qtdeColunas - this.getColunas().size();
        if (diferencaColunas > 0) {
            for (int i = 0; i < diferencaColunas; i++) {
                List<Integer> novaColunaZerada = new ArrayList<Integer>();
                for (int j = 0; j < qtdeLinhas; j++) {
                    novaColunaZerada.add(0);
                }
                this.getColunas().add(novaColunaZerada);
            }
        }

        //remove os zeros
        for (List<Integer> coluna : this.getColunas()) {
            Iterator<Integer> _it = coluna.iterator();
            while (_it.hasNext()) {
                Integer elemento = _it.next();
                if (elemento == 0) {
                    _it.remove();
                }
            }
        }

        //adiciona os zeros novamente, na posicao correta
        for (List<Integer> coluna : this.getColunas()) {
            Integer diferencaLinhas = qtdeLinhas - coluna.size();
            if (diferencaLinhas > 0) {
                for (int i = 0; i < diferencaLinhas; i++) {
                    coluna.add(0);
                }
            }
        }
    }

    public List<Posicao> getVizinhos(Posicao posicao) {
        List<Posicao> vizinhos = new ArrayList<Posicao>();

        Posicao posicao3 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
        Posicao posicao1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
        Posicao posicao4 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
        Posicao posicao2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());

        if (isPosicaoValida(posicao1)) {
            vizinhos.add(posicao1);
        }
        if (isPosicaoValida(posicao2)) {
            vizinhos.add(posicao2);
        }
        if (isPosicaoValida(posicao3)) {
            vizinhos.add(posicao3);
        }
        if (isPosicaoValida(posicao4)) {
            vizinhos.add(posicao4);
        }

        return vizinhos;
    }

    public Boolean isPosicaoValida(Posicao posicao) {
        if (posicao == null) {
            return false;
        }
        Integer coluna = posicao.getColuna();
        Integer linha = posicao.getLinha();
        if (linha < 0 || coluna < 0) {
            return false;
        } else {
            if (coluna > this.getColunas().size() - 1 || linha > this.getColuna(0).size() - 1) {
                return false;
            }
        }
        return true;
    }

    public Integer getCor(Posicao posicao) {
        List<Integer> coluna = this.getColuna(posicao.getColuna());
        if (coluna.isEmpty()) {
            return 0;
        }
        return coluna.get(posicao.getLinha());
    }

    public Boolean isResolvido() {
        for (List<Integer> coluna : this.getColunas()) {
            for (Integer elemento : coluna) {
                if (!elemento.equals(0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Posicao> getGrupo(Posicao posicaoInicial) {
        return pega(posicaoInicial, null);
    }

    private List<Posicao> pega(Posicao posicaoInicial, List<Posicao> grupo) {
        if (grupo == null) {
            grupo = new ArrayList<Posicao>();
        }
        Integer corInicial = getCor(posicaoInicial);
        if (corInicial.equals(0)) {
            return grupo;
        }
        if (!grupo.contains(posicaoInicial)) {
            grupo.add(posicaoInicial);
        } else {
            return grupo;
        }
        List<Posicao> vizinhos = getVizinhos(posicaoInicial);
        for (Posicao posicao : vizinhos) {
            if (getCor(posicao).equals(corInicial)) {
                grupo.addAll(pega(posicao, grupo));
            }
        }
        //remover repetidos
        HashSet hs = new HashSet();
        hs.addAll(grupo);
        grupo.clear();
        grupo.addAll(hs);
        return grupo;
    }

    public List<List<Posicao>> getTodosGrupos() {
        List<List<Posicao>> todosGrupos = new ArrayList<List<Posicao>>();
        List<Posicao> posicoes = getTodasPosicoes();
        for (Posicao posicao : posicoes) {
            List<Posicao> grupo = getGrupo(posicao);
            if (!todosGrupos.contains(grupo)) {
                todosGrupos.add(grupo);
            }
        }
        return todosGrupos;
    }

    public List<List<Posicao>> getTodosGruposRemoviveis() {
        List<List<Posicao>> grupos = getTodosGrupos();
        Iterator<List<Posicao>> it = grupos.iterator();
        while (it.hasNext()) {
            List<Posicao> grupo = it.next();
            if (grupo.size() <= 1) {
                it.remove();
            }
        }
        return grupos;
    }

    public List<Posicao> getTodasPosicoes() {
        List<Posicao> posicoes = new ArrayList<Posicao>();
        for (int i = 0; i < this.getColunas().size(); i++) {
            List<Integer> coluna = this.getColuna(i);
            for (int j = 0; j < coluna.size(); j++) {
                Posicao posicao = new Posicao(j, i);
                if (!getCor(posicao).equals(0)) {
                    posicoes.add(posicao);
                }
            }
        }
        return posicoes;
    }
}
