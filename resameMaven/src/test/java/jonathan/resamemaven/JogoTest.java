package jonathan.resamemaven;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

/**
 * 
 * @author jonathan
 */
public class JogoTest extends TestCase {

	public JogoTest(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		System.setOut(null);
		System.setErr(null);
	}

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Test
	public void testEquals() {
		Jogo jogo1 = Jogo.novoJogo("3-3-3-sem-solucao");
		Jogo jogo2 = Jogo.novoJogo("3-3-3-com-solucao");
		Jogo jogo3 = Jogo.novoJogo("3-3-3-sem-solucao");
		boolean expResult = false;
		boolean result = jogo1.equals(jogo2);
		assertEquals(expResult, result);
		expResult = true;
		result = jogo1.equals(jogo3);
		assertEquals(expResult, result);
	}

	@Test
	public void testGetColunas() {
		Jogo jogo = Jogo.novoJogo("3-3-3-sem-solucao");
		List<List<Integer>> expResult = new ArrayList<List<Integer>>();
		List<Integer> coluna0 = new ArrayList<Integer>();
		coluna0.add(2);
		coluna0.add(2);
		coluna0.add(1);
		List<Integer> coluna1 = new ArrayList<Integer>();
		coluna1.add(3);
		coluna1.add(1);
		coluna1.add(3);
		List<Integer> coluna2 = new ArrayList<Integer>();
		coluna2.add(1);
		coluna2.add(1);
		coluna2.add(2);
		expResult.add(coluna0);
		expResult.add(coluna1);
		expResult.add(coluna2);
		List<List<Integer>> result = jogo.getColunas();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetColuna() {
		Jogo jogo = Jogo.novoJogo("3-3-3-sem-solucao");
		List<Integer> expResult = new ArrayList<Integer>();
		expResult.add(1);
		expResult.add(1);
		expResult.add(2);
		List<Integer> result = jogo.getColuna(2);
		assertEquals(expResult, result);
	}

	@Test
	public void testImprimir() {
		Jogo jogo = Jogo.novoJogo("3-3-3-sem-solucao");
		jogo.imprimir(false);
		assertEquals("1 3 2 \n2 1 1 \n2 3 1", outContent.toString().trim());

		outContent.reset();
		jogo = Jogo.novoJogo("3-3-3-com-solucao");
		jogo.imprimir(false);
		assertEquals("2 1 2 \n3 3 2 \n1 1 1", outContent.toString().trim());

		outContent.reset();
		jogo = Jogo.novoJogo("3-3-3-resolvido");
		jogo.imprimir(false);
		assertEquals("0 0 0 \n0 0 0 \n0 0 0", outContent.toString().trim());
	}

	@Test
	public void testRemoverGrupo() {
		Jogo jogo = Jogo.novoJogo("3-3-3-com-solucao");
		Posicao posicao0 = new Posicao(0, 0);
		Posicao posicao1 = new Posicao(1, 0);
		jogo.removerGrupo(posicao1);
		jogo.removerGrupo(posicao0);
		jogo.removerGrupo(posicao0);
		assertTrue(jogo.isResolvido());
	}

	@Test
	public void testGetVizinhos() {
		Posicao posicao = new Posicao(1, 1);
		Jogo instance = Jogo.novoJogo("3-3-3-com-solucao");
		List<Posicao> vizinhos = new ArrayList<Posicao>();
		vizinhos.add(new Posicao(1, 0));
		vizinhos.add(new Posicao(0, 1));
		vizinhos.add(new Posicao(1, 2));
		vizinhos.add(new Posicao(2, 1));
		List<Posicao> expResult = vizinhos;
		List<Posicao> result = instance.getVizinhos(posicao);
		assertEquals(expResult, result);

		vizinhos.clear();
		Posicao posicao2 = new Posicao(2, 2);
		vizinhos.add(new Posicao(2, 1));
		vizinhos.add(new Posicao(1, 2));
		expResult = vizinhos;
		result = instance.getVizinhos(posicao2);
		assertEquals(expResult, result);
	}

	@Test
	public void testIsPosicaoValida() {
		Posicao posicao = new Posicao(0, 0);
		Jogo instance = Jogo.novoJogo("3-3-3-com-solucao");
		Boolean expResult = true;
		Boolean result = instance.isPosicaoValida(posicao);
		assertEquals(expResult, result);

		posicao = new Posicao(-1, 0);
		result = instance.isPosicaoValida(posicao);
		assertNotSame(expResult, result);

		posicao = new Posicao(2, 5);
		result = instance.isPosicaoValida(posicao);
		assertNotSame(expResult, result);

		posicao = new Posicao(2, 2);
		result = instance.isPosicaoValida(posicao);
		assertEquals(expResult, result);
	}

	@Test
	public void testGetCor() {
		Jogo instance = Jogo.novoJogo("3-3-3-com-solucao");
		Posicao posicao11 = new Posicao(1, 1);
		Integer result = instance.getCor(posicao11);
		Integer expResult = 3;
		assertEquals(expResult, result);
		Posicao posicao02 = new Posicao(0, 2);
		result = instance.getCor(posicao02);
		expResult = 1;
		assertEquals(expResult, result);
		Posicao posicao22 = new Posicao(2, 2);
		result = instance.getCor(posicao22);
		expResult = 2;
		assertEquals(expResult, result);
	}

	@Test
	public void testIsResolvido() {
		Jogo instance = Jogo.novoJogo("3-3-3-com-solucao");
		Boolean expResult = false;
		Boolean result = instance.isResolvido();
		assertEquals(expResult, result);
		Jogo jogoResolvido = Jogo.novoJogo("3-3-3-resolvido");
		expResult = true;
		result = jogoResolvido.isResolvido();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetGrupo() {
		Jogo jogo = Jogo.novoJogo("3-3-3-com-solucao");
		Posicao posicaoInicial = new Posicao(1, 1);
		Grupo grupoEsperado = Grupo.novoGrupoVazio();
		grupoEsperado.addPosicao(new Posicao(1, 1));
		grupoEsperado.addPosicao(new Posicao(1, 0));
		Grupo grupoResultado = jogo.getGrupo(posicaoInicial);
		assertEquals(grupoEsperado, grupoResultado);

		posicaoInicial = new Posicao(0, 0);
		grupoEsperado = Grupo.novoGrupoVazio();
		grupoEsperado.addPosicao(new Posicao(0, 0));
		grupoEsperado.addPosicao(new Posicao(0, 1));
		grupoEsperado.addPosicao(new Posicao(0, 2));
		grupoResultado = jogo.getGrupo(posicaoInicial);
		assertEquals(grupoEsperado, grupoResultado);
	}

	@Test
	public void testGetTodosGrupos() {
		Jogo jogo = Jogo.novoJogo("3-3-3-com-solucao");
		List<Grupo> gruposEsperados = Lists.newArrayList();
		Grupo grupo1 = Grupo.novoGrupoVazio();
		grupo1.addPosicao(new Posicao(0, 0));
		grupo1.addPosicao(new Posicao(0, 1));
		grupo1.addPosicao(new Posicao(0, 2));
		Grupo grupo2 = Grupo.novoGrupoVazio();
		grupo2.addPosicao(new Posicao(1, 1));
		grupo2.addPosicao(new Posicao(1, 0));
		Grupo grupo3 = Grupo.novoGrupoVazio();
		grupo3.addPosicao(new Posicao(2, 0));
		Grupo grupo4 = Grupo.novoGrupoVazio();
		grupo4.addPosicao(new Posicao(2, 1));
		Grupo grupo5 = Grupo.novoGrupoVazio();
		grupo5.addPosicao(new Posicao(1, 2));
		grupo5.addPosicao(new Posicao(2, 2));
		gruposEsperados.add(grupo1);
		gruposEsperados.add(grupo2);
		gruposEsperados.add(grupo3);
		gruposEsperados.add(grupo4);
		gruposEsperados.add(grupo5);

		List<Grupo> gruposResultado = jogo.getTodosGrupos();
		assertEquals(gruposEsperados, gruposResultado);
	}

	@Test
	public void testGetTodasPosicoes() {
		Jogo instance = Jogo.novoJogo("3-3-3-sem-solucao");
		ArrayDeque<Posicao> expResult = new ArrayDeque<Posicao>();
		expResult.add(new Posicao(0, 0));
		expResult.add(new Posicao(1, 0));
		expResult.add(new Posicao(2, 0));
		expResult.add(new Posicao(0, 1));
		expResult.add(new Posicao(1, 1));
		expResult.add(new Posicao(2, 1));
		expResult.add(new Posicao(0, 2));
		expResult.add(new Posicao(1, 2));
		expResult.add(new Posicao(2, 2));
		List<Posicao> result = instance.getTodasPosicoes();
		for (Posicao posicao : result) {
			if (!expResult.contains(posicao))
				fail("Posicao nao encontrada: " + posicao.toString());
		}
		assertFalse(result.contains(new Posicao(5, 7)));
	}

}
