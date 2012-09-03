package jonathan.resamemaven;

/**
 *
 * @author jonathan
 */
public class Posicao {
    
    private Integer linha;
    private Integer coluna;

    public void imprimir(Boolean quebrarLinha) {
        System.out.println(linha+" "+coluna);
        if (quebrarLinha) System.out.print("\n");
    }
    
    @Override
    public String toString() {
        return "("+ linha + "," + coluna + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Posicao other = (Posicao) obj;
        if (this.linha != other.linha && (this.linha == null || !this.linha.equals(other.linha))) {
            return false;
        }
        if (this.coluna != other.coluna && (this.coluna == null || !this.coluna.equals(other.coluna))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.linha != null ? this.linha.hashCode() : 0);
        hash = 59 * hash + (this.coluna != null ? this.coluna.hashCode() : 0);
        return hash;
    }

    Posicao(Integer linha, Integer coluna) {
        this.coluna = coluna;
        this.linha = linha;
    }

    public Integer getColuna() {
        return coluna;
    }

    public void setColuna(Integer coluna) {
        this.coluna = coluna;
    }

    public Integer getLinha() {
        return linha;
    }

    public void setLinha(Integer linha) {
        this.linha = linha;
    }
    
}
