package api_servidor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CategoriaController {
	@Autowired
    private CategoriaRepository categoriaRepository;
	
    @RequestMapping(value = "/categoria", method =  RequestMethod.POST)
    public Categoria cadastrarCategoria(@RequestBody Categoria categoria) {
    	return categoriaRepository.save(categoria);
    }
    
    @RequestMapping(value = "/categoria", method = RequestMethod.GET)
    public List<Categoria> recuperarCategorias() {  
    	return categoriaRepository.findAll(Sort.by("id"));
     }

    @RequestMapping(value = "/categoria/{idCategoria}", method = RequestMethod.GET)
    public Categoria recuperarCategoriaPorId(@PathVariable("idCategoria") Long id) {
    	return (categoriaRepository.findById(id))
                .orElseThrow(()-> new EntidadeNaoEncontradaException(
                        "Categoria número " + id + " não encontrada."));
    }
    
	@RequestMapping(value = "/categoria/{idCategoria}", method = RequestMethod.DELETE)
	public void removerCategoria(@PathVariable("idCategoria") Long id) {
		recuperarCategoriaPorId(id);
		categoriaRepository.deleteById(id);
	}
 
}
