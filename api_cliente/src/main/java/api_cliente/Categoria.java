package api_cliente;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Table(name="Categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nome;
	
	public Categoria(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
    
	public Categoria() {
		
	}
    public Categoria(String nome) {
        this.setNome(nome);
    }
    
	public Long getId()
	{	return id;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	@SuppressWarnings("unused")
	private void setId(Long id)
	{	this.id = id;
	}
	

	public void setNome(String nome) {
		this.nome = nome;
	}

}
