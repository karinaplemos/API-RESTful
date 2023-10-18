package api_servidor;


import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;

public interface AcessorioRepository extends JpaRepository<Acessorio, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Acessorio a where a.id = :id")
    Optional<Acessorio> recuperarAcessorioPorIdComLock(@Param("id") Long id);

    @Query("select a from Acessorio a left join fetch a.categoria c where c.id = :id")
    List<Acessorio> findByCategoriaId(Long id);
    
    @Query("select a from Acessorio a order by a.id")
    List<Acessorio> recuperarAcessorios();

    @Query("select a from Acessorio a left join fetch a.categoria")
    List<Acessorio> recuperarAcessoriosComCategoria();

    @Query(
            value = "select a from Acessorio a left join fetch a.categoria order by a.id",
            countQuery = "select count(a) from Acessorio a"
    )
    Page<Acessorio> recuperarAcessoriosPaginados(Pageable pageable);
}
