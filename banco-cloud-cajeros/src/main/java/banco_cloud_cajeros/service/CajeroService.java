package banco_cloud_cajeros.service;

import banco_cloud_cajeros.entity.Cajero;
import banco_cloud_cajeros.repository.CajeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CajeroService {

    private final CajeroRepository repository;

    public CajeroService(CajeroRepository repository) {
        this.repository = repository;
    }

    public List<Cajero> obtenerTodos() {
        return repository.findByActivoTrue();
    }

    public List<Cajero> buscarPorComuna(String comuna) {
        return repository.findByComunaIgnoreCaseAndActivoTrue(comuna);
    }

    public List<Cajero> buscarPorRegion(String region) {
        return repository.findByRegionIgnoreCaseAndActivoTrue(region);
    }

    public List<Cajero> buscarPorSucursal(Long sucursalId) {
        return repository.findBySucursalIdAndActivoTrue(sucursalId);
    }

    public Cajero obtenerPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cajero no encontrado"));
    }

    public Cajero crear(Cajero cajero) {

        cajero.setActivo(true);

        return repository.save(cajero);
    }

    public Cajero actualizar(Long id, Cajero datos) {

        Cajero cajero = obtenerPorId(id);

        cajero.setNombre(datos.getNombre());
        cajero.setDireccion(datos.getDireccion());
        cajero.setComuna(datos.getComuna());
        cajero.setRegion(datos.getRegion());
        cajero.setHorarioApertura(datos.getHorarioApertura());
        cajero.setHorarioCierre(datos.getHorarioCierre());
        cajero.setSucursalId(datos.getSucursalId());

        return repository.save(cajero);
    }

    public void desactivar(Long id) {

        Cajero cajero = obtenerPorId(id);

        cajero.setActivo(false);

        repository.save(cajero);
    }
}