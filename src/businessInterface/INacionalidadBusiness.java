package businessInterface;

import java.util.List;

import entity.Nacionalidad;

public interface INacionalidadBusiness {

    List<Nacionalidad> selectAll();

    Nacionalidad selectOne(int id);
}
