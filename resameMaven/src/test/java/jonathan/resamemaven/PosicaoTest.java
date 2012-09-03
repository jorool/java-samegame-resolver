package jonathan.resamemaven;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import junit.framework.TestCase;

/**
 *
 * @author jonathan
 */
public class PosicaoTest extends TestCase {
    
    public PosicaoTest(String testName) {
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
    
    /**
     * Test of imprimir method, of class Posicao.
     */
    public void testImprimir() {
        Boolean quebrarLinha = false;
        Posicao instance = new Posicao(0, 0);
        instance.imprimir(quebrarLinha);
        assertEquals("0 0", outContent.toString().trim());
        
        outContent.reset();
        instance = new Posicao(2, 1);
        instance.imprimir(quebrarLinha);
        assertEquals("2 1", outContent.toString().trim());
    }

    /**
     * Test of toString method, of class Posicao.
     */
    public void testToString() {
        System.out.println("toString");
        Posicao instance = new Posicao(1, 2);
        String expResult = "(1,2)";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Posicao.
     */
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Posicao(1, 1);
        Posicao instance = new Posicao(1, 1);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new Posicao(1, 2);
        instance = new Posicao(1, 1);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Posicao.
     */
    public void testHashCode() {
        System.out.println("hashCode");
        Posicao instance = new Posicao(5, 2);
        int expResult = 24664;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
        instance = new Posicao(1, 0);
        expResult = 24426;
        result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColuna method, of class Posicao.
     */
    public void testGetColuna() {
        System.out.println("getColuna");
        Posicao instance = new Posicao(0, 5);
        Integer expResult = 5;
        Integer result = instance.getColuna();
        assertEquals(expResult, result);
    }

    /**
     * Test of setColuna method, of class Posicao.
     */
    public void testSetColuna() {
        System.out.println("setColuna");
        Integer coluna = 2;
        Posicao instance = new Posicao(0, 0);
        instance.setColuna(coluna);
        assertEquals(instance.getColuna(), coluna);
    }

    /**
     * Test of getLinha method, of class Posicao.
     */
    public void testGetLinha() {
        System.out.println("getLinha");
        Posicao instance = new Posicao(0, 0);
        Integer expResult = 0;
        Integer result = instance.getLinha();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLinha method, of class Posicao.
     */
    public void testSetLinha() {
        System.out.println("setLinha");
        Integer linha = 2;
        Posicao instance = new Posicao(5, 4);
        instance.setLinha(linha);
        assertEquals(instance.getLinha(), linha);
    }
}
