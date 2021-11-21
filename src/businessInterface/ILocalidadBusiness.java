package businessInterface;

import java.util.List;

import entity.Localidad;

public interface ILocalidadBusiness {

    List<Localidad> selectAll();

    Localidad selectOne(int id);
}
