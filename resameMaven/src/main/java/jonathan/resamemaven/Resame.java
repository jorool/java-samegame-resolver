package jonathan.resamemaven;

import java.util.*;

public class Resame {

    private static ArrayDeque<Jogo> pilhaJogos = new ArrayDeque<Jogo>();
    private static List<List<Posicao>> gruposJogados = new ArrayList<List<Posicao>>();
    private static List<List<Posicao>> gruposDisponiveis;
    private static Map<Posicao, Jogo> mapJogadas = new LinkedHashMap<Posicao, Jogo>();
    //condicao de parada para os sem solucao
    private static Integer maximoTentativas;
    private static Integer numeroTentativas = 0;

    public static void main(String[] args) {
        if (args[0] != null) {
            Jogo jogo = new Jogo(args[0]);
            maximoTentativas = jogo.getTodosGruposRemoviveis().size();
            numeroTentativas++;
            resolver(jogo, true);
            imprimirResultado();
        } else {
            System.out.println("nenhum argumento");
        }
    }

    private static void resolver(Jogo jogo, Boolean primeiraVez) {
        if (numeroTentativas >= maximoTentativas) {
            System.out.println("sem-solucao");
            return;
        }
        //jogo.imprimir(Boolean.TRUE);
        if (jogo.isResolvido()) {
            return;
        }
        if (!primeiraVez && pilhaJogos.isEmpty()) {
            gruposJogados.retainAll(gruposJogados.get(0));
            numeroTentativas++;
        }
        gruposDisponiveis = jogo.getTodosGruposRemoviveis();
        gruposDisponiveis.removeAll(gruposJogados);
        if (!gruposDisponiveis.isEmpty()) {
            List<Posicao> grupo = selecionaGrupo();
            if (grupo == null) {
                System.out.println("sem-solucao");
                return;
            }
            pilhaJogos.addLast(jogo.clone());
            gruposJogados.add(grupo);
            //grupo.get(0).imprimir(Boolean.TRUE);
            jogo.removerGrupo(grupo);
            mapJogadas.put(grupo.get(0), jogo.clone());
            resolver(jogo, false);
        } else {
            //volta jogo
            if (pilhaJogos.isEmpty()) {
                System.out.println("sem-solucao");
                return;
            }
            jogo = pilhaJogos.getLast();
            pilhaJogos.removeLast();
//            List<Posicao> list = new ArrayList<Posicao>(mapJogadas.keySet());
//            if (!list.isEmpty()) mapJogadas.remove(list.get(list.size()-1));
            resolver(jogo, false);
        }
    }

    private static List<Posicao> selecionaGrupo() {
        if (!pilhaJogos.isEmpty()) {
            for (List<Posicao> grupo : gruposDisponiveis) {
                if (!gruposJogados.contains(grupo)) {
                    return grupo;
                }
            }
        } else {
            Collections.shuffle(gruposDisponiveis);
            return gruposDisponiveis.get(0);
        }
        return null;
    }

    private static void imprimirResultado() {
        for (Map.Entry<Posicao, Jogo> entry : mapJogadas.entrySet()) {
            entry.getKey().imprimir(Boolean.TRUE);
            entry.getValue().imprimir(Boolean.TRUE);
        }
    }
}
