package api_servidor;
import java.util.List;


public class AcessoriosPaginados {
 
	private long totalDeProdutos;
    private int totalDePaginas;
    private int paginaCorrente;
    private List<Acessorio> acessorios;
    
	public AcessoriosPaginados(long totalDeProdutos, int totalDePaginas, int paginaCorrente, List<Acessorio> acessorios) {
		this.totalDeProdutos = totalDeProdutos;
		this.totalDePaginas = totalDePaginas;
		this.paginaCorrente = paginaCorrente;
		this.acessorios = acessorios;
		
	}
	
	public long getTotalDeProdutos() {
		return totalDeProdutos;
	}
	public void setTotalDeProdutos(long totalDeProdutos) {
		this.totalDeProdutos = totalDeProdutos;
	}
	public int getTotalDePaginas() {
		return totalDePaginas;
	}
	public void setTotalDePaginas(int totalDePaginas) {
		this.totalDePaginas = totalDePaginas;
	}
	public int getPaginaCorrente() {
		return paginaCorrente;
	}
	public void setPaginaCorrente(int paginaCorrente) {
		this.paginaCorrente = paginaCorrente;
	}
	public List<Acessorio> getAcessorios() {
		return acessorios;
	}
	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}
}
