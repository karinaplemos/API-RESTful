package api_cliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import corejava.Console;

@SpringBootApplication()
public class Main {
	
	 private static Logger logger = LoggerFactory.getLogger(Main.class);
	 private static RestTemplate restTemplate = new RestTemplate();
	
	 public static void main(String[] args) {
		 
		 logger.info("Iniciando a execução da aplicação cliente.");
		 String nome;
	     String descricao;
		 Double preco;
		 Acessorio acessorio = null;
	     Categoria categoria = null;

		boolean continua = true;
		while (continua)
		{	System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um acessório");
			System.out.println('\n' + "2. Alterar um acessório");
			System.out.println('\n' + "3. Remover um acessório");
			System.out.println('\n' + "4. Recuperar um acessório");
	        System.out.println('\n' + "5. Listar todos os acessórios");
	        System.out.println('\n' + "6. Listar todos os acessórios por categoria");
			System.out.println('\n' + "7. Cadastrar uma categoria");
	        System.out.println('\n' + "8. Listar todas as categorias");
			System.out.println('\n' + "9. Remover uma categoria");
			System.out.println('\n' + "10. Sair");
					
		int opcao =  Integer.parseInt(Console.readLine('\n' + 
						"Digite um número entre 1 e 5:"));
				
		switch (opcao)
		{	case 1:
			{

				nome = Console.readLine('\n' + 
					"Informe o nome do acessório: ");
				descricao = Console.readLine(
					"Informe a descrição do acessório: ");
				preco = Double.parseDouble(Console.readLine(
					"Informe o preco do acessório: "));
				long id = Console.readInt('\n' + "Informe o número da categoria: ");
                try {
                    categoria = restTemplate.getForObject(
                            "http://localhost:8080/categoria/{id}",
                            Categoria.class, id);
                }
                catch (EntidadeNaoEncontradaException e) {
                    System.out.println('\n' + e.getMessage());
                    break;
                }
                
                acessorio = new Acessorio(id, nome, descricao, preco, categoria);
				
                try {
                    
                    ResponseEntity<Acessorio> res = restTemplate.postForEntity(
                            "http://localhost:8080/acessorio", acessorio, Acessorio.class);
                    acessorio = res.getBody();

                    System.out.println('\n' + "Acessório número " + acessorio.getId() + " cadastrado com sucesso!");

                    System.out.println('\n' +
                            "Id = " + acessorio.getId() +
                            "  Nome = " + acessorio.getNome() +
                            "  Descrição = " + acessorio.getDescricao() +
                            "  Preço = " + acessorio.getPreco() +
                            "  Categoria = " + acessorio.getCategoria().getNome());
                } catch (ViolacaoDeConstraintException e) {
                    System.out.println('\n' + e.getMessage());
                }
                break;
			}

			case 2:
			{	
				
				try {
                    acessorio = recuperarObjeto(
                            "Informe o número do acessório que você deseja alterar: ",
                            "http://localhost:8080/acessorio/{id}", Acessorio.class);
                }
                catch (EntidadeNaoEncontradaException e) {
                    System.out.println('\n' + e.getMessage());
                    break;
                }

                System.out.println('\n' +
 	                    "Id = " + acessorio.getId() +
 	                    "  Nome = " + acessorio.getNome() +
 	                    "  Descrição = " + acessorio.getDescricao() +
 	                    "  Preço = " + acessorio.getPreco() +
 	                    "  Categoria = " + acessorio.getCategoria().getNome());
                
	            
	            System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Nome");
				System.out.println('\n' + "2. Descrição");
				System.out.println('\n' + "3. Preço");
				System.out.println('\n' + "4. Categoria");
				
				int opcaoAlteracao =  Integer.parseInt(Console.readLine('\n' + 
						"Digite um número de 1 a 4:"));

				switch (opcaoAlteracao)
				{	
					case 1:
					{
						String novoNome = Console.readLine("Digite o novo nome: ");
						acessorio.setNome(novoNome);
		
						break;
					}
				
					case 2:
					{
						String novaDescricao = Console.readLine("Digite a nova descrição: ");
						acessorio.setDescricao(novaDescricao);
												
						break;
					}
					
					case 3:
					{
						Double novoPreco = Double.parseDouble(Console.readLine("Digite o novo preço: "));
						acessorio.setPreco(novoPreco);
												
						break;
					}
					
					case 4:
					{
						try {
						 categoria = recuperarObjeto(
		                            "Informe o número da nova categoria: ",
		                            "http://localhost:8080/categoria/{id}", Categoria.class);
		                   acessorio.setCategoria(categoria);
		                   
						} catch (EntidadeNaoEncontradaException e) {
                            System.out.println('\n' + e.getMessage());
                            break;
                        }
						break;
					}
											
						
					default:
						System.out.println('\n' + "Opção inválida!");
						
				}
				try {
					 restTemplate.put("http://localhost:8080/acessorio", acessorio);

                     System.out.println('\n' + "Acessório número " + acessorio.getId() + " alterado com sucesso!");
                     
                     System.out.println('\n' +
 	 	                    "Id = " + acessorio.getId() +
 	 	                    "  Nome = " + acessorio.getNome() +
 	 	                    "  Descrição = " + acessorio.getDescricao() +
 	 	                    "  Preço = " + acessorio.getPreco() +
 	 	                    "  Categoria = " + acessorio.getCategoria().getNome());
 				
				}
				catch(ViolacaoDeConstraintException | EntidadeNaoEncontradaException e) {
                    System.out.println('\n' + e.getMessage());
				}
				break;
				
				 
			}
			case 3 : {
                 try {
                     acessorio = recuperarObjeto(
                             "Informe o número do acessório que você deseja remover: ",
                             "http://localhost:8080/acessorio/{id}", Acessorio.class);
                 }
                 catch (EntidadeNaoEncontradaException e) {
                     System.out.println('\n' + e.getMessage());
                     break;
                 }

                 System.out.println('\n' +
 	                    "Id = " + acessorio.getId() +
 	                    "  Nome = " + acessorio.getNome() +
 	                    "  Descrição = " + acessorio.getDescricao() +
 	                    "  Preço = " + acessorio.getPreco() +
 	                    "  Categoria = " + acessorio.getCategoria().getNome());
 	            

                 String resp = Console.readLine('\n' +
                         "Confirma a remoção do acessório?");

                 if (resp.equals("s")) {
                     try {
                       
                         restTemplate.delete("http://localhost:8080/acessorio/{id}", acessorio.getId());

                         System.out.println('\n' + "Acessório número " + acessorio.getId() + " removido com sucesso!");
                     } catch (EntidadeNaoEncontradaException e) {
                         System.out.println('\n' + e.getMessage());
                         break;
                     }
                   
                 } else {
                     System.out.println('\n' + "Acessório não removido.");
                 }
                 break;
             }
			
			case 4 : {
                try {
                    acessorio = recuperarObjeto(
                            "Informe o número do acessório que você deseja recuperar: ",
                            "http://localhost:8080/acessorio/{id}", Acessorio.class);
                }
                catch (EntidadeNaoEncontradaException e) {
                    System.out.println('\n' + e.getMessage());
                    break;
                }

                System.out.println('\n' +
 	                    "Id = " + acessorio.getId() +
 	                    "  Nome = " + acessorio.getNome() +
 	                    "  Descrição = " + acessorio.getDescricao() +
 	                    "  Preço = " + acessorio.getPreco() +
 	                    "  Categoria = " + acessorio.getCategoria().getNome());
                
                break;
            }
            case 5 : {
                ResponseEntity<Acessorio[]> res = restTemplate.exchange(
                        "http://localhost:8080/acessorio",
                        HttpMethod.GET, null, Acessorio[].class);
                
                Acessorio[] acessorios = res.getBody();

                for (Acessorio umAcessorio : acessorios) {
                    System.out.println('\n' +
                            "Id = " + umAcessorio.getId() +
                            "  Nome = " + umAcessorio.getNome() +
     	                    "  Descrição = " + umAcessorio.getDescricao() +
     	                    "  Preço = " + umAcessorio.getPreco() +
     	                    "  Categoria = " + umAcessorio.getCategoria().getNome());
                }
                break;

            }
            case 6 : {                
                Acessorio[] acessorios = recuperarObjeto(
                        "Informe o número da categoria: ",
                        "http://localhost:8080/acessorio/categoria/{id}", Acessorio[].class);

                if (acessorios.length  ==  0) {
                    System.out.println("\nNenhum acessório foi encontrado para esta categoria.");
                    break;
                }

                for (Acessorio umAcessorio : acessorios) {
                	System.out.println('\n' +
                            "Id = " + umAcessorio.getId() +
                            "  Nome = " + umAcessorio.getNome() +
     	                    "  Descrição = " + umAcessorio.getDescricao() +
     	                    "  Preço = " + umAcessorio.getPreco() +
     	                    "  Categoria = " + umAcessorio.getCategoria().getNome());
                }
                break;
            }
            case 7:
			{

				nome = Console.readLine('\n' + 
					"Informe o nome da categoria: ");
                
                categoria = new Categoria(nome);
				
                try {
                    
                    ResponseEntity<Categoria> res = restTemplate.postForEntity(
                            "http://localhost:8080/categoria", categoria, Categoria.class);
                    categoria = res.getBody();

                    System.out.println('\n' + "Categoria número " + categoria.getId() + " cadastrada com sucesso!");

                    System.out.println('\n' +
                            "Id = " + categoria.getId() +
                            "  Nome = " + categoria.getNome());
                } catch (ViolacaoDeConstraintException e) {
                    System.out.println('\n' + e.getMessage());
                }
                break;
			}
            case 8 : {
                ResponseEntity<Categoria[]> res = restTemplate.exchange(
                        "http://localhost:8080/categoria",
                        HttpMethod.GET, null, Categoria[].class);
                
                Categoria[] categorias = res.getBody();

                for (Categoria umaCategoria : categorias) {
                    System.out.println('\n' +
                            "Id = " + umaCategoria.getId() +
                            "  Nome = " + umaCategoria.getNome());
                }
                break;

            }
            case 9 : {
                try {
                    acessorio = recuperarObjeto(
                            "Informe o número da categoria que você deseja remover: ",
                            "http://localhost:8080/categoria/{id}", Acessorio.class);
                }
                catch (EntidadeNaoEncontradaException e) {
                    System.out.println('\n' + e.getMessage());
                    break;
                }

                System.out.println('\n' +
	                    "Id = " + categoria.getId() +
	                    "  Nome = " + categoria.getNome());

                String resp = Console.readLine('\n' +
                        "Confirma a remoção da categoria?");

                if (resp.equals("s")) {
                    try {
                      
                        restTemplate.delete("http://localhost:8080/categoria/{id}", categoria.getId());

                        System.out.println('\n' + "Categoria número " + categoria.getId() + " removida com sucesso!");
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                  
                } else {
                    System.out.println('\n' + "Categoria não removida.");
                }
                break;
            }
            case 10:
			{	continua = false;
				break;
			}

			default:
				System.out.println('\n' + "Opção inválida!");	
			
		}}
	
			
	}
	 
	 private static <T> T recuperarObjeto(String msg, String url, Class<T> classe) {
	        int id = Console.readInt('\n' + msg);
	        return restTemplate.getForObject(url, classe, id);
	 }
    
}
