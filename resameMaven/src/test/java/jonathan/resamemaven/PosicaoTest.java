package jonathan.resamemaven;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

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
    
    @Test
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

    @Test
    public void testToString() {
        Posicao instance = new Posicao(1, 2);
        String expResult = "1 2";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
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

    @Test
    public void testHashCode() {
        Posicao instance = new Posicao(5, 2);
        int expResult = 24664;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
        instance = new Posicao(1, 0);
        expResult = 24426;
        result = instance.hashCode();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColuna() {
        Posicao instance = new Posicao(0, 5);
        Integer expResult = 5;
        Integer result = instance.getColuna();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetColuna() {
        Integer coluna = 2;
        Posicao instance = new Posicao(0, 0);
        instance.setColuna(coluna);
        assertEquals(instance.getColuna(), coluna);
    }

    @Test
    public void testGetLinha() {
        Posicao instance = new Posicao(0, 0);
        Integer expResult = 0;
        Integer result = instance.getLinha();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetLinha() {
        Integer linha = 2;
        Posicao instance = new Posicao(5, 4);
        instance.setLinha(linha);
        assertEquals(instance.getLinha(), linha);
    }
}
