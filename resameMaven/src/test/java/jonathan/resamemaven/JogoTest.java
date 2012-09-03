package jonathan.resamemaven;

import java.util.ArrayList;
import java.util.List;
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
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of equals method, of class Jogo.
     */
//    public void testEquals() {
//        System.out.println("equals");
//        Object obj = null;
//        Jogo instance = null;
//        boolean expResult = false;
//        boolean result = instance.equals(obj);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of hashCode method, of class Jogo.
     */
//    public void testHashCode() {
//        System.out.println("hashCode");
//        Jogo instance = null;
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getColunas method, of class Jogo.
     */
//    public void testGetColunas() {
//        System.out.println("getColunas");
//        Jogo instance = null;
//        List expResult = null;
//        List result = instance.getColunas();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getColuna method, of class Jogo.
     */
//    public void testGetColuna() {
//        System.out.println("getColuna");
//        Integer index = null;
//        Jogo instance = null;
//        List expResult = null;
//        List result = instance.getColuna(index);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of imprimir method, of class Jogo.
     */
//    public void testImprimir() {
//        System.out.println("imprimir");
//        Boolean quebrarLinha = null;
//        Jogo instance = null;
//        instance.imprimir(quebrarLinha);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of removerGrupo method, of class Jogo.
     */
//    public void testRemoverGrupo() {
//        System.out.println("removerGrupo");
//        Posicao posicaoInicial = null;
//        Jogo instance = null;
//        instance.removerGrupo(posicaoInicial);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getVizinhos method, of class Jogo.
     */
    public void testGetVizinhos() {
        System.out.println("getVizinhos");
        Posicao posicao = new Posicao(1, 1);
        Jogo instance = new Jogo("3-3-3");
        List<Posicao> vizinhos = new ArrayList<Posicao>();
        vizinhos.add(new Posicao(1, 0));
        vizinhos.add(new Posicao(0, 1));
        vizinhos.add(new Posicao(1, 2));
        vizinhos.add(new Posicao(2, 1));
        List expResult = vizinhos;
        List result = instance.getVizinhos(posicao);
        assertEquals(expResult, result);
    }

    /**
     * Test of isPosicaoValida method, of class Jogo.
     */
    public void testIsPosicaoValida() {
        System.out.println("isPosicaoValida");
        Posicao posicao = new Posicao(0, 0);
        Jogo instance = new Jogo("3-3-3");
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

    /**
     * Test of getCor method, of class Jogo.
     */
    public void testGetCor() {
        System.out.println("getCor");
        Posicao posicao = new Posicao(1, 1);
        Jogo instance = new Jogo("3-3-3");
        Integer expResult = 3;
        Integer result = instance.getCor(posicao);
        assertEquals(expResult, result);
    }

    /**
     * Test of isResolvido method, of class Jogo.
     */
    public void testIsResolvido() {
        System.out.println("isResolvido");
        Jogo instance = new Jogo("3-3-3");
        Boolean expResult = false;
        Boolean result = instance.isResolvido();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGrupo method, of class Jogo.
     */
//    public void testGetGrupo() {
//        System.out.println("getGrupo");
//        Posicao posicaoInicial = new Posicao(1, 1);
//        Jogo instance = new Jogo("3-3-3");
//        List expResult = null;
//        List result = instance.getGrupo(posicaoInicial);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getTodosGrupos method, of class Jogo.
     */
//    public void testGetTodosGrupos() {
//        System.out.println("getTodosGrupos");
//        Jogo instance = null;
//        List expResult = null;
//        List result = instance.getTodosGrupos();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getTodasPosicoes method, of class Jogo.
     */
//    public void testGetTodasPosicoes() {
//        System.out.println("getTodasPosicoes");
//        Jogo instance = null;
//        List expResult = null;
//        List result = instance.getTodasPosicoes();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
