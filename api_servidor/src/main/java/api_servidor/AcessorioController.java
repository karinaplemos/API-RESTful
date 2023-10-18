package api_servidor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AcessorioController {
	@Autowired
    private AcessorioRepository acessorioRepository;


    @RequestMapping(value = "/acessorio", method = RequestMethod.GET)
    public List<Acessorio> recuperarAcessorios() {
    	List<Acessorio> Lista = acessorioRepository.recuperarAcessorios();
		return Lista;
    }
    
    @RequestMapping(value = "/acessorio", method =  RequestMethod.POST)
    public Acessorio cadastrarAcessorio(@RequestBody Acessorio acessorio) {
    	return acessorioRepository.save(acessorio);
    }
   
    @RequestMapping(value = "/acessorio/{idAcessorio}", method = RequestMethod.GET)
    public Acessorio recuperarAcessorioPorId(@PathVariable("idAcessorio") Long id) {
    	 return acessorioRepository.findById(id)
                 .orElseThrow(() -> new EntidadeNaoEncontradaException(
                         "Acessório número " + id + " não encontrado."));
    }
    
    //OLHAR DEPOIS
    @RequestMapping(value = "/acessorio", method =  RequestMethod.PUT)
    public Acessorio atualizarAcessorio(@RequestBody Acessorio acessorio) {
         return acessorioRepository.save(acessorio);
         
    }

                                  
    @RequestMapping(value = "/acessorio/{idAcessorio}", method = RequestMethod.DELETE)
    public void removerAcessorio(@PathVariable("idAcessorio") Long id) {
    	recuperarAcessorioPorId(id);
        acessorioRepository.deleteById(id);
    }

     @RequestMapping(value = "/acessorio/categoria/{idCategoria}", method = RequestMethod.GET)
    public List<Acessorio> recuperarAcessoriosPorCategoria(@PathVariable("idCategoria") Long id) {
    	 return acessorioRepository.findByCategoriaId(id);
    }
     
    @RequestMapping(value = "/paginacao", method = RequestMethod.GET)
    public AcessoriosPaginados recuperarAcessoriosPaginados(
            @RequestParam(name="pagina", defaultValue = "0") int pagina,
            @RequestParam(name="tamanho", defaultValue = "3") int tamanho
    ) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Acessorio> paginaDeAcessorio =  acessorioRepository.recuperarAcessoriosPaginados(pageable);
        AcessoriosPaginados acessoriosPaginados = new AcessoriosPaginados(
                paginaDeAcessorio.getTotalElements(),
                paginaDeAcessorio.getTotalPages(),
                paginaDeAcessorio.getNumber(),
                paginaDeAcessorio.getContent());
        return acessoriosPaginados;
    }
	
	

}
