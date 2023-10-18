package api_cliente;

import jakarta.persistence.*;

@Entity
@Table(name="Acessorio")
public class Acessorio
{	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
    @ManyToOne
	private Categoria categoria;
    
    public Acessorio(Long id, String nome, String descricao, Double preco, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}
    
    public Acessorio() {
		
	}
	
	public Long getId()
	{	return id;
	}
	

	public String getNome()
	{	return nome;
	}
	
	public String getDescricao()
	{	return descricao;
	}

	public Double getPreco()
	{	return preco;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}


	@SuppressWarnings("unused")
	private void setId(Long id)
	{	this.id = id;
	}
	

	public void setNome(String nome)
	{	this.nome = nome;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}



